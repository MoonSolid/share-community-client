package com.moonsolid.sc.handler;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import com.moonsolid.sc.domain.Plan;

public class PlanListCommand implements Command {

  ObjectOutputStream out;
  ObjectInputStream in;


  public PlanListCommand(ObjectOutputStream out, ObjectInputStream in) {
    this.out = out;
    this.in = in;
  }

  @SuppressWarnings("unchecked")
  @Override
  public void execute() {
    try {
      out.writeUTF("/plan/list");

      out.flush();

      String response = in.readUTF();
      if (response.equals("FAIL")) {
        System.out.println(in.readUTF());
        return;
      }

      List<Plan> plans = (List<Plan>) in.readObject();
      for (Plan p : plans) {
        System.out.printf("%d, %s, %s, %s, %s\n", p.getNo(), p.getPlace(), p.getDescription(),
            p.getMemo(), p.getCost());
      }

    } catch (Exception e) {
      System.out.println("통신 오류 발생!");
    }
  }


}
