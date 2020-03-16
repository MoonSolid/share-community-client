package com.moonsolid.sc.handler;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import com.moonsolid.sc.domain.Plan;
import com.moonsolid.util.Prompt;

public class PlanAddCommand implements Command {

  ObjectOutputStream out;
  ObjectInputStream in;

  Prompt prompt;

  public PlanAddCommand(ObjectOutputStream out, ObjectInputStream in, Prompt prompt) {
    this.out = out;
    this.in = in;
    this.prompt = prompt;
  }

  @Override
  public void execute() {
    Plan plan = new Plan();

    plan.setNo(prompt.inputInt("일정번호 : "));
    plan.setPlace(prompt.inputString("일정장소 : "));
    plan.setDescription(prompt.inputString("내용 :"));
    plan.setMemo(prompt.inputString("메모 :"));
    plan.setCost(prompt.inputString("비용 :"));

    try {
      out.writeUTF("/plan/add");
      out.writeObject(plan);
      out.flush();

      String response = in.readUTF();
      if (response.equals("FAIL")) {
        System.out.println(in.readUTF());
        return;
      }

      System.out.println("저장하였습니다.");

    } catch (Exception e) {
      System.out.println("통신 오류 발생!");
    }
  }

}
