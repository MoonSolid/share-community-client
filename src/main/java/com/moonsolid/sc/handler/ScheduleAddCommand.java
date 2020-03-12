package com.moonsolid.sc.handler;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import com.moonsolid.sc.domain.Schedule;
import com.moonsolid.util.Prompt;

public class ScheduleAddCommand implements Command {

  ObjectOutputStream out;
  ObjectInputStream in;

  Prompt prompt;

  public ScheduleAddCommand(ObjectOutputStream out, ObjectInputStream in, Prompt prompt) {
    this.out = out;
    this.in = in;
    this.prompt = prompt;
  }

  @Override
  public void execute() {
    Schedule schedule = new Schedule();

    schedule.setNo(prompt.inputInt("일정 번호를 입력하세요  "));
    schedule.setPlace(prompt.inputString("일정 장소를 입력하세요  "));
    schedule.setDescription(prompt.inputString("일정 내용을 입력하세요  "));
    schedule.setMemo(prompt.inputString("메모를 입력하세요 "));
    schedule.setCost(prompt.inputString("일정에 사용되는 비용을 입력하세요  "));
    schedule.setScheduleTime(prompt.inputString("일정시간을 입력하세요  "));

    try {
      out.writeUTF("/schedule/add");
      out.writeObject(schedule);
      out.flush();

      String response = in.readUTF();
      if (response.equals("FAIL")) {
        System.out.println(in.readUTF());
        return;
      }

      System.out.println("저장하였습니다.");

    } catch (Exception e) {
      System.out.println("통신 오류 발생!");
      e.printStackTrace();
    }
  }
}


