package kr.co.bteam.marketBoard.service;

import java.util.List;

import kr.co.bteam.marketBoard.domain.MarketBoardAttachDTO;
import kr.co.bteam.marketBoard.domain.MarketBoardDTO;
import kr.co.bteam.noticeBoard.Criteria;

public interface IMarketBoard_Service {
	public void register(MarketBoardDTO mbDto);
	public MarketBoardDTO read(Integer m_no);
	public int modify(MarketBoardDTO mbDto);
	public int remove(Integer m_no);
	public List<MarketBoardDTO> listAll(Criteria cri);
	public List<MarketBoardDTO> listAllApp();
	
	public int getTotalCnt(Criteria cri); 
	public List<MarketBoardAttachDTO> getAttachList(int m_no);
	public List<MarketBoardAttachDTO> imageList();

}
