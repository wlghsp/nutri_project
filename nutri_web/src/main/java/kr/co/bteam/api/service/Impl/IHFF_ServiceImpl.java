package kr.co.bteam.api.service.Impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.bteam.api.domain.HFF_IngredientDTO;
import kr.co.bteam.api.domain.HFF_MaterialDTO;
import kr.co.bteam.api.mapper.IHFF_Mapper;
import kr.co.bteam.api.service.IHFF_Service;
import kr.co.bteam.noticeBoard.Criteria;

@Service
public class IHFF_ServiceImpl implements IHFF_Service{
	
	@Autowired
	private IHFF_Mapper mapper;
	
	private static final Logger log = LoggerFactory.getLogger(IHFF_ServiceImpl.class);
	
	@Override
	public List<HFF_IngredientDTO> iListApp(String search) {
		log.info("getIList................." + search);
		return mapper.iListApp(search);
	}

	@Override
	public List<HFF_MaterialDTO> mListApp(String search) {
		log.info("getHList................." + search);
		return mapper.mListApp(search);
	}

	@Override
	public int getMTotalCnt(Criteria cri) {
		return mapper.getMTotalCnt(cri);
	}
	
	@Override
	public int getITotalCnt(Criteria cri) {
		return mapper.getITotalCnt(cri);
	}

	@Override
	public List<HFF_IngredientDTO> iListWeb(Criteria cri) {
		return mapper.getIListWithPaging(cri);
	}

	@Override
	public List<HFF_MaterialDTO> mListWeb(Criteria cri) {
		return mapper.getMListWithPaging(cri);
	}

	@Override
	public HFF_IngredientDTO iRead(Integer INO) {
		return mapper.iRead(INO);
	}

	@Override
	public HFF_MaterialDTO mRead(Integer MNO) {
		return mapper.mRead(MNO);
	}

	
	
	


}
