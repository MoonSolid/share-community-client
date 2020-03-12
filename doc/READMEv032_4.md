# 32_4 - 서버에 데이터를 요청하는 기능을 추가



- src/main/java/com/eomcs/lms/ClientApp.java 변경

## 

###  1: Board 클래스 추가.

- com.eomcs.lms.domain 패키지 생성.
- Board.java 생성.

###  2: 게시물 관리를 처리하는 Command 객체 생성.

- com.eomcs.lms.handler 패키지 생성.
- BoardXxxCommand.java 추가. 

###  3: Command 객체가 서버와 통신할 수 있도록 입출력 도구생성.

- ClientApp.java 변경
  - 서버와 연결하는 코드를 적용.
  - 서버와 통신할 수 있는 입출력 도구를 BoardXxxCommand 객체에 추가.
  
### 4: BoardListCommand 가 작업할 때 서버와 통신하도록 처리.

- BoardListCommand.java 변경
  - 서버의 입출력 도구를 받을 수 있도록 생성자를 변경.
  - 데이터를 읽고 쓸 때 서버에 요청하도록 execute()를 변경.

###  5: BoardAddCommand 가 작업할 때 서버와 통신하도록 처리.

- BoardAddCommand.java 변경
  - 서버의 입출력 도구를 받을 수 있도록 생성자를 변경.
  - 데이터를 읽고 쓸 때 서버에 요청하도록 execute()를 변경.

###  6: BoardDetailCommand 가 작업할 때 서버와 통신하도록 처리.

- BoardDetailCommand.java 변경
  - 서버의 입출력 도구를 받을 수 있도록 생성자를 변경.
  - 데이터를 읽고 쓸 때 서버에 요청하도록 execute()를 변경.
  
###  7: BoardUpdateCommand 가 작업할 때 서버와 통신하도록 처리.

- BoardUpdateCommand.java 변경
  - 서버의 입출력 도구를 받을 수 있도록 생성자를 변경.
  - 데이터를 읽고 쓸 때 서버에 요청하도록 execute()를 변경.
  
###  8: BoardDeleteCommand 가 작업할 때 서버와 통신하도록 처리.

- BoardDeleteCommand.java 변경
  - 서버의 입출력 도구를 받을 수 있도록 생성자를 변경.
  - 데이터를 읽고 쓸 때 서버에 요청하도록 execute()를 변경.

###  9: LessonXxxCommand 가 작업할 때 서버와 통신하도록 처리.

- LessonXxxCommand.java 변경
  - 서버의 입출력 도구를 받을 수 있도록 생성자를 변경.
  - 데이터를 읽고 쓸 때 서버에 요청하도록 execute()를 변경.
  
###  10: MemberXxxCommand 가 작업할 때 서버와 통신하도록 처리.

- MemberXxxCommand.java 변경
  - 서버의 입출력 도구를 받을 수 있도록 생성자를 변경.
  - 데이터를 읽고 쓸 때 서버에 요청하도록 execute()를 변경.