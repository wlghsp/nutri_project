package kr.co.bteam.myHealth.service.Impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.bteam.myHealth.domain.MyHealthDTO;
import kr.co.bteam.myHealth.domain.RecordDTO;
import kr.co.bteam.myHealth.mapper.IRecordMapper;
import kr.co.bteam.myHealth.service.IRecordService;

@Service
public class RecordServiceImpl implements IRecordService{
	
	@Autowired
	IRecordMapper mapper;
	
	private static final Logger log = LoggerFactory.getLogger(RecordServiceImpl.class);
	
	@Override
	public RecordDTO selDate(String date, String nickname) {
		RecordDTO dto = new RecordDTO();
		dto.setNickname(nickname);
		dto.setUpdatedate(date);
		return mapper.selDate(dto);
	}

	@Override
	public int insert(RecordDTO rDto) {
		return mapper.insert(rDto);
	}

	@Override
	public int update(RecordDTO rDto) {
		return mapper.update(rDto);
	}
	
	//월별 
	@Override
	public List<RecordDTO> AllDate(String nickname) {
		
		List<RecordDTO> list = mapper.AllDate(nickname);
		
		int push1 = 0, push2 = 0, push3 = 0, push4 = 0, push5 = 0, push6 = 0, 
			push7 = 0, push8 = 0, push9 = 0, push10 = 0, push11 = 0, push12 = 0;
		int standdown1 = 0, standdown2 = 0, standdown3 = 0, standdown4 = 0, standdown5 = 0,
			standdown6 = 0, standdown7 = 0, standdown8 = 0, standdown9 = 0, standdown10 = 0,
			standdown11 = 0, standdown12 = 0;
		int count1 = 0, count2 = 0, count3 = 0, count4 = 0, count5 = 0, count6 = 0,
			count7 = 0, count8 = 0, count9 = 0, count10 = 0, count11 = 0, count12 = 0;
	
		for (int i = 0; i < list.size(); i++) {
			String date =list.get(i).getUpdatedate();
			String date2[] = date.split("-");
			int month = Integer.parseInt(date2[1]);
		
			if(month==1) {
				int push = list.get(i).getPushup();
				int standdown = list.get(i).getStanddown();
				push1 = push1 + push;
				standdown1 = standdown1 +standdown;
				count1 ++;
			}
			if(month==2) {
				int push = list.get(i).getPushup();
				int standdown = list.get(i).getStanddown();
				push2 = push2 + push;
				standdown2 = standdown2 +standdown;
				count2 ++;
			}
			if(month==3) {
				int push = list.get(i).getPushup();
				int standdown = list.get(i).getStanddown();
				push3 = push3 + push;
				standdown3 = standdown3 +standdown;
				count3 ++;
			}
			if(month==4) {
				int push = list.get(i).getPushup();
				int standdown = list.get(i).getStanddown();
				push4 = push4 + push;
				standdown4 = standdown4 +standdown;
				count4 ++;
			}
			if(month==5) {
				int push = list.get(i).getPushup();
				int standdown = list.get(i).getStanddown();
				push5 = push5 + push;
				standdown5 = standdown5 +standdown;
				count5 ++;
			}
			if(month==6) {
				int push = list.get(i).getPushup();
				int standdown = list.get(i).getStanddown();
				push6 = push6 + push;
				standdown6 = standdown6 +standdown;
				count6 ++;
			}
			if(month==7) {
				int push = list.get(i).getPushup();
				int standdown = list.get(i).getStanddown();
				push7 = push7 + push;
				standdown7 = standdown7 +standdown;
				count7 ++;
			}
			if(month==8) {
				int push = list.get(i).getPushup();
				int standdown = list.get(i).getStanddown();
				push8 = push8 + push;
				standdown8 = standdown8 +standdown;
				count8 ++;
			}
			if(month==9) {
				int push = list.get(i).getPushup();
				int standdown = list.get(i).getStanddown();
				push9 = push9 + push;
				standdown9 = standdown9 +standdown;
				count9 ++;
			}
			if(month==10) {
				int push = list.get(i).getPushup();
				int standdown = list.get(i).getStanddown();
				push10 = push10 + push;
				standdown10 = standdown10 +standdown;
				count10 ++;
			}
			if(month==11) {
				int push = list.get(i).getPushup();
				int standdown = list.get(i).getStanddown();
				push11 = push11 + push;
				standdown11 = standdown11 +standdown;
				count11 ++;
			}
			if(month==12) {
				int push = list.get(i).getPushup();
				int standdown = list.get(i).getStanddown();
				push12 = push12 + push;
				standdown12 = standdown12 +standdown;
				count12 ++;
			}
		}
		RecordDTO jan = new RecordDTO(); RecordDTO feb = new RecordDTO();
		RecordDTO mar = new RecordDTO(); RecordDTO apr = new RecordDTO();
		RecordDTO may = new RecordDTO(); RecordDTO jun = new RecordDTO();
		RecordDTO jul = new RecordDTO(); RecordDTO aug = new RecordDTO();
		RecordDTO sep = new RecordDTO(); RecordDTO oct = new RecordDTO();
		RecordDTO nov = new RecordDTO(); RecordDTO dec = new RecordDTO();
		
		if(count1 != 0) {
			jan.setPushup(push1/count1); jan.setStanddown(standdown1/count1);
			jan.setUpdatedate("1월");
		}
		if(count2 != 0) {
			feb.setPushup(push2/count2); feb.setStanddown(standdown2/count2);
			feb.setUpdatedate("2월");
		}
		if(count3 != 0) {
			mar.setPushup(push3/count3); mar.setStanddown(standdown3/count3);
			mar.setUpdatedate("3월");
		}
		if(count4 != 0) {
			apr.setPushup(push4/count4); apr.setStanddown(standdown4/count4);
			apr.setUpdatedate("4월");
		}
		if(count5 != 0) {
			may.setPushup(push5/count5); may.setStanddown(standdown5/count5);
			may.setUpdatedate("5월");
		}
		if(count6 != 0) {
			jun.setPushup(push6/count6); jun.setStanddown(standdown6/count6);
			jun.setUpdatedate("6월");
		}
		if(count7 != 0) {
			jul.setPushup(push7/count7); jul.setStanddown(standdown7/count7);
			jul.setUpdatedate("7월");
		}
		if(count8 != 0) {
			aug.setPushup(push8/count8); aug.setStanddown(standdown8/count8);
			aug.setUpdatedate("8월");
		}
		if(count9 != 0) {
			sep.setPushup(push9/count9); sep.setStanddown(standdown9/count9);
			sep.setUpdatedate("9월");
		}
		if(count10 != 0) {
			oct.setPushup(push10/count10); oct.setStanddown(standdown10/count10);
			oct.setUpdatedate("10월");
		}
		if(count11 != 0) {
			nov.setPushup(push11/count11); nov.setStanddown(standdown11/count11);
			nov.setUpdatedate("11월");
		}
		if(count12 != 0) {
			dec.setPushup(push12/count12); dec.setStanddown(standdown12/count12);
			dec.setUpdatedate("12월");
		}
		
		ArrayList<RecordDTO> yearList = new ArrayList<RecordDTO>();
		yearList.add(jan); yearList.add(feb); yearList.add(mar); yearList.add(apr);
		yearList.add(may); yearList.add(jun); yearList.add(jul); yearList.add(aug);
		yearList.add(sep); yearList.add(oct); yearList.add(nov); yearList.add(dec);
		
		return yearList;
	}
	
