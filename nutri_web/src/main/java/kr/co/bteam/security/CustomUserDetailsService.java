package kr.co.bteam.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import kr.co.bteam.member.domain.AdminVO;
import kr.co.bteam.member.mapper.AdminMapper;
import kr.co.bteam.security.domain.CustomUser;


public class CustomUserDetailsService implements UserDetailsService {

	private static final Logger log = LoggerFactory.getLogger(CustomUserDetailsService.class);
	
	@Autowired
	private AdminMapper mapper;

	@Override
	public UserDetails loadUserByUsername(String userName)  throws UsernameNotFoundException {

		log.warn("Load User By UserName : " + userName) ;

		
		AdminVO vo = mapper.read(userName);

	
		
		log.warn("queried by member mapper: " + vo);

		return vo == null ? null : new CustomUser(vo);
	} 

}
