package com.moonsolid.sc.handler;

import java.util.List;
import com.moonsolid.sc.dao.PlanDao;
import com.moonsolid.sc.domain.Plan;

public class PlanListCommand implements Command {

  PlanDao planDao;

  public PlanListCommand(PlanDao planDao) {
    this.planDao = planDao;
  }

  @Override
  public void execute() {
    try {
      List<Plan> plans = planDao.findAll();
      for (Plan p : plans) {
        System.out.printf("%d, %s, %s, %s, %s\n", p.getNo(), p.getPlace(), p.getDescription(),
            p.getMemo(), p.getCost());
      }

    } catch (Exception e) {
      System.out.println("목록 조회 실패");
    }
  }


}