	//주별 
	@Override
	public List<RecordDTO> weeklyDate(String nickname) {
		Date today = new Date();
		SimpleDateFormat date1 = new SimpleDateFormat("yy/MM");
		String thisdate = date1.format(today);
		
		//thisdate = "20/03";
		
		List<RecordDTO> mlist = monthDate(nickname, thisdate);
		log.info("닉네임은  ===>  " + mlist);
		
		int push1 = 0;
		int push2 = 0;
		int push3 = 0;
		int push4 = 0;
		int push5 = 0;
		int standdown1 = 0;
		int standdown2 = 0;
		int standdown3 = 0;
		int standdown4 = 0;
		int standdown5 = 0;
		String month2 =null;
		
		for (int i = 0; i < mlist.size(); i++) {
			String date = mlist.get(i).getUpdatedate();
			log.info("date:" + date);
			String date2[] = date.split("-");
			month2 = date2[1];
			String day2 = date2[2].substring(0,2);
			int day = Integer.parseInt(day2);
			log.info("월이에요" + month2);
			log.info("날짜에요 "+ day);
			
			if(day >=1&& day <= 7) {
				int push = mlist.get(i).getPushup();
				int standdown = mlist.get(i).getStanddown();
				log.info("푸쉬에요"+push);
				push1 = push1 + push;
				standdown1 = standdown1 + standdown;
								
			}else if(day >=8&& day <= 14) {
				int push = mlist.get(i).getPushup();
				int standdown = mlist.get(i).getStanddown();
				log.info("푸쉬에요"+push);
				push2 = push2 + push;
				standdown2 = standdown2 + standdown;
				
			}else if(day >=15&& day <= 21) {
				int push = mlist.get(i).getPushup();
				int standdown = mlist.get(i).getStanddown();
				log.info("푸쉬에요"+push);
				push3 = push3 + push;
				standdown3 = standdown3 + standdown;
				
			}else if(day >=22&& day <= 28) {
				int push = mlist.get(i).getPushup();
				int standdown = mlist.get(i).getStanddown();
				log.info("푸쉬에요"+push);
				push4 = push4 + push;
				standdown4 = standdown4 + standdown;
				
			}else if(day >=29 && day <= 31) {
				int push = mlist.get(i).getPushup();
				int standdown = mlist.get(i).getStanddown();
				log.info("푸쉬에요"+push);
				push5 = push5 + push;
				standdown5 = standdown5 + standdown;
			}
		}
		log.info("1째 주 푸쉬&스쿼트 총 횟수에요"+push1 + "/" + standdown1);
		log.info("2째 주 푸쉬&스쿼트 총 횟수에요"+push2 + "/" + standdown2);
		log.info("3째 주 푸쉬&스쿼트 총 횟수에요"+push3 + "/" + standdown3);
		log.info("4째 주 푸쉬&스쿼트 총 횟수에요"+push4 + "/" + standdown4);
		log.info("5째 주 푸쉬&스쿼트 총 횟수에요"+push5 + "/" + standdown5);
		
		ArrayList<RecordDTO> weekList = new ArrayList<>(); 
		int days = mlist.size();
		log.info("결과는??????"+days);
		RecordDTO oweek = new RecordDTO();
		RecordDTO tweek = new RecordDTO();
		RecordDTO trweek = new RecordDTO();
		RecordDTO fuweek = new RecordDTO();
		RecordDTO fiweek = new RecordDTO();
		if(days>7) {
			int oneWeekPush = push1/7;
			int oneWeekStanddown = standdown1/7;
			log.info("1주 평균은 푸시=====" + oneWeekPush);
			log.info("1주 평균은 스쿼트=====" + oneWeekStanddown);
			oweek.setPushup(oneWeekPush);
			oweek.setStanddown(oneWeekStanddown);
			oweek.setUpdatedate(month2+"월 1주");
			weekList.add(oweek);
		}
		if(days>14) {
			int twoWeekPush = push2/7;
			int twoWeekStanddown = standdown2/7;
			log.info("2주 평균은 =====" + twoWeekPush);
			tweek.setPushup(twoWeekPush);
			tweek.setStanddown(twoWeekStanddown);
			tweek.setUpdatedate(month2+"월 2주");
			weekList.add(tweek);
		}
		if(days>21) {
			int treeWeekPush = push3/7;
			int treeWeekStanddown = standdown3/7;
			log.info("3주 평균은 =====" + treeWeekPush);
			trweek.setPushup(treeWeekPush);
			trweek.setStanddown(treeWeekStanddown);
			trweek.setUpdatedate(month2+"월 3주");
			weekList.add(trweek);
		}
		if(days>=28) {
			int fourWeekPush = push4/7;
			int fourWeekStanddown = standdown4/7;
			log.info("4주 평균은 =====" + fourWeekPush);
			fuweek.setPushup(fourWeekPush);
			fuweek.setStanddown(fourWeekStanddown);
			fuweek.setUpdatedate(month2+"월 4주");
			weekList.add(fuweek);
		}
		if(days>28) {
			int day = days%7;
			int fiveWeekPush = push5/day;
			int fiveWeekStanddown = standdown5/7;
			log.info("5주 평균은 =====" + fiveWeekPush);
			fiweek.setPushup(fiveWeekPush);
			fiweek.setStanddown(fiveWeekStanddown);
			fiweek.setUpdatedate(month2+"월 5주");
			weekList.add(fiweek);
		}
	
		log.info("야호 weekList닷!!!!!!!!!!!"+weekList);
		
		return weekList;
	}
	
