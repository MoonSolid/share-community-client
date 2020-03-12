package com.moonsolid.sc.handler;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import com.moonsolid.util.Prompt;

public class ScheduleDeleteCommand implements Command {

  ObjectOutputStream out;
  ObjectInputStream in;

  Prompt prompt;

  public ScheduleDeleteCommand(ObjectOutputStream out, ObjectInputStream in, Prompt prompt) {
    this.out = out;
    this.in = in;
    this.prompt = prompt;
  }

  @Override
  public void execute() {
    try {
      int no = prompt.inputInt("삭제할 일정번호를 입력하세요 : ");

      out.writeUTF("/schedule/delete");
      out.writeInt(no);
      out.flush();

      String response = in.readUTF();

      if (response.equals("FAIL")) {
        System.out.println(in.readUTF());
        return;
      }
      System.out.println("일정을 삭제했습니다.");

    } catch (Exception e) {
      System.out.println("명령 실행 중 오류 발생!");
    }
  }
}


