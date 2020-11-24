package kr.co.bteam.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import kr.co.bteam.api.domain.HFF_IngredientDTO;
import kr.co.bteam.api.domain.HFF_MaterialDTO;
import kr.co.bteam.api.mapper.IHFF_Mapper;
import kr.co.bteam.member.domain.MemberDTO;
import kr.co.bteam.member.service.IMemberService;
import kr.co.bteam.myHealth.domain.RecordDTO;
import kr.co.bteam.myHealth.service.IRecordService;

@Component
public class Scheduler {
		// cron 문법으로 스케줄러가 실행되는 주기를 설정.
		
	@Autowired
	private IHFF_Mapper mapper;
	
	@Autowired
	private IRecordService reService;
	
	@Autowired
	private IMemberService mService;
	
	private static final Logger logger = LoggerFactory.getLogger(Scheduler.class);
	
	//초(0~59), 분(0~59) , 시간(0~23) , 일(0~31), 월(1~12), 주일(0 - 7), 년
	//주일에서 일요일은 0 또는 7 이 된다. 2로 지정할 경우 화요일이며 sun, mon, tue, wed, thu 같이 축약어로도 지정 가능하다.
	//*:모두, ?:제외, -:기간, /:시작시간과 반복시간, L:마지막, W:가까운평일
	//@Scheduled(cron = "0 0 0 1 6,12 *")
	
	@Scheduled(cron = "0 0 0 * * *")
	public void run2() {
		System.out.println("my health 날마다 등록!!");
		List<MemberDTO> mlist = mService.selectAll();
		logger.info(""+mlist);
		
		for (int i = 0; i < mlist.size(); i++) {
			String nickname = mlist.get(i).getNickname();
			RecordDTO rDto2 = new RecordDTO();
			rDto2.setPushup(0);
			rDto2.setStanddown(0);
			rDto2.setNickname(nickname);
			int result = reService.insert(rDto2);
			logger.info("인설트 결과는???????????????????"+result);
		}
	}
	
	@Scheduled(cron = "0 0 0 1 6,12 *")
	public void run() throws IOException, ParseException {
		Date today = new Date();
		System.out.println("cron! 스타트!" +today);
		HFF_H();
		HFF_I();
		
	}
		
	public void HFF_I() throws IOException, ParseException {
		String key = "0b69bed70fd540aea6df";
		String apiUrl = "http://openapi.foodsafetykorea.go.kr/api/"+ key +"/I2710/json/1/99";
		StringBuilder urlBuilder = new StringBuilder(apiUrl);
		URL url = new URL(urlBuilder.toString());
			
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
	    conn.setRequestProperty("Content-type", "application/json");
	   
	    BufferedReader rd;
	    if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
	        rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	    } else {
	        rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
	    }
	    StringBuilder sb = new StringBuilder();
	    String line;
	    while ((line = rd.readLine()) != null) {
	        sb.append(line);
	    }
	    rd.close();
	    conn.disconnect();
	    String result= sb.toString();
	            
	    JSONParser parser = new JSONParser(); 
	    JSONObject obj = (JSONObject) parser.parse(result); 
		JSONObject I2710 = (JSONObject) obj.get("I2710"); 
	
		JSONArray jarr = (JSONArray) I2710.get("row");
			
		Gson gson = new Gson();
		Type listType = new TypeToken<ArrayList<HFF_IngredientDTO>>() {			
		}.getType();
		
		List<HFF_IngredientDTO> row = gson.fromJson(jarr.toString(), listType);
		
		int re;
	
		for (int i = 0; i < row.size(); i++) {
			
			row.get(i).setINO(i+1);
			
			re = mapper.Iupdate(row.get(i));
			
			if(re == 1) {
				logger.info("결과 성공");
			}else {
				logger.info("결과 실패");
			}
		}
	}
	
	public void HFF_H() throws IOException, ParseException {
		String key = "0b69bed70fd540aea6df";
		String apiUrl = "http://openapi.foodsafetykorea.go.kr/api/"+ key +"/I-0040/json/1/506";
		StringBuilder urlBuilder = new StringBuilder(apiUrl);
		URL url = new URL(urlBuilder.toString());
			
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
	    conn.setRequestProperty("Content-type", "application/json");
	   
	    BufferedReader rd;
	    if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
	        rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	    } else {
	        rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
	    }
	    StringBuilder sb = new StringBuilder();
	    String line;
	    while ((line = rd.readLine()) != null) {
	        sb.append(line);
	    }
	    rd.close();
	    conn.disconnect();
	    String result= sb.toString();
	            
	    JSONParser parser = new JSONParser(); 
	 	JSONObject obj = (JSONObject) parser.parse(result); 
	 	JSONObject I_0040 = (JSONObject) obj.get("I-0040"); 

	 	JSONArray jarr = (JSONArray) I_0040.get("row");
			
		Gson gson = new Gson();
		Type listType = new TypeToken<ArrayList<HFF_MaterialDTO>>() {			
		}.getType();
		
		List<HFF_MaterialDTO> row = gson.fromJson(jarr.toString(), listType);
		
		int re;
	
		for (int i = 0; i < row.size(); i++) {
			
			row.get(i).setMNO(i+1);
			
			re = mapper.Mupdate(row.get(i));
			
			if(re == 1) {
				logger.info("결과 성공");
			}else {
				logger.info("결과 실패");
			}
		}
		
	}
}


