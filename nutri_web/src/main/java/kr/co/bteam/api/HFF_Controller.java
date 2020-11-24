package kr.co.bteam.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.bteam.api.domain.HFF_IngredientDTO;
import kr.co.bteam.api.domain.HFF_MaterialDTO;
import kr.co.bteam.api.service.IHFF_Service;
import kr.co.bteam.noticeBoard.Criteria;
import kr.co.bteam.noticeBoard.NoticeBoardController;
import kr.co.bteam.noticeBoard.domain.PageDTO;

@Controller
@RequestMapping("/hff_board")
public class HFF_Controller {
	private static final Logger log = LoggerFactory.getLogger(NoticeBoardController.class);
	@Autowired
	private IHFF_Service service;
	
//	@RequestMapping("/iListApp")
//	public @ResponseBody Map<String, List<HFF_IngredientDTO>> getIList(String search) throws Exception{
//		log.info("getIList search============================" + search);
//		
//		if(search !=null) {
//			List<HFF_IngredientDTO> iList =service.getIList(search);
//			log.info("getIList============================" + iList);
//			Map<String, List<HFF_IngredientDTO>> map = new HashMap<>();
//			
//			map.put("iList",iList);
//	
//			return map;
//		}else {
//			log.info("검색값 없음");
//			return null;
//		}
//	}
//	
//	@RequestMapping("/mListApp")
//	public @ResponseBody Map<String, List<HFF_MaterialDTO>> getMList(String search) throws Exception{
//		log.info("getIList search============================" + search);
//		
//		if(search !=null) {
//			List<HFF_MaterialDTO> mList =service.getMList(search);
//			Map<String, List<HFF_MaterialDTO>> map = new HashMap<>();
//			
//			map.put("mList",mList);
//	
//			return map;
//		}else {
//			log.info("검색값 없음");
//			return null;
//		}
//	}
	
	@GetMapping("/iRead")
	public void iRead(@RequestParam("INO") int INO, @ModelAttribute("cri") Criteria cri, Model model){
		model.addAttribute("ingredient", service.iRead(INO));
	}
	
	@GetMapping("/mRead")
	public void mRead(@RequestParam("MNO") int MNO, @ModelAttribute("cri") Criteria cri, Model model){
		model.addAttribute("material", service.mRead(MNO));
	}
	
	
	@RequestMapping(value = "/iList", method = RequestMethod.GET)
	public void iList(Criteria cri, Model model){
		log.info("show all list .....................................");
		
		model.addAttribute("iList", service.iListWeb(cri));
		
		int total = service.getITotalCnt(cri);
		log.info("total : " + total);
		
		model.addAttribute("pageMaker", new PageDTO(cri, total));
	}
	
	@RequestMapping(value = "/mList", method = RequestMethod.GET)
	public void mList(Criteria cri, Model model){
		log.info("show all list .....................................");
		
		model.addAttribute("mList", service.mListWeb(cri));
		
		int total = service.getMTotalCnt(cri);
		log.info("total : " + total);
		
		model.addAttribute("pageMaker", new PageDTO(cri, total));
	}
	
	
	@RequestMapping("/AllListApp")
	public @ResponseBody Map<String,Object> getAllList(String search) throws Exception{
		log.info("getIList search============================" + search);

		if(search != null) {
			List<HFF_MaterialDTO> mList =service.mListApp(search);
			Map<String, Object> map = new HashMap<>();
			List<HFF_IngredientDTO> iList =service.iListApp(search);
			log.info("getIList============================" + iList);
						
			map.put("iList",iList);
			map.put("mList",mList);
	
			return map;
		}else {
			log.info("검색값 없음");
			return null;
		}
	}
	

}
