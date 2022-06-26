package com.javalec.team.controller;

import java.io.Console;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import java.io.Console;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.javalec.team.dto.CartDto;
import com.javalec.team.dto.GoodsDto;
import com.javalec.team.dto.MovieDto;
import com.javalec.team.service.GoodsService;


@Controller
public class GoodsController {
	@Autowired
	private SqlSession sqlSession;
	
	@Autowired
	private GoodsService goodsService;
	
	@Autowired
	private CartController cartController;
	
	@RequestMapping(value = "addGoods", method = RequestMethod.GET)
	public String upload2(Locale locale, Model model) {
		
		return "goods/addGoods";
	}
	
	@RequestMapping(value = "goodsList", method = RequestMethod.GET)
	public String goodsList(Locale locale, Model model) {
		
		return "goods/goodsList";
	}
	
	@RequestMapping(value = "goodsList_section", method = RequestMethod.GET)
	public String goodsList_section(@RequestParam HashMap<String, String> param, Locale locale, Model model) {
		String name = null;
		String g_section = param.get("g_section");
		
		if (g_section.equals("1")) {
			name="팝콘";
		} else if (g_section.equals("2")) {
			name="음료";
		} else if (g_section.equals("3")) {
			name="스낵";
		}
		
		ArrayList<GoodsDto> list = goodsService.getGoods_section(param);
		
		model.addAttribute("name", name);
		model.addAttribute("list", list);
		
		return "main/main_goods_list";
	}

	@RequestMapping(value = "/upload")
	public String requestupload2(MultipartHttpServletRequest mtfRequest, HttpServletRequest request ,@RequestParam HashMap<String, String> param) throws IllegalStateException, IOException {
		List<MultipartFile> fileList = mtfRequest.getFiles("file");

		System.out.println("src value : " + param.get("g_name"));
		System.out.println("src value : " + param.get("g_price"));

		//String path = "C:\\test\\";
		 ServletContext servletContext = request.getSession().getServletContext();
		 String path = servletContext.getRealPath("/");
		 path += "resources\\";
		 System.out.println("path@@@@"+path);
		 
		GoodsDto dto = goodsService.getGoodsGcode();	
		int g_code = dto.getG_code()+1;	
		param.put("g_code", g_code+"");
		int count = 0;
		
		for (MultipartFile mf : fileList) {
			count ++;
			String originFileName = mf.getOriginalFilename(); // 원본 파일 명
			long fileSize = mf.getSize(); // 파일 사이즈

			System.out.println("originFileName : " + originFileName);
			System.out.println("fileSize : " + fileSize);
			
			String filename = System.currentTimeMillis() + originFileName;
			String safeFile = path +filename;
			
			param.put("img_origin", originFileName);
			
			if(count == 1) {
				mf.transferTo(new File(safeFile));
				param.put("img_1", filename);
				goodsService.insertGoods(param);
			
			}else {
				mf.transferTo(new File(safeFile));
				param.put("img_2", filename);
				goodsService.insertGoodsImg(param);
			}
			
		}

		return "goods/goodsList";
	}

	@RequestMapping("goodsajax")
	@ResponseBody
	public ArrayList<GoodsDto> ajax(@RequestParam HashMap<String, String> param, Model model) {
	
		String sort = "";
		
		if (param.get("kind").trim() == null) {	// 처음화면 시작시 null값이라서. 첫화면 컨트롤러 새로 만들어서 그럴 일 없겠지만!
			sort = "g_code desc";
			
		} else {
			
			if (param.get("kind").trim().equals("최신순")) {
				sort = "g_code desc";
			} else if (param.get("kind").trim().equals("오래된순")) {
				sort = "g_code";
			}
		}
	
		// 정렬순 dao에 보내기 위해 담음
		param.put("sort", sort);
		ArrayList<GoodsDto> list_sort = goodsService.list_sort(param);
		
		
		 System.out.println(list_sort.get(0).getG_code());
		 System.out.println(list_sort.get(0).getG_name());
		return list_sort;
	}

