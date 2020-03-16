package com.moonsolid.sc;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import com.moonsolid.sc.handler.BoardAddCommand;
import com.moonsolid.sc.handler.BoardDeleteCommand;
import com.moonsolid.sc.handler.BoardDetailCommand;
import com.moonsolid.sc.handler.BoardListCommand;
import com.moonsolid.sc.handler.BoardUpdateCommand;
import com.moonsolid.sc.handler.Command;
import com.moonsolid.sc.handler.MemberAddCommand;
import com.moonsolid.sc.handler.MemberDeleteCommand;
import com.moonsolid.sc.handler.MemberDetailCommand;
import com.moonsolid.sc.handler.MemberListCommand;
import com.moonsolid.sc.handler.MemberUpdateCommand;
import com.moonsolid.sc.handler.PlanAddCommand;
import com.moonsolid.sc.handler.PlanDeleteCommand;
import com.moonsolid.sc.handler.PlanDetailCommand;
import com.moonsolid.sc.handler.PlanListCommand;
import com.moonsolid.sc.handler.PlanUpdateCommand;
import com.moonsolid.util.Prompt;

public class ClientApp {

  Scanner keyboard = new Scanner(System.in);
  Prompt prompt = new Prompt(keyboard);

  public void service() {

    String serverAddr = null;
    int port = 0;

    try {
      serverAddr = prompt.inputString("서버주소를 입력하세요 : ");
      port = prompt.inputInt("포트번호를 입력하세요 : ");

    } catch (Exception e) {
      System.out.println("서버 주소 또는 포트 번호가 유효하지 않습니다!");
      keyboard.close();
      return;
    }

    try (Socket socket = new Socket(serverAddr, port);
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {

      System.out.println("서버와 연결을 되었습니다.");

      processCommand(out, in);

      System.out.println("서버와 연결을 끊었습니다.");

    } catch (Exception e) {
      System.out.println("예외 발생:");
      e.printStackTrace();
    }

    keyboard.close();
  }

  private void processCommand(ObjectOutputStream out, ObjectInputStream in) {

    Deque<String> commandStack = new ArrayDeque<>();
    Queue<String> commandQueue = new LinkedList<>();

    HashMap<String, Command> commandMap = new HashMap<>();
    commandMap.put("/board/list", new BoardListCommand(out, in));
    commandMap.put("/board/add", new BoardAddCommand(out, in, prompt));
    commandMap.put("/board/detail", new BoardDetailCommand(out, in, prompt));
    commandMap.put("/board/update", new BoardUpdateCommand(out, in, prompt));
    commandMap.put("/board/delete", new BoardDeleteCommand(out, in, prompt));

    commandMap.put("/member/list", new MemberListCommand(out, in));
    commandMap.put("/member/add", new MemberAddCommand(out, in, prompt));
    commandMap.put("/member/detail", new MemberDetailCommand(out, in, prompt));
    commandMap.put("/member/update", new MemberUpdateCommand(out, in, prompt));
    commandMap.put("/member/delete", new MemberDeleteCommand(out, in, prompt));

    commandMap.put("/plan/list", new PlanListCommand(out, in));
    commandMap.put("/plan/add", new PlanAddCommand(out, in, prompt));
    commandMap.put("/plan/detail", new PlanDetailCommand(out, in, prompt));
    commandMap.put("/plan/update", new PlanUpdateCommand(out, in, prompt));
    commandMap.put("/plan/delete", new PlanDeleteCommand(out, in, prompt));

    try {
      while (true) {
        String command;
        command = prompt.inputString("\n명령> ");

        if (command.length() == 0)
          continue;

        if (command.equals("quit") || command.equals("/server/stop")) {
          out.writeUTF(command);
          out.flush();
          System.out.println("서버: " + in.readUTF());
          System.out.println("서버가 종료되었습니다.");
          break;
        } else if (command.equals("history")) {
          printCommandHistory(commandStack.iterator());
          continue;
        } else if (command.equals("history2")) {
          printCommandHistory(commandQueue.iterator());
          continue;
        }

        commandStack.push(command);

        commandQueue.offer(command);

        Command commandHandler = commandMap.get(command);

        if (commandHandler != null) {
          try {
            commandHandler.execute();
          } catch (Exception e) {
            e.printStackTrace();
            System.out.printf("명령어 실행 중 오류 발생: %s\n", e.getMessage());
          }
        } else {
          System.out.println("실행할 수 없는 명령입니다.");
        }
      }
    } catch (Exception e) {
      System.out.println("프로그램 실행 중 오류 발생했습니다.");
    }

    keyboard.close();
  }

  private void printCommandHistory(Iterator<String> iterator) {
    int count = 0;
    while (iterator.hasNext()) {
      System.out.println(iterator.next());
      count++;

      if ((count % 5) == 0) {
        String str = prompt.inputString(":");
        if (str.equalsIgnoreCase("q")) {
          break;
        }
      }
    }
  }

  public static void main(String[] args) throws Exception {
    System.out.println("클라이언트 스케쥴 관리 시스템입니다.");

    ClientApp app = new ClientApp();
    app.service();


  }
}
