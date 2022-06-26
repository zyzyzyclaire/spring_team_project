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
![image](https://user-images.githubusercontent.com/95404191/175818610-4bc6afae-6b25-4cc4-b825-005602639ca6.png)
