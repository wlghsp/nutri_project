package kr.co.bteam.member.service;

import javax.servlet.http.HttpServletRequest;

public interface IUserMailSendService {

	public void mailSendWithUserKey(String email, String nickname, HttpServletRequest request) throws Exception;
	public void alter_userKey(String nickname, String key) throws Exception;
	public void mailSendWithPassword(String email, HttpServletRequest request) throws Exception;
}
