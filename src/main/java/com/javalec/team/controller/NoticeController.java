package com.javalec.team.controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.javalec.team.dto.Criteria;
import com.javalec.team.dto.NoticeDto;
import com.javalec.team.dto.pageMakerDto;
import com.javalec.team.service.NoticeService;

@Controller
public class NoticeController {
	
	@Autowired
	HttpSession session;
	
	@Autowired
	private NoticeService service;
	
	@RequestMapping("/noticeList")
	public String list(Model model, Criteria cri) {
		System.out.println("@@@### NoticeController list() start");
		
//		ArrayList<NoticeDto> dtos = service.listPaging(cri);
//		model.addAttribute("list", dtos);
		model.addAttribute("list", service.listPaging(cri));
		
		int total = service.getTotal();
		
		pageMakerDto pageMake = new pageMakerDto(cri, total);
		
		model.addAttribute("pageMaker", pageMake);
		
		System.out.println("@@@### NoticeController list() end");
		
		return "/notice/list";
	}
	
//	@RequestMapping("/notice/list")
//	public String list(@RequestParam HashMap<String, String> param, Model model) {
//		System.out.println("@@@### NoticeController list() start");
//		
//		ArrayList<NoticeDto> dtos = service.list(param);
//		model.addAttribute("list", dtos);
//		
//		System.out.println("@@@### NoticeController list() end");
//		
//		return "/notice/list";
//	}
	
	@RequestMapping("/noticeWrite_view")
	public String write_view(Model model) {
		System.out.println("@@@### write_view()");
		
		int num = service.num();
		
		System.out.println("Num ===>"+num);
		
		model.addAttribute("num", num);
		
		return "/notice/write";
	}
	
	@RequestMapping("/noticeWrite")
	public String write(@RequestParam HashMap<String, String> param) {
		System.out.println("@@@### NoticeController write() start");
		
		int num = service.getncode();
		num+=1;
		param.put("n_code", Integer.toString(num));
		
		service.write(param);
		
		System.out.println("@@@### NoticeController write() end");
		
		return "redirect:noticeList";
	}
	
	@RequestMapping("/noticeShow")
	public String show(@RequestParam HashMap<String, String> param, Model model) {
		System.out.println("@@@### NoticeController show() start");
		
		System.out.println("@@@### NoticeController upView() start");
		
		service.upView(param); //�Խñ��� Ŭ���ϸ� ��ȸ���� �ø�
		
		System.out.println(session.getAttribute("u_auth"));
		
		System.out.println("@@@### NoticeController upView() end");
		
		NoticeDto dto = service.show(param);
		model.addAttribute("show", dto);
		
		System.out.println("@@@### NoticeController show() end");
		
		return "/notice/show";
	}
	
	@RequestMapping("/noticeDelete")
	public String delete(@RequestParam HashMap<String, String> param) {
		System.out.println("@@@### NoticeController delete() start");
		
		service.delete(param);
		
		System.out.println("@@@### NoticeController delete() end");
		
		return "redirect:noticeList";
	}
	
	@RequestMapping("/noticeModify_view")
	public String modify_view(@RequestParam HashMap<String, String> param, Model model) {
		System.out.println("@@@### modify_view()");
		
		NoticeDto dto = service.show(param);
		model.addAttribute("show", dto);
		
		return "/notice/modify";
	}
	
	@RequestMapping(value = "/noticeModify")
	public String modify(@RequestParam HashMap<String, String> param, Model model) {
		System.out.println("@@@### NoticeController modify() start");
		
		service.modify(param);
		
		System.out.println("@@@### NoticeController modify() end");
		
		return "redirect:noticeList";
	}
	
	@RequestMapping("/noticeSearch")
	public String search(HttpServletRequest request, Model model, Criteria cri) {
		System.out.println("@@@### NoticeController search() start");
		
		request.getParameter("kind");
		if (request.getParameter("kind").equals("����")) {
			ArrayList<NoticeDto> dtos = service.searchTitle(cri);
			model.addAttribute("list", dtos);
			
//			Assertions.assertThat("kind").isSameAs("");
		} else {
			ArrayList<NoticeDto> dtos = service.searchContent(cri);
			model.addAttribute("list", dtos);
		}
		
		int total = service.getTotal();
		
		pageMakerDto pageMake = new pageMakerDto(cri, total);
		
		model.addAttribute("pageMaker", pageMake);
		
		System.out.println("@@@### NoticeController search() end");
		
		return "/notice/list";
	}

}
