package com.javalec.team.service.Impl;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javalec.team.dao.CartDao;
import com.javalec.team.dto.CartDto;
import com.javalec.team.service.CartService;




@Service("CartService")
//@Service("cartService")
public class CartServiceImpl implements CartService{
	@Autowired
	private SqlSession sqlSession;

	@Override
	public void insertCart(HashMap<String, String> param) {
		//System.out.println("CartServiceImpl - insertCart");
		CartDao dao = sqlSession.getMapper(CartDao.class);
		
		dao.insertCart(param);
		//System.out.println("CartServiceImpl - insertCart end");
	}

	@Override
	public ArrayList<CartDto> getAllCart(HashMap<String, String> param) {
		CartDao dao = sqlSession.getMapper(CartDao.class);
		 ArrayList<CartDto> cartlist = dao.getAllCart(param);
		return cartlist;
	}

	@Override
	public void delCart(HashMap<String, String> param) {
		CartDao dao = sqlSession.getMapper(CartDao.class);
		dao.delCart(param);
	}

	@Override
	public CartDto cartlistbuy(HashMap<String, String> param) {
		CartDao dao = sqlSession.getMapper(CartDao.class);
		CartDto cartDto  = dao.cartlistbuy(param);
		return cartDto;
	}

	@Override
	public void updateCart(HashMap<String, String> param) {
		CartDao dao = sqlSession.getMapper(CartDao.class);
		dao.updateCart(param);
	}

	@Override
	public int getccode() {
		CartDao dao = sqlSession.getMapper(CartDao.class);
		int num =dao.getccode();
	
		return num;
	}
	
}
