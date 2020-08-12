package following_server;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static following_server.jdbc.JdbcUtil.close;

import following_server.jdbc.connection.ConnectionProvider;

/**
 * 유사한 태그 삭제
 */
public class TagDel implements Runnable {
    private Socket socket;
    private BufferedWriter input;

    public TagDel() {
        socket = new Socket();
        InetSocketAddress address = new InetSocketAddress("localhost", 7576);
        // 소켓 연결 수행
        try {
            socket.connect(address);// 주소로 연결
            socket.setKeepAlive(true);
        } catch (IOException e) {

            e.printStackTrace();
        }
        try {
            input = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    private void sendLength(int len) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(4);
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        byteBuffer.putInt(len);
        try {
            socket.getOutputStream().write(byteBuffer.array(), 0, 4);
            socket.getOutputStream().flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendString(String str) {
        try {
            sendLength(str.getBytes().length);
            input.write(str);
            input.flush();// 보내기
            System.out.println("send String: " + str);
        } catch (IOException e) {
            e.printStackTrace();
        } // buffer에 string 정보 입력

    }

    private int getLength() {
        byte[] length = new byte[4];
        try {
            socket.getInputStream().read(length, 0, 4);
            ByteBuffer byteBuffer = ByteBuffer.wrap(length);
            byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
            return byteBuffer.getInt();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public String recvString() {
        int length = getLength();
        byte[] recv = new byte[length];
        try {
            socket.getInputStream().read(recv, 0, length);
            System.out.println("recv String: " + new String(recv, StandardCharsets.UTF_8));
            return new String(recv, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void run() {
        delSimTags();
    }

    /**
     * 비슷한 태그들 삭제
     */
    private void delSimTags() {

        try (Connection conn = ConnectionProvider.getConnection();
                PreparedStatement placePstmt = conn.prepareStatement(
                        "SELECT place, tagcontent FROM place_tag " + "INNER JOIN place ON place.id=place_tag.place_id"
                                + " INNER JOIN tag ON tag.id=place_tag.tag_id");
                ResultSet place_tagRS = placePstmt.executeQuery();) {

            Map<String, List<String>> place_tagMap = new HashMap<>();
            while (place_tagRS.next()) {
                List<String> tagsTemp;
                String placeName = place_tagRS.getString("place");
                if (place_tagMap.containsKey(placeName)) {
                    // 해당 장소가 key로 등록되어 있을 경우
                    tagsTemp = place_tagMap.get(placeName);
                    tagsTemp.add(place_tagRS.getString("tagcontent"));
                } else {
                    // 해당 장소가 key로 아직 등록되지 않았을 경우
                    tagsTemp = new ArrayList<>();
                    tagsTemp.add(place_tagRS.getString("tagcontent"));
                    place_tagMap.put(placeName, tagsTemp);
                }
            }
            List<String> removedTag = new ArrayList<>();
            place_tagMap.keySet().forEach(key -> {
                place_tagMap.get(key).forEach(tag1 -> {
                    place_tagMap.get(key).forEach(tag2 -> {
                        if (!tag1.equals(tag2) && !removedTag.stream().anyMatch(rmTag -> {
                            return rmTag.equals(tag1) || rmTag.equals(tag2);
                        })) {
                            // 태그가 같으면 안되고, 전에 삭제된 태그면 안됨다.
                            sendString("get_tag_sim");
                            sendString(tag1);
                            sendString(tag2);
                            double sim = Double.parseDouble(recvString());
                            if (sim > 0.7) {
                                try {
                                    System.out.printf("Tag Del!!!\n%s, %s\n", tag1, tag2);
                                    removedTag.add(tag2);
                                    Connection conn2 = ConnectionProvider.getConnection();
                                    PreparedStatement pstmt2 = conn.prepareStatement(
                                            "DELETE FROM place_tag WHERE tag_id=(SELECT id from tag WHERE tag.tagcontent=?)");
                                    pstmt2.setString(1, tag2);
                                    pstmt2.executeUpdate();
                                    // System.out.printf("%s %s -> %f", tags[i], tags[j], sim);
                                    PreparedStatement pstmt3 = conn.prepareStatement(
                                            "update place_tag set tagcount = tagcount + 1 where tag_id = (Select id from tag where tag.tagcontent=?)");
                                    pstmt3.setString(1, tag1);
                                    pstmt3.executeUpdate();
                                    close(pstmt2);
                                    close(pstmt3);
                                    PreparedStatement pstmt4 = conn.prepareStatement(
                                            "update tag set tagcount = tagcount+1 where tagcontent =?");
                                    pstmt4.setString(1, tag1);
                                    pstmt4.executeUpdate();
                                    close(pstmt4);
                                    close(conn2);
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    });
                });
            });

            // place의 태그를 모아서 배열저장
            close(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}