	//일별 데이터 
	@Override
	public List<RecordDTO> monthDate(String nickname, String date) {
		RecordDTO dto = new RecordDTO();
		dto.setNickname(nickname);
		//date = "20/03";
		dto.setUpdatedate(date);
		return mapper.monthDate(dto);
	}

	  //{"변수명" : [{},{},{}], "변수명" : "값"}
    @SuppressWarnings("unchecked")
	@Override
    public JSONObject getDailyData(String nickname) throws Exception {
    	//제이슨 오브젝트를 리턴하는 것
        // getChartData메소드를 호출하면
        //db에서 리스트 받아오고, 받아온걸로 json형식으로 만들어서 리턴을 해주게 된다.
    	
    	Date today = new Date();
		SimpleDateFormat date1 = new SimpleDateFormat("yy/MM");
		String thisdate = date1.format(today);
		//String thisdate = "20/03";
		
		List<RecordDTO> items = monthDate(nickname, thisdate);
    	   	
             
        //리턴할 json 객체
        JSONObject data = new JSONObject(); //{}
        
        //json의 칼럼 객체
        JSONObject col1 = new JSONObject();
        JSONObject col2 = new JSONObject();
        JSONObject col3 = new JSONObject();
        
        //json 배열 객체, 배열에 저장할때는 JSONArray()를 사용
        JSONArray title = new JSONArray();
        col1.put("label","날짜"); //col1에 자료를 저장 ("필드이름","자료형")
        col1.put("type", "string");
        col2.put("label", "푸시업");
        col2.put("type", "number");
        col3.put("label", "스쿼트");
        col3.put("type", "number");
        
        //테이블행에 컬럼 추가
        title.add(col1);
        title.add(col2);
        title.add(col3);
        
        
        //json 객체에 타이틀행 추가
        data.put("cols", title);//제이슨을 넘김
        
        SimpleDateFormat sdf = new SimpleDateFormat("");
                
        
        JSONArray body = new JSONArray(); //json 배열을 사용하기 위해 객체를 생성
        for (RecordDTO dto : items) { //items에 저장된 값을 dto로 반복문을 돌려서 하나씩 저장한다.
            
        	JSONObject date = new JSONObject(); //json오브젝트 객체를 생성
        	String updatedate = dto.getUpdatedate().substring(5, 10);
            date.put("v", updatedate); //name변수에 dto에 저장된 상품의 이름을 v라고 저장한다.
            
            JSONObject pushup = new JSONObject(); //json오브젝트 객체를 생성
            pushup.put("v", dto.getPushup()); //pushup변수에 dto에 저장된 pushup횟수를 v라고 저장한다.
            
            JSONObject squat = new JSONObject(); //json오브젝트 객체를 생성
            squat.put("v", dto.getStanddown()); //pushup변수에 dto에 저장된 pushup횟수를 v라고 저장한다.
            
            JSONArray row = new JSONArray(); //json 배열 객체 생성 (위에서 저장한 변수를 칼럼에 저장하기위해)
            row.add(date);
            row.add(pushup); //name을 row에 저장 (테이블의 행)
            row.add(squat); //name을 row에 저장 (테이블의 행)
            
            JSONObject cell = new JSONObject(); 
            cell.put("c", row); //cell 2개를 합쳐서 "c"라는 이름으로 추가
            body.add(cell); //레코드 1개 추가
                
        }
        data.put("rows", body); //data에 body를 저장하고 이름을 rows라고 한다.
        
        return data; //이 데이터가 넘어가면 json형식으로 넘어가게되서 json이 만들어지게 된다.
    }
    
   
    
