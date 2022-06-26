package com.javalec.team.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.ui.Model;

import com.javalec.team.dto.MovieDto;

public interface Reg_movie_Service {
	public void reg_movie(HashMap<String, String> param);
	public ArrayList<MovieDto> list();
	public void edit_movie(HashMap<String, String> param);
	public void del_movie(HashMap<String, String> param);
	public MovieDto show(HashMap<String, String> param);
	public MovieDto getm_code();
	public void reg_movieimg(HashMap<String, String> param);
	public ArrayList<MovieDto> listPicsImg(HashMap<String, String> param);
	public ArrayList<MovieDto> list_sort(Model model);
	public void screen(HashMap<String, String> param);
	public ArrayList<MovieDto> serch_mov(Model model);
	public MovieDto getMove(HashMap<String, String> param);
	 public ArrayList<MovieDto> getStillCut(HashMap<String, String> param);

	public void edit_movieimg(HashMap<String, String> param);
	public void del_movieimg(HashMap<String, String> param);

}
