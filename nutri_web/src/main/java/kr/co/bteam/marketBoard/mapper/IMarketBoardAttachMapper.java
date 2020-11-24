package kr.co.bteam.marketBoard.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.co.bteam.marketBoard.domain.MarketBoardAttachDTO;

public interface IMarketBoardAttachMapper {
	
	public void insert(MarketBoardAttachDTO MarketBoardAttachDto);
	public void delete(String uuid);
	public List<MarketBoardAttachDTO> findByBno(int m_no);
	
	public void deleteAll(@Param("m_no") int m_no);
	public List<MarketBoardAttachDTO> imageList();
	
}
