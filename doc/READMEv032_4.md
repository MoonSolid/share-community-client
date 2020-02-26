# 32_4 - 서버에 게시물 데이터를 요청하는 기능을 추가하기

### 1.도메인 패키지 생성

com.moonsolid.sc.domain 패키지 생성.

- Board.java 생성.
  Member.java 생성
  Lesson.java 생성

###  2: 게시물 관리를 처리하는 Command 객체 생성.

- com.moonsolid.sc.handler 패키지에 BoardAddCommand.java 클래스 생성
- com.moonsolid.sc.handler 패키지에 BoardDeleteCommand.java 클래스 생성
- com.moonsolid.sc.handler 패키지에 BoardUpdateCommand.java 클래스 생성
- com.moonsolid.sc.handler 패키지에 BoardListCommand.java 클래스 생성
- com.moonsolid.sc.handler 패키지에 BoardDetailCommand.java 클래스 생성
- com.moonsolid.sc.handler 패키지에 LessonAddCommand.java 클래스 생성
- com.moonsolid.sc.handler 패키지에 LessonDeleteCommand.java 클래스 생성
- com.moonsolid.sc.handler 패키지에 LessonUpdateCommand.java 클래스 생성
- com.moonsolid.sc.handler 패키지에 LessonListCommand.java 클래스 생성
- com.moonsolid.sc.handler 패키지에 LessonDetailCommand.java 클래스 생성
- com.moonsolid.sc.handler 패키지에 MemberAddCommand.java 클래스 생성
- com.moonsolid.sc.handler 패키지에 MemberDeleteCommand.java 클래스 생성
- com.moonsolid.sc.handler 패키지에 MemberUpdateCommand.java 클래스 생성
- com.moonsolid.sc.handler 패키지에 MemberListCommand.java 클래스 생성
- com.moonsolid.sc.handler 패키지에 MemberDetailCommand.java 클래스 생성

###  3: Command 객체가 서버와 통신할 수 있도록 입출력 도구생성.

- src/main/java/com/moonsolid/sc/ClientApp.java 변경
  - 서버와 연결하는 코드를 적용한다.
  - 서버와 통신할 수 있는 입출력 도구를 XxxCommand 객체에 제공한다.
  
###  4: Command객체가 작업할 때 서버와 통신하도록 처리.

- XxxCommand.java 변경
  - 서버의 입출력 도구를 받을 수 있도록 생성자를 변경한다.
  - 데이터를 읽고 쓸 때 서버에 요청하도록 execute()를 변경한다.



