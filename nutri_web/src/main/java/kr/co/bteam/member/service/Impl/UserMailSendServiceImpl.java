package kr.co.bteam.member.service.Impl;

import java.util.Random;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import kr.co.bteam.member.domain.MemberDTO;
import kr.co.bteam.member.mapper.MemberMapper;
import kr.co.bteam.member.service.IUserMailSendService;

@Service
public class UserMailSendServiceImpl implements IUserMailSendService{
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private MemberMapper mapper;	
	@Autowired
	private PasswordEncoder pwencoder;
	
	// 이메일 난수 만드는 메서드
	private String init() {
		Random ran = new Random();
		StringBuffer sb = new StringBuffer();
		int num = 0;

		do {
			num = ran.nextInt(75) + 48;
			if ((num >= 48 && num <= 57) || (num >= 65 && num <= 90) || (num >= 97 && num <= 122)) {
				sb.append((char) num);
			} else {
				continue;
			}

		} while (sb.length() < size);
		if (lowerCheck) {
			return sb.toString().toLowerCase();
		}
		return sb.toString();
	}

	// 난수를 이용한 키 생성
	private boolean lowerCheck;
	private int size;

	public String getKey(boolean lowerCheck, int size) {
		this.lowerCheck = lowerCheck;
		this.size = size;
		return init();
	}
	
		
	//회원 가입 후 인증메일 발송 하는 메서드 
	@Override
	public void mailSendWithUserKey(String email, String nickname, HttpServletRequest request) throws Exception {
		String key = getKey(false, 20);
		mapper.setKey(nickname, key); 
		MimeMessage mail = mailSender.createMimeMessage();
		String htmlStr = "<h2>안녕하세요 당신의 건강을 책임지는 누트리 입니다!</h2><br><br>" 
				+ "<h3>" + nickname + "님</h3>" + "<p>인증하기 버튼을 누르시면 로그인을 하실 수 있습니다 : " 
				+ "<a href='http://192.168.0.200:8081" + request.getContextPath() + "/member/key_alter?nickname="+ nickname +"&user_key="+key+"'>인증하기</a></p>"
				+ "(혹시 잘못 전달된 메일이라면 이 이메일을 무시하셔도 됩니다)";
		try {
			mail.setSubject("[본인인증] 누트리 인증메일", "utf-8");
			mail.setText(htmlStr, "utf-8", "html");
			mail.addRecipient(RecipientType.TO, new InternetAddress(email));
			mailSender.send(mail);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		
	}
		
	

	//이메일 확인 후 클릭 시 유저키 수정 y로 수정함. 
	@Override
	public void alter_userKey(String nickname, String key) throws Exception {
		mapper.alter_userKey(nickname, key);
	}

	
	// 비밀번호 찾기 시 임시 비밀번호 발송 
	@Override
	public void mailSendWithPassword(String email, HttpServletRequest request) throws Exception {
		// 비밀번호는 6자리로 보내고 데이터베이스 비밀번호를 바꿔준다
		String key = getKey(false, 6);
		
		// 회원 이름 꺼내는 코드
		MemberDTO mDto = mapper.selMember(email);
		String nickname = mDto.getNickname();
				
		MimeMessage mail = mailSender.createMimeMessage();
		String htmlStr = "<h2>안녕하세요 '"+ nickname +"' 님</h2><br><br>" 
				+ "<p>비밀번호 찾기를 신청해주셔서 임시 비밀번호를 발급해드렸습니다.</p>"
				+ "<p>임시로 발급 드린 비밀번호는 <h2 style='color : blue'>'" + key +"'</h2>이며 로그인 후 마이페이지에서 비밀번호를 변경해주시면 됩니다.</p><br>"
				+ "<h3><a href='http://localhost:8082" +request.getContextPath()+"'>누트리 홈페이지 접속</a></h3><br><br>"
				+ "(혹시 잘못 전달된 메일이라면 이 이메일을 무시하셔도 됩니다)";
		try {
			mail.setSubject("[누트리] 임시 비밀번호가 발급되었습니다", "utf-8");
			mail.setText(htmlStr, "utf-8", "html");
			mail.addRecipient(RecipientType.TO, new InternetAddress(email));
			mailSender.send(mail);
		} catch (MessagingException e) { 
			e.printStackTrace();
		}
		// 임시 비밀번호 암호화 
		key = pwencoder.encode(key);
		
		// 데이터 베이스 값은 암호한 값으로 저장시킨다.
		mapper.temporaryPw(email, nickname, key);
		
	}
	
	
}
