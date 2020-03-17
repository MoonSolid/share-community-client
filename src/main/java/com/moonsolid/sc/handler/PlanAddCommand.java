package com.moonsolid.sc.handler;

import com.moonsolid.sc.dao.PlanDao;
import com.moonsolid.sc.domain.Plan;
import com.moonsolid.util.Prompt;

public class PlanAddCommand implements Command {

  Prompt prompt;
  PlanDao planDao;

  public PlanAddCommand(PlanDao planDao, Prompt prompt) {
    this.planDao = planDao;
    this.prompt = prompt;
  }

  @Override
  public void execute() {
    Plan plan = new Plan();
    plan.setPlace(prompt.inputString("일정장소 : "));
    plan.setDescription(prompt.inputString("내용 : "));
    plan.setMemo(prompt.inputString("메모 : "));
    plan.setCost(prompt.inputString("비용 : "));

    try {
      planDao.insert(plan);
      System.out.println("저장하였습니다.");

    } catch (Exception e) {
      System.out.println("데이터 저장 실패");
    }
  }
}
