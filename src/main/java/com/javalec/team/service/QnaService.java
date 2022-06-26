package com.javalec.team.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.javalec.team.dto.Criteria;
import com.javalec.team.dto.QnaDto;

public interface QnaService {
	public ArrayList<QnaDto> list(HashMap<String, String> param);
	public ArrayList<QnaDto> newList(HashMap<String, String> param);
	public ArrayList<QnaDto> listPaging(Criteria cri);
	public ArrayList<QnaDto> newListPaging(Criteria cri);
//	public ArrayList<QnaDto> listPaging(HashMap<String, String> param, Criteria cri);
//	public ArrayList<QnaDto> newListPaging(HashMap<String, String> param, Criteria cri);
	public void write(HashMap<String, String> param);
	public QnaDto show(HashMap<String, String> param);
	public void delete(HashMap<String, String> param);
	public void modify(HashMap<String, String> param);
	public int num();
	public int getqcode();
    /* �Խ��� �� ���� */
    public int getTotal();
    public int newGetTotal(HashMap<String, String> param);
}
