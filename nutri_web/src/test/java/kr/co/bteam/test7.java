package kr.co.bteam;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.co.bteam.myHealth.domain.RecordDTO;
import kr.co.bteam.myHealth.service.IRecordService;
import lombok.extern.log4j.Log4j;

public class test7 {
	
	public static void main(String[] args) {
		String date ="아연(단위:mg)";
		int idx = date.indexOf("("); ;
		String result = date.substring(0, idx);
		String result2 = date.substring(date.indexOf("("));
		
		System.out.println(result);
		System.out.println(result2);
	}
	
}
