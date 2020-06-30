package com.jardin.shop11.dao;

import java.util.List;

import org.springframework.ui.Model;

import com.jardin.shop11.dto.BoardDto;

public interface BoardDao {

	public List<BoardDto> list(Model model);

}
