package com.javalec.team.service;

import com.javalec.team.dto.BookDTO;
import org.springframework.ui.Model;

import java.util.ArrayList;

public interface Res_Movie_SeatService {

    //���� ��ȭ ���� Ȯ��
    int findJvalidation(String hNUm, String sTime);

    //���� ���� DB ���
    void putBookedData(BookDTO bdto);

    //����
    ArrayList<Integer> findPriceInfo(int jVali);

    //�¼�
    void findAllSeats(String mcode, String date, String time, String tcode, Model model);


}
