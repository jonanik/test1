package com.jardin.shop11.service;

import java.util.List;

import org.springframework.ui.Model;

import com.jardin.shop11.dto.BoardDto;

public interface BoardService {

	public List<BoardDto> list(Model model);

}
