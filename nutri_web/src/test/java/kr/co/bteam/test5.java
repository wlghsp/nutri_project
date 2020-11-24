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
import kr.co.bteam.myHealth.mapper.IRecordMapper;
import kr.co.bteam.myHealth.service.IRecordService;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class test5 {
	
	@Autowired
	IRecordMapper mapper;
	
	@Autowired
	IMemberService service;
	
	@Autowired
	IRecordService reService;
	
	@Test
	public void test() {
		List<RecordDTO> list = reService.AllDate("지호다네");
		
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
		
		log.info("각달의 평균은????" + yearList);
		
	}
}
