# 33_3 - 리팩토링: 서버 연결 부분을 캡슐화



###  1: 서버와 통신을 담당할 실제 작업의 규칙을 정의.

- com.moonsolid.sc.dao.proxy.Worker 인터페이스를 정의.

###  2: DaoProxy를 도와 서버와의 연결을 담당할 객체를 정의. 

- com.moonsolid.sc.dao.proxy.DaoProxyHelper 를 정의.

###  3: DaoProxyHelper를 사용하도록 DaoProxy를 변경.

- com.moonsolid.sc.dao.proxy.XxxDaoProxy 를 변경.

###  4: DaoProxyHelper가 추가된 것에 맞춰 ClientApp을 변경.

- com.moonsolid.sc.ClientApp 변경.
  
  
  
  
  
  
  
  
  
  