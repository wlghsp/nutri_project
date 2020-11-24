package kr.co.bteam.streamView.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.co.bteam.noticeBoard.Criteria;
import kr.co.bteam.streamView.domain.StreamReplyDTO;

public interface StreamReplyMapper {
	public int insert(StreamReplyDTO stReplyDto);
	
	public StreamReplyDTO read(int mvrno);
	public int delete(@Param("mvrno") int mvrno);
	
	public int update(StreamReplyDTO stReplyDto);
	
	public List<StreamReplyDTO> getListWithPaging(
			@Param("cri") Criteria cri,
			@Param("mvno") int mvno);
	
	public int getCountByBno(int mvno);
}