    @SuppressWarnings("unchecked")
	@Override
    public JSONObject getWeeklyData(String nickname) throws Exception {
    	//제이슨 오브젝트를 리턴하는 것
        // getChartData메소드를 호출하면
        //db에서 리스트 받아오고, 받아온걸로 json형식으로 만들어서 리턴을 해주게 된다.
    	
    	Date today = new Date();
		SimpleDateFormat date1 = new SimpleDateFormat("yy/MM");
		String thisdate = date1.format(today);
		
		List<RecordDTO> items = weeklyDate(nickname);
    	   	
             
        //리턴할 json 객체
        JSONObject data = new JSONObject(); //{}
        
        //json의 칼럼 객체
        JSONObject col1 = new JSONObject();
        JSONObject col2 = new JSONObject();
        JSONObject col3 = new JSONObject();
        
        //json 배열 객체, 배열에 저장할때는 JSONArray()를 사용
        JSONArray title = new JSONArray();
        col1.put("label","날짜"); //col1에 자료를 저장 ("필드이름","자료형")
        col1.put("type", "string");
        col2.put("label", "푸시업");
        col2.put("type", "number");
        col3.put("label", "스쿼트");
        col3.put("type", "number");
        
        //테이블행에 컬럼 추가
        title.add(col1);
        title.add(col2);
        title.add(col3);
        
        
        //json 객체에 타이틀행 추가
        data.put("cols", title);//제이슨을 넘김
        
        SimpleDateFormat sdf = new SimpleDateFormat("");
                
        
        JSONArray body = new JSONArray(); //json 배열을 사용하기 위해 객체를 생성
        for (RecordDTO dto : items) { //items에 저장된 값을 dto로 반복문을 돌려서 하나씩 저장한다.
            
        	JSONObject date = new JSONObject(); //json오브젝트 객체를 생성
        	String updatedate = dto.getUpdatedate();
            date.put("v", updatedate); //name변수에 dto에 저장된 상품의 이름을 v라고 저장한다.
            
            JSONObject pushup = new JSONObject(); //json오브젝트 객체를 생성
            pushup.put("v", dto.getPushup()); //pushup변수에 dto에 저장된 pushup횟수를 v라고 저장한다.
            
            JSONObject squat = new JSONObject(); //json오브젝트 객체를 생성
            squat.put("v", dto.getStanddown()); //pushup변수에 dto에 저장된 pushup횟수를 v라고 저장한다.
            
            JSONArray row = new JSONArray(); //json 배열 객체 생성 (위에서 저장한 변수를 칼럼에 저장하기위해)
            row.add(date);
            row.add(pushup); //name을 row에 저장 (테이블의 행)
            row.add(squat); //name을 row에 저장 (테이블의 행)
            
            JSONObject cell = new JSONObject(); 
            cell.put("c", row); //cell 2개를 합쳐서 "c"라는 이름으로 추가
            body.add(cell); //레코드 1개 추가
                
        }
        data.put("rows", body); //data에 body를 저장하고 이름을 rows라고 한다.
        
        return data; //이 데이터가 넘어가면 json형식으로 넘어가게되서 json이 만들어지게 된다.
    }
	
