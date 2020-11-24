package kr.co.bteam.marketBoard;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.co.bteam.marketBoard.domain.MarketBoardAttachDTO;
import kr.co.bteam.marketBoard.domain.MarketBoardDTO;
import kr.co.bteam.marketBoard.service.IMarketBoard_Service;
import kr.co.bteam.noticeBoard.Criteria;
import kr.co.bteam.noticeBoard.domain.PageDTO;

@Controller
@RequestMapping("/market_board")
public class MarketBoardController {
	
	private String uploadPath = "D:\\project_spring\\Bteam\\src\\main\\webapp\\resources\\fileUpload";
	
	private static final Logger logger = LoggerFactory.getLogger(MarketBoardController.class);

	@Autowired
	private IMarketBoard_Service service;

	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public void registerGET() throws Exception {
		logger.info("register get................");
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registerPOST(MarketBoardDTO mbDto, RedirectAttributes rttr){
		logger.info("register post................");
		if(mbDto.getAttachList() != null) {
			mbDto.getAttachList().forEach(attach -> logger.info(""+attach));
		}
		service.register(mbDto);
		
		logger.info(mbDto.toString());
		rttr.addFlashAttribute("result", mbDto.getM_no());

		return "redirect:/market_board/list";
	}
	
//	@RequestMapping(value = "/registerApp", method = RequestMethod.GET)
//	public @ResponseBody int registerApp(MarketBoardDTO mbDto, RedirectAttributes rttr) {
//		logger.info("register post................");
//		
//		if(mbDto.getAttachList() != null) {
//			mbDto.getAttachList().forEach(attach -> logger.info(""+attach));
//		}		
//		service.register(mbDto);
//		rttr.addFlashAttribute("result", mbDto.getM_no());
//		
//		return result;		
//	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public void listAll(Criteria cri, Model model){
		logger.info("show all list .....................................");
		
		model.addAttribute("list", service.listAll(cri));
		model.addAttribute("image", service.imageList());
		int total = service.getTotalCnt(cri);
		logger.info("total : " + total);
		
		model.addAttribute("pageMaker", new PageDTO(cri, total));
	}
	
	@GetMapping("/listApp")
	public @ResponseBody Map<String, List<MarketBoardDTO>> listAllApp(){
		logger.info("listApp Get......................");
		
		List<MarketBoardDTO>list = service.listAllApp();
		logger.info("멀까요????????" + list);		
		Map<String, List<MarketBoardDTO>> map = new HashMap<>();
		
		map.put("marketList", list);
		
		return map;
	}
	
	@RequestMapping(value = {"/read", "/modify"}, method = RequestMethod.GET)
	public void readAndModify(@RequestParam("m_no") int m_no, @ModelAttribute("cri") Criteria cri, Model model){
		model.addAttribute("market_board", service.read(m_no));
	}
	
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modifyPOST(MarketBoardDTO mbDto, @ModelAttribute("cri") Criteria cri, RedirectAttributes rttr){
		logger.info("modify post ......................................");
		
		if(service.modify(mbDto)==1) {
			rttr.addFlashAttribute("result", "success");
		}
		return "redirect:/market_board/list" + cri.getListLink();
	}
	
	@RequestMapping(value = "/modifyApp", method = RequestMethod.POST)
	public int modifyAppPOST(MarketBoardDTO mbDto){
		logger.info("modify post App ......................................");
		
		int result = service.modify(mbDto);
		
		return result;
	}
	

	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	public String remove(@RequestParam("m_no") int m_no, @ModelAttribute("cri") Criteria cri, RedirectAttributes rttr){
		logger.info("remove ......................................");
		
		List<MarketBoardAttachDTO> attachList = service.getAttachList(m_no);
				
		if(service.remove(m_no)==1) {
			deleteFile(attachList);
			rttr.addFlashAttribute("result", "success");
			logger.info("삭제 성공");
		}				
		
		return "redirect:/market_board/list" + cri.getListLink();
	}
	
	@RequestMapping(value = "/removeApp", method = RequestMethod.POST)
	public int removeApp(@RequestParam("m_no") int m_no){
		logger.info("remove ......................................");
		List<MarketBoardAttachDTO> attachList = service.getAttachList(m_no);
		
		int result = service.remove(m_no);
		if(service.remove(m_no)==1) {
			deleteFile(attachList);			
		}
		
		return result;
	}
	
	
	@GetMapping(value = "/getAttachList",
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseEntity<List<MarketBoardAttachDTO>> getAttachList(int m_no){
		logger.info("getAttachList bno" + m_no);
		logger.info("service bno" + service.getAttachList(m_no));
		return new ResponseEntity<>(service.getAttachList(m_no),HttpStatus.OK);
	}
	
	private void deleteFile(List<MarketBoardAttachDTO> attachList) {
		if(attachList == null || attachList.size()==0) {
			return;
		}
		
		logger.info("delete attach files.........................");
		logger.info("" + attachList);
		
		attachList.forEach(attach -> {
			try {
				//원본삭제
				Path file = Paths.get(uploadPath + "\\" + attach.getUploadPath() 
				+ "\\" + attach.getUuid() + "_" +attach.getFileName());
				
				Files.deleteIfExists(file);
				//섬네일삭제
				if(Files.probeContentType(file).startsWith("image")) {
					Path thumNail = Paths.get(uploadPath + "\\" + attach.getUploadPath() + "\\s_" 
								+ attach.getUuid() + "_" +attach.getFileName());
					Files.deleteIfExists(thumNail);
				}
				
			} catch (IOException e) {
				logger.error("delete file error : " + e.getMessage());
			}
		});
	}
	


}
