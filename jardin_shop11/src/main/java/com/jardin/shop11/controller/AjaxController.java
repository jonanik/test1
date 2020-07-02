package com.jardin.shop11.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jardin.shop11.service.BoardService;

@Controller
public class AjaxController {

	@Autowired
	BoardService boardService;

	@RequestMapping("ajax_page")
	public String ajax_page() {
		return "ajax_page";
	}

	@RequestMapping("ajax_view") // ajax -> json
	@ResponseBody // 제이슨 형태로 리턴해준다.
	public Map<String, Object> ajax_view() {

		Map<String, Object> map = new HashMap<String, Object>();
		// dao->dto 1개의 데이터를 가지고 와서 map에 put에서 넣을 거임
		map.put("bNum", 1001);
		map.put("bName", "홍길동");
		map.put("bTitle", "게시글 제목");
		map.put("bContent", "게시판 글입니다.");
		// 리스트 출력
		map.put("ajaxname", "mvc게시판");
		map.put("list", boardService.list());//

		return map;
	}
}
