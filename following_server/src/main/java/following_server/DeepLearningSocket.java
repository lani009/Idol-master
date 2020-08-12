package following_server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;

/**
 * Python 서버랑 소켓을 통해서 통신하는 클래스
 * @author
 */
public class DeepLearningSocket implements Closeable {
    private Socket socket;
    private BufferedWriter input;
    private BufferedReader br;
    public DeepLearningSocket() {
        socket = new Socket();
        InetSocketAddress address = new InetSocketAddress("localhost", 7575);
        // 소켓 연결 수행
        try {
			socket.connect(address);// 주소로 연결
		} catch (IOException e) {

			e.printStackTrace();
		}
		try {
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
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


    public String sendReview(String review){
        try {
            sendString("parse_text");
            sendString(review);
            String encodedTag = recvString();
    
            return encodedTag;
            
        } catch (Exception e) {
           System.out.println("리뷰 태그화 오류");
           e.printStackTrace();
        }
        return null;

    }

    public String placeUserSim(String tag){
        try {
            sendString("place_user_sim");
            sendString(tag);
            String encodedTag = recvString();

            return encodedTag;

        } catch (Exception e) {
           System.out.println("플레이스 유저 심 오류");
           e.printStackTrace();
        }
        return null;
    }

    public double tagSim(String tag1, String tag2){

        try {
            sendString("get_tag_sim");
            sendString(tag1);
            sendString(tag2);
            String encodedTag = recvString();

            return Double.parseDouble(encodedTag);

        } catch (Exception e) {
           System.out.println("태그 심 오류");
           e.printStackTrace();
        }
        return 0;


    }

    @Override
    public void close() throws IOException {
        sendString("0");    // 종료 코드
        socket.close();
        br.close();
        input.close();
    }
}