package com.moonsolid.sc.handler;

import com.moonsolid.sc.dao.BoardDao;
import com.moonsolid.sc.domain.Board;
import com.moonsolid.util.Prompt;

public class BoardAddCommand implements Command {

  Prompt prompt;
  BoardDao boardDao;

  public BoardAddCommand(BoardDao boardDao, Prompt prompt) {
    this.boardDao = boardDao;
    this.prompt = prompt;
  }

  @Override
  public void execute() {
    Board board = new Board();
    board.setTitle(prompt.inputString("내용? "));

    try {
      boardDao.insert(board);
      System.out.println("저장하였습니다.");

    } catch (Exception e) {
      System.out.println("데이터 저장 실패");
    }
  }
}


