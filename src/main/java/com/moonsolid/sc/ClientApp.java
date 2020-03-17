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
import com.moonsolid.sc.dao.proxy.BoardDaoProxy;
import com.moonsolid.sc.dao.proxy.DaoProxyHelper;
import com.moonsolid.sc.dao.proxy.MemberDaoProxy;
import com.moonsolid.sc.dao.proxy.PlanDaoProxy;
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

  Deque<String> commandStack;
  Queue<String> commandQueue;

  String host;
  int port;

  HashMap<String, Command> commandMap = new HashMap<>();

  public ClientApp() {
    commandStack = new ArrayDeque<>();
    commandQueue = new LinkedList<>();



    try {
      host = prompt.inputString("서버주소를 입력하세요 : ");
      port = prompt.inputInt("포트번호를 입력하세요 : ");
    } catch (Exception e) {
      System.out.println("서버 주소 또는 포트 번호가 유효하지 않습니다!");
      keyboard.close();
      return;
    }
    DaoProxyHelper daoProxyHelper = new DaoProxyHelper(host, port);

    BoardDaoProxy boardDao = new BoardDaoProxy(daoProxyHelper);
    PlanDaoProxy planDao = new PlanDaoProxy(daoProxyHelper);
    MemberDaoProxy memberDao = new MemberDaoProxy(daoProxyHelper);

    commandMap.put("/board/list", new BoardListCommand(boardDao));
    commandMap.put("/board/add", new BoardAddCommand(boardDao, prompt));
    commandMap.put("/board/detail", new BoardDetailCommand(boardDao, prompt));
    commandMap.put("/board/update", new BoardUpdateCommand(boardDao, prompt));
    commandMap.put("/board/delete", new BoardDeleteCommand(boardDao, prompt));

    commandMap.put("/member/list", new MemberListCommand(memberDao));
    commandMap.put("/member/add", new MemberAddCommand(memberDao, prompt));
    commandMap.put("/member/detail", new MemberDetailCommand(memberDao, prompt));
    commandMap.put("/member/update", new MemberUpdateCommand(memberDao, prompt));
    commandMap.put("/member/delete", new MemberDeleteCommand(memberDao, prompt));

    commandMap.put("/plan/list", new PlanListCommand(planDao));
    commandMap.put("/plan/add", new PlanAddCommand(planDao, prompt));
    commandMap.put("/plan/detail", new PlanDetailCommand(planDao, prompt));
    commandMap.put("/plan/update", new PlanUpdateCommand(planDao, prompt));
    commandMap.put("/plan/delete", new PlanDeleteCommand(planDao, prompt));

    commandMap.put("/server/stop", () -> {
      try {
        try (Socket socket = new Socket(host, port);
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {

          out.writeUTF("/server/stop");
          out.flush();
          System.out.println("서버: " + in.readUTF());
          System.out.println("서버가 종료되었습니다");
        }
      } catch (Exception e) {
      }
    });
  }

  public void service() {


    while (true) {
      String command;
      command = prompt.inputString("\n명령> ");

      if (command.length() == 0)
        continue;

      if (command.equals("history")) {
        printCommandHistory(commandStack.iterator());
        continue;
      } else if (command.equals("history2")) {
        printCommandHistory(commandQueue.iterator());
        continue;
      } else if (command.equals("quit")) {
        break;
      }

      commandStack.push(command);
      commandQueue.offer(command);

      processCommand(command);

    }

    keyboard.close();
  }

  private void processCommand(String command) {
    Command commandHandler = commandMap.get(command);
    if (commandHandler == null) {
      System.out.println("실행할 수 없는 명령입니다.");
      return;
    }
    commandHandler.execute();
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
