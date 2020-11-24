package kr.co.bteam.member.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.annotations.Param;

import kr.co.bteam.member.domain.MemberDTO;
import kr.co.bteam.noticeBoard.Criteria;

public interface IMemberService {
	 public int insertMember(MemberDTO mDto) throws Exception;
	 public MemberDTO selectMember(String email) throws Exception;
	 
	 public MemberDTO getMemberApp(String nickname) throws Exception;
	 
	 public boolean updateMember(MemberDTO mDto) throws Exception;
	 public boolean updatePw(MemberDTO mDto, String old_pw, HttpServletResponse response) throws Exception;
	 public boolean updatePwApp(MemberDTO mDto, String old_pw) throws Exception;
	 public MemberDTO login(MemberDTO mDto) throws Exception;
	 public int nickCheck(String nickname) throws Exception;
	 public int emailCheck(String email) throws Exception;
	 public List<MemberDTO> selectAll();
	 
	 public int banMember(Integer meno);
	 
	 public List<MemberDTO> getListwithPaging(Criteria cri);
	 
	 public int getTotalCnt(Criteria cri);
	 
	 public int modifyMemberApp(String nickname, String image_path);
	 
	 //회원탈퇴
	 public boolean delmember(MemberDTO mDto, HttpServletResponse response) throws Exception;
	 public boolean delmemberApp(MemberDTO mDto) throws Exception;
}
