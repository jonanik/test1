package com.jardin.shop11.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;

import com.jardin.shop11.dto.BoardDto;
import com.jardin.shop11.dto.JoinDto;
import com.jardin.shop11.dto.LoginDto;

@Repository
public class BoardDaoImpl implements BoardDao {

	@Autowired
	SqlSession sqlSession;

	// 리스트 출력 test
	@Override
	public List<BoardDto> list(Model model) {

		return sqlSession.selectList("board.list");
	}

	// 회원가입 test
	@Override
	public void join(JoinDto joinDto) {
		sqlSession.insert("board.join", joinDto);

	}

	// 로그인 체크 - 아이디와 비밀번호가 멤버테이블에 있는지 비교 확인하여 세션 부여
	@Override
	public LoginDto loginOk(LoginDto loginDto) {
		return sqlSession.selectOne("board.loginOk", loginDto);
	}

}
