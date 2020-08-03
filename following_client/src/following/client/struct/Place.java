package following.client.struct;

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
    /**
     * 태그
     */
    private List<String> tags;

    public Place(String placeName, List<Review> reviews, List<String> tags) {
        this.placeName = placeName;
        this.reviews = reviews;
        this.tags = tags;
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

    /**
     * @return 장소에 해당하는 태그
     */
    public List<String> getTags() {
        return this.tags;
    }

}