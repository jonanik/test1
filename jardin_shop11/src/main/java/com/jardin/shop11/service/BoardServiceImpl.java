package com.jardin.shop11.service;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jardin.shop11.dao.BoardDao;
import com.jardin.shop11.dto.BoardDto;
import com.jardin.shop11.dto.EventDto;
import com.jardin.shop11.dto.JoinDto;
import com.jardin.shop11.dto.LoginDto;
import com.jardin.shop11.dto.ReplyDto;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	BoardDao boardDao;

	// 리스트 출력 test
	@Override
	public List<BoardDto> list() {

		return boardDao.list();
	}

	// 회원가입 test
	@Override
	public void join(JoinDto joinDto) {
		boardDao.join(joinDto);

	}

	@Override
	public int idCheck(String memId) {
		
		return boardDao.idCheck(memId);
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

	// 이벤트
	@Override
	public void eventWrite(List<MultipartFile> multi, EventDto eventDto) throws Exception {
		// 실제파일을 받아온다
		MultipartFile mpf1 = multi.get(0);
		MultipartFile mpf2 = multi.get(1);
		// 파일경로설정
		String path = "C:/Users/yohan/git/test1/jardin_shop11/src/main/webapp/resources/eventImage/";

		// 파일의 이름을 String형식으로 가져온다.
		String filename1 = mpf1.getOriginalFilename();
		String filename2 = mpf2.getOriginalFilename();

		// db에 올리기전에 현재 밀리세컨즈를 붙여서 이름을 정해준다.
		String thumbnail = System.currentTimeMillis() + filename1;
		String eventImage = System.currentTimeMillis() + filename2;

		// 실제로 파일을 경로에 따른 폴터에 보내준다.
		mpf1.transferTo(new File(path + thumbnail));
		mpf2.transferTo(new File(path + eventImage));

		eventDto.setThumbnail(thumbnail);
		eventDto.setEventImage(eventImage);

		boardDao.eventWrite(eventDto);

	}

	// 이벤트 리스트 출력
	public List<EventDto> eventList() {

		return boardDao.eventList();
	}

	//이벤트 상세페이지 출력
	@Override
	public EventDto eventView(EventDto eventDto) {
		
		return boardDao.eventView(eventDto);
	}

	//이벤트 댓글 쓰기(insert)
	@Override
	public void eventReplyWrite(ReplyDto replyDto) {
		 boardDao.eventReplyWrite(replyDto);
	}
	
	//이벤트 댓글 리스트 가져오기(selectList)
	@Override
	public List<ReplyDto> replyList(int eventNo) {
		return boardDao.replyList(eventNo);
	}

	//이벤트 댓글 개수
		@Override
		public int replyCnt(int eventNo) {
			return boardDao.replyCnt(eventNo);
		}
	
	//이벤트 댓글 삭제
	@Override
	public void replyDelete(int replyNo) {
		 boardDao.replyDelete(replyNo);
	}
	
	//이벤트 댓글 수정
	@Override
	public void replyUpdate(ReplyDto replyDto) {
		boardDao.replyUpdate(replyDto);
		
	}
	
	//이벤트 댓글 이전글
	@Override
	public EventDto prePost(EventDto eventDto) {
		return boardDao.prePost(eventDto);
	}

	//이벤트 댓글 다음글
	@Override
	public EventDto nextPost(EventDto eventDto) {
		return boardDao.nextPost(eventDto);
	}

}
