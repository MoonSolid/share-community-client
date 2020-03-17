# 32_11 - 서버에서 제공한 프록시 객체를 사용하여 데이터를 처리





###  1: BoardXxxCommand 객체가 BoardDaoProxy 객체를 사용하여 데이터를 처리.

- com.moonsolid.sc.handler.BoardXxxCommand 클래스를 변경.
  - 입출력 스트림 필드를 제거.
  - 생성자에서 프록시 객체를 받는다.
  - 프록시 객체를 사용하여 데이터를 처리.
- com.moonsolid.sc.ClientApp 변경.
  - BoardDaoProxy 객체를 생성.
  - BoardXxxCommand 객체에 주입.

###  2: PlanXxxCommand 객체가 PlanDaoProxy 객체를 사용하여 데이터를 처리.

- com.moonsolid.sc.handler.PlanXxxCommand 클래스를 변경.
  - 입출력 스트림 필드를 제거한다.
  - 생성자에서 프록시 객체를 받다.
  - 프록시 객체를 사용하여 데이터를 처리.
- com.moonsolid.sc.ClientApp 변경.
  - PlanDaoProxy 객체를 생성.
  - PlanXxxCommand 객체에 주입.
  
###  3: MemberXxxCommand 객체가 MemberDaoProxy 객체를 사용하여 데이터를 처리하게 .

- com.moonsolid.sc.handler.MemberXxxCommand 클래스를 변경.
  - 입출력 스트림 필드를 제거.
  - 생성자에서 프록시 객체를 받는다.
  - 프록시 객체를 사용하여 데이터를 처리.
- com.moonsolid.sc.ClientApp 변경.
  - MemberDaoProxy 객체를 생성.
  - MemberXxxCommand 객체에 주입.


