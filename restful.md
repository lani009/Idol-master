# restful api Spec
## 1. 로그인
### 1.1. URI
/login
### 1.2. request
<strong>id</strong> 유저 아이디

### 1.3. response
로그인 성공: status code -> 200  
로그인 실패: status code -> 400
## 2. 취향
인기 태그들 빼오기
### 2.1. URI
/taste/show
### 2.2. request
<strong>None</strong>
### 2.3. response
top 10 취향들

## 3. 리뷰
### 3.1. 리뷰들 가져오기
#### 3.1.1 URI
/review/get
#### 3.1.2. resquest
<strong>place</strong> 장소 이름

#### 3.1.3. response
review content  
tag content
### 3.2. 리뷰 작성
#### 3.2.1. URI
/review/set
#### 3.2.2. request
<strong>id</strong> 유저 아이디  
<strong>text</strong> 리뷰 텍스트  
<strong>place</strong> 장소 이름

#### 3.2.3. response
success or failed
## 4. 추천하기
### 4.1. URI
/recommend
### 4.2. request
<strong>id</strong> 유저 아이디

### 4.3. response
장소들 추천
