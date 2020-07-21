package struct;

import java.util.List;

public class Place {
    /**
     * 장소 명
     */
    private String placeName;
    /**
     * 리뷰
     */
    private List<Review> reviews;

    public Place(String placeName, List<Review> reviews) {
        this.placeName = placeName;
        this.reviews = reviews;
    }

    /**
     * @return 장소 이름
     */
    public String getPlaceName() {
        return this.placeName;
    }

    /**
     * @return 리뷰
     */
    public List<Review> getReviews() {
        return this.reviews;
    }

}