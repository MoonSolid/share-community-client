package com.moonsolid.sc.handler;

import java.sql.Date;
import com.moonsolid.sc.dao.BoardDao;
import com.moonsolid.sc.domain.Board;
import com.moonsolid.util.Prompt;

public class BoardUpdateCommand implements Command {

  Prompt prompt;
  BoardDao boardDao;

  public BoardUpdateCommand(BoardDao boardDao, Prompt prompt) {
    this.boardDao = boardDao;
    this.prompt = prompt;
  }

  @Override
  public void execute() {
    try {
      int no = prompt.inputInt("번호? ");
      Board oldBoard = null;
      try {
        oldBoard = boardDao.findByNo(no);
      } catch (Exception e) {
        System.out.println("해당 번호의 게시글이 없습니다");
        return;
      }

      Board newBoard = new Board();

      newBoard.setNo(oldBoard.getNo());
      newBoard.setViewCount(oldBoard.getViewCount());
      newBoard.setDate(new Date(System.currentTimeMillis()));
      newBoard.setTitle(
          prompt.inputString(String.format("내용(%s)? ", oldBoard.getTitle()), oldBoard.getTitle()));

      if (newBoard.equals(oldBoard)) {
        System.out.println("게시글 변경을 취소했습니다.");
        return;
      }

      boardDao.update(newBoard);
      System.out.println("게시글을 변경했습니다.");

    } catch (Exception e) {
      System.out.println("변경 실패");
    }
  }
}


