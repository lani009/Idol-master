# restful api
## 1. 로그인
### 1.1. URI
/login
### 1.2. request
id
### 1.3. response
로그인 성공: login success
로그인 실패: login failed
## 2. 취향
### 2.1. 인기 취향 가져오기
취향 가져오기(인기 태그들 빼오기)
#### 2.1.1 URI
/taste/show
#### 2.1.2 request
None
#### 2.1.3 response
top 10 취향들
### 2.2. 취향 선택한것 저장하기//여러번실행됨/ 한번에 하나씩 저장
#### 2.2.1 URI
/taste/set
#### 2.2.2 request
id
tagcontent
#### 2.2.3 reponse
true or false

## 3. 리뷰
### 3.1. 리뷰들 가져오기
#### 3.1.1 URI
/review/get
#### 3.1.2. resquest
place
#### 3.1.3. response
review content  
tag content
### 3.2. 리뷰 작성
#### 3.2.1. URI
/review/set
#### 3.2.2. request
id  
text  
place
#### 3.2.3. response
success or failed
## 4. 추천하기
### 4.1. URI
/recommend
### 4.2. request
id  
### 4.3. response
장소들 추천