    @SuppressWarnings("unchecked")
   	@Override
       public JSONObject getMonthlyData(String nickname) throws Exception {
       	//제이슨 오브젝트를 리턴하는 것
           // getChartData메소드를 호출하면
           //db에서 리스트 받아오고, 받아온걸로 json형식으로 만들어서 리턴을 해주게 된다.
       	
       	Date today = new Date();
   		SimpleDateFormat date1 = new SimpleDateFormat("yy/MM");
   		String thisdate = date1.format(today);
   		
		List<RecordDTO> items = AllDate(nickname);        	   	
                
           //리턴할 json 객체
           JSONObject data = new JSONObject(); //{}
           
           //json의 칼럼 객체
           JSONObject col1 = new JSONObject();
           JSONObject col2 = new JSONObject();
           JSONObject col3 = new JSONObject();
           
           //json 배열 객체, 배열에 저장할때는 JSONArray()를 사용
           JSONArray title = new JSONArray();
           col1.put("label","날짜"); //col1에 자료를 저장 ("필드이름","자료형")
           col1.put("type", "string");
           col2.put("label", "푸시업");
           col2.put("type", "number");
           col3.put("label", "스쿼트");
           col3.put("type", "number");
           
           //테이블행에 컬럼 추가
           title.add(col1);
           title.add(col2);
           title.add(col3);
           
           
           //json 객체에 타이틀행 추가
           data.put("cols", title);//제이슨을 넘김
           
           SimpleDateFormat sdf = new SimpleDateFormat("");
                   
           
           JSONArray body = new JSONArray(); //json 배열을 사용하기 위해 객체를 생성
           for (RecordDTO dto : items) { //items에 저장된 값을 dto로 반복문을 돌려서 하나씩 저장한다.
               
           	JSONObject date = new JSONObject(); //json오브젝트 객체를 생성
           	String updatedate = dto.getUpdatedate();
               date.put("v", updatedate); //name변수에 dto에 저장된 상품의 이름을 v라고 저장한다.
               
               JSONObject pushup = new JSONObject(); //json오브젝트 객체를 생성
               pushup.put("v", dto.getPushup()); //pushup변수에 dto에 저장된 pushup횟수를 v라고 저장한다.
               
               JSONObject squat = new JSONObject(); //json오브젝트 객체를 생성
               squat.put("v", dto.getStanddown()); //pushup변수에 dto에 저장된 pushup횟수를 v라고 저장한다.
               
               JSONArray row = new JSONArray(); //json 배열 객체 생성 (위에서 저장한 변수를 칼럼에 저장하기위해)
               row.add(date);
               row.add(pushup); //name을 row에 저장 (테이블의 행)
               row.add(squat); //name을 row에 저장 (테이블의 행)
               
               JSONObject cell = new JSONObject(); 
               cell.put("c", row); //cell 2개를 합쳐서 "c"라는 이름으로 추가
               body.add(cell); //레코드 1개 추가
                   
           }
           data.put("rows", body); //data에 body를 저장하고 이름을 rows라고 한다.
           
           return data; //이 데이터가 넘어가면 json형식으로 넘어가게되서 json이 만들어지게 된다.
       }
    
    
    //{"변수명" : [{},{},{}], "변수명" : "값"}
    @SuppressWarnings("unchecked")
	@Override
    public Map<String, List<RecordDTO>> getDailyDataApp(String nickname) throws Exception {
    	//제이슨 오브젝트를 리턴하는 것
        // getChartData메소드를 호출하면
        //db에서 리스트 받아오고, 받아온걸로 json형식으로 만들어서 리턴을 해주게 된다.
    	Date today = new Date();
		SimpleDateFormat date1 = new SimpleDateFormat("yy/MM");
		String thisdate = date1.format(today);
		//String thisdate = "20/03";
		
		List<RecordDTO> items = monthDate(nickname, thisdate);                    
        
        Map<String, List<RecordDTO>> hMap = new HashMap<String, List<RecordDTO>>();
		hMap.put("dailyList", items);
		return hMap;
		
    }

