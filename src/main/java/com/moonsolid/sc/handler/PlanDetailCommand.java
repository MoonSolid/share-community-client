package com.moonsolid.sc.handler;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import com.moonsolid.sc.domain.Plan;
import com.moonsolid.util.Prompt;

public class PlanDetailCommand implements Command {

  ObjectOutputStream out;
  ObjectInputStream in;

  Prompt prompt;

  public PlanDetailCommand(ObjectOutputStream out, ObjectInputStream in, Prompt prompt) {
    this.out = out;
    this.in = in;
    this.prompt = prompt;
  }

  @Override
  public void execute() {
    try {
      int no = prompt.inputInt("일정번호 : ");

      out.writeUTF("/plan/detail");
      out.writeInt(no);
      out.flush();

      String response = in.readUTF();

      if (response.equals("FAIL")) {
        System.out.println(in.readUTF());
        return;
      }

      Plan plan = (Plan) in.readObject();
      System.out.printf("번호: %d\n", plan.getNo());
      System.out.printf("장소: %s\n", plan.getPlace());
      System.out.printf("내용: %s\n", plan.getDescription());
      System.out.printf("메모: %d\n", plan.getMemo());
      System.out.printf("비용: %d\n", plan.getCost());



    } catch (Exception e) {
      System.out.println("명령 실행 중 오류 발생!");
    }
  }

}
