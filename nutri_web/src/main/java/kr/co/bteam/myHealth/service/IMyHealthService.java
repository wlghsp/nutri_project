package kr.co.bteam.myHealth.service;

import java.util.List;

import kr.co.bteam.member.domain.MemberDTO;
import kr.co.bteam.myHealth.domain.MyHealthDTO;

public interface IMyHealthService {
	public List<MyHealthDTO> read(MemberDTO mDto);
	public MemberDTO selmemberApp(String nickname);
}
