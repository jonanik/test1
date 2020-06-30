package com.jardin.shop11.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jardin.shop11.service.BoardService;

@Controller
public class BoardController {

	@Autowired
	BoardService boardService;

	@RequestMapping("list")
	public String list(Model model) {
		model.addAttribute("list", boardService.list(model));
		return "list";
	}

	@RequestMapping("join")
	public String join() {
		return "join/join";
	}

	@RequestMapping("joinOk")
	public String joinOk() {
		return "join/joinOk";
	}

}
