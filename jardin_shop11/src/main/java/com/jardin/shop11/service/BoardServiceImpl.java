package com.jardin.shop11.service;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.jardin.shop11.dao.BoardDao;
import com.jardin.shop11.dto.BoardDto;
import com.jardin.shop11.dto.EventDto;
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

	// 이벤트
	@Override
	public void eventWrite(MultipartHttpServletRequest multi, EventDto eventDto) {
		String title = multi.getParameter("title");
		String content = multi.getParameter("content");
		String startDate = multi.getParameter("startDate");
		String endDate = multi.getParameter("endDate");
		MultipartFile mpf1 = multi.getFile("thumbnail");
		MultipartFile mpf2 = multi.getFile("eventImage");

		String path = "C:/eventImage/";

		String filename1 = mpf1.getOriginalFilename();
		String filename2 = mpf2.getOriginalFilename();

		String thumbnail = System.currentTimeMillis() + filename1;
		String eventImage = System.currentTimeMillis() + filename2;

		try {
			mpf1.transferTo(new File(path + thumbnail));
			mpf2.transferTo(new File(path + content));
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		eventDto.setTitle(title);
		eventDto.setContent(content);
		eventDto.setTitle(startDate);
		eventDto.setTitle(endDate);
		eventDto.setTitle(thumbnail);
		eventDto.setTitle(eventImage);

		boardDao.eventWrite(eventDto);

	}

}
