package com.moonsolid.sc.handler;

import com.moonsolid.sc.dao.PlanDao;
import com.moonsolid.sc.domain.Plan;
import com.moonsolid.util.Prompt;

public class PlanDetailCommand implements Command {

  Prompt prompt;
  PlanDao planDao;

  public PlanDetailCommand(PlanDao planDao, Prompt prompt) {
    this.planDao = planDao;
    this.prompt = prompt;
  }

  @Override
  public void execute() {
    try {
      int no = prompt.inputInt("일정번호 : ");

      Plan plan = planDao.findByNo(no);

      System.out.printf("번호: %d\n", plan.getNo());
      System.out.printf("장소: %s\n", plan.getPlace());
      System.out.printf("내용: %s\n", plan.getDescription());
      System.out.printf("메모: %d\n", plan.getMemo());
      System.out.printf("비용: %d\n", plan.getCost());



    } catch (Exception e) {
      System.out.println("조회 실패");
    }
  }

}
