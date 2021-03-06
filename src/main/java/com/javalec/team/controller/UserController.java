package com.javalec.team.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javalec.team.dto.BookDTO;
import com.javalec.team.dto.FaqDto;
import com.javalec.team.dto.GoodsDto;
import com.javalec.team.dto.NoticeDto;
import com.javalec.team.dto.QnaDto;
import com.javalec.team.dto.ReviewDto;
import com.javalec.team.dto.UserDto;
import com.javalec.team.service.MailSendService;
import com.javalec.team.service.UserService;


@Controller
public class UserController {
	
	@Autowired
	private UserService service;
	
	@Autowired
	HttpSession session;
	
	@Autowired
	private MailSendService mailService;
	
	@RequestMapping("login")
	public String login() {
		System.out.println("@@@### login()");
		
		return "user/login";
	}
	
	@RequestMapping("userPage")
	public String userpage(@RequestParam HashMap<String, String> param, Model model) {
		System.out.println(param.get("u_id"));

		UserDto MyUser_t =  service.getMyUser_t(param);
		model.addAttribute("user",MyUser_t);

		 ArrayList<ReviewDto> myReviewList = service.getMyReviewList(param); 
		ArrayList<FaqDto> myFaqList =  service.getMyFaqList(param);
		ArrayList<NoticeDto> myNotice_tList = service.getMyNotice_tList(param);
		ArrayList<QnaDto> myQnaList = service.getMyQnaList(param);
		ArrayList<GoodsDto> mygoodsList = service.getMygoodsList(param);
		ArrayList<BookDTO> myBookList = service.getMybookList(param);
		 model.addAttribute("myReviewList",myReviewList); 
		model.addAttribute("myFaqList",myFaqList);
		model.addAttribute("myNotice_tList",myNotice_tList);
		model.addAttribute("myQnaList",myQnaList);
		model.addAttribute("mygoodsList",mygoodsList);
		model.addAttribute("myBookList", myBookList);
		return "user/userPage";
	}
	
	@RequestMapping("login_yn")
	public String login_yn(@RequestParam HashMap<String, String> param, Model model, HttpServletResponse response) throws IOException {
		ArrayList<UserDto> dtos = service.loginYn(param);
		model.addAttribute("dtos", dtos);
		
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();

		if (dtos.isEmpty()) {
			out.println("<script>alert('????????? ???????????? ??????????????? ???????????????.'); self.close();</script>");
			out.flush();
			return "user/login";
		} else {
			if (param.get("u_pwd").equals(dtos.get(0).getU_pwd())) {
				session.setAttribute("u_id", param.get("u_id"));
				session.setAttribute("u_auth", dtos.get(0).getU_auth());
				System.out.println(param.get("u_id"));
				System.out.println(dtos.get(0).getU_auth());
				return "redirect:/";
			} else {
				out.println("<script>alert('??????????????? ???????????????.'); self.close();</script>");
				out.flush();
				return "user/login";
			}
		}
		
	}
	
	
	@RequestMapping("register")
	public String register() {
			
		return "/user/register";
	}
	
	@RequestMapping("registerOk")
	public String registerOk(@RequestParam HashMap<String, String> param,  HttpServletResponse response) throws IOException {
//		System.out.println("@@@### userController.registerOk() start");
		
		UserDto dto =  service.confirmUserId(param);
		
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		
		if (dto != null) {
			return "user/register";
		} else {
			try {
				service.write(param);
				out.println("<script>alert('???????????????! ??????????????? ?????? ???????????????.'); self.close(); location.href='/team/'; </script>");
				out.flush();
				return "/team/";
			} catch (Exception e) {
				return "user/register";
			}
		}
	}

	
	@RequestMapping(value = "/checkId", method = RequestMethod.POST)
	@ResponseBody
	public String checkId(@RequestParam HashMap<String, String> param) throws IOException {
		
		UserDto result =  service.checkId(param);
		int check = result.getUidcheck();
		
		
		if(check != 0) {
			return "fail";	// ?????????????? ???????????????????????????????????? ???????????????
		} else {
			return "success";	// ?????????????? ??????????????????????????? x
		}	
	}
	
	@RequestMapping("editUser")
	public String editUser(@RequestParam HashMap<String, String> param, Model model) {
		UserDto dto =  service.confirmUserId(param);
		model.addAttribute("dto",dto);
		
		return "/user/editUser";
	}
	
	@RequestMapping("editUserOk")
	public void editUserOk(@RequestParam HashMap<String, String> param, Model model, HttpServletResponse response) throws IOException {
		
		
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		
		service.editUser(param);
		out.println("<script>alert('????????? ?????????????????????.'); location.href='/team/';</script>");
		out.flush();
	}
	
	//???????????????????????? ??????????????????
	@RequestMapping(value = "/mailCheck", method = RequestMethod.GET)
	@ResponseBody
	public String mailCheck(String email) {
		System.out.println("????????? ?????? ????????? ?????????!");
		System.out.println("????????? ?????? ????????? : " + email);
		return mailService.joinEmail(email);
	}
	
	
//	@RequestMapping(value = "delete")
  
//	public String delete(@RequestParam HashMap<String, String> param) throws IOException {
//		service.outUser(param);
//		session.invalidate();
//		return "redirect:/";
//
//  
//	}
	
	@RequestMapping(value = "delete")
	public String delete(@RequestParam HashMap<String, String> param) throws IOException {
		session.invalidate();
		service.outUser(param);
		return "redirect:/";
	}
	
	@RequestMapping("findId")
	public String findId() {
		
		return "user/findId";
	}
	
	@RequestMapping("findIdOk")
	public void findIdOk(@RequestParam HashMap<String, String> param, HttpServletResponse response) throws IOException {
		
		UserDto dto = service.tryToFindId(param);
		
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		
		if (dto == null) {
			out.println("<script>alert('???????????? ?????? ???????????????.'); location.href='findId';</script>");
			out.flush();
		} else {
			out.println("<script>alert('???????????? ???????????? "+dto.getU_id()+" ?????????.'); location.href='login';</script>");
			out.flush();
		}
		
	}

	
	@RequestMapping("findPwd")
	public String findPwd() {
		
		return "user/findPwd";
	}
	
	@RequestMapping("logOut")
	public String logout() {
		System.out.println("@@@### logOut()");
		
		return "user/logOut";
	}
	
	@RequestMapping("findPwdOk")
	public void findPwd(@RequestParam HashMap<String, String> param, HttpServletResponse response) throws IOException {
		
		UserDto dto = service.tryToFinPwd(param);
		
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		
		if (dto == null) {
			out.println("<script>alert('???????????? ?????? ???????????????.'); location.href='findPwd';</script>");
			out.flush();
		} else {
			out.println("<script>alert('???????????? ??????????????? "+dto.getU_pwd()+" ?????????.'); location.href='login';</script>");
			out.flush();
		}
		
	}
	
}
	
