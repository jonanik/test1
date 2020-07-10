package com.jardin.shop11.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jardin.shop11.dto.EventDto;
import com.jardin.shop11.dto.JoinDto;
import com.jardin.shop11.dto.LoginDto;
import com.jardin.shop11.dto.PagenationDto;
import com.jardin.shop11.dto.ReplyDto;
import com.jardin.shop11.service.BoardService;


@Controller
public class BoardController {

	@Autowired
	BoardService boardService;
//-----------------------------------------------------------------------------------------
	@RequestMapping("main")
	public String main(Model model,HttpSession session) {
		String memId=(String)session.getAttribute("memId");
		System.out.println("세션이 들어갔을까: "+memId);
		model.addAttribute("memId",memId);
		return "main/main";
	}
//-----------------------------------------------------------------------------------------
	// 리스트 출력
	@RequestMapping("list")
	public String list(Model model) {
		model.addAttribute("list", boardService.list());
		return "list";
	}
//-----------------------------------------------------------------------------------------
	// 회원가입 폼 이동
	@RequestMapping("joinOk")
	public String join(JoinDto joinDto) {
		boardService.join(joinDto);
		return "join/joinOk";
	}
	//아이디 중복체크
	@RequestMapping("ajaxIdCheck")
	@ResponseBody
	public int idCheck(String memId) {
		System.out.println("컨트롤러 멤버 아이디: "+memId);
		return boardService.idCheck(memId);
	}

	// 회원가입 완료페이지 불러오기
	@RequestMapping("join")
	public String joinOk() {
		return "join/join";
	}
	
//-----------------------------------------------------------------------------------------
	// 로그인 페이지로 이동
	@RequestMapping("login")
	public String login() {
		return "login/login";
	}

	// 로그인 체크 - 아이디와 비밀번호가 멤버테이블에 있는지 비교 확인하여 세션 부여
	@RequestMapping("loginOk")
	@ResponseBody
	public LoginDto loginOk(LoginDto loginDto, HttpSession session, Model model) {
		return boardService.loginOk(loginDto, session);
	}
	//로그아웃
	@RequestMapping("logout")
	public String logout(HttpSession session) {
		System.out.println("로그아웃 한다.");
		session.invalidate();
		return "main/main";
	}
//-----------------------------------------------------------------------------------------

	// 이벤트글 작성 페이지(관리자페이지 대신)
	@RequestMapping("eventWriteForm")
	public String eventWriteForm() {
		return "event/eventWrite";
	}

	// 이벤트 글 작성(insert)
	@RequestMapping("eventWrite")
	public String eventWrite(List<MultipartFile> multi, EventDto eventDto) throws Exception {
		boardService.eventWrite(multi, eventDto);
		return "redirect:event";
	}

	// 이벤트 글 리스트 출력,검색,페이징
	@RequestMapping("event")
	public String eventList(com.jardin.shop11.dto.SearchValue sv,Model model) {
		PagenationDto PDto = boardService.eventPageNation(sv); // 게시글 수 저장
		List<EventDto> eventlist = boardService.eventPageNationList(sv);
		String path="";
		if(sv.getEventType().equals("event")) {
			path="event/event";
		}else if(sv.getEventType().equals("finEvent")) {
			path="event/finEvent";
		}
		model.addAttribute("eventList", eventlist);
		model.addAttribute("pDto", PDto);
		model.addAttribute("sv", sv);
		
			return path;
		}
	
	// 이벤트글 상세페이지
	@RequestMapping("eventView")
	public String eventView(EventDto eventDto,Model model,HttpSession session) {
		String memId=(String)session.getAttribute("memId");
		eventDto =boardService.eventView(eventDto);
		model.addAttribute("eventView",boardService.eventView(eventDto));
		model.addAttribute("memId",memId);

		return "event/eventView";
	}
	//이벤트글 상세페이지 이전글 다음글
	@RequestMapping("preNextPost")
	@ResponseBody
	public Map<String, Object> preNextPost(EventDto eventDto) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("prePost", boardService.prePost(eventDto));
		map.put("nextPost", boardService.nextPost(eventDto));
		return map;
	}
	 
	//
	
//-----------------------------------------------------------------------------------------
	//댓글 쓰기(ajax)
	@RequestMapping("eventReply")
	@ResponseBody
	public void eventReply(ReplyDto replyDto,RedirectAttributes redirect) {
		boardService.eventReplyWrite(replyDto);
	}
	//댓글 리스트 가져오기
	@RequestMapping("replyList")
	@ResponseBody
	public List<ReplyDto> replyList(int eventNo){
		return boardService.replyList(eventNo);
	}
	
	//댓글 개수
	@RequestMapping("replyCnt")
	@ResponseBody
	public int replyCnt(int eventNo) {
		return boardService.replyCnt(eventNo);
	}
	
	//댓글 삭제
	@RequestMapping("replyDelete")
	@ResponseBody
	public void replyDelete(int replyNo){
	 boardService.replyDelete(replyNo);
	}
	
	//댓글 수정
	@RequestMapping("replyUpdate")
	@ResponseBody
	public int replyUpdate(ReplyDto replyDto) {
		int check=1;
		
		try {
			System.out.println("컨트롤러 댓글 번호"+replyDto.getReplyNo());
			System.out.println("컨트롤러 이벤트 번호"+replyDto.getEventNo());
			boardService.replyUpdate(replyDto);
		} catch (Exception e) {
			check=0;
		}
		return check;
	}

}
