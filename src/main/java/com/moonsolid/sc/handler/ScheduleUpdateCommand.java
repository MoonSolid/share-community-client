package com.moonsolid.sc.handler;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import com.moonsolid.sc.domain.Schedule;
import com.moonsolid.util.Prompt;

public class ScheduleUpdateCommand implements Command {

  ObjectOutputStream out;
  ObjectInputStream in;

  Prompt prompt;

  public ScheduleUpdateCommand(ObjectOutputStream out, ObjectInputStream in, Prompt prompt) {
    this.out = out;
    this.in = in;
    this.prompt = prompt;
  }

  @Override
  public void execute() {
    try {
      int no = prompt.inputInt("번호? ");

      out.writeUTF("/schedule/detail");
      out.writeInt(no);
      out.flush();

      String response = in.readUTF();
      if (response.equals("FAIL")) {
        System.out.println(in.readUTF());
        return;
      }

      Schedule oldSchedule = (Schedule) in.readObject();
      Schedule newSchedule = new Schedule();

      newSchedule.setNo(oldSchedule.getNo());

      newSchedule.setPlace(prompt.inputString(
          String.format("변경할 일정 장소를 입력하세요 (기존 장소 : %s) ", oldSchedule.getPlace()),
          oldSchedule.getPlace()));

      newSchedule.setDescription(
          prompt.inputString("변경할 일정내용을 입력하세요 (기존 일정내용 : %s) ", oldSchedule.getDescription()));

      newSchedule.setMemo(prompt.inputString("변경할 메모를 입력하세요 (기존 메모 : %s) ", oldSchedule.getMemo()));

      newSchedule.setCost(prompt.inputString("변경비용을 입력하세요 (기존 비용 : %s) ", oldSchedule.getCost()));

      newSchedule.setScheduleTime(
          prompt.inputString("변경할 일정시간을 입력하세요 (기존 일정시간 : %s)", oldSchedule.getScheduleTime()));


      if (oldSchedule.equals(newSchedule)) {
        System.out.println("일정 변경을 취소하였습니다.");
        return;
      }

      out.writeUTF("/schedule/update");
      out.writeObject(newSchedule);
      out.flush();

      response = in.readUTF();
      if (response.equals("FAIL")) {
        System.out.println(in.readUTF());
        return;
      }

      System.out.println("일정을 변경했습니다.");

    } catch (Exception e) {
      System.out.println("명령 실행 중 오류 발생!");
    }
  }
}


