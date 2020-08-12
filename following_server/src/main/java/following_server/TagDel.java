package following_server;

import java.io.IOException;
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

// place_tag에서 받아와 place랑 tag
// 한 place의 모든 태그들끼리의 심 비교
// 만약 심 0.7이상이면 후자 삭제 전자 tagcount+1
// 다음 place의 모든 ''""

// 1. place < tag

// select place 모든 place select 하나씩 불러오기 배열에 저장
// select place tag =place 인 태그를 불러와서 배열돌려가면서 if 배열[i]=place_tag의 place
// tag들끼리 비교

// {}
// place[]="에그슬럿", 아러아러
// select place from place_tag -> places[]->에그슬럿
// for문을 돌려서 if(place[]==places[]){String realplace}//////
// select tag from place_tag where realplace=
// tag 들끼리 심알고리즘----------다시반복

// (SELECT tagcontent from tag WHERE id=?)
public class TagDel implements Runnable {

    public TagDel() {

    }

    @Override
    public void run() {
        delSimTags();
        // while (true) {
        //     delSimTags();
        //     try {
        //         System.out.println("태그삭제");
        //         Thread.sleep(1000 * 3600);
        //     } catch (InterruptedException e) {
        //         e.printStackTrace();
        //     }
        // }
    }

    /**
	 * 비슷한 태그들 삭제
	 */
	private void delSimTags() {
            
        try (Connection conn = ConnectionProvider.getConnection();
            PreparedStatement placePstmt = conn.prepareStatement("SELECT place, tagcontent FROM place_tag " +
                                                                 "INNER JOIN place ON place.id=place_tag.place_id" +
                                                                 " INNER JOIN tag ON tag.id=place_tag.tag_id");
                ResultSet place_tagRS = placePstmt.executeQuery();
                DeepLearningSocket dls = new DeepLearningSocket();) {

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
                        if (!tag1.equals(tag2) && !removedTag.stream().anyMatch(rmTag -> {return rmTag.equals(tag1) || rmTag.equals(tag2);})) {
                            // 태그가 같으면 안되고, 전에 삭제된 태그면 안됨다.
                            double sim = dls.tagSim(tag1, tag2);
                            if (sim >0.7){
                                try {
                                    System.out.printf("Tag Del!!!\n%s, %s\n", tag1, tag2);
                                    removedTag.add(tag2);
                                    Connection conn2 = ConnectionProvider.getConnection();
                                    PreparedStatement pstmt2 = conn.prepareStatement("DELETE FROM place_tag WHERE tag_id=(SELECT id from tag WHERE tag.tagcontent=?)");
                                    pstmt2.setString(1, tag2);
                                    pstmt2.executeUpdate();
                                    // System.out.printf("%s %s -> %f", tags[i], tags[j], sim);
                                    PreparedStatement pstmt3 = conn.prepareStatement("update place_tag set tagcount = tagcount + 1 where tag_id = (Select id from tag where tag.tagcontent=?)");
                                    pstmt3.setString(1, tag1);
                                    pstmt3.executeUpdate();
                                    close(pstmt2);
                                    close(pstmt3);
                                    PreparedStatement pstmt4 = conn.prepareStatement("update tag set tagcount = tagcount+1 where tagcontent =?");
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
                
                //place의 태그를 모아서 배열저장
            close(conn);
            close(dls);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}