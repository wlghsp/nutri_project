package kr.co.bteam.myHealth;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.co.bteam.member.domain.MemberDTO;
import kr.co.bteam.myHealth.domain.MyHealthDTO;
import kr.co.bteam.myHealth.domain.RecordDTO;
import kr.co.bteam.myHealth.domain.monthDTO;
import kr.co.bteam.myHealth.service.IMyHealthService;
import kr.co.bteam.myHealth.service.IRecordService;

@Controller
@RequestMapping("/myhealth")
public class MyHealthContoller {
		
	@Autowired
	IMyHealthService service;
	
	@Autowired
	IRecordService reService;
	
	private static final Logger log = LoggerFactory.getLogger(MyHealthContoller.class);
	
	
	
	
	@RequestMapping("/nutrient")
	public void MyHealth(Model model, HttpServletRequest request) {
		MemberDTO mDto =new MemberDTO();
		HttpSession session = request.getSession();
		mDto = (MemberDTO) session.getAttribute("login");
		log.info(""+ mDto);
		List<MyHealthDTO> list = null;
		if(mDto!=null) {
			list = service.read(mDto);			
			log.info("list=================="+list);
			for (int i = 0; i < list.size(); i++) {
				if(list.get(i).getNutrient().equals("vitamin_a")) {
					list.get(i).setNutrient("비타민A (단위 : μg RAE)");
				}
				if(list.get(i).getNutrient().equals("vitamin_d")) {
					list.get(i).setNutrient("비타민D (단위: μg)");
				}
				if(list.get(i).getNutrient().equals("vitamin_e")) {
					list.get(i).setNutrient("비타민E (단위: mg α-TE)");
				}
				if(list.get(i).getNutrient().equals("vitamin_k")) {
					list.get(i).setNutrient("비타민K (단위: μg)");
				}
				if(list.get(i).getNutrient().equals("vitamin_c")) {
					list.get(i).setNutrient("비타민C (단위: mg)");
				}
				if(list.get(i).getNutrient().equals("thiamine")) {
					list.get(i).setNutrient("티아민 (단위: mg)");
				}
				if(list.get(i).getNutrient().equals("riboflavin")) {
					list.get(i).setNutrient("리보플라빈 (단위: mg)");
				}
				if(list.get(i).getNutrient().equals("niacin")) {
					list.get(i).setNutrient("나이아신 (단위: mg NE)");
				}
				if(list.get(i).getNutrient().equals("vitamin_b6")) {
					list.get(i).setNutrient("비타민B6 (단위: mg)");
				}
				if(list.get(i).getNutrient().equals("folate")) {
					list.get(i).setNutrient("엽산 (단위: μg DFE)");
				}
				if(list.get(i).getNutrient().equals("vitamin_b12")) {
					list.get(i).setNutrient("비타민B12 (단위: μg)");
				}
				if(list.get(i).getNutrient().equals("pantothenic_acid")) {
					list.get(i).setNutrient("판토텐산 (단위: mg)");
				}
				if(list.get(i).getNutrient().equals("biotin")) {
					list.get(i).setNutrient("비오틴 (단위: μg)");
				}
				if(list.get(i).getNutrient().equals("calcium")) {
					list.get(i).setNutrient("칼슘 (단위: mg)");
				}
				if(list.get(i).getNutrient().equals("natrium")) {
					list.get(i).setNutrient("나트륨 (단위: mg)");
				}
				if(list.get(i).getNutrient().equals("potassium")) {
					list.get(i).setNutrient("칼륨 (단위: mg)");
				}
				if(list.get(i).getNutrient().equals("magnesium")) {
					list.get(i).setNutrient("마그네슘 (단위: mg)");
				}
				if(list.get(i).getNutrient().equals("iron")) {
					list.get(i).setNutrient("철분 (단위: mg)");
				}
				if(list.get(i).getNutrient().equals("zinc")) {
					list.get(i).setNutrient("아연 (단위: mg)");
				}
				if(list.get(i).getNutrient().equals("iodine")) {
					list.get(i).setNutrient("요오드 (단위: μg)");
				}
				if(list.get(i).getNutrient().equals("selenium")) {
					list.get(i).setNutrient("셀레늄 (단위: μg)");
				}
				if(list.get(i).getNutrient().equals("cromium")) {
					list.get(i).setNutrient("크롬 (단위: μg)");
				}
				
				if(list.get(i).getGender().equals("Toddler")) {
					list.get(i).setGender("유아");
				}
				if(list.get(i).getGender().equals("male")) {
					list.get(i).setGender("남성");
				}
				if(list.get(i).getGender().equals("female")) {
					list.get(i).setGender("여성");
				}
			}
			
		}
		model.addAttribute("nutrient", list);
	}
	
