package kr.co.bteam.admin;

import java.util.List;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.co.bteam.marketBoard.MarketBoardController;
import kr.co.bteam.member.domain.MemberDTO;
import kr.co.bteam.member.mapper.MemberMapper;
import kr.co.bteam.member.service.IMemberService;
import kr.co.bteam.noticeBoard.Criteria;
import kr.co.bteam.noticeBoard.domain.PageDTO;

@Controller
@RequestMapping("/admin")
public class AdminsController {
	
	private static final Logger log = LoggerFactory.getLogger(AdminsController.class);
	@Autowired
	  private JavaMailSender mailSender;
	  
	
	@Autowired
	private IMemberService service;
	
	@RequestMapping("/index")
	public void index(Model model, Criteria cri) {
		log.info("관리자 페이지 접속");
	 
        model.addAttribute("list", service.getListwithPaging(cri));
        
        int total = service.getTotalCnt(cri);
        log.info("회원 명수는 ????" + total);
        
        model.addAttribute("pageMaker", new PageDTO(cri, total));
		
	}
	
	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	public String remove(@RequestParam("meno") int meno, @ModelAttribute("cri") Criteria cri, RedirectAttributes rttr) throws Exception{
		log.info("remove ......................................");
		
		if(service.banMember(meno) == 1) {
			rttr.addFlashAttribute("result", "삭제 성공");
		}
		
		return "redirect:/admin/index" + cri.getListLink();
	}
	

	@GetMapping("/login")
	public void loginInput(String error, String logout, Model model) {
		
		log.info("error: " + error );
		log.info("logout: " + logout );
		
		
		if (error != null) {
			model.addAttribute("error", "Login Error Check Your Accout");
		}
		if (logout != null) {
			model.addAttribute("logout", "Logout!!");
		}
		
	}
	
	@GetMapping("/logout")
	public void logoutGET() {

		log.info("custom logout");
	}

	@PostMapping("/logout")
	public void logoutPost() {

		log.info("post custom logout");
	}
	
	
	 
	  // mailForm
	  @RequestMapping(value = "/mailForm", method = RequestMethod.POST)
	  public void mailForm(Model model, @RequestParam String email) {
		  log.info("mailForm post................");
		  
		  model.addAttribute("email", email);
		  
	  }  
	  
	  
	  // 1:1 문의 메일 발송 
	  @RequestMapping(value = "/mailSending", method = RequestMethod.POST)
	  public String mailSending(RedirectAttributes rttr, HttpServletRequest request) {
		  log.info("mailSending Start................");
	    String toAdmin = "wlghsp@gmail.com";   //관리자 메일 주소       
	    String userEmail  = request.getParameter("userEmail");     // 보낸 사람 이메일
	    String title   = request.getParameter("title");      // 제목
	    String content = request.getParameter("content");    // 내용
	    String titleFinal = "[안내]  " + title; 
	    String contentFinal = "<h2>누트리에서"+ "("+userEmail+")" + "님에게 메일이 도착하였습니다.</h2><br><br>" 
	    						+ "<h3>작성내용<br>"+ content + "</h3>" ; 
	    
	    try {
	      MimeMessage message = mailSender.createMimeMessage();
	      MimeMessageHelper messageHelper 
	                        = new MimeMessageHelper(message, true, "UTF-8");
	 
	      messageHelper.setFrom(toAdmin);  // 보내는사람 생략하거나 하면 정상작동을 안함 . 하지만 메일에는 나오지 않음. 
	      messageHelper.setTo(userEmail);     // 받는사람 이메일 -관리자. 
	      messageHelper.setSubject(title); // 메일제목은 생략이 가능하다
	      messageHelper.setText(contentFinal, true);  // 메일 내용, true는 html 사용 허가 
	      mailSender.send(message);
	      rttr.addFlashAttribute("msg", "회원에게 메일이 발송되었습니다");
	    } catch(Exception e){
	      System.out.println(e);
	    }
	    
	    return "redirect:/admin/index";
	  }
	  
	  // mailForm
	  @RequestMapping(value = "/bulkMail", method = RequestMethod.GET)
	  public void bulkMail() {
		  log.info("BulkMailForm get................");
		  
		  
	  }  
	  
	  
	  // 1:1 문의 메일 발송 
	  @RequestMapping(value = "/bulkMailSending", method = RequestMethod.POST)
	  public String bulkMailSending(RedirectAttributes rttr, HttpServletRequest request) {
		  log.info("mailSending Start................");
	    String admin = "wlghsp@gmail.com";   //관리자 메일 주소       
	    String title   = request.getParameter("title");      // 제목
	    String content = request.getParameter("content");    // 내용
	    String titleFinal = "[안내]  " + title; 
	    String contentFinal = "<h2>누트리에서 메일이 도착하였습니다.</h2><br><br>" 
	    						+ "<h3>작성내용<br>"+ content + "</h3>" ; 
	    
	    List<MemberDTO> mails = service.selectAll();
	    MimeMessagePreparator[] preparators = new MimeMessagePreparator[mails.size()];
	    int i = 0;
	    for (final MemberDTO mDto : mails) {
			preparators[i++] = new MimeMessagePreparator() {
				@Override
				public void prepare(MimeMessage mimeMessage) throws Exception {
					 final MimeMessageHelper helper  = new MimeMessageHelper(mimeMessage, true, "UTF-8");
					 helper.setFrom(admin); 
					 helper.setTo(mDto.getEmail()); 
					 helper.setSubject(titleFinal); 
					 helper.setText(contentFinal, true);
				}
			};
		}
	
	    mailSender.send(preparators);
	    rttr.addFlashAttribute("msg", "전체 메일이 발송되었습니다");
	    
	    return "redirect:/admin/index";
	  }
}
