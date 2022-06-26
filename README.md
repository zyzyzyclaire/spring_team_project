# spring_team_project
* 스프링을 이용한 영화 예매 사이트 팀프로젝트입니다.
* 개발기간 : 3주
* 개발인원 : 5명

# 개요
개발환경으로는
* FrontEnd : HTML, CSS, JS, JQuery, Bootstrap
* BackEnd : spring, Tomcat, java, jsp, Maven
* DataBase : Oracle
* API : I'mport, kakao
* 협업툴 : slack

# 사용방법
깃허브
1. Spring_team_project.zip 파일 다운받기
2. 다운받은 zip 파일 압축을 푼다.

임포트
1. 이클립스를 실행하고 File > Import 클릭
2. General > Exisiting Projects into Workspace > next 클릭
3. Select root directory/select archive file 에 Browse... 클릭
4. 압축을 한 파일이나 압축을 푼 파일을 선택
5. Finish

DB 설정
1. cmd 실행 후 sqlplus > system 계정접속
2. create user team identified by 1234;
3. grant connect,resource,unlimited tablespace, create view to team;
4. Team 계정 접속 후 zip 파일에있는 team.sql 파일을 복사해서 @붙이고 넣어준다.

# 설명
### ERD
![image](https://user-images.githubusercontent.com/95404191/175818610-4bc6afae-6b25-4cc4-b825-005602639ca6.png)

### 마이페이지
![image](https://user-images.githubusercontent.com/95404191/175818658-405cb27f-4be2-44d7-8744-befda1d6d6c5.png)

### 파일 업로드
![image](https://user-images.githubusercontent.com/95404191/175818672-59c5d24b-9f6d-4adc-bcc7-f708f80f25f8.png)

### 장바구니
![image](https://user-images.githubusercontent.com/95404191/175818685-4f0f5ffd-b7b1-47dc-8faa-64d11d3ae496.png)

### 영화 예매
![image](https://user-images.githubusercontent.com/95404191/175818730-95c25b88-5079-4d08-8d53-d4c8e5936c0f.png)

### 좌석 선택
![image](https://user-images.githubusercontent.com/95404191/175818746-3637bf19-2e2f-4da6-a53e-f8f033487c48.png)

### 영화 구매
![image](https://user-images.githubusercontent.com/95404191/175818790-4c766c17-bafd-4779-90a5-d211c401e2ac.png)

### 회원 관리
![image](https://user-images.githubusercontent.com/95404191/175818828-7c72414c-600a-4ddc-b348-5464e69b2d95.png)

### 회원 관리 API
![image](https://user-images.githubusercontent.com/95404191/175818853-fa0d71f0-01f2-4381-83a7-3e9fd32f94d3.png)

### 영화 상세페이지
![image](https://user-images.githubusercontent.com/95404191/175818920-a4234383-dab3-41ef-a36a-83574507b99a.png)

### 관리자 영화 등록 / 숙정 / 삭제
![image](https://user-images.githubusercontent.com/95404191/175818953-b5759155-11ad-42ea-99fa-3355ccde1525.png)

### 메인 페이지
![image](https://user-images.githubusercontent.com/95404191/175818971-579ccfac-6a6b-4f20-b772-db8d38f1e0e0.png)

### 공지시항
![image](https://user-images.githubusercontent.com/95404191/175818989-3113b8d9-b965-4f68-a0fd-03668f370a95.png)

### Q&A
![image](https://user-images.githubusercontent.com/95404191/175819041-e624b6be-b1c8-4900-a2cd-d20dd2b3a97a.png)

### 메인 페이지 특징
![image](https://user-images.githubusercontent.com/95404191/175819065-d2c63364-ae83-453d-949f-6ab93c1e5c66.png)
1. 영화 포스터를 클릭하면 제이쿼리 팝업 플러그인으로 동영상을 출력함
2. 검색 기능
3. 스크롤 내리면 나오는 div

### 영화 상품
![image](https://user-images.githubusercontent.com/95404191/175819113-f1c3102d-9056-4196-9fd7-073e62f1b5f3.png)

### 관리자 영화 리스트 특징
![image](https://user-images.githubusercontent.com/95404191/175819144-c7041a8b-a719-4953-8e19-41d8db266663.png)
1. 영화 등록 / 수정 / 삭제 가능
2. 비동기 ajax방식으로 정렬 가능
3. 검색 가능

### FAQ
![image](https://user-images.githubusercontent.com/95404191/175819212-37f7e6a8-8de5-4cb5-9871-f52d741bcac0.png)

### 리뷰
![image](https://user-images.githubusercontent.com/95404191/175819221-448ff95f-65df-413c-8db5-b0438e3790b5.png)
