package com.jardin.shop11.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;

import com.jardin.shop11.dto.BoardDto;

@Repository
public class BoardDaoImpl implements BoardDao {

	@Autowired
	SqlSession sqlSession;

	@Override
	public List<BoardDto> list(Model model) {

		return sqlSession.selectList("board.list");
	}

}
