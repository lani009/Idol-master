package following.client.struct;

public class Review {
    /**
     * 리뷰 텍스트
     */
    private String text;
    /**
     * 리뷰 작성자
     */
    private String author;

    public Review(String text, String author) {
        this.text = text;
        this.author = author;
    }

    /**
     * @return 리뷰 내용
     */
    public String getText() {
        return this.text;
    }

    /**
     * @return 리뷰 작성자
     */
    public String getAuthor() {
        return this.author;
    }
}