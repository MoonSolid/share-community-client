package com.moonsolid.sc.dao.proxy;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import com.moonsolid.sc.dao.PlanDao;
import com.moonsolid.sc.domain.Plan;

public class PlanDaoProxy implements PlanDao {

  DaoProxyHelper daoProxyHelper;

  public PlanDaoProxy(DaoProxyHelper daoProxyHelper) {
    this.daoProxyHelper = daoProxyHelper;
  }

  @Override
  public int insert(Plan plan) throws Exception {
    class InsertWorker implements Worker {
      @Override
      public Object execute(ObjectInputStream in, ObjectOutputStream out) throws Exception {
        out.writeUTF("/plan/add");
        out.writeObject(plan);
        out.flush();

        String response = in.readUTF();
        if (response.equals("FAIL")) {
          throw new Exception(in.readUTF());
        }
        return 1;
      }
    }

    InsertWorker worker = new InsertWorker();

    int resultState = (int) daoProxyHelper.request(worker);

    return resultState;
  }



  @SuppressWarnings("unchecked")
  @Override
  public List<Plan> findAll() throws Exception {
    Object result = daoProxyHelper.request(new Worker() {
      @Override
      public Object execute(ObjectInputStream in, ObjectOutputStream out) throws Exception {
        out.writeUTF("/plan/list");
        out.flush();
        String response = in.readUTF();
        if (response.equals("FAIL")) {
          throw new Exception(in.readUTF());
        }
        return in.readObject();
      }
    });
    return (List<Plan>) result;
  }

  @Override
  public Plan findByNo(int no) throws Exception {
    Object result = daoProxyHelper.request(new Worker() {
      @Override
      public Object execute(ObjectInputStream in, ObjectOutputStream out) throws Exception {
        out.writeUTF("/plan/detail");
        out.writeInt(no);
        out.flush();

        String response = in.readUTF();
        if (response.equals("FAIL")) {
          throw new Exception(in.readUTF());
        }
        return in.readObject();
      }
    });
    return (Plan) result;
  }


  @Override
  public int update(Plan plan) throws Exception {
    return (int) daoProxyHelper.request(new Worker() {
      @Override
      public Object execute(ObjectInputStream in, ObjectOutputStream out) throws Exception {
        out.writeUTF("/plan/update");
        out.writeObject(plan);
        out.flush();

        String response = in.readUTF();
        if (response.equals("FAIL")) {
          throw new Exception(in.readUTF());
        }
        return 1;
      }
    });
  }

  @Override
  public int delete(int no) throws Exception {

    return (int) daoProxyHelper.request((in, out) -> {
      out.writeUTF("/plan/delete");
      out.writeInt(no);
      out.flush();

      String response = in.readUTF();
      if (response.equals("FAIL")) {
        throw new Exception(in.readUTF());
      }
      return 1;
    });
  }

}
