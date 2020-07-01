package com.jardin.shop11.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jardin.shop11.dto.JoinDto;
import com.jardin.shop11.dto.LoginDto;
import com.jardin.shop11.service.BoardService;

@Controller
public class BoardController {

	@Autowired
	BoardService boardService;

	// 리스트 출력
	@RequestMapping("list")
	public String list(Model model) {
		model.addAttribute("list", boardService.list(model));
		return "list";
	}

	// 회원가입 폼 이동
	@RequestMapping("joinOk")
	public String join(JoinDto joinDto) {
		boardService.join(joinDto);
		return "join/joinOk";
	}

	// 회원가입 완료페이지 불러오기
	@RequestMapping("join")
	public String joinOk() {
		return "join/join";
	}

	// 로그인 페이지로 이동
	@RequestMapping("login")
	public String login() {
		return "login/login";
	}

	// 로그인 체크 - 아이디와 비밀번호가 멤버테이블에 있는지 비교 확인하여 세션 부여
	@RequestMapping("loginOk")
	public String loginOk(LoginDto loginDto, HttpSession session, Model model) {
		boardService.loginOk(loginDto, session);
		model.addAttribute("memId", (String) session.getAttribute("memId"));
		return "main/main";
	}

	// 이벤트리스트 출력
	@RequestMapping("event")
	public String eventList() {
		return "event/event";
	}

	@RequestMapping("eventView")
	public String eventView() {
		return "event/eventView";
	}

}