	@RequestMapping("/nutrientApp")
	@ResponseBody
	public Map<String, List<MyHealthDTO>> MyHealthApp(@RequestParam("nickname") String nickname) {

		MemberDTO mDto = service.selmemberApp(nickname);
		
		log.info(""+ mDto);
		List<MyHealthDTO> list = null;
		if(mDto!=null) {
			list = service.read(mDto);			
			log.info("list=================="+list);
			for (int i = 0; i < list.size(); i++) {
				if(list.get(i).getNutrient().equals("vitamin_a")) {
					list.get(i).setNutrient("비타민A (단위 : μg RAE)");
				}
				if(list.get(i).getNutrient().equals("vitamin_d")) {
					list.get(i).setNutrient("비타민D (단위: μg)");
				}
				if(list.get(i).getNutrient().equals("vitamin_e")) {
					list.get(i).setNutrient("비타민E (단위: mg α-TE)");
				}
				if(list.get(i).getNutrient().equals("vitamin_k")) {
					list.get(i).setNutrient("비타민K (단위: μg)");
				}
				if(list.get(i).getNutrient().equals("vitamin_c")) {
					list.get(i).setNutrient("비타민C (단위: mg)");
				}
				if(list.get(i).getNutrient().equals("thiamine")) {
					list.get(i).setNutrient("티아민 (단위: mg)");
				}
				if(list.get(i).getNutrient().equals("riboflavin")) {
					list.get(i).setNutrient("리보플라빈 (단위: mg)");
				}
				if(list.get(i).getNutrient().equals("niacin")) {
					list.get(i).setNutrient("나이아신 (단위: mg NE)");
				}
				if(list.get(i).getNutrient().equals("vitamin_b6")) {
					list.get(i).setNutrient("비타민B6 (단위: mg)");
				}
				if(list.get(i).getNutrient().equals("folate")) {
					list.get(i).setNutrient("엽산 (단위: μg DFE)");
				}
				if(list.get(i).getNutrient().equals("vitamin_b12")) {
					list.get(i).setNutrient("비타민B12 (단위: μg)");
				}
				if(list.get(i).getNutrient().equals("pantothenic_acid")) {
					list.get(i).setNutrient("판토텐산 (단위: mg)");
				}
				if(list.get(i).getNutrient().equals("biotin")) {
					list.get(i).setNutrient("비오틴 (단위: μg)");
				}
				if(list.get(i).getNutrient().equals("calcium")) {
					list.get(i).setNutrient("칼슘 (단위: mg)");
				}
				if(list.get(i).getNutrient().equals("natrium")) {
					list.get(i).setNutrient("나트륨 (단위: mg)");
				}
				if(list.get(i).getNutrient().equals("potassium")) {
					list.get(i).setNutrient("칼륨 (단위: mg)");
				}
				if(list.get(i).getNutrient().equals("magnesium")) {
					list.get(i).setNutrient("마그네슘 (단위: mg)");
				}
				if(list.get(i).getNutrient().equals("iron")) {
					list.get(i).setNutrient("철분 (단위: mg)");
				}
				if(list.get(i).getNutrient().equals("zinc")) {
					list.get(i).setNutrient("아연 (단위: mg)");
				}
				if(list.get(i).getNutrient().equals("iodine")) {
					list.get(i).setNutrient("요오드 (단위: μg)");
				}
				if(list.get(i).getNutrient().equals("selenium")) {
					list.get(i).setNutrient("셀레늄 (단위: μg)");
				}
				if(list.get(i).getNutrient().equals("cromium")) {
					list.get(i).setNutrient("크롬 (단위: μg)");
				}
				
				if(list.get(i).getGender().equals("Toddler")) {
					list.get(i).setGender("유아");
				}
				if(list.get(i).getGender().equals("male")) {
					list.get(i).setGender("남성");
				}
				if(list.get(i).getGender().equals("female")) {
					list.get(i).setGender("여성");
				}
			}
			
		}
		log.info("결과는????" + list);
		
		Map<String, List<MyHealthDTO>> hMap = new HashMap<String, List<MyHealthDTO>>();
		hMap.put("nutrient", list);
		
		return hMap;
	}
	
