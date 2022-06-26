package com.javalec.team.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.javalec.team.dto.Criteria;
import com.javalec.team.dto.GoodDto;
import com.javalec.team.dto.MovieDto;
import com.javalec.team.dto.ReviewDto;
import com.javalec.team.dto.pageMakerDto;
import com.javalec.team.service.GoodService;
import com.javalec.team.service.Reg_movie_Service;
import com.javalec.team.service.ReviewService;

@Controller
public class Reg_movie_Controller {
	@Autowired
	private Reg_movie_Service service;

	@Autowired
	private ReviewService reviewService;
	
	@Autowired
	private GoodService goodService;
	
	@Autowired
	HttpSession session;
	
	
	//0524 관리자 영화 넣기 - 근지
	@RequestMapping("/reg_movie")
	public String reg_movie(Model model) {
		System.out.println("@@@### reg_movie()");
		
		return "reg_movie/reg_movie";
	}
	
	public void getMove(@RequestParam HashMap<String, String> param,Model model) {
		System.out.println("들어오냐?@@@@@@@@@@@@@@");
		MovieDto movieDto = service.getMove(param);		
		model.addAttribute("movie",movieDto);
		ArrayList<MovieDto> dtoList =service.getStillCut(param);
		model.addAttribute("dtoList",dtoList);
	}

//	@RequestMapping("/movieDisplay")
//	public String goodsDisplay(@RequestParam HashMap<String, String> param,Model model) {
//		getMove(param,model);
//		return "reg_movie/movieDisplay";
//	}
	
	@RequestMapping("/movieDisplay")
	public String goodsDisplay(@RequestParam HashMap<String, String> param,Model model,Criteria cri) {
		getMove(param,model);
		
		System.out.println("m_code ===>"+param.get("m_code"));
		
		model.addAttribute("u_auth", session.getAttribute("u_auth"));
		
		model.addAttribute("list", reviewService.newList(cri));
		
		ArrayList<ReviewDto> dtos = reviewService.newList(cri);
//		HashMap<String, String> map = new HashMap<String, String>();
//		System.out.println(dtos.get(0).getM_code());
//		map.put("m_code", dtos.get(0).getM_code());
		
		System.out.println(service.show(param));
		
		MovieDto dto = service.show(param);
		model.addAttribute("movie", dto);
		
		
		int total = reviewService.getTotal();
		
		pageMakerDto pageMake = new pageMakerDto(cri, total);
		
		model.addAttribute("pageMaker", pageMake);
		
		model.addAttribute("count", reviewService.getTotal());
		
		return "reg_movie/movieDisplay";
	}
	

	//0525 관리자 영화 수정 - 근지
	@RequestMapping("/edit")
	public String edit(@RequestParam HashMap<String, String> param, Model model) {
		service.show(param);
		MovieDto dto = service.show(param);
		model.addAttribute("dto", dto);
		return "reg_movie/edit_movie";	
	}

