package com.fastcampus.ch4.domain.dao;

import com.fastcampus.ch4.domain.BoardDto;

public interface BoardDao {
    BoardDto select(int bno) throws Exception;

    int deleteAll();

    int count()throws Exception;

    int delete(Integer bno, String writer) throws Exception;
}
