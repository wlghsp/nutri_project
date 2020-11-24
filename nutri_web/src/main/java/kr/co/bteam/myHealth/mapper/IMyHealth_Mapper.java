package kr.co.bteam.myHealth.mapper;

import java.util.List;

import kr.co.bteam.member.domain.MemberDTO;
import kr.co.bteam.myHealth.domain.MyHealthDTO;

public interface IMyHealth_Mapper {
	public List<MyHealthDTO> read(MyHealthDTO dto);

	public MemberDTO selmemberApp(String nickname);
}
