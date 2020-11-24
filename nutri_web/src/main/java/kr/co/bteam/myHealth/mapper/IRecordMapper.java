package kr.co.bteam.myHealth.mapper;

import java.util.List;

import kr.co.bteam.myHealth.domain.RecordDTO;

public interface IRecordMapper {
	public RecordDTO selDate(RecordDTO dto);
	public int insert(RecordDTO rDto);
	public int update(RecordDTO rDto);
	public List<RecordDTO> AllDate(String nickname);
	public List<RecordDTO> monthDate(RecordDTO dto);
}
