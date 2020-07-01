package com.jardin.shop11.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.jardin.shop11.dao.BoardDao;
import com.jardin.shop11.dto.BoardDto;
import com.jardin.shop11.dto.JoinDto;
import com.jardin.shop11.dto.LoginDto;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	BoardDao boardDao;

	// 리스트 출력 test
	@Override
	public List<BoardDto> list(Model model) {

		return boardDao.list(model);
	}

	// 회원가입 test
	@Override
	public void join(JoinDto joinDto) {
		boardDao.join(joinDto);

	}

	// 로그인 체크 - 아이디와 비밀번호가 멤버테이블에 있는지 비교 확인하여 세션 부여
	@Override
	public void loginOk(LoginDto loginDto, HttpSession session) {
		int loginCheck = boardDao.loginOk(loginDto).getLoginCheck();
		if (loginCheck == 1) {
			session.setAttribute("memId", loginDto.getMemId());
		} else if (loginCheck == 0) {

		}

	}

}
