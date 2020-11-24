package kr.co.bteam.marketBoard.service.Impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import kr.co.bteam.marketBoard.domain.MarketBoardAttachDTO;
import kr.co.bteam.marketBoard.domain.MarketBoardDTO;
import kr.co.bteam.marketBoard.mapper.IMarketBoardAttachMapper;
import kr.co.bteam.marketBoard.mapper.IMarketBoard_Mapper;
import kr.co.bteam.marketBoard.service.IMarketBoard_Service;
import kr.co.bteam.noticeBoard.Criteria;

@Service
public class MarketBoard_ServiceImpl implements IMarketBoard_Service{
	
	@Autowired
	private IMarketBoard_Mapper mapper;
	
	@Autowired
	private IMarketBoardAttachMapper attachMapper;
	
	private static final Logger logger = 
			LoggerFactory.getLogger(MarketBoard_ServiceImpl.class);
	
	
	@Transactional
	@Override
	public void register(MarketBoardDTO mbDto) {
		mapper.create(mbDto);
		if(mbDto.getAttachList() == null||mbDto.getAttachList().size() <= 0) {
			return;
		}
		mbDto.getAttachList().forEach(attach ->{
			attach.setM_no(mbDto.getM_no());
			attachMapper.insert(attach);
		});		
	}
	
	@Transactional(isolation = Isolation.READ_COMMITTED)
	@Override
	public MarketBoardDTO read(Integer m_no) {
		//mapper.updateViewCnt(m_no);
		return mapper.read(m_no);
	}
	
	@Transactional
	@Override
	public int modify(MarketBoardDTO mbDto) {
		attachMapper.deleteAll(mbDto.getM_no());
		int modifyResult = mapper.update(mbDto);
		logger.info("modifyResult ===========================" + modifyResult);
		
		if(mbDto.getAttachList() != null) {
			if(modifyResult==1 && mbDto.getAttachList().size() > 0) {
				mbDto.getAttachList().forEach(attach ->{
					attach.setM_no(mbDto.getM_no());
					attachMapper.insert(attach);
				});
				
			}
		}
		return mapper.update(mbDto);
	}

	@Override
	public int remove(Integer m_no) {
		attachMapper.deleteAll(m_no);
		return mapper.delete(m_no);
	}

	@Override
	public List<MarketBoardDTO> listAll(Criteria cri) {
		return mapper.getListwithPaging(cri);
	}

	@Override
	public int getTotalCnt(Criteria cri) {
		return mapper.getTotalCnt(cri);
	}

	@Override
	public List<MarketBoardAttachDTO> getAttachList(int m_no) {
		return attachMapper.findByBno(m_no);
	}

	@Override
	public List<MarketBoardDTO> listAllApp() {
		return mapper.listAllApp();
	}

	@Override
	public List<MarketBoardAttachDTO> imageList() {
		List<MarketBoardAttachDTO> dto = attachMapper.imageList();
		for (int i = 0; i < dto.size(); i++) {
			String path = dto.get(i).getUploadPath();
			String year = path.substring(0, 4);
			String month = path.substring(5, 7);
			String day = path.substring(8, 10);
			
			path = year + "/" + month + "/" + day;
			dto.get(i).setUploadPath(path);
		}
		
		return dto;
	}
}
