package kr.co.bteam;


import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.bteam.marketBoard.service.IMarketBoard_Service;
import kr.co.bteam.noticeBoard.Criteria;
import kr.co.bteam.noticeBoard.domain.PageDTO;
import kr.co.bteam.noticeBoard.service.INoticeService;
import kr.co.bteam.streamView.service.IStreamService;


/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	@Autowired
	private INoticeService nService;
	
	@Autowired
	private IMarketBoard_Service mService;
	
	@Autowired
	private IStreamService stService;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 * 
	 * @throws Exception
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Criteria cri, Model model) throws Exception {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		
		model.addAttribute("noticeList", nService.listforHome());	
		int nTotal = nService.getTotalCnt(cri);		
		model.addAttribute("nPageMaker", new PageDTO(cri, nTotal));
		
		model.addAttribute("marketList", mService.listAllApp());
		int mTotal = mService.getTotalCnt(cri);
		model.addAttribute("mPageMaker", new PageDTO(cri, mTotal));
		
		model.addAttribute("streamList", stService.listAllApp());
		int stTotal = stService.getTotalCnt(cri);
		model.addAttribute("stPageMaker", new PageDTO(cri, mTotal));
		
		
			
		
		return "home";
	}
	
	@RequestMapping("/map")
	public void map() {
		logger.info("map 입장");
	}

}
