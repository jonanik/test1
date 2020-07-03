package com.jardin.shop11.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jardin.shop11.dto.BoardDto;
import com.jardin.shop11.dto.EventDto;
import com.jardin.shop11.dto.JoinDto;
import com.jardin.shop11.dto.LoginDto;
import com.jardin.shop11.dto.ReplyDto;

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
	public List<EventDto> eventList() {
		return sqlSession.selectList("board.eventList");
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
	public List<ReplyDto> replyList() {
		
		return sqlSession.selectList("board.eventReplyList");
	}
	
	//이벤트 댓글 삭제(delete)
	public void replyDelete(int replyNo) {
		sqlSession.delete("board.replyDelete",replyNo);
	}



}
