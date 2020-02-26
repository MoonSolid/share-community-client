package com.moonsolid.sc;

import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class ClientApp {
  public static void main(String[] args) {
    System.out.println("클라이언트 스케쥴관리 시스템입니다.");

    String serverAddr = null;
    int port = 0;

    Scanner keyScan = new Scanner(System.in);

    try {
      System.out.print("서버주소를 입력하세요 : ");
      serverAddr = keyScan.nextLine();

      System.out.print("포트번호를 입력하세요 : ");
      port = Integer.parseInt(keyScan.nextLine());

    } catch (Exception e) {
      System.out.println("서버 주소 또는 포트 번호가 유효하지 않습니다!");
      keyScan.close();
      return;
    }

    try (Socket socket = new Socket(serverAddr, port);
        PrintStream out = new PrintStream(socket.getOutputStream());
        Scanner in = new Scanner(socket.getInputStream())) {

      System.out.println("서버와 연결되었습니다.");

      System.out.print("서버에 보낼 메시지: ");
      String sendMsg = keyScan.nextLine();
      out.println(sendMsg);
      System.out.println("서버에 메시지를 전송하였습니다.");

      String message = in.nextLine();
      System.out.println("서버로부터 메시지를 수신하였습니다.");

      System.out.println("서버: " + message);

      System.out.println("서버와 연결을 끊었습니다");

    } catch (Exception e) {
      System.out.println("예외 발생:");
      e.printStackTrace();
    }

    keyScan.close();
  }
}
