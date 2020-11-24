package kr.co.bteam.api.mapper;

import java.util.List;

import kr.co.bteam.api.domain.HFF_IngredientDTO;
import kr.co.bteam.api.domain.HFF_MaterialDTO;
import kr.co.bteam.marketBoard.domain.MarketBoardDTO;
import kr.co.bteam.noticeBoard.Criteria;

public interface IHFF_Mapper {
	public int Iinsert(HFF_IngredientDTO Idto);
	public int Minsert(HFF_MaterialDTO Mdto);
	public int Iupdate(HFF_IngredientDTO Idto);
	public int Mupdate(HFF_MaterialDTO Mdto);
	
	public List<HFF_MaterialDTO> mListApp(String search);
	public List<HFF_IngredientDTO> iListApp(String search);
	
	public HFF_IngredientDTO iRead(Integer INO);
	public HFF_MaterialDTO mRead(Integer MNO);
	
	
	
	public List<HFF_MaterialDTO> getMListWithPaging(Criteria cri);
	public List<HFF_IngredientDTO> getIListWithPaging(Criteria cri);
	
	public int getMTotalCnt(Criteria cri); 
	public int getITotalCnt(Criteria cri); 
}
