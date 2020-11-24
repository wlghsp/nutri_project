package kr.co.bteam;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.co.bteam.member.domain.MemberDTO;
import kr.co.bteam.myHealth.service.IMyHealthService;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class test3 {

	@Autowired
	IMyHealthService service;
	
	@Test
	public void test() {
		MemberDTO mDto= new MemberDTO();
		mDto.setDateofbirth("1984");
		
		log.info("결과는????????"+service.read(mDto));
	}

}
