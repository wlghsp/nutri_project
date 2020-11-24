package kr.co.bteam;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.co.bteam.member.service.IMemberService;
import kr.co.bteam.myHealth.domain.RecordDTO;
import kr.co.bteam.myHealth.domain.monthDTO;
import kr.co.bteam.myHealth.mapper.IRecordMapper;
import kr.co.bteam.myHealth.service.IRecordService;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class test4 {
	
	@Autowired
	IRecordMapper mapper;
	
	@Autowired
	IMemberService service;
	
	@Autowired
	IRecordService reService;
	
	@Test
	public void test() {
		
		Date today = new Date();
		SimpleDateFormat date1 = new SimpleDateFormat("yy/MM");
		String thisdate = date1.format(today);
			
		List<RecordDTO> mlist = reService.monthDate("지호다네", thisdate);
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
			oweek.setUpdatedate(month2+"-1주");
			weekList.add(oweek);
		}
		if(days>14) {
			int twoWeekPush = push2/7;
			int twoWeekStanddown = standdown2/7;
			log.info("2주 평균은 =====" + twoWeekPush);
			tweek.setPushup(twoWeekPush);
			tweek.setStanddown(twoWeekStanddown);
			tweek.setUpdatedate(month2+"-2주");
			weekList.add(tweek);
		}
		if(days>21) {
			int treeWeekPush = push3/7;
			int treeWeekStanddown = standdown3/7;
			log.info("3주 평균은 =====" + treeWeekPush);
			trweek.setPushup(treeWeekPush);
			trweek.setStanddown(treeWeekStanddown);
			trweek.setUpdatedate(month2+"-3주");
			weekList.add(trweek);
		}
		if(days>=28) {
			int fourWeekPush = push4/7;
			int fourWeekStanddown = standdown4/7;
			log.info("4주 평균은 =====" + fourWeekPush);
			fuweek.setPushup(fourWeekPush);
			fuweek.setStanddown(fourWeekStanddown);
			fuweek.setUpdatedate(month2+"-4주");
			weekList.add(fuweek);
		}
		if(days>28) {
			int day = days%7;
			int fiveWeekPush = push5/day;
			int fiveWeekStanddown = standdown5/7;
			log.info("5주 평균은 =====" + fiveWeekPush);
			fiweek.setPushup(fiveWeekPush);
			fiweek.setStanddown(fiveWeekStanddown);
			fiweek.setUpdatedate(month2+"-5주");
			weekList.add(fiweek);
		}
	
		log.info("야호 weekList닷!!!!!!!!!!!"+weekList);
		
	}
}
