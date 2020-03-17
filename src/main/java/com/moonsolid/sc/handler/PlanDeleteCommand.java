package com.moonsolid.sc.handler;

import com.moonsolid.sc.dao.PlanDao;
import com.moonsolid.util.Prompt;

public class PlanDeleteCommand implements Command {

  Prompt prompt;
  PlanDao planDao;

  public PlanDeleteCommand(PlanDao planDao, Prompt prompt) {
    this.planDao = planDao;
    this.prompt = prompt;
  }

  @Override
  public void execute() {
    try {
      int no = prompt.inputInt("일정번호 : ");
      planDao.delete(no);
      System.out.println("일정을 삭제했습니다.");

    } catch (Exception e) {
      System.out.println("삭제 실패");
    }
  }

}