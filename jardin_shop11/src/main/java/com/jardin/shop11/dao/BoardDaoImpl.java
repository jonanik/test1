package com.jardin.shop11.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jardin.shop11.dto.BoardDto;
import com.jardin.shop11.dto.EventDto;
import com.jardin.shop11.dto.JoinDto;
import com.jardin.shop11.dto.LoginDto;
import com.jardin.shop11.dto.PagenationDto;
import com.jardin.shop11.dto.ReplyDto;
import com.jardin.shop11.dto.SearchValue;

@Repository
public class BoardDaoImpl implements BoardDao {

	@Autowired
	SqlSession sqlSession;

	// 리스트 출력 
	@Override
	public List<BoardDto> list() {

		return sqlSession.selectList("board.list");
	}

	// 회원가입 
	@Override
	public void join(JoinDto joinDto) {
		sqlSession.insert("board.join", joinDto);

	}
	
	//회원가입 아이디 중복체크
	@Override
	public int idCheck(String memId) {
		
		return sqlSession.selectOne("board.idCheck",memId);
	}

	// 로그인 체크 - 아이디와 비밀번호가 멤버테이블에 있는지 비교 확인하여 세션 부여
	@Override
	public LoginDto loginOk(LoginDto loginDto) {
		return sqlSession.selectOne("board.loginOk", loginDto);
	}

	// 이벤트 글 작성(insert)
	@Override
	public void eventWrite(EventDto eventDto) {
		sqlSession.insert("board.eventWrite", eventDto);
	}

	// 이벤트 리스트 출력
	@Override
	public List<EventDto> eventPageNationList(SearchValue sv) {
		return sqlSession.selectList("board.eventList",sv);
	}
	//이벤트 리스트 글 개수
	@Override
	public PagenationDto eventPageNation(SearchValue sv) {
		return sqlSession.selectOne("board.eventListNo",sv);
	}

	//이벤트 상세페이지
	@Override
	public EventDto eventView(EventDto eventDto) {
		
		return sqlSession.selectOne("board.eventView",eventDto);
	}

	//이벤트 댓글 쓰기
	@Override
	public void eventReplyWrite(ReplyDto replyDto) {
		sqlSession.insert("board.eventReplyWrite",replyDto);
	}
	//이벤트 댓글 리스트 출력(selectList)
	@Override
	public List<ReplyDto> replyList(int eventNo) {
		return sqlSession.selectList("board.eventReplyList",eventNo);
	}

	//이벤트 댓글 개수
	@Override
	public int replyCnt(int eventNo) {
		return sqlSession.selectOne("board.eventReplyCnt",eventNo);
	}
	
	//이벤트 댓글 삭제(delete)
	public void replyDelete(int replyNo) {
		sqlSession.delete("board.replyDelete",replyNo);
	}
	
	//이벤트 댓글 수정(update)
	public void replyUpdate(ReplyDto replyDto) {
		System.out.println("댓글 내용 수정: "+replyDto.getContent());
		System.out.println("댓글 번호"+replyDto.getReplyNo());
		System.out.println("이벤트 번호"+replyDto.getEventNo());
		sqlSession.update("board.replyUpdate",replyDto);
		
	}
	
	//이벤트 상세페이지 이전글
	@Override
	public EventDto prePost(EventDto eventDto) {
		return sqlSession.selectOne("board.prePost",eventDto);
	}
	
	//이벤트 상세페이지 다음글
	@Override
	public EventDto nextPost(EventDto eventDto) {
		return sqlSession.selectOne("board.nextPost",eventDto);
	}

}
