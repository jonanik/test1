package com.jardin.shop11.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

import com.jardin.shop11.dto.BoardDto;
import com.jardin.shop11.dto.JoinDto;
import com.jardin.shop11.dto.LoginDto;

public interface BoardService {

	// 리스트 출력
	public List<BoardDto> list(Model model);

	// 회원가입
	public void join(JoinDto joinDto);

	// 로그인 체크 - 아이디와 비밀번호가 멤버테이블에 있는지 비교 확인하여 세션 부여
	public void loginOk(LoginDto loginDto, HttpSession session);
}
