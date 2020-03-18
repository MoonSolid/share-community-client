# 36_1 - DBMS를 도입하여 데이터 관리를 맡기기

## 

### 1: 애플리케이션에서 사용할 사용자와 데이터베이스를 MariaDB에 추가.

- doc/db/project-ddl.sql
- doc/db/project-data.sql



### 2: 프로젝트에 `MariaDB` JDBC 드라이버를 추가.

- build.gradle 
    - 의존 라이브러리에 MariaDB JDBC Driver 정보를 추가.

build.gradle 파일
```
plugins {
    id 'java'
    id 'application'
    id 'eclipse'
}

tasks.withType(JavaCompile) {
    options.encoding = 'UTF-8'
    sourceCompatibility = '1.8'
    targetCompatibility = '1.8'
}

mainClassName = 'App'

dependencies {
    compile group: 'org.mariadb.jdbc', name: 'mariadb-java-client', version: '2.3.0'
    compile 'com.google.guava:guava:23.0'
    testCompile 'junit:junit:4.12'
}

repositories {
    jcenter()
}
```



### 3: MariaDB에서 제공하는 Proxy를 사용하여 DBMS와 연동하여 데이터 처리 작업을 수행할 DAO를 정의.

- com.moonsolid.sc.dao.mariadb 패키지 생성.
- com.moonsolid.sc.dao.mariadb.BoardDaoImpl 추가.
- com.moonsolid.sc.dao.mariadb.PlanDaoImpl 추가.
- com.moonsolid.sc.dao.mariadb.MemberDaoImpl 추가.

### 5: Command 객체의 기존 DAO를 MariaDB 연동 DAO로 교체. 

- com.eomcs.sc.ClientApp 변경.

### 6: DBMS 연동에 맞춰서 Command 객체를 변경.

- com.eomcs.sc.handler.XxxAddCommand 변경.
- com.eomcs.sc.handler.XxxUpdateCommand 변경.
- com.eomcs.sc.handler.XxxDeleteCommand 변경.



