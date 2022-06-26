package com.javalec.team.controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.javalec.team.dto.Criteria;
import com.javalec.team.dto.FaqDto;
import com.javalec.team.dto.NoticeDto;
import com.javalec.team.dto.pageMakerDto;
import com.javalec.team.service.FaqService;

@Controller
public class FaqController {

	@Autowired
	private FaqService service;
	
	@RequestMapping("/faqList")
	public String list(@RequestParam HashMap<String, String> param, Model model, Criteria cri) {
		System.out.println("@@@### FaqController list() start");
		
//		ArrayList<FaqDto> dtos = service.list(param);
//		model.addAttribute("list", dtos);
		model.addAttribute("list", service.listPaging(cri));
		
		int total = service.getTotal();
		
		pageMakerDto pageMake = new pageMakerDto(cri, total);
		
		model.addAttribute("pageMaker", pageMake);
		
		System.out.println("@@@### FaqController list() end");
		
		return "/faq/list";
	}
	
//	@RequestMapping("/faq/list")
//	public String list(@RequestParam HashMap<String, String> param, Model model) {
//		System.out.println("@@@### FaqController list() start");
//		
//		ArrayList<FaqDto> dtos = service.list(param);
//		model.addAttribute("list", dtos);
//		
//		System.out.println("@@@### FaqController list() end");
//		
//		return "/faq/list";
//	}
	
	@RequestMapping("/faqWrite_view")
	public String write_view(Model model) {
		System.out.println("@@@### write_view()");
		
		int num = service.num();
		
		System.out.println("Num ===>"+num);
		
		model.addAttribute("num", num);
		
		return "/faq/write";
	}
	
	@RequestMapping("/faqWrite")
	public String write(@RequestParam HashMap<String, String> param) {
		System.out.println("@@@### FaqController write() start");
		
		int num = service.getfcode();
		num+=1;
		param.put("f_code", Integer.toString(num));
		
		service.write(param);
		
		System.out.println("@@@### FaqController write() end");
		
		return "redirect:faqList";
	}
	
	@RequestMapping("/faqShow")
	public String show(@RequestParam HashMap<String, String> param, Model model) {
		System.out.println("@@@### FaqController show() start");
		
		FaqDto dto = service.show(param);
		model.addAttribute("show", dto);
		
		System.out.println("@@@### FaqController show() end");
		
		return "/faq/show";
	}
	
	@RequestMapping("/faqDelete")
	public String delete(@RequestParam HashMap<String, String> param) {
		System.out.println("@@@### FaqController delete() start");
		
		service.delete(param);
		
		System.out.println("@@@### FaqController delete() end");
		
		return "redirect:faqList";
	}
	
	@RequestMapping("/faqModify_view")
	public String modify_view(@RequestParam HashMap<String, String> param, Model model) {
		System.out.println("@@@### modify_view()");
		
		FaqDto dto = service.show(param);
		model.addAttribute("show", dto);
		
		return "/faq/modify";
	}
	
	@RequestMapping(value = "/faqModify")
	public String modify(@RequestParam HashMap<String, String> param, Model model) {
		System.out.println("@@@### FaqController modify() start");
		
		service.modify(param);
		
		System.out.println("@@@### FaqController modify() end");
		
		return "redirect:faqList";
	}
	
	@RequestMapping("/faqSearch")
	public String search(HttpServletRequest request, Model model, Criteria cri) {
		System.out.println("@@@### FaqController search() start");
		
		request.getParameter("kind");
		if (request.getParameter("kind").equals("����")) {
			ArrayList<FaqDto> dtos = service.searchTitle(cri);
			model.addAttribute("list", dtos);
			
//			Assertions.assertThat("kind").isSameAs("");
		} else if (request.getParameter("kind").equals("����")) {
			ArrayList<FaqDto> dtos = service.searchContent(cri);
			model.addAttribute("list", dtos);
		} else {
			ArrayList<FaqDto> dtos = service.searchCategory(cri);
			model.addAttribute("list", dtos);
		}
		
		int total = service.getTotal();
		
		pageMakerDto pageMake = new pageMakerDto(cri, total);
		
		model.addAttribute("pageMaker", pageMake);
		
		System.out.println("@@@### FaqController search() end");
		
		return "/faq/list";
	}
}
