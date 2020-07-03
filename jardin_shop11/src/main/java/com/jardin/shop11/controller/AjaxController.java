package com.jardin.shop11.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jardin.shop11.dto.BoardDto;
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
	
	@RequestMapping("ajax_insert") // ajax -> json
	@ResponseBody // 제이슨 형태로 리턴해준다.
	public String ajax_insert(@RequestParam("bTitle") String bTitle,@RequestParam("bContent") String bContent,@RequestParam("bName") String bName) { //어노테이션을 붙일경우 데이터가 널일경우도 공백으로 쳐주기도하는 장점이 있음

		Map<String, Object> map = new HashMap<String, Object>();
		System.out.println("제목: "+bTitle);
		System.out.println("내용: "+bContent);
		System.out.println("이름: "+bName);

		
		return "success";
	}
}
