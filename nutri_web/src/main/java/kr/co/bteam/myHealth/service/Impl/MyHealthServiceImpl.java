package kr.co.bteam.myHealth.service.Impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.bteam.member.domain.MemberDTO;
import kr.co.bteam.myHealth.domain.MyHealthDTO;
import kr.co.bteam.myHealth.mapper.IMyHealth_Mapper;
import kr.co.bteam.myHealth.service.IMyHealthService;

@Service
public class MyHealthServiceImpl implements IMyHealthService{

	@Autowired
	IMyHealth_Mapper mapper;
	
	private static final Logger log = LoggerFactory.getLogger(MyHealthServiceImpl.class);
	
	@Override
	public List<MyHealthDTO> read(MemberDTO mDto) {
	    Date today = new Date();
	    SimpleDateFormat date = new SimpleDateFormat("yyyy");
	    String toDay = date.format(today);

	    log.info("toDay======================="+toDay);
	    
	    String oldday = mDto.getDateofbirth();
	    String regender = mDto.getGender();
	    log.info("regender======================="+regender);
	    
	    if(oldday == null) {
	    	return null;
	    }
	    
	    
	    int Itoday = Integer.parseInt(toDay);
	    int Ioldday = Integer.parseInt(oldday);
	    
	    int Iage = Itoday-Ioldday+1;
	    
	    String age = null;
	    String gender = null;
	    
	    if(Iage >= 1 && Iage <= 2) {
	    	age="1~2";
	    	gender = "Toddler";
	    }else if(Iage >= 3 && Iage <= 5){
	    	age="3~5";
	    	gender = "Toddler";
		}else if(Iage >= 6 && Iage <= 8){
	    	age="6~8";
	        if(regender.equals("0")) {
		       	gender = "male";
		    }else if (regender.equals("1")) {
		    	gender = "female";
			}
		}else if(Iage >= 9 && Iage <= 11){
	    	age="9~11";
	        if(regender.equals("0")) {
		    	gender = "male";
		    }else if (regender.equals("1")) {
		    	gender = "female";
			}
		}else if(Iage >= 12 && Iage <= 14){
	    	age="12~14";
	        if(regender.equals("0")) {
		    	gender = "male";
		    }else if (regender.equals("1")) {
		    	gender = "female";
			}
		}else if(Iage >= 15 && Iage <= 18){
	    	age="15~18";
	        if(regender.equals("0")) {
		    	gender = "male";
		    }else if (regender.equals("1")) {
		    	gender = "female";
			}
		}else if(Iage >= 19 && Iage <= 29){
	    	age="19~29";
	        if(regender.equals("0")) {
		    	gender = "male";
		    }else if (regender.equals("1")) {
		    	gender = "female";
			}
		}else if(Iage >= 30 && Iage <= 49){
	    	age="30~49";
	        if(regender.equals("0")) {
		    	gender = "male";
		    }else if (regender.equals("1")) {
		    	gender = "female";
			}
		}else if(Iage >= 50 && Iage <= 64){
	    	age="50~64";
	        if(regender.equals("0")) {
		    	gender = "male";
		    }else if (regender.equals("1")) {
		    	gender = "female";
			}
		}else if(Iage >= 65 && Iage <= 74){
	    	age="65~74";
	        if(regender.equals("0")) {
		    	gender = "male";
		    }else if (regender.equals("1")) {
		    	gender = "female";
			}
		}else if(Iage >=75){
	    	age="75";
	        if(regender.equals("0")) {
		    	gender = "male";
		    }else if (regender.equals("1")) {
		    	gender = "female";
			}
		}
	    
	
	    
	    MyHealthDTO dto =new MyHealthDTO();
	    dto.setAge(age);
	    dto.setGender(gender);
	    
	    log.info("dto======================="+dto);
	   
		return mapper.read(dto);
	}

	@Override
	public MemberDTO selmemberApp(String nickname) {
		
		return mapper.selmemberApp(nickname);
	}

	

}
