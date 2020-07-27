package rest.parser;

import java.io.IOException;
import java.util.List;

import exception.PlaceNotFoundException;
import struct.Place;

public class Test {
    public static void main(String[] args) throws IOException {
        String id = "asdf";
        if (RestRequest.login(id)) {
            System.out.println("로그인 성공");
            try {
                RestRequest.getInstance().writeReview("여기 정말 맛있어요", "피자나라치킨공주");
                List<Place> recommendedPlaces = RestRequest.getInstance().getRecommendedPlace();
                System.out.println(recommendedPlaces.get(0).getPlaceName());
            } catch (InstantiationException | PlaceNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("로그인 실패");
            // --
        }
    }
}