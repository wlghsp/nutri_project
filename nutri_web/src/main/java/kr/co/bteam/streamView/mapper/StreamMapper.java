package kr.co.bteam.streamView.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.co.bteam.marketBoard.domain.MarketBoardDTO;
import kr.co.bteam.noticeBoard.Criteria;
import kr.co.bteam.streamView.domain.StreamDTO;

public interface StreamMapper {
	public int create(StreamDTO mvDto);
	public StreamDTO read(Integer mvno);
	public int update(StreamDTO mvDto);
	public int delete(Integer mvno);
	public List<StreamDTO> getListwithPaging(Criteria cri);
	public List<StreamDTO> listAllApp();
	public void updateReplyCnt(@Param("m_no") Integer m_no, @Param("amount") int amount);
	public int getTotalCnt(Criteria cri);
	
	//메인 페이지 리스트
	public List<MarketBoardDTO> listforHome();
}
