package struct.rest.parser;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import java.util.List;

import exception.PlaceNotFoundException;
import struct.Place;

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
            URL url = new URL(ServerADDRESS + "/login");
            URLConnection conn = url.openConnection();
            byte[] bStream = new byte[255];
            conn.getInputStream().read(bStream);
            conn.getInputStream().close();
            String body = new String(bStream);
            if (body.equals("{[True]}")) {
                userId = id;
                isLoggedIn = true;
                return true;
            } else {
                return false;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 인기 태그 가져오기 취향 선택 시에 사용
     * 
     * @return 인기 태그 리스트
     */
    public List<String> getPopularTags() {
        return null;
    }

    /**
     * 해당 장소에 대한 설명-객체 리턴
     * 
     * <pre>
     * Place pwcp = RestRequest.getInstance().getPlaceDescription("피자나라치킨공주");
     * String placeName = pwcp.getPlaceName(); // 장소명
     * List<Review> reviews = pwcp.getReviews();   // 리뷰
     * </pre>
     * 
     * @param placeName 장소명
     * @return Place객체
     * @throws PlaceNotFoundException 해당 장소가 존재하지 않을 경우
     */
    public Place getPlaceDescription(String placeName) throws PlaceNotFoundException {
        return null;
    }

    /**
     * 리뷰 작성
     * @param review 리뷰 텍스트
     * @param placeName 장소 명
     * @throws PlaceNotFoundException 해당 장소가 존재하지 않을 경우
     */
    public void writeReview(String review, String placeName) throws PlaceNotFoundException {
        
    }

    /**
     * 해당 유저에게 맞는 장소 추천
     * @return 장소 리스트
     */
    public List<Place> getRecommendedPlace() {
        return null;
    }
}