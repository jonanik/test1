package com.jardin.shop11.dao;

import java.util.List;

import com.jardin.shop11.dto.BoardDto;
import com.jardin.shop11.dto.EventDto;
import com.jardin.shop11.dto.JoinDto;
import com.jardin.shop11.dto.LoginDto;
import com.jardin.shop11.dto.PagenationDto;
import com.jardin.shop11.dto.ReplyDto;
import com.jardin.shop11.dto.SearchValue;

public interface BoardDao {

	// 리스트 출력 test
	public List<BoardDto> list();

	// 회원가입 test
	public void join(JoinDto joinDto);

	//회원가입 아이디 중복체크
	public int idCheck(String memId);
	
	// 로그인 체크 - 아이디와 비밀번호가 멤버테이블에 있는지 비교 확인하여 세션 부여
	public LoginDto loginOk(LoginDto loginDto);

	// 이벤트 글 작성(insert)
	public void eventWrite(EventDto eventDto);

	// 이벤트 리스트 가져오기
	public List<EventDto> eventPageNationList(SearchValue sv);
		
	//이벤트 리스트 글 개수
	public PagenationDto eventPageNation(SearchValue sv);
	
	//이벤트 상세페이지 출력
	public EventDto eventView(EventDto eventDto);
	
	//이벤트 댓글 쓰기
	public void eventReplyWrite(ReplyDto replyDto);
	
	//이벤트 댓글 리스트 가져오기(selectList)
	public List<ReplyDto> replyList(int eventNo);
	
	//이벤트 댓글 갯수 가져오기
	public int replyCnt(int eventNo);
	
	//이벤트 댓글 삭제
	public void replyDelete(int replyDelete);
	
	//이벤트 댓글 수정
	public void replyUpdate(ReplyDto replyDto);
	
	//이벤트 상세페이지 이전글
	public EventDto prePost(EventDto eventDto); 
		
	//이벤트 상세페이지 다음글
	public EventDto nextPost(EventDto eventDto); 
}
