package kr.co.bteam.member;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.co.bteam.api.domain.CrawlerDTO;
import kr.co.bteam.crawler.Foodsafety;
import kr.co.bteam.member.domain.MemberDTO;
import kr.co.bteam.member.service.IMemberService;
import kr.co.bteam.member.service.IUserMailSendService;


@Controller
@RequestMapping("/member")
public class MemberController {
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Autowired
	private IMemberService service;
	
	@Autowired
	private IUserMailSendService mailsender;
	
	@Autowired
	private PasswordEncoder pwencoder;
	
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public void signupGET() throws Exception{
		logger.info("signup get ......................................");
	}
	
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String signupPOST(MemberDTO mDto, RedirectAttributes rttr, HttpServletRequest request) throws Exception{
		logger.info("signup post .....................................");
		mDto.setPasswd(pwencoder.encode(mDto.getPasswd()));
		
		logger.info(mDto.toString());
		int result = 0;
		result = service.insertMember(mDto);
		
		// 인증 메일 보내기 
	    if (result == 1) {
	    	mailsender.mailSendWithUserKey(mDto.getEmail(), mDto.getNickname(), request);
	    	 return "redirect:/";
		} else {
			rttr.addAttribute("msg", "회원가입에 실패하였으니 다시 회원가입 바랍니다.");
			return "redirect:/member/signup";
		}
	    
	   
	}
	
	// 유저키 key 'Y'로 바꿔줌 
	@RequestMapping(value = "/key_alter", method = RequestMethod.GET)
	public String key_alterConfirm(@RequestParam("nickname") String nickname, @RequestParam("user_key") String key) throws Exception {
		mailsender.alter_userKey(nickname, key); 
		
		return "member/signupSuccess";
	}
	
	@RequestMapping(value = "/signupApp", method = RequestMethod.POST)
	public @ResponseBody int signupAppPOST(MemberDTO mDto, HttpServletRequest request) throws Exception{
		logger.info("signupApp GET .....................................");
		mDto.setPasswd(pwencoder.encode(mDto.getPasswd()));
		logger.info(mDto.toString());
		
		int result = 0;
		result = service.insertMember(mDto);
		
		if (result == 1) {
			mailsender.mailSendWithUserKey(mDto.getEmail(), mDto.getNickname(), request);
		}	
		
		logger.info("어플 회원가입 결과===>" + result);
		return result;
	}
	
	//이메일 중복 체크 
	@RequestMapping(value = "/emailCheck", method = RequestMethod.GET)
	@ResponseBody
	public int emailCheck(@RequestParam("email") String email) throws Exception {
		logger.info("emailCheck get .....................................");
		return service.emailCheck(email);
	}
	
	//닉네임 중복 체크 
	@RequestMapping(value = "/nickCheck", method = RequestMethod.GET)
	@ResponseBody
	public int nickCheck(@RequestParam("nickname") String nickname) throws Exception {
		logger.info("nickCheck get .....................................");
		return service.nickCheck(nickname);
	}
	

	
	// 어플 이미지 업로드 
	@RequestMapping(value = "/modifyMemberApp", method = RequestMethod.POST)
	@ResponseBody
	public int modifyMemberApp(HttpServletRequest request, MultipartFile uploadFile) throws Exception {
		logger.info("getInfo get .....................................");
	
		String nickname = (String) request.getParameter("nickname");
		logger.info("닉네임은 "+ nickname);
		
		String dbImgPath = (String) request.getParameter("image_path");
	
		
		MultipartRequest multi = (MultipartRequest)request;
		MultipartFile file = multi.getFile("uploaded_file");
		
			
		if(file != null) {
			String fileName = file.getOriginalFilename();
			System.out.println(fileName);

				
			if(file.getSize() > 0){			
				String realImgPath = "D:\\Study_Spring\\Bteam\\src\\main\\webapp\\resources\\fileUpload\\img";
				
				System.out.println( fileName + " : " + realImgPath);
				System.out.println( "fileSize : " + file.getSize());					
												
			 	try {
			 		// 이미지파일 저장
					file.transferTo(new File(realImgPath, fileName));										
				} catch (Exception e) {
					e.printStackTrace();
				} 
									
			}else{
				// 이미지파일 실패시
				fileName = "FileFail.jpg";
				String realImgPath = request.getSession().getServletContext()
						.getRealPath("/resources/images/" + fileName);
				System.out.println(fileName + " : " + realImgPath);
						
			}			
			
		}
		
		return service.modifyMemberApp(nickname, dbImgPath);
	}
	

	

