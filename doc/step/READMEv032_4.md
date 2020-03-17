# 32_4 - 일정 게시판 추가



## 



###  1: Plan 클래스 추가.

- Plan.java 생성.

###  2: 일정 관리를 처리하는 Command 객체 생성.

- PlanXxxCommand.java 추가. 

###  3: Command 객체가 서버와 통신할 수 있도록 입출력 도구생성.

- ClientApp.java 변경
  - 서버와 연결하는 코드를 적용.
  - 서버와 통신할 수 있는 입출력 도구를 PlanXxxCommand 객체에 추가.
  
### 4: PlanListCommand 가 작업할 때 서버와 통신하도록 처리.

- PlanListCommand.java 추가

###  5: PlanAddCommand 가 작업할 때 서버와 통신하도록 처리.

- PlanAddCommand.java 추가

###  6: PlanDetailCommand 가 작업할 때 서버와 통신하도록 처리.

- PlanDetailCommand.java 변경
  
  
###  7: PlanUpdateCommand 가 작업할 때 서버와 통신하도록 처리.

- PlanUpdateCommand.java 변경
###  8: PlanDeleteCommand 가 작업할 때 서버와 통신하도록 처리.

- PlanDeleteCommand.java 변경

### 9.lesson객체 삭제

- com.moonsolid.domain.Lesson 삭제

### 10.수업게시판 명령을 처리하는 Command 삭제

- com.moonsolid.handler.LessonXxxCommand 삭제

### 11.Client의 Command관련 코드 삭제

- com.moonsolid.sc.ClientApp변경