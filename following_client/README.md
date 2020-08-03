# Server - Client 연동 방법
서버와 클라이언트는 JSON 형식으로 통신한다. 예를 들어서 장소 추천의 경우
> 클라이언트가 서버에게 요청: http://101.101.208.***?id=lani
> 서버가 클라이언트에게 전송: {["피자나라치킨공주", "에슐리", "피자헛"]}

이러한 과정을 대신 해주는 것이 RestRequest.java이다.
![5684d701](https://user-images.githubusercontent.com/12856941/89168829-61d0e400-d5b8-11ea-9582-2f3ae1e2ae49.jpg)
간략한 그림

## 1. 로그인
``` Java
EditText editText = (EditText) findViewById(R.id.editText);
String id = editText.getText(); // 사용자로부터 아이디를 입력 받았다고 가정

boolean loginBool = RestRequest.login(id);  // 로그인 성공 여부를 저장
if (loginBool) {
    // 로그인 성공
} else {
    // 로그인 실패
}
```

## 2. 인기 태그 가져오기
사용자가 취향 선택을 하기 위해서는 인기 태그들을 가져와야 한다.

```Java
List<String> popularTags = RestRequest.getInstance().getPopularTags();
for (String tag : popularTags) {
    화면에띄우기(tag);  // 화면에 띄워서 사용자가 선택할 수 있게끔 한다.
}
```

## 3. 리뷰 작성하기

```Java
EditText editText = (EditText) findById(R.id.EditText);
String reviewText = editText.getText(); // 사용자로부터 리뷰를 입력받았다고 가정

try {
    RestRequest.getInstance().writeReview(reivewText, "피자나라치킨공주");
} catch (PlaceNotFoundException e) {
    e.printStackTrace();
}
```
