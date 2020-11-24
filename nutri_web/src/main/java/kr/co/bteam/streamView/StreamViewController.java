package kr.co.bteam.streamView;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.co.bteam.noticeBoard.Criteria;
import kr.co.bteam.noticeBoard.domain.PageDTO;
import kr.co.bteam.streamView.domain.StreamDTO;
import kr.co.bteam.streamView.service.IStreamService;

@Controller
@RequestMapping("/video")
public class StreamViewController {
	
	@Autowired
	private IStreamService stService;
	
	private static final Logger log = LoggerFactory.getLogger(StreamViewController.class);
	
	@GetMapping("/list")
	public void listAll(Criteria cri, Model model){
		log.info("show all list .....................................");
		
		model.addAttribute("list", stService.listAll(cri));
		
		int total = stService.getTotalCnt(cri);
		log.info("total : " + total);
		
		model.addAttribute("pageMaker", new PageDTO(cri, total));
	}
	
	@GetMapping("/listApp")
	@ResponseBody
	public Map<String, List<StreamDTO>> listAllApp(){
		log.info("listAllapp...........................");
		List<StreamDTO> list=stService.listAllApp();
		
		Map<String, List<StreamDTO>> vMap = new HashMap<String, List<StreamDTO>>();
		vMap.put("vList", list);
		log.info("비디오 데이터는????" + vMap);
		return vMap;
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public void registerGET() throws Exception {
		log.info("register get................");
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registerPOST(StreamDTO mvDto, RedirectAttributes rttr){
		log.info("register post................");
		
		stService.register(mvDto);
		
		log.info(mvDto.toString());
		rttr.addFlashAttribute("result", mvDto.getMvno());

		return "redirect:/video/list";
	}
	
	@RequestMapping(value = {"/read", "/modify"}, method = RequestMethod.GET)
	public void readAndModify(@RequestParam("mvno") int mvno, @ModelAttribute("cri") Criteria cri, Model model){
		model.addAttribute("video", stService.read(mvno));
	}
	
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modifyPOST(StreamDTO mvDto, @ModelAttribute("cri") Criteria cri, RedirectAttributes rttr){
		log.info("modify post ......................................");
		
		if(stService.modify(mvDto)==1) {
			rttr.addFlashAttribute("result", "success");
		}
		return "redirect:/video/list" + cri.getListLink();
	}
	
	@RequestMapping(value = "/modifyApp", method = RequestMethod.POST)
	public int modifyAppPOST(StreamDTO mvDto){
		log.info("modify post App ......................................");
		
		int result = stService.modify(mvDto);
		
		return result;
	}
	
	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	public String remove(@RequestParam("mvno") int mvno, @ModelAttribute("cri") Criteria cri, RedirectAttributes rttr){
		log.info("remove ......................................");
						
		stService.remove(mvno);
				
		return "redirect:/video/list" + cri.getListLink();
	}
	
	@GetMapping("/streamView")
	public void StreamView() {
		log.info("StreamView.............................");
	}
}
