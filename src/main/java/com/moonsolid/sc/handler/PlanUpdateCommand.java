package com.moonsolid.sc.handler;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import com.moonsolid.sc.domain.Plan;
import com.moonsolid.util.Prompt;

public class PlanUpdateCommand implements Command {

  ObjectOutputStream out;
  ObjectInputStream in;

  Prompt prompt;

  public PlanUpdateCommand(ObjectOutputStream out, ObjectInputStream in, Prompt prompt) {
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

      Plan oldPlan = (Plan) in.readObject();
      Plan newPlan = new Plan();

      newPlan.setNo(oldPlan.getNo());

      newPlan.setPlace(prompt.inputString(String.format("장소를 입력하세요 : (기존장소 : %s) ", //
          oldPlan.getPlace()), oldPlan.getPlace()));

      newPlan.setDescription(prompt.inputString(String.format("내용을 입력하세요 : (기존내용 : %s)", //
          oldPlan.getDescription()), oldPlan.getDescription()));

      newPlan.setMemo(prompt.inputString(String.format("메모을 입력하세요 : (기존메모 : %s)", //
          oldPlan.getMemo()), oldPlan.getMemo()));

      newPlan.setCost(prompt.inputString(String.format("비용을 입력하세요 : (기존비용 : %s)", //
          oldPlan.getCost()), oldPlan.getCost()));

      if (oldPlan.equals(newPlan)) {
        System.out.println("일정 변경을 취소하였습니다.");
        return;
      }
      out.writeUTF("/plan/update");
      out.writeObject(newPlan);
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
