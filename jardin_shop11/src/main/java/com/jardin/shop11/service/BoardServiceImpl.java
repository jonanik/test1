package com.jardin.shop11.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.jardin.shop11.dao.BoardDao;
import com.jardin.shop11.dto.BoardDto;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	BoardDao boardDao;

	@Override
	public List<BoardDto> list(Model model) {

		return boardDao.list(model);
	}

}
