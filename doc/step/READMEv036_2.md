# 36_2 - DB 커넥션 객체 공유

## 

- src/main/java/com/moonsolid/scdao/mariadb/BoardDaoImpl.java 변경
- src/main/java/com/moonsolid/sc/dao/mariadb/LessonDaoImpl.java 변경
- src/main/java/com/moonsolid/sc/dao/mariadb/MemberDaoImpl.java 변경
- src/main/java/com/moonsolid/sc/ClientApp.java 변경

## 

### 1: 한 개의 DB 커넥션 객체를 DAO 모두가 공유하여 사용.

- com.moonsolid.sc.ClientApp 변경.
- com.moonsolid.sc.dao.mariadb.XxxDaoImpl 변경.

