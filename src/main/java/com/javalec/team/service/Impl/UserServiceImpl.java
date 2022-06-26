package com.javalec.team.service.Impl;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.javalec.team.dao.UserDao;
import com.javalec.team.dto.BookDTO;
import com.javalec.team.dto.FaqDto;
import com.javalec.team.dto.GoodsDto;
import com.javalec.team.dto.MovieDto;
import com.javalec.team.dto.NoticeDto;
import com.javalec.team.dto.QnaDto;
import com.javalec.team.dto.ReviewDto;
import com.javalec.team.dto.UserDto;
import com.javalec.team.service.UserService;

@Service("UserService")
public class UserServiceImpl implements UserService{
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public ArrayList<UserDto> loginYn(@RequestParam HashMap<String, String> param) {
		
		UserDao dao = sqlSession.getMapper(UserDao.class);
		ArrayList<UserDto> dtos = dao.loginYn(param);
		
		return dtos;
	}

	@Override
	public void write(@RequestParam HashMap<String, String> param) {
//		System.out.println("@@@### UserServiceImpl.write() start");
		
		UserDao dao = sqlSession.getMapper(UserDao.class);
		dao.write(param);
		
	}

	@Override
	public void outUser(HashMap<String, String> param) {
		UserDao dao = sqlSession.getMapper(UserDao.class);
		dao.outUser(param);
		
	}
	
	
	@Override
	public UserDto confirmUserId(@RequestParam HashMap<String, String> param) {
		UserDao dao = sqlSession.getMapper(UserDao.class);
		UserDto dto = dao.confirmUserId(param);
		
		return dto;
		
	}
	
	@Override
	public UserDto tryToFindId(@RequestParam HashMap<String, String> param) {
		
		UserDao dao = sqlSession.getMapper(UserDao.class);
		UserDto u_id = dao.tryToFindId(param);
		
		return u_id;
	}

	@Override
	public UserDto tryToFinPwd(HashMap<String, String> param) {
		
		UserDao dao = sqlSession.getMapper(UserDao.class);
		UserDto u_pwd = dao.tryToFinPwd(param);
		
		return u_pwd;
	}

	@Override
	public UserDto checkId(HashMap<String, String> param) {
	
		UserDao dao = sqlSession.getMapper(UserDao.class);
		UserDto result = dao.checkId(param);
		return result;
	}

	
	
//	@Override
//	public ArrayList<MovieDto> getMybookList(HashMap<String, String> param) {
//		// TODO Auto-generated method stub
//		return null;
//	}

	
	
	@Override
	public ArrayList<ReviewDto> getMyReviewList(HashMap<String, String> param) {
		
		UserDao dao = sqlSession.getMapper(UserDao.class);
		ArrayList<ReviewDto> list = dao.getMyReviewList(param);
		return list;
	}

	@Override
	public ArrayList<FaqDto> getMyFaqList(HashMap<String, String> param) {
		UserDao dao = sqlSession.getMapper(UserDao.class);
		ArrayList<FaqDto> list = dao.getMyFaqList(param);
		return list;
	}

	@Override
	public ArrayList<NoticeDto> getMyNotice_tList(HashMap<String, String> param) {
		UserDao dao = sqlSession.getMapper(UserDao.class);
		ArrayList<NoticeDto> list = dao.getMyNotice_tList(param);
		return list;
	}

	@Override
	public ArrayList<QnaDto> getMyQnaList(HashMap<String, String> param) {
		UserDao dao = sqlSession.getMapper(UserDao.class);
		ArrayList<QnaDto> list = dao.getMyQnaList(param);
		return list;
	}

	@Override
	public ArrayList<GoodsDto> getMygoodsList(HashMap<String, String> param) {
		UserDao dao = sqlSession.getMapper(UserDao.class);
		ArrayList<GoodsDto> list = dao.getMygoodsList(param);
		return list;
	}
	
	@Override
	public void editUser(HashMap<String, String> param) {
		UserDao dao = sqlSession.getMapper(UserDao.class);
		dao.editUser(param);
	}

	@Override
	public UserDto getMyUser_t(HashMap<String, String> param) {
		UserDao dao = sqlSession.getMapper(UserDao.class);
		UserDto dto = dao.getMyUser_t(param);
		return dto;
	}

	@Override
	public ArrayList<BookDTO> getMybookList(HashMap<String, String> param) {
		UserDao dao = sqlSession.getMapper(UserDao.class);
		ArrayList<BookDTO> list = dao.getMybookList(param);
		
		return list;
	}



}
