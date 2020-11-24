package kr.co.bteam.marketBoard.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.co.bteam.marketBoard.domain.ReplyDTO;
import kr.co.bteam.noticeBoard.Criteria;

public interface IMarketReply_Mapper {
	public int insert(ReplyDTO replyDto);
	
	public ReplyDTO read(int rno);
	public int delete(@Param("rno") int rno);
	
	public int update(ReplyDTO replyDto);
	
	public List<ReplyDTO> getListWithPaging(
			@Param("cri") Criteria cri,
			@Param("m_no") int m_no);
	
	public int getCountByBno(int m_no);
	
}
