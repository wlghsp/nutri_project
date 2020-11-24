package kr.co.bteam.noticeBoard;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.co.bteam.noticeBoard.domain.NoticeBoardDTO;
import kr.co.bteam.noticeBoard.domain.PageDTO;
import kr.co.bteam.noticeBoard.service.INoticeService;



@Controller
@RequestMapping("/notice_board")
public class NoticeBoardController {
	private static final Logger logger = LoggerFactory.getLogger(NoticeBoardController.class);
	
	@Autowired
	private INoticeService service;
	
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	
	public void listAll(Criteria cri, Model model) throws Exception{
		logger.info("show all list .....................................");
		
		model.addAttribute("list", service.listAll(cri));
		
		int total = service.getTotalCnt(cri);
		logger.info("total : " + total);
		
		model.addAttribute("pageMaker", new PageDTO(cri, total));

	}
	
	@RequestMapping(value = "/listApp", method = RequestMethod.GET)
	public @ResponseBody Map<String, List<NoticeBoardDTO>> listApp() throws Exception{
		logger.info("/listApp.................");
		
		List<NoticeBoardDTO> dto= service.listforHome();
		logger.info(""+dto);
		Map<String, List<NoticeBoardDTO>> map = new HashMap<>();
		map.put("list", dto);

		return map;
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public void regsterGET() throws Exception{
		logger.info("register get ......................................");
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String regsterPOST(NoticeBoardDTO nbDto, RedirectAttributes rttr) throws Exception{
		logger.info("register post .....................................");	
		logger.info(nbDto.toString());
		
		service.register(nbDto);
		
		logger.info(nbDto.toString());
		rttr.addFlashAttribute("result", nbDto.getNbno());
		
		return "redirect:/notice_board/list";
	}
	
	@RequestMapping(value = "/registerApp", method = RequestMethod.POST)
	public int regsterAppPOST(NoticeBoardDTO nbDto, RedirectAttributes rttr) throws Exception{
		logger.info("register post .....................................");	
		logger.info(nbDto.toString());
		
		int result = service.register(nbDto);
		
		logger.info(nbDto.toString());
		rttr.addFlashAttribute("result", nbDto.getNbno());
		
		return result;
	}
	
	@RequestMapping(value = {"/read", "/modify"}, method = RequestMethod.GET)
	public void modifyGET(@RequestParam("nbno") int nbno, @ModelAttribute("cri") Criteria cri, Model model) throws Exception{
		model.addAttribute("board", service.read(nbno));
	}
	
	@RequestMapping(value = {"/readApp"}, method = RequestMethod.GET)
	public @ResponseBody Map<String, NoticeBoardDTO> readAPPGET(@RequestParam("nbno") int nbno) throws Exception{
		NoticeBoardDTO nbdto = service.read(nbno);
		Map<String, NoticeBoardDTO> map = new HashMap<>();
		map.put("list", nbdto);
		return map;
	}
	
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modifyPOST(NoticeBoardDTO nbDto, @ModelAttribute("cri") Criteria cri, RedirectAttributes rttr) throws Exception{
		logger.info("modify post ......................................");
		
		if(service.modify(nbDto)) {
			rttr.addFlashAttribute("result", "success");
		}
		//실패했을시도 고려해야함 if문으로 해야할듯....
		
		return "redirect:/notice_board/list" + cri.getListLink();
	}
	
	@RequestMapping(value = "/modifyApp", method = RequestMethod.POST)
	public boolean modifyAppPOST(NoticeBoardDTO nbDto, @ModelAttribute("cri") Criteria cri) throws Exception{
		logger.info("modify post ......................................");
		
		boolean result = service.modify(nbDto);

		return result;
	}
	
	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	public String remove(@RequestParam("nbno") int nbno, @ModelAttribute("cri") Criteria cri, RedirectAttributes rttr) throws Exception{
		logger.info("remove ......................................");
		
		if(service.remove(nbno)) {
			rttr.addFlashAttribute("result", "success");
		}
		
		return "redirect:/notice_board/list" + cri.getListLink();
	}
	
	@RequestMapping(value = "/removeAll", method = RequestMethod.POST)
	public boolean removeAll(@RequestParam("nbno") int nbno, @ModelAttribute("cri") Criteria cri) throws Exception{
		logger.info("remove ......................................");
		
		boolean reseult = service.remove(nbno);
		
		return reseult;
	}
	
}















