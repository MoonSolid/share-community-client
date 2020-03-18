# 37_5 - Application Server 구조로 변경: 서버 종료 명령 처리

## 

### 1: '/server/stop' 명령을 처리

- com.moonsolid.sc.ClientApp 변경
  - 사용자가 '/server/stop'을 입력했을 때 서버가 종료 작업을 즉시 할 수 있도록 두 번 요청.

