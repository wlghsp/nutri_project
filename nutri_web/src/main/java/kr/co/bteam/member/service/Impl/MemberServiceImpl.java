package kr.co.bteam.member.service.Impl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import kr.co.bteam.member.MemberController;
import kr.co.bteam.member.domain.MemberDTO;
import kr.co.bteam.member.mapper.MemberMapper;
import kr.co.bteam.member.service.IMemberService;
import kr.co.bteam.noticeBoard.Criteria;

@Service
public class MemberServiceImpl implements IMemberService{
	
	private static final Logger log = LoggerFactory.getLogger(MemberController.class);	
	@Autowired
	private MemberMapper mapper;
	
	@Autowired
	private PasswordEncoder pwencoder;
	
	@Override
	public int insertMember(MemberDTO mDto) throws Exception{
		return mapper.insertMember(mDto);
	}

	@Override
	public MemberDTO selectMember(String email) throws Exception {
		return mapper.selMember(email);
	}


	@Override
	public boolean updateMember(MemberDTO mDto) throws Exception {
		return mapper.updateMember(mDto) == 1;
	}

	@Override
	public boolean updatePw(MemberDTO mDto, String old_pw, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String newPasswd = mDto.getPasswd();
		log.info("기존 비밀번호" + old_pw);
		String pw = mapper.selMember(mDto.getEmail()).getPasswd();
		log.info("암호화 비밀번호" + pw);
			
		
		if(pwencoder.matches(old_pw, pw)) {
			log.info("비밀번호 일치");
			// mDto에 비밀번호 암호화하여 넣어줌 
			mDto.setPasswd(pwencoder.encode(newPasswd));
			return  mapper.updatePw(mDto) == 1;
		} else {
			log.info("비밀번호 불일치");   
			out.println("<script>");
			out.println("alert('기존 비밀번호가 다릅니다.');");
			out.println("history.go(-1);");
			out.println("</script>");
			out.close();
			return false;
		}	
	}
	
	@Override
	public boolean updatePwApp(MemberDTO mDto, String old_pw) throws Exception {
		String newPasswd = mDto.getPasswd();
		log.info("기존 비밀번호" + old_pw);
		String pw = mapper.selMember(mDto.getEmail()).getPasswd();
		log.info("암호화 비밀번호" + pw);
			
		
		if(pwencoder.matches(old_pw, pw)) {
			log.info("비밀번호 일치");
			// mDto에 비밀번호 암호화하여 넣어줌 
			mDto.setPasswd(pwencoder.encode(newPasswd));
			return  mapper.updatePw(mDto) == 1;
		} else {
			log.info("비밀번호 불일치");  			
			return false;
		}	
	}

	@Override
	public MemberDTO login(MemberDTO mDto) throws Exception {
		return mapper.login(mDto);
	}

	@Override
	public int nickCheck(String nickname) throws Exception {
		return mapper.nickCheck(nickname);
	}

	@Override
	public int emailCheck(String email) throws Exception {
		return mapper.emailCheck(email);
	}

	@Override
	public List<MemberDTO> selectAll() {
		return mapper.selAll();
	}
	
	@Override
	public int banMember(Integer meno) {
		return mapper.banMember(meno);
	}

	@Override
	public int getTotalCnt(Criteria cri) {
		return mapper.getTotalCnt(cri);
	}

	@Override
	public List<MemberDTO> getListwithPaging(Criteria cri) {
		return mapper.getListwithPaging(cri);
	}

	@Override
	public MemberDTO getMemberApp(String nickname) throws Exception {
		return mapper.getMemberApp(nickname);
	}

	@Override
	public int modifyMemberApp(String nickname, String image_path) {
		
		return mapper.modifyMemberApp(nickname, image_path);
	}

	@Override
	public boolean delmember(MemberDTO mDto, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=utf-8");

		PrintWriter out = response.getWriter();
		log.info("dto===========" + mDto);
		int result = mapper.delmember(mDto);
		if(result != 1) {
			out.println("<script>");
			out.println("alert('회원탈퇴 실패');");
			out.println("history.go(-1);");
			out.println("</script>");
			out.close();
			return false;
		}else {
			return true;
		}
	}
	
	@Override
	public boolean delmemberApp(MemberDTO mDto) throws Exception {
		log.info("dto===========" + mDto);
		int result = mapper.delmember(mDto);
		if(result != 1) {
			log.info("회원탈퇴 실패");
			return false;
		}else {
			log.info("회원탈퇴 성공");
			return true;
		}
	}
	

}
