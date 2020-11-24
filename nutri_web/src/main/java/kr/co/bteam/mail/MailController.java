package kr.co.bteam.mail;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.co.bteam.marketBoard.MarketBoardController;

@Controller
@RequestMapping("/mail")
public class MailController {
 
  @Autowired
  private JavaMailSender mailSender;
  
  private static final Logger logger = LoggerFactory.getLogger(MarketBoardController.class);
 
  // mailForm
  @RequestMapping(value = "/mailForm", method = RequestMethod.GET)
  public void mailForm() {
	  logger.info("mailForm get................");
   
  }  
 
  // 1:1 문의 메일 발송 
  @RequestMapping(value = "/mailSending")
  public String mailSending(RedirectAttributes rttr, HttpServletRequest request) {
	  logger.info("mailSending Start................");
    String toAdmin = "wlghsp@gmail.com";   //관리자 메일 주소       
    String userEmail  = request.getParameter("userEmail");     // 보낸 사람 이메일
    String userNickname  = request.getParameter("userNickname");     // 보낸 사람 닉네임
    String title   = request.getParameter("title");      // 제목
    String content = request.getParameter("content");    // 내용
    String titleFinal = "[1:1문의]  " + title; 
    String contentFinal = "<h2>"+ userNickname +"("+userEmail+")" + "님으로부터 1:1문의가 도착하였습니다.</h2><br><br>" 
    						+ "<h3>작성내용<br>"+ content + "</h3>" ; 
    
    try {
      MimeMessage message = mailSender.createMimeMessage();
      MimeMessageHelper messageHelper 
                        = new MimeMessageHelper(message, true, "UTF-8");
 
      messageHelper.setFrom(userEmail);  // 보내는사람 생략하거나 하면 정상작동을 안함 . 하지만 메일에는 나오지 않음. 
      messageHelper.setTo(toAdmin);     // 받는사람 이메일 -관리자. 
      messageHelper.setSubject(titleFinal); // 메일제목은 생략이 가능하다
      messageHelper.setText(contentFinal, true);  // 메일 내용, true는 html 사용 허가 
      mailSender.send(message);
      rttr.addFlashAttribute("msg", "관리자에게 메일이 발송되었습니다");
    } catch(Exception e){
      System.out.println(e);
    }
    
    return "redirect:/";
  }
} 