	//0525 관리자 영화 db 수정 - 근지	
	@RequestMapping("/edit_movie")
	public String edit_movie(MultipartHttpServletRequest mtfRequest ,@RequestParam HashMap<String, String> param, Model model, HttpServletRequest request) {
		
			List<MultipartFile> m_positionfiles = mtfRequest.getFiles("m_position");
			List<MultipartFile> m_picsfiles = mtfRequest.getFiles("m_pics");
			 //String path = "C:\\test\\"; 

			
			 ServletContext servletContext = request.getSession().getServletContext();
			 String path = servletContext.getRealPath("/");
			 path += "resources\\";
			 System.out.println("path@@@@"+path);
			for (MultipartFile mf : m_positionfiles) {
				String originFileName = mf.getOriginalFilename(); // 원본 파일 명
				long fileSize = mf.getSize(); // 파일 사이즈

				System.out.println("originFileName : " + originFileName);
				System.out.println("fileSize : " + fileSize);
				
				String filename = System.currentTimeMillis() + originFileName;
				String safeFile = path +filename;
				
				param.put("m_originimg", originFileName);
				param.put("m_position", filename);
				param.put("m_pics", "");
				
				try {
					mf.transferTo(new File(safeFile));
					service.edit_movie(param);
					
				} catch (IllegalStateException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		    MovieDto movieDto = service.getm_code();
			for (MultipartFile mf : m_picsfiles) {
				String originFileName = mf.getOriginalFilename(); // 원본 파일 명
				long fileSize = mf.getSize(); // 파일 사이즈
				param.put("m_code", movieDto.getM_code());
				System.out.println("originFileName : " + originFileName);
				System.out.println("fileSize : " + fileSize);
				
				 
				
				String filename = System.currentTimeMillis() + originFileName;
				String safeFile = path +filename;
				
				param.put("m_originimg", originFileName);
				param.put("m_pics", filename);
				
				try {
					mf.transferTo(new File(safeFile));
					service.edit_movieimg(param);
					
				} catch (IllegalStateException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		
		return "redirect:movie_list";	
	}


	//0525 관리자 영화 삭제 - 근지
	@RequestMapping(value = "/del_movie")
	public String del_movie(@RequestParam HashMap<String, String> param, Model model) {
		service.del_movie(param);
		service.del_movieimg(param);
		return "redirect:movie_list";	
	}
	
	//0526 에이젝스 사용시 첫화면. 기본값은 order by 최신순 - 근지 - 이게 필요 없어짐
	@RequestMapping("/movie_list")
	//@RequestMapping("/reg_movie/movie_list")
	public String movie_list(HttpServletRequest request, Model model) {
		System.out.println("request 값 제대로 나옴??? movie_list ===>"+request.getParameter("kind"));
		ArrayList<MovieDto> list = service.list();
		model.addAttribute("list", list);
		return "reg_movie/movie_list";
	}

	@RequestMapping("/ajax")
	@ResponseBody
	public ArrayList<MovieDto> ajax(HttpServletRequest request, Model model) {
		String sort = "";
		if (request.getParameter("kind") == null) {	// 처음화면 시작시 null값이라서. 첫화면 컨트롤러 새로 만들어서 그럴 일 없겠지만!
			sort = "m_date desc";
		} else {
			if (request.getParameter("kind").trim().equals("최신순")) {
				sort = "m_date desc";
			} else if (request.getParameter("kind").trim().equals("오래된순")) {
				sort = "m_date";
			} else if (request.getParameter("kind").trim().equals("인기순")) {
				sort = "m_rate desc";
			}
		}
		// 정렬순 dao에 보내기 위해 담음
		model.addAttribute("sort", sort);
		ArrayList<MovieDto> list_sort = service.list_sort(model);
		
		for (int i = 0; i < list_sort.size(); i++) {
			String str = String.valueOf(list_sort.get(i).getM_date());
			str = str.substring(0, 10);
			list_sort.get(i).setM_date2(str);
		}
		return list_sort;
	}


	//0524 관리자 영화 db 넣기 - 근지
	@RequestMapping("/reg_movie_insert")
	public String reg_movie_insert(MultipartHttpServletRequest mtfRequest ,@RequestParam HashMap<String, String> param, Model model , HttpServletRequest request) {
		List<MultipartFile> m_positionfiles = mtfRequest.getFiles("m_position");
		List<MultipartFile> m_picsfiles = mtfRequest.getFiles("m_pics");
		//String path = "C:\\test\\";
		ServletContext servletContext = request.getSession().getServletContext();
		String path = servletContext.getRealPath("/");
		path += "resources\\";
		System.out.println("path@@@@"+path);
		for (MultipartFile mf : m_positionfiles) {
			String originFileName = mf.getOriginalFilename(); // 원본 파일 명
			long fileSize = mf.getSize(); // 파일 사이즈

			System.out.println("originFileName : " + originFileName);
			System.out.println("fileSize : " + fileSize);

			String filename = System.currentTimeMillis() + originFileName;
			String safeFile = path +filename;
			MovieDto movieDto = service.getm_code();
			int m_code = Integer.parseInt(movieDto.getM_code()) + 1;

			param.put("m_originimg", originFileName);
			param.put("m_position", filename);
			param.put("m_pics", "");
			param.put("m_code", m_code+"");
			
			try {
				mf.transferTo(new File(safeFile));
				service.reg_movie(param);

			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		for (MultipartFile mf : m_picsfiles) {
			String originFileName = mf.getOriginalFilename(); // 원본 파일 명
			long fileSize = mf.getSize(); // 파일 사이즈
			System.out.println("originFileName : " + originFileName);
			System.out.println("fileSize : " + fileSize);

			String filename = System.currentTimeMillis() + originFileName;
			String safeFile = path +filename;

			param.put("m_originimg", originFileName);
			param.put("m_pics", filename);

			try {
				mf.transferTo(new File(safeFile));
				service.reg_movieimg(param);

			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "redirect:movie_list";
	}
	
	//0531 영화 검색 - 근지
	@RequestMapping("/serch_mov")
	@ResponseBody
	public ArrayList<MovieDto> serch_mov(HttpServletRequest request, Model model) {
		System.out.println("검색 내용=====>"+request.getParameter("kind").trim());
		
		model.addAttribute("serch_mov",request.getParameter("kind").trim());	
		ArrayList<MovieDto> list_sort = service.serch_mov(model);
		
		for (int i = 0; i < list_sort.size(); i++) {
			String str = String.valueOf(list_sort.get(i).getM_date());
			str = str.substring(0, 10);
			list_sort.get(i).setM_date2(str);
		}
		
		return list_sort;
	}
		
		@RequestMapping("/reviewGood1")
		public String good(@RequestParam HashMap<String, String> param, HttpServletResponse response, Model model) throws IOException {
			
			response.setContentType("text/html");
			response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			
			//추천수를 올리기전에 추천 테이블 만들고 거기에 유저아이디, 리뷰코드, 추천코드, 추천yn만든다
			GoodDto dto = goodService.get(param);
			if (goodService.get(param) == null) {
				System.out.println("추천한적 없음 start");
				goodService.insert(param);
				reviewService.upGood(param);
				System.out.println("추천한적 없음 end");
			} else {
				System.out.println("추천한적 있음 start");
				
				out.println("<script>alert('이미 추천한 글입니다.');history.back();</script>");
				out.flush();
				
			}
			
			model.addAttribute("m_code", param.get("m_code"));
			
			return "redirect:movieDisplay";
		}
		
		
		@RequestMapping("/reviewDelete1")
		public String delete(@RequestParam HashMap<String, String> param, Model model) {
			
			reviewService.delete(param);
			
			model.addAttribute("m_code", param.get("m_code"));
			
			
			return "redirect:movieDisplay";
		}
		
		@RequestMapping("/reviewModify_view1")
		public String modify_view(@RequestParam HashMap<String, String> param, Model model) {
			System.out.println("@@@### modify_view()");
			
			ReviewDto dto = reviewService.show(param);
			model.addAttribute("show", dto);
			
			return "/review/modify2";
		}
		
		@RequestMapping(value = "/reviewModify1")
		public String modify(@RequestParam HashMap<String, String> param, Model model) {
			
			reviewService.modify(param);
			
			
			model.addAttribute("m_code", param.get("m_code"));
			
			return "redirect:movieDisplay";
		}
		
}