	@GetMapping("/login")
	public void logingGET(@ModelAttribute("mDto") MemberDTO mDto, Model model) throws Exception{
		logger.info("login get get ......................................");
		
		Foodsafety food = new Foodsafety();
		ArrayList<CrawlerDTO> list = food.FoodsafetyInfo();
		logger.info("" + list);
		
		for (int i = 0; i < list.size(); i++) {
			String src = list.get(i).getSrc();

			model.addAttribute("food_info" + i, src);
		}
	}
	
	@PostMapping("/loginPost")
	public String loginPOST(MemberDTO mDto, RedirectAttributes rttr, Model model) throws Exception{
		String returnURL = "/";
		String y = "Y";			
		MemberDTO member = service.selectMember(mDto.getEmail());
		
		if(member != null) {
		String pw = member.getPasswd();
        String rawPw = mDto.getPasswd();
        if(pwencoder.matches(rawPw, pw)) {
            logger.info("비밀번호 일치");
            mDto.setPasswd(pw);
          }else {
            logger.info("비밀번호 불일치");    
          }  
		}else {
			logger.info("아이디 불일치");    
		}
		
		
		MemberDTO memInfo =  service.login(mDto);
		logger.info("memInfo =========>"+ memInfo);
		
		// 아이디 비번 오류로 로그인 실패 시 메세지 및 로그인 화면으로 리다이렉트 
		if (memInfo == null ) {
			rttr.addFlashAttribute("msg", "사용자의 ID 혹은 패스워드가 일치하지 않습니다.");
			return "redirect:/member/login";
		}
		
		// 인증 안 했을 경우 인증하란 메세지 발생
		if (!(memInfo.getUser_key().equals(y))) {
			logger.info("User key 난수 =========>"+ memInfo.getUser_key());
			rttr.addFlashAttribute("msg", "가입 시 입력하신 이메일 주소로 인증메일이 발송되었으니 인증 후 로그인 바랍니다.");
			return "redirect:/member/login";
		}
		
		model.addAttribute("memInfo", memInfo);
		
		return returnURL;
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		logger.info("logout ============================");
		
		Object obj = session.getAttribute("login");
		
		if (obj != null) {
			session.removeAttribute("login");
			session.invalidate();
		}
		
		//메인 페이지로 돌아가게 바꿈. 
		return "redirect:/";
	}
	
	
	//안드로이드 로그인 
	@RequestMapping(value = "/loginApp", method = RequestMethod.POST)
	public @ResponseBody Map<String, MemberDTO> loginAppPOST(MemberDTO mDto) throws Exception{
		logger.info("loginApp GET ......................................");
		logger.info(mDto.toString());
		
		MemberDTO member = service.selectMember(mDto.getEmail());
		
		if(member != null) {
		String pw = member.getPasswd();
        String rawPw = mDto.getPasswd();
	        if(pwencoder.matches(rawPw, pw)) {
	            logger.info("비밀번호 일치");
	            mDto.setPasswd(pw);
	          }else {
	            logger.info("비밀번호 불일치");    
	          }  
		}else {
			logger.info("아이디 불일치");    
		}
		               
		MemberDTO user = service.login(mDto);
		
		Map<String, MemberDTO> map = new HashMap<>();
		map.put("list", user);
		
		logger.info("======================="+map);

		return map;
	}
	
	// 비밀번호 찾기 페이지 이동
	@RequestMapping(value = "/pwFind")
	public void pwFindGet() throws Exception{
		logger.info("pwFind get ......................................");
	}
	
