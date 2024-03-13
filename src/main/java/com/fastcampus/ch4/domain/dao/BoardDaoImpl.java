package com.fastcampus.ch4.domain.dao;

import com.fastcampus.ch4.domain.BoardDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class BoardDaoImpl implements BoardDao {
    @Autowired
    SqlSession session;

    String namespace="com.fastcampus.ch4.dao.BoardMapper.";

    public int count() throws Exception {
        return session.selectOne(namespace+"count");
    } // T selectOne(String statement)


    @Override
    public BoardDto select(int bno) throws Exception{
       return session.selectOne(namespace+ "select", bno);
    }

    @Override
    public int deleteAll(){
        return session.delete(namespace+"deleteAll");
    }

    @Override
    public int delete(Integer bno , String writer) throws Exception {
        Map map = new HashMap();
        map.put("bno", bno);
        map.put("writer", writer);
       return session.delete(namespace+"delete",map);
    }
}
