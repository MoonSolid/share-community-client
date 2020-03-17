# 33_2 - 리팩토링: 요청할 때 마다 프록시와 커맨드를 생성하는 부분을 개선



### 1: 프록시 클래스 생성 부분을 변경.

- com.moonsolid.sc.dao.proxy.XxxDaoProxy 변경.
- com.moonsolid.sc.ClientApp 변경
  - 요청할 때 서버에 연결.
