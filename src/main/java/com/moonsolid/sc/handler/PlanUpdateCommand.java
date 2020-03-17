package com.moonsolid.sc.handler;

import com.moonsolid.sc.dao.PlanDao;
import com.moonsolid.sc.domain.Plan;
import com.moonsolid.util.Prompt;

public class PlanUpdateCommand implements Command {

  Prompt prompt;
  PlanDao planDao;

  public PlanUpdateCommand(PlanDao planDao, Prompt prompt) {
    this.planDao = planDao;
    this.prompt = prompt;
  }

  @Override
  public void execute() {
    try {
      int no = prompt.inputInt("일정번호 : ");
      Plan oldPlan = null;
      try {
        oldPlan = planDao.findByNo(no);
      } catch (Exception e) {
        System.out.println("해당 번호의 일정이 없습니다");
        return;
      }


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

      if (newPlan.getDescription().equals(oldPlan.getDescription())) {
        System.out.println("일정 변경을 취소하였습니다.");
        return;
      }

      planDao.update(newPlan);
      System.out.println("일정을 변경했습니다.");

    } catch (Exception e) {
      System.out.println("변경 실패");
    }
  }
}
