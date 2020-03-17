package com.moonsolid.sc.dao.proxy;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class DaoProxyHelper {
  String host;
  int port;

  public DaoProxyHelper(String host, int port) {
    this.host = host;
    this.port = port;
  }

  DaoProxyHelper daoProxyHelper;

  public DaoProxyHelper(DaoProxyHelper daoProxyHelper) {
    this.daoProxyHelper = daoProxyHelper;

  }

  public Object request(Worker worker) throws Exception {
    try (Socket socket = new Socket(host, port);
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {
      return worker.execute(in, out);
    }
  }
}