	@Override
	public Map<String, List<RecordDTO>> getWeeklyDataApp(String nickname) throws Exception {
		Date today = new Date();
		SimpleDateFormat date1 = new SimpleDateFormat("yy/MM");
		String thisdate = date1.format(today);
		
		//thisdate = "20/03";
		
		List<RecordDTO> mlist = monthDate(nickname, thisdate);
		log.info("닉네임은  ===>  " + mlist);
		
		int push1 = 0;
		int push2 = 0;
		int push3 = 0;
		int push4 = 0;
		int push5 = 0;
		int standdown1 = 0;
		int standdown2 = 0;
		int standdown3 = 0;
		int standdown4 = 0;
		int standdown5 = 0;
		String month2 =null;
		
		for (int i = 0; i < mlist.size(); i++) {
			String date = mlist.get(i).getUpdatedate();
			log.info("date:" + date);
			String date2[] = date.split("-");
			month2 = date2[1];
			String day2 = date2[2].substring(0,2);
			int day = Integer.parseInt(day2);
			log.info("월이에요" + month2);
			log.info("날짜에요 "+ day);
			
			if(day >=1&& day <= 7) {
				int push = mlist.get(i).getPushup();
				int standdown = mlist.get(i).getStanddown();
				log.info("푸쉬에요"+push);
				push1 = push1 + push;
				standdown1 = standdown1 + standdown;
								
			}else if(day >=8&& day <= 14) {
				int push = mlist.get(i).getPushup();
				int standdown = mlist.get(i).getStanddown();
				log.info("푸쉬에요"+push);
				push2 = push2 + push;
				standdown2 = standdown2 + standdown;
				
			}else if(day >=15&& day <= 21) {
				int push = mlist.get(i).getPushup();
				int standdown = mlist.get(i).getStanddown();
				log.info("푸쉬에요"+push);
				push3 = push3 + push;
				standdown3 = standdown3 + standdown;
				
			}else if(day >=22&& day <= 28) {
				int push = mlist.get(i).getPushup();
				int standdown = mlist.get(i).getStanddown();
				log.info("푸쉬에요"+push);
				push4 = push4 + push;
				standdown4 = standdown4 + standdown;
				
			}else if(day >=29 && day <= 31) {
				int push = mlist.get(i).getPushup();
				int standdown = mlist.get(i).getStanddown();
				log.info("푸쉬에요"+push);
				push5 = push5 + push;
				standdown5 = standdown5 + standdown;
			}
		}
		log.info("1째 주 푸쉬&스쿼트 총 횟수에요"+push1 + "/" + standdown1);
		log.info("2째 주 푸쉬&스쿼트 총 횟수에요"+push2 + "/" + standdown2);
		log.info("3째 주 푸쉬&스쿼트 총 횟수에요"+push3 + "/" + standdown3);
		log.info("4째 주 푸쉬&스쿼트 총 횟수에요"+push4 + "/" + standdown4);
		log.info("5째 주 푸쉬&스쿼트 총 횟수에요"+push5 + "/" + standdown5);
		
		ArrayList<RecordDTO> weekList = new ArrayList<>(); 
		int days = mlist.size();
		log.info("결과는??????"+days);
		RecordDTO oweek = new RecordDTO();
		RecordDTO tweek = new RecordDTO();
		RecordDTO trweek = new RecordDTO();
		RecordDTO fuweek = new RecordDTO();
		RecordDTO fiweek = new RecordDTO();
		if(days>7) {
			int oneWeekPush = push1/7;
			int oneWeekStanddown = standdown1/7;
			log.info("1주 평균은 푸시=====" + oneWeekPush);
			log.info("1주 평균은 스쿼트=====" + oneWeekStanddown);
			oweek.setPushup(oneWeekPush);
			oweek.setStanddown(oneWeekStanddown);
			oweek.setUpdatedate(month2+"월 1주");
			weekList.add(oweek);
		}
		if(days>14) {
			int twoWeekPush = push2/7;
			int twoWeekStanddown = standdown2/7;
			log.info("2주 평균은 =====" + twoWeekPush);
			tweek.setPushup(twoWeekPush);
			tweek.setStanddown(twoWeekStanddown);
			tweek.setUpdatedate(month2+"월 2주");
			weekList.add(tweek);
		}
		if(days>21) {
			int treeWeekPush = push3/7;
			int treeWeekStanddown = standdown3/7;
			log.info("3주 평균은 =====" + treeWeekPush);
			trweek.setPushup(treeWeekPush);
			trweek.setStanddown(treeWeekStanddown);
			trweek.setUpdatedate(month2+"월 3주");
			weekList.add(trweek);
		}
		if(days>=28) {
			int fourWeekPush = push4/7;
			int fourWeekStanddown = standdown4/7;
			log.info("4주 평균은 =====" + fourWeekPush);
			fuweek.setPushup(fourWeekPush);
			fuweek.setStanddown(fourWeekStanddown);
			fuweek.setUpdatedate(month2+"월 4주");
			weekList.add(fuweek);
		}
		if(days>28) {
			int day = days%7;
			int fiveWeekPush = push5/day;
			int fiveWeekStanddown = standdown5/7;
			log.info("5주 평균은 =====" + fiveWeekPush);
			fiweek.setPushup(fiveWeekPush);
			fiweek.setStanddown(fiveWeekStanddown);
			fiweek.setUpdatedate(month2+"월 5주");
			weekList.add(fiweek);
		}
	
		log.info("야호 weekList닷!!!!!!!!!!!"+weekList);
		
		 Map<String, List<RecordDTO>> map = new HashMap<>();
		 map.put("weeklyList", weekList);
		return map;
	}

