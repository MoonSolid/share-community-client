package com.moonsolid.sc.dao.proxy;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import com.moonsolid.sc.dao.PlanDao;
import com.moonsolid.sc.domain.Plan;

public class PlanDaoProxy implements PlanDao {

  ObjectInputStream in;
  ObjectOutputStream out;

  public PlanDaoProxy(ObjectInputStream in, ObjectOutputStream out) {
    this.in = in;
    this.out = out;
  }

  @Override
  public int insert(Plan plan) throws Exception {
    out.writeUTF("/plan/add");
    out.writeObject(plan);
    out.flush();

    String response = in.readUTF();
    if (response.equals("FAIL")) {
      throw new Exception(in.readUTF());
    }
    return 1;
  }


  @SuppressWarnings("unchecked")
  @Override
  public List<Plan> findAll() throws Exception {
    out.writeUTF("/plan/list");
    out.flush();
    String response = in.readUTF();
    if (response.equals("FAIL")) {
      throw new Exception(in.readUTF());
    }
    return (List<Plan>) in.readObject();
  }

  @Override
  public Plan findByNo(int no) throws Exception {
    out.writeUTF("/plan/detail");
    out.writeInt(no);
    out.flush();

    String response = in.readUTF();
    if (response.equals("FAIL")) {
      throw new Exception(in.readUTF());
    }
    return (Plan) in.readObject();
  }

  @Override
  public int update(Plan plan) throws Exception {
    out.writeUTF("/plan/update");
    out.writeObject(plan);
    out.flush();

    String response = in.readUTF();
    if (response.equals("FAIL")) {
      throw new Exception(in.readUTF());
    }
    return 1;
  }

  @Override
  public int delete(int no) throws Exception {
    out.writeUTF("/plan/delete");
    out.writeInt(no);
    out.flush();

    String response = in.readUTF();
    if (response.equals("FAIL")) {
      throw new Exception(in.readUTF());
    }
    return 1;
  }

}
