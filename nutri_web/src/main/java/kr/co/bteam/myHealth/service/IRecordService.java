package kr.co.bteam.myHealth.service;

import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;

import kr.co.bteam.myHealth.domain.RecordDTO;

public interface IRecordService {
	 
	 public RecordDTO selDate(String date, String nickname);
	 public int insert(RecordDTO rDto);
	 public int update(RecordDTO rDto);
  	 public List<RecordDTO> AllDate(String nickname);
  	 public List<RecordDTO> monthDate(String nickname, String date);
  	 public List<RecordDTO> weeklyDate(String nickname);

  	 
  	 public JSONObject getDailyData(String nickname) throws Exception;  //json 타입으로 리턴
  	 public Map<String, List<RecordDTO>> getDailyDataApp(String nickname) throws Exception;  //json 타입으로 리턴
  	 public JSONObject getWeeklyData(String nickname) throws Exception;  //json 타입으로 리턴
  	 public Map<String, List<RecordDTO>> getWeeklyDataApp(String nickname) throws Exception; 
  	 public JSONObject getMonthlyData(String nickname) throws Exception;  //json 타입으로 리턴
     public Map<String, List<RecordDTO>> getMonthlyDataApp(String nickname) throws Exception;

}
