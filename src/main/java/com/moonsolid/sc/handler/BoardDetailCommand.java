package com.moonsolid.sc.handler;

import com.moonsolid.sc.dao.BoardDao;
import com.moonsolid.sc.domain.Board;
import com.moonsolid.util.Prompt;

public class BoardDetailCommand implements Command {

  Prompt prompt;
  BoardDao boardDao;

  public BoardDetailCommand(BoardDao boardDao, Prompt prompt) {
    this.boardDao = boardDao;
    this.prompt = prompt;
  }

  @Override
  public void execute() {
    try {
      int no = prompt.inputInt("번호? ");

      Board board = boardDao.findByNo(no);

      System.out.printf("번호: %d\n", board.getNo());
      System.out.printf("제목: %s\n", board.getTitle());
      System.out.printf("등록일: %s\n", board.getDate());
      System.out.printf("조회수: %d\n", board.getViewCount());

    } catch (Exception e) {
      System.out.println("조회 실패");
    }
  }
}


