package com.moonsolid.sc.handler;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import com.moonsolid.sc.domain.Schedule;
import com.moonsolid.util.Prompt;

public class ScheduleDetailCommand implements Command {

  ObjectOutputStream out;
  ObjectInputStream in;

  Prompt prompt;

  public ScheduleDetailCommand(ObjectOutputStream out, ObjectInputStream in, Prompt prompt) {
    this.out = out;
    this.in = in;
    this.prompt = prompt;
  }

  @Override
  public void execute() {
    try {
      int no = prompt.inputInt("일정번호를 입력하세요 : ");

      out.writeUTF("/schedule/detail");
      out.writeInt(no);
      out.flush();

      String response = in.readUTF();

      if (response.equals("FAIL")) {
        System.out.println(in.readUTF());
        return;
      }

      Schedule schedule = (Schedule) in.readObject();
      System.out.printf("일정 번호: %d\n", schedule.getNo());
      System.out.printf("일정 장소: %s\n", schedule.getPlace());
      System.out.printf("일정 내용: %s\n", schedule.getDescription());
      System.out.printf("메모: %s\n", schedule.getMemo());
      System.out.printf("일정에 사용되는 비용: %s\n", schedule.getCost());
      System.out.printf("일정 시간: %s\n", schedule.getScheduleTime());



    } catch (Exception e) {
      System.out.println("명령 실행 중 오류 발생!");
    }
  }
}