	public void getGoods(@RequestParam HashMap<String, String> param, Model model) {
		GoodsDto goodsdto = goodsService.getGoods(param);		
		model.addAttribute("goods",goodsdto);
	}
	
	@RequestMapping("goodsDisplay")
	public String goodsDisplay(@RequestParam HashMap<String, String> param,Model model) {
		getGoods(param,model);
		return "goods/goodsDisplay";
	}
	
	@RequestMapping("/buy")
	public String buy(@RequestParam HashMap<String, String> param,HttpServletRequest request,Model model) {
		String[] cart_listc_code = null;
		
		System.out.println("@@@"+request.getParameterValues("cart_listc_code"));
		if(request.getParameterValues("cart_listc_code") != null) {
			ArrayList<CartDto> cartlist = new ArrayList<CartDto>();
			//System.out.println("들어오냐?1"+param.get("u_id"));
			cart_listc_code = request.getParameterValues("cart_listc_code");
			ArrayList<CartDto> buylist = cartController.cartlistbuy(cart_listc_code,param);
			for (int i = 0; i < cart_listc_code.length; i++) {
				System.out.println("들어오냐?");
				System.out.println("@@@@@@@123123@@"+cart_listc_code[i]);
				int price = buylist.get(i).getG_price()*buylist.get(i).getC_amount();
				buylist.get(i).setG_price(price);
				param.put("c_code", cart_listc_code[i]);
				param.put("g_code",Integer.toString( buylist.get(i).getC_amount()));
				param.put("c_amount", Integer.toString(buylist.get(i).getC_amount()));
				param.put("g_price", Integer.toString(price));
				cartController.deleteCart(param, null);
				goodsService.insertBuy(param);
			}
			model.addAttribute("cartlist",buylist);
			return "redirect:/";
		}
		
		
		if(cart_listc_code == null) {
			int price = Integer.parseInt(param.get("g_price"))*Integer.parseInt(param.get("c_amount"));
			param.put("g_price", Integer.toString(price));
		
			goodsService.insertBuy(param);
		}
		return "redirect:/";	
	}

	//0609 관리자 상품 수정 - 근지
	@RequestMapping("/edit_goods_process")
	public String edit_goods_process(@RequestParam HashMap<String, String> param, Model model) {
		GoodsDto dto = goodsService.show(param);
		model.addAttribute("dto", dto);
		System.out.println("getG_code====="+dto.getG_code());
		System.out.println("getG_name====="+dto.getG_name());
		System.out.println("getG_price====="+dto.getG_price());
		return "goods/edit_goods";	
	}

	//0609 관리자 상품 db 수정 - 근지	
	@RequestMapping("/edit_goods")
	public String edit_goods(MultipartHttpServletRequest mtfRequest ,@RequestParam HashMap<String, String> param, Model model, HttpServletRequest request) throws IllegalStateException, IOException {
		
		List<MultipartFile> fileList = mtfRequest.getFiles("file");

		 ServletContext servletContext = request.getSession().getServletContext();
		 String path = servletContext.getRealPath("/");
		 path += "resources\\";
		 
		int count = 0;
			
		for (MultipartFile mf : fileList) {
			count ++;
			String originFileName = mf.getOriginalFilename(); // 원본 파일 명
			long fileSize = mf.getSize(); // 파일 사이즈

			System.out.println("originFileName : " + originFileName);
			System.out.println("fileSize : " + fileSize);
			
			String filename = System.currentTimeMillis() + originFileName;
			String safeFile = path +filename;
			
			param.put("img_origin", originFileName);
			
			if(count == 1) {
				mf.transferTo(new File(safeFile));
				param.put("img_1", filename);
				goodsService.edit_goods(param);
			
			}else {
				mf.transferTo(new File(safeFile));
				param.put("img_2", filename);
				goodsService.edit_goodsimg(param);
			}
		}
		
		return "redirect:goodsList";	
	}
	
	//0609 관리자 상품 삭제 - 근지
	@RequestMapping(value = "/del_goods")
	public String del_goods(@RequestParam HashMap<String, String> param, Model model) {
		goodsService.del_goods(param);
		goodsService.del_goodsimg(param);
		return "redirect:goodsList";	
	}
}
