package com.moonsolid.sc.dao.mariadb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.moonsolid.sc.dao.PlanDao;
import com.moonsolid.sc.domain.Plan;

public class PlanDaoImpl implements PlanDao {

  @Override
  public int insert(Plan plan) throws Exception {
    Class.forName("org.mariadb.jdbc.Driver");

    try (Connection con = DriverManager.getConnection( //
        "jdbc:mariadb://localhost:3306/scdb", "study", "1111");
        Statement stmt = con.createStatement()) {

      int result = stmt.executeUpdate(
          "insert into sc_plan(place, cont, memo, cost) " + "values('" + plan.getPlace() + "', '" //
              + plan.getDescription() + "', '" //
              + plan.getMemo() + "', '" //
              + plan.getCost() + "')");

      return result;
    }
  }

  @Override
  public List<Plan> findAll() throws Exception {
    Class.forName("org.mariadb.jdbc.Driver");

    try (Connection con = DriverManager.getConnection( //
        "jdbc:mariadb://localhost:3306/scdb", "study", "1111");
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery( //
            "select plan_id, place, cont, memo, cost from sc_plan")) {

      ArrayList<Plan> list = new ArrayList<>();

      while (rs.next()) {
        Plan plan = new Plan();

        plan.setNo(rs.getInt("plan_id"));
        plan.setPlace(rs.getString("place"));
        plan.setDescription(rs.getString("cont"));
        plan.setMemo(rs.getString("memo"));
        plan.setCost(rs.getString("cost"));

        list.add(plan);
      }

      return list;
    }
  }

  @Override
  public Plan findByNo(int no) throws Exception {
    Class.forName("org.mariadb.jdbc.Driver");

    try (Connection con = DriverManager.getConnection( //
        "jdbc:mariadb://localhost:3306/scdb", "study", "1111");
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery( //
            "select plan_id, place, cont, memo, cost" + " from sc_plan" + " where plan_id=" + no)) {

      if (rs.next()) {
        Plan plan = new Plan();
        plan.setNo(rs.getInt("plan _id"));
        plan.setPlace(rs.getString("place"));
        plan.setDescription(rs.getString("cont"));
        plan.setMemo(rs.getString("memo"));
        plan.setCost(rs.getString("cost"));
        return plan;

      } else {
        return null;
      }
    }
  }

  @Override
  public int update(Plan plan) throws Exception {
    Class.forName("org.mariadb.jdbc.Driver");

    try (Connection con = DriverManager.getConnection( //
        "jdbc:mariadb://localhost:3306/scdb", "study", "1111");
        Statement stmt = con.createStatement()) {

      int result = stmt.executeUpdate("update sc_plan set place= '" //
          + plan.getPlace() + "', cont='" //
          + plan.getDescription() + "', memo='" //
          + plan.getMemo() + "', cost='" //
          + plan.getCost() + "' where plan_id=" + plan.getNo());

      return result;
    }
  }

  @Override
  public int delete(int no) throws Exception {
    Class.forName("org.mariadb.jdbc.Driver");

    try (Connection con = DriverManager.getConnection( //
        "jdbc:mariadb://localhost:3306/scdb", "study", "1111");
        Statement stmt = con.createStatement()) {

      int result = stmt.executeUpdate("delete from sc_plan where plan_id=" + no);

      return result;
    }
  }

}