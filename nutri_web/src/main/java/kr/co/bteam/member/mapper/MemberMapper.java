package kr.co.bteam.member.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.co.bteam.marketBoard.domain.MarketBoardDTO;
import kr.co.bteam.member.domain.MemberDTO;
import kr.co.bteam.noticeBoard.Criteria;

public interface MemberMapper {
	
	public MemberDTO login(MemberDTO memberDTO); // 회원 로그인 
	public int insertMember(MemberDTO memberDTO); //회원 가입 정보 등록
	public MemberDTO selMember(@Param("email") String email) throws Exception; // 이메일로 회원정보 찾아 옴. 
	public MemberDTO getMemberApp(@Param("nickname") String nickname) throws Exception; //(App)닉네임으로 회원정보 찾아 옴. 
	public int updateMember(MemberDTO mDto) throws Exception; // 회원정보 수정 
	public int updatePw(MemberDTO mDto) throws Exception;    // 회원 비밀번호 수정 
	public int nickCheck(String nickname) throws Exception; //회원가입 시 닉네임 중복 체크  
	public int emailCheck(String email) throws Exception;  //회원가입 시 이메일 중복 체크
	
	// 임시비밀번호 저장
	public void temporaryPw(String email, String nickname, String key) throws Exception;
	
	
	//회원가입 직후 유저키 저장
	public int setKey(@Param("nickname")String nickname, @Param("user_key")String user_key) throws Exception;
	
	//개인메일로 인증메일 클릭시 'Y'로 변경 
	public int alter_userKey(String nickname, String user_key) throws Exception;
	
	//회원전원 정보 가져오기
	public List<MemberDTO> selAll();
	
	//관리자 페이지 회원 추방 
	public int banMember(Integer meno);
	
	public List<MemberDTO> getListwithPaging(Criteria cri);
	
	//회원수 
	public int getTotalCnt(Criteria cri);
	
	//이미지 주소 입력 
	public int modifyMemberApp(@Param("nickname")String nickname, @Param("image_path") String image_path);
	
	//회원탈퇴
	public int delmember(MemberDTO mDto);
	
}
