package com.moonsolid.sc.handler;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import com.moonsolid.sc.domain.Schedule;

public class ScheduleListCommand implements Command {

  ObjectOutputStream out;
  ObjectInputStream in;

  public ScheduleListCommand(ObjectOutputStream out, ObjectInputStream in) {
    this.out = out;
    this.in = in;
  }

  @SuppressWarnings("unchecked")
  @Override
  public void execute() {
    try {
      out.writeUTF("/schedule/list");
      out.flush();

      String response = in.readUTF();
      if (response.equals("FAIL")) {
        System.out.println(in.readUTF());
        return;
      }

      List<Schedule> schedules = (List<Schedule>) in.readObject();
      for (Schedule s : schedules) {
        System.out.printf("%d, %s, %s , %s, %s ,%s\n", s.getNo(), s.getPlace(), s.getDescription(),
            s.getMemo(), s.getCost(), s.getScheduleTime());
      }
    } catch (Exception e) {
      System.out.println("통신 오류 발생!");
    }
  }
}


