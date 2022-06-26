# spring_team_project
스프링을 이용한 영화 예매 사이트 팀프로젝트입니다.

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