	// 비밀번호 찾기 확인 버튼 클릭 시
	@RequestMapping(value = "/pwFindPost", method = RequestMethod.POST)
	public String pwFindPost(@RequestParam("email")String email, RedirectAttributes rttr, HttpServletRequest request) throws Exception{
		logger.info("pwFind post ......................................");
		
		int result = service.emailCheck(email);
		logger.info("회원여부 체크 result====>" + result);
		
		// 인증 메일 보내기 
	    if (result == 1) {
	    	mailsender.mailSendWithPassword(email, request);		    
		    rttr.addFlashAttribute("msg", "이메일 주소로 임시비밀번호를 전송하였으니 확인 바랍니다.");
		} else {
			rttr.addFlashAttribute("msg", "등록되지 않은 이메일주소입니다.");
			return "redirect:/member/pwFind";
		}
		return "redirect:/member/login";
	}
	
	// 어플 비밀번호 찾기 
	@RequestMapping(value = "/pwFindApp", method = RequestMethod.GET)
	public @ResponseBody int pwFindApp(@RequestParam("email")String email, HttpServletRequest request) throws Exception{
		logger.info("pwFind post ......................................");
		
		int result = service.emailCheck(email);
		logger.info("회원여부 체크 result====>" + result);
		
		// 인증 메일 보내기 
	    if (result == 1) {
	    	mailsender.mailSendWithPassword(email, request);		    
		} else {
			return result;
		}
	    
		return result;
	}
	
		
	
	// 마이페이지 이동
	@RequestMapping(value = "/mypage")
	public void mypage() throws Exception{
		logger.info("mypage get ......................................");
			
	}
	
	// 마이페이지 회원정보 수정 
	@RequestMapping(value = "/update_mypage", method = RequestMethod.POST)
	public String updateMember(MemberDTO mDto, RedirectAttributes rttr) throws Exception {
		logger.info("update_mypage post ......................................");
		
		if(service.updateMember(mDto)) {
			rttr.addFlashAttribute("msg", "회원정보 수정이 완료되었습니다.");
			logger.info("회원정보 수정 성공~~~~~");
		}
		//메인 페이지로 돌아가게 바꿈.
		return "redirect:/";
	}
	
	// 마이페이지 비밀번호 변경
	@RequestMapping(value = "/update_pw", method = RequestMethod.POST)
	public String update_Pw(MemberDTO mDto, @RequestParam("old_pw") String old_pw, HttpServletResponse response, RedirectAttributes rttr) throws Exception{
		logger.info("update_pw post ......................................");
		
		if(service.updatePw(mDto, old_pw, response)) {
			rttr.addFlashAttribute("msg", "비밀번호가 변경되었습니다.");
			logger.info("비밀번호 변경 성공~~~~~");
			return "redirect:/member/mypage";
		} else {
			logger.info("비밀번호 변경 실패~~~~~");
			return null;
		}
			
	}
	
	// 어플용 마이페이지 비밀번호 변경
	@RequestMapping(value = "/update_pwApp", method = RequestMethod.POST)
	@ResponseBody
	public int update_PwApp(MemberDTO mDto, @RequestParam("old_pw") String old_pw) throws Exception{
		logger.info("update_pw post ......................................");
		
		if(service.updatePwApp(mDto, old_pw)) {
			logger.info("비밀번호 변경 성공~~~~~");
			return 1;
		} else {
			logger.info("비밀번호 변경 실패~~~~~");
			return 0;
		}
			
	}
	
	//회원탈퇴
	@RequestMapping(value = "/delmember", method = RequestMethod.POST)
	public String withdrawal_form(@ModelAttribute MemberDTO mDto, HttpSession session, HttpServletResponse response) throws Exception{
		if(service.delmember(mDto, response)) {
			session.removeAttribute("login");
			session.invalidate();
			logger.info("탈퇴성공~~~~~");
			return "redirect:/	";
		}else {
			logger.info("탈퇴 실패~~~~~");
			return null;
		}
	}
	
	//App용 회원탈퇴
	@RequestMapping(value = "/delmemberApp", method = RequestMethod.POST)
	@ResponseBody
	public int withdrawal_formApp(@ModelAttribute MemberDTO mDto) throws Exception{
		if(service.delmemberApp(mDto)) {			
			logger.info("탈퇴성공~~~~~");
			return 1;
		}else {
			logger.info("탈퇴 실패~~~~~");
			return 0;
		}
	}
	
}
