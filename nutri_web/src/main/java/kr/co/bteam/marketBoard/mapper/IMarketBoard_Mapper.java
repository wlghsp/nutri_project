package kr.co.bteam.marketBoard.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.co.bteam.marketBoard.domain.MarketBoardAttachDTO;
import kr.co.bteam.marketBoard.domain.MarketBoardDTO;
import kr.co.bteam.noticeBoard.Criteria;
import kr.co.bteam.noticeBoard.domain.NoticeBoardDTO;

public interface IMarketBoard_Mapper {
	public int create(MarketBoardDTO mbDto);
	public MarketBoardDTO read(Integer m_no);
	public int update(MarketBoardDTO mbDto);
	public int delete(Integer m_no);
	public List<MarketBoardDTO> getListwithPaging(Criteria cri);
	public List<MarketBoardDTO> listAllApp();
	public List<MarketBoardDTO> listforHome();
	public void updateReplyCnt(@Param("m_no") Integer m_no, @Param("amount") int amount);
	public int getTotalCnt(Criteria cri);

	
	
}
