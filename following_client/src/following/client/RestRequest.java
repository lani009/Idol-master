package following.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import following.client.exception.PlaceNotFoundException;
import following.client.exception.QueryException;
import following.client.struct.Place;
import following.client.struct.Review;

public class RestRequest {
    private static final String ServerADDRESS = "http://101.101.208.218";
    private static String userId = null;
    private static boolean isLoggedIn = false;

    private RestRequest() {

    }

    /**
     * Singleton pattern. Lazy Loader
     */
    private static class Holder {
        static final RestRequest INSTANCE = new RestRequest();
    }

    /**
     * RestRequest 인스턴스 리턴. login 메소드 호출 및 login 성공 시 부터 사용 가능.
     * 
     * @return
     * @throws InstantiationException
     */
    public static RestRequest getInstance() throws InstantiationException {
        if (!isLoggedIn) {
            throw new InstantiationException("Please do login before calling getInstance method.");
        }
        return Holder.INSTANCE;
    }

    /**
     * User login
     * 
     * @param id 아이디
     * @return bool
     * @throws IOException
     */
    public static boolean login(String id) throws IOException {
        try {
            Map<String, String> param = new HashMap<>();
            param.put("id", id);
            sendRequestQuery("/login", param);
            return true;
        } catch (QueryException e) {
            // 로그인에 실패한 경우
            return false;
        }
    }

    /**
     * 인기 태그 가져오기. 취향 선택 시에 사용
     * 
     * @return 인기 태그 리스트
     */
    @SuppressWarnings("unchecked")
    public List<String> getPopularTags() {
        try {
            JSONObject jsonObj = sendRequestQuery("/taste/show", null);
            List<String> tagList = (ArrayList<String>) jsonObj.get("taste");    // 인기 태그 저장
            return tagList;
        } catch (QueryException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 해당 장소에 대한 설명-객체 리턴
     * 
     * <pre>
     *Place pwcp = RestRequest.getInstance().getPlaceDescription("피자나라치킨공주");
     *String placeName = pwcp.getPlaceName(); // 장소명
     *List<Review> reviews = pwcp.getReviews(); // 리뷰
     * </pre>
     * 
     * @param placeName 장소명
     * @return Place객체
     * @throws PlaceNotFoundException 해당 장소가 존재하지 않을 경우
     */
    public Place getPlaceDescription(String placeName) throws PlaceNotFoundException {
        try {
            JSONObject jsonObj = sendRequestQuery("/review/get", null);
            List<String> tags = (ArrayList<String>) jsonObj.get("tagcontent");
            // TODO
        } catch (QueryException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 리뷰 작성
     * 
     * @param review    리뷰 텍스트
     * @param placeName 장소 명
     * @throws PlaceNotFoundException 해당 장소가 존재하지 않을 경우
     */
    public void writeReview(String review, String placeName) throws PlaceNotFoundException {

    }

    /**
     * 해당 유저에게 맞는 장소 추천
     * 
     * @return 장소 리스트
     */
    public List<Place> getRecommendedPlace() {
        return null;
    }

    /**
     * 사용자 로그아웃
     */
    public void logout() {
        userId = null;
        isLoggedIn = false;
    }

    /**
     * 서버에 쿼리를 전송한뒤, 응답을 JSONObject 형태로 리턴한다
     * 
     * @param uri   uri
     * @param param GET 파라미터
     * @return JSONObject
     * @throws QueryException 서버와의 쿼리 실패시
     */
    private static JSONObject sendRequestQuery(String uri, Map<String, String> param) throws QueryException {
        try {
            URL url = new URL(ServerADDRESS + uri);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            if (param != null) {
                // 만약에 파라미터가 주어졌을 경우, param 추가. 없을 경우에는 건너뜀.
                param.keySet().forEach(key -> {
                    conn.setRequestProperty(key, param.get(key));
                });
            }
            conn.connect(); // 서버와 연결 수행
            if (conn.getResponseCode() == 400) {
                throw new QueryException("쿼리 실패");
            }

            // inputStream을 JSONObject로 변환하여 리턴
            return (JSONObject) new JSONParser().parse(new InputStreamReader(conn.getInputStream()));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
