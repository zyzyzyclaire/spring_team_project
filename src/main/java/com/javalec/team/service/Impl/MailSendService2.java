package com.javalec.team.service.Impl;

import java.util.Random;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class MailSendService2 {
	@Autowired
	private JavaMailSenderImpl mailSender;
	private int authNumber; 
	// ���� �߻�
	
		public void makeRandomNumber() {
			// ������ ���� 111111 ~ 999999 (6�ڸ� ����)
			Random r = new Random();
			int checkNum = r.nextInt(888888) + 111111;
			System.out.println("������ȣ : " + checkNum);
			authNumber = checkNum;
		}
		
		
				//�̸��� ���� ���! 
		public String joinEmail1(String email) {
			makeRandomNumber();
			String setFrom = "karas1027@nate.com"; // email-config�� ������ �ڽ��� �̸��� �ּҸ� �Է� 
			String toMail = email;
			String title = "1��1 ���� ���� �̸��� �Դϴ�."; // �̸��� ���� 
			String content = 
					"Ȩ�������� �湮���ּż� �����մϴ�." + 	//html �������� �ۼ� ! 
	                "<br><br>" + 
				    "���� ��ȣ�� " + authNumber + "�Դϴ�." + 
				    "<br>" + 
				    "�ش� ������ȣ�� ������ȣ Ȯ�ζ��� �����Ͽ� �ּ���."; //�̸��� ���� ����
			mailSend(setFrom, toMail, title, content);
			return Integer.toString(authNumber);
		}
		
		//�̸��� ���� �޼ҵ�
		public void mailSend(String setFrom, String toMail, String title, String content) { 
			MimeMessage message = mailSender.createMimeMessage();
			// true �Ű����� �����ϸ� multipart ������ �޼��� ������ ����.���� ���ڵ� ������ �����ϴ�.
			try {
				MimeMessageHelper helper = new MimeMessageHelper(message,true,"utf-8");
				helper.setFrom(setFrom);
				helper.setTo(toMail);
				helper.setSubject(title);
				// true ���� > html �������� ���� , �ۼ����� ������ �ܼ� �ؽ�Ʈ�� ����.
				helper.setText(content,true);
				mailSender.send(message);
			} catch (MessagingException e) {
				e.printStackTrace();
			}
		}
		
	
}