	@Override
	public Map<String, List<RecordDTO>> getMonthlyDataApp(String nickname) throws Exception {

		List<RecordDTO> list = mapper.AllDate(nickname);
		
		int push1 = 0, push2 = 0, push3 = 0, push4 = 0, push5 = 0, push6 = 0, 
			push7 = 0, push8 = 0, push9 = 0, push10 = 0, push11 = 0, push12 = 0;
		int standdown1 = 0, standdown2 = 0, standdown3 = 0, standdown4 = 0, standdown5 = 0,
			standdown6 = 0, standdown7 = 0, standdown8 = 0, standdown9 = 0, standdown10 = 0,
			standdown11 = 0, standdown12 = 0;
		int count1 = 0, count2 = 0, count3 = 0, count4 = 0, count5 = 0, count6 = 0,
			count7 = 0, count8 = 0, count9 = 0, count10 = 0, count11 = 0, count12 = 0;
	
		for (int i = 0; i < list.size(); i++) {
			String date =list.get(i).getUpdatedate();
			String date2[] = date.split("-");
			int month = Integer.parseInt(date2[1]);
		
			if(month==1) {
				int push = list.get(i).getPushup();
				int standdown = list.get(i).getStanddown();
				push1 = push1 + push;
				standdown1 = standdown1 +standdown;
				count1 ++;
			}
			if(month==2) {
				int push = list.get(i).getPushup();
				int standdown = list.get(i).getStanddown();
				push2 = push2 + push;
				standdown2 = standdown2 +standdown;
				count2 ++;
			}
			if(month==3) {
				int push = list.get(i).getPushup();
				int standdown = list.get(i).getStanddown();
				push3 = push3 + push;
				standdown3 = standdown3 +standdown;
				count3 ++;
			}
			if(month==4) {
				int push = list.get(i).getPushup();
				int standdown = list.get(i).getStanddown();
				push4 = push4 + push;
				standdown4 = standdown4 +standdown;
				count4 ++;
			}
			if(month==5) {
				int push = list.get(i).getPushup();
				int standdown = list.get(i).getStanddown();
				push5 = push5 + push;
				standdown5 = standdown5 +standdown;
				count5 ++;
			}
			if(month==6) {
				int push = list.get(i).getPushup();
				int standdown = list.get(i).getStanddown();
				push6 = push6 + push;
				standdown6 = standdown6 +standdown;
				count6 ++;
			}
			if(month==7) {
				int push = list.get(i).getPushup();
				int standdown = list.get(i).getStanddown();
				push7 = push7 + push;
				standdown7 = standdown7 +standdown;
				count7 ++;
			}
			if(month==8) {
				int push = list.get(i).getPushup();
				int standdown = list.get(i).getStanddown();
				push8 = push8 + push;
				standdown8 = standdown8 +standdown;
				count8 ++;
			}
			if(month==9) {
				int push = list.get(i).getPushup();
				int standdown = list.get(i).getStanddown();
				push9 = push9 + push;
				standdown9 = standdown9 +standdown;
				count9 ++;
			}
			if(month==10) {
				int push = list.get(i).getPushup();
				int standdown = list.get(i).getStanddown();
				push10 = push10 + push;
				standdown10 = standdown10 +standdown;
				count10 ++;
			}
			if(month==11) {
				int push = list.get(i).getPushup();
				int standdown = list.get(i).getStanddown();
				push11 = push11 + push;
				standdown11 = standdown11 +standdown;
				count11 ++;
			}
			if(month==12) {
				int push = list.get(i).getPushup();
				int standdown = list.get(i).getStanddown();
				push12 = push12 + push;
				standdown12 = standdown12 +standdown;
				count12 ++;
			}
		}
		RecordDTO jan = new RecordDTO(); RecordDTO feb = new RecordDTO();
		RecordDTO mar = new RecordDTO(); RecordDTO apr = new RecordDTO();
		RecordDTO may = new RecordDTO(); RecordDTO jun = new RecordDTO();
		RecordDTO jul = new RecordDTO(); RecordDTO aug = new RecordDTO();
		RecordDTO sep = new RecordDTO(); RecordDTO oct = new RecordDTO();
		RecordDTO nov = new RecordDTO(); RecordDTO dec = new RecordDTO();
		jan.setUpdatedate("1월");
		feb.setUpdatedate("2월");
		mar.setUpdatedate("3월");
		apr.setUpdatedate("4월");
		may.setUpdatedate("5월");
		jun.setUpdatedate("6월");
		jul.setUpdatedate("7월");
		aug.setUpdatedate("8월");
		sep.setUpdatedate("9월");
		oct.setUpdatedate("10월");
		nov.setUpdatedate("11월");
		dec.setUpdatedate("12월");
		
		if(count1 != 0) {
			jan.setPushup(push1/count1); jan.setStanddown(standdown1/count1);
		}
		if(count2 != 0) {
			feb.setPushup(push2/count2); feb.setStanddown(standdown2/count2);
		}
		if(count3 != 0) {
			mar.setPushup(push3/count3); mar.setStanddown(standdown3/count3);
		}
		if(count4 != 0) {
			apr.setPushup(push4/count4); apr.setStanddown(standdown4/count4);
		}
		if(count5 != 0) {
			may.setPushup(push5/count5); may.setStanddown(standdown5/count5);
		}
		if(count6 != 0) {
			jun.setPushup(push6/count6); jun.setStanddown(standdown6/count6);
		}
		if(count7 != 0) {
			jul.setPushup(push7/count7); jul.setStanddown(standdown7/count7);
		}
		if(count8 != 0) {
			aug.setPushup(push8/count8); aug.setStanddown(standdown8/count8);
		}
		if(count9 != 0) {
			sep.setPushup(push9/count9); sep.setStanddown(standdown9/count9);
		}
		if(count10 != 0) {
			oct.setPushup(push10/count10); oct.setStanddown(standdown10/count10);
		}
		if(count11 != 0) {
			nov.setPushup(push11/count11); nov.setStanddown(standdown11/count11);
		}
		if(count12 != 0) {
			dec.setPushup(push12/count12); dec.setStanddown(standdown12/count12);
		}
		
		ArrayList<RecordDTO> yearList = new ArrayList<RecordDTO>();
		yearList.add(jan); yearList.add(feb); yearList.add(mar); yearList.add(apr);
		yearList.add(may); yearList.add(jun); yearList.add(jul); yearList.add(aug);
		yearList.add(sep); yearList.add(oct); yearList.add(nov); yearList.add(dec);
		
		 Map<String, List<RecordDTO>> map = new HashMap<>();
		 map.put("monthlyList", yearList);
		return map;	}


}