	@GetMapping("/record")
	public void record(@Param("pcnt") String pcnt, @Param("scnt") String scnt, @Param("nickname") String nickname,
			HttpServletRequest request) {
		log.info("pcnt================"+pcnt +"scnt=====================" +scnt + "nickname============" + nickname);
		
		Date today = new Date();
		SimpleDateFormat date1 = new SimpleDateFormat("yy/MM/dd");
		String date = date1.format(today);
		
//		nickname = "지호다네"; 테스트용
		int newPushup = Integer.parseInt(pcnt);
		int newStanddown = Integer.parseInt(scnt);
		RecordDTO rDto =reService.selDate(date,nickname);
		log.info("검색 결과는 ??????????????"+ rDto);
		
		if(rDto !=null) {
			int pushup = rDto.getPushup();
			int standdown = rDto.getStanddown();
			
			rDto.setPushup(pushup + newPushup);
			rDto.setStanddown(standdown + newStanddown);
			rDto.setUpdatedate(date);
			rDto.setNickname(nickname);
			
			int result = reService.update(rDto);
			log.info("업데이트 결과는???????????????????"+result);
		}else {
			RecordDTO rDto2 = new RecordDTO();
			rDto2.setPushup(newPushup);
			rDto2.setStanddown(newStanddown);
			rDto2.setNickname(nickname);
			int result = reService.insert(rDto2);
			log.info("인설트 결과는???????????????????"+result);
		}
		
	}

    @RequestMapping("/sDiary")
    public void sDiary() {
    	log.info("sDiary 입장");
    }
 
    //@ResponseBody //화면으로 넘어가는 것이 아닌 데이터를 리턴하는 경우 사용
    
    @RequestMapping("/sDiaryDaily")
    @ResponseBody
    public JSONObject sDiaryDaily(@RequestParam("nickname") String nickname) throws Exception {
    		
    	
        return reService.getDailyData(nickname);
    }
    
    @RequestMapping("/sDiaryDailyApp")
    @ResponseBody
    public Map<String, List<RecordDTO>> sDiaryDailyApp(String nickname) throws Exception {
    	    	
        return reService.getDailyDataApp(nickname);
    }
    
    @RequestMapping("/sDiaryWeeklyApp")
    @ResponseBody
    public Map<String, List<RecordDTO>> sDiaryWeeklyApp(String nickname) throws Exception {
    	    	
        return reService.getWeeklyDataApp(nickname);
    }
    
    @RequestMapping("/sDiaryMonthlyApp")
    @ResponseBody
    public Map<String, List<RecordDTO>> sDiaryMonthlyApp(String nickname) throws Exception {
    	    	
        return reService.getMonthlyDataApp(nickname);
    }
    
    @RequestMapping("/sDiaryAllApp")
    @ResponseBody
    public Map<String, Object> sDiaryAllApp(String nickname) throws Exception {
    	    	
    	Map<String, Object> map = new HashMap<>();
    	
    	map.put("daily", reService.getDailyDataApp(nickname));
    	map.put("weekly", reService.getWeeklyDataApp(nickname));
    	map.put("monthly", reService.getMonthlyDataApp(nickname));
    	
        return map;
    }
    
    
    @RequestMapping("/sDiaryWeekly")
    @ResponseBody
    public JSONObject sDiaryWeekly(@RequestParam("nickname") String nickname) throws Exception {
      	
        return reService.getWeeklyData(nickname);
    }
	
    @RequestMapping("/sDiaryMonthly")
    @ResponseBody
    public JSONObject sDiaryMonthly(@RequestParam("nickname") String nickname) throws Exception {
    
    	
        return reService.getMonthlyData(nickname);
    }
	
	
}
