package kr.co.bteam.api.service;

import java.util.List;

import kr.co.bteam.api.domain.HFF_IngredientDTO;
import kr.co.bteam.api.domain.HFF_MaterialDTO;
import kr.co.bteam.marketBoard.domain.MarketBoardDTO;
import kr.co.bteam.noticeBoard.Criteria;

public interface IHFF_Service {
	public List<HFF_IngredientDTO> iListApp(String search);
	public List<HFF_MaterialDTO> mListApp(String search);
	
	
	public List<HFF_IngredientDTO> iListWeb(Criteria cri);
	public List<HFF_MaterialDTO> mListWeb(Criteria cri);
	public HFF_IngredientDTO iRead(Integer INO);
	public HFF_MaterialDTO mRead(Integer MNO);
	
	public int getMTotalCnt(Criteria cri); 
	public int getITotalCnt(Criteria cri); 
	
}
