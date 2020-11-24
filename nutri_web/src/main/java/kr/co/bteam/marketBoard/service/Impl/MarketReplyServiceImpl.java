package kr.co.bteam.marketBoard.service.Impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.bteam.marketBoard.domain.ReplyDTO;
import kr.co.bteam.marketBoard.domain.ReplyPageDTO;
import kr.co.bteam.marketBoard.mapper.IMarketBoard_Mapper;
import kr.co.bteam.marketBoard.mapper.IMarketReply_Mapper;
import kr.co.bteam.marketBoard.service.IMarketReplyService;
import kr.co.bteam.noticeBoard.Criteria;

@Service
public class MarketReplyServiceImpl implements IMarketReplyService{
	
	@Autowired
	private IMarketReply_Mapper replyMapper;
	
	@Autowired
	private IMarketBoard_Mapper mapper;
	
	private static final Logger log =
			LoggerFactory.getLogger(MarketReplyServiceImpl.class);
	
	@Transactional
	@Override
	public int register(ReplyDTO replyDto) {
	    log.info("register serviseImp................. "+ replyDto);
	    
	    try {
	    	mapper.updateReplyCnt(replyDto.getM_no(), +1);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return replyMapper.insert(replyDto);
	}

	@Override
	public ReplyDTO read(int rno) {
	    log.info("read serviseImp................. "+ rno);
		return replyMapper.read(rno);
	}

	@Override
	public int modify(ReplyDTO replyDto) {
		log.info("modify serviseImp................. "+ replyDto);
		return replyMapper.update(replyDto);
	}
	
	@Transactional
	@Override
	public int remove(int rno) {
		  log.info("remove serviseImp................. "+ rno);
		  
		  ReplyDTO replyDto = replyMapper.read(rno);
		  
		  try {
			mapper.updateReplyCnt(replyDto.getM_no(), -1);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		  
		return replyMapper.delete(rno);
	}

	@Override
	public List<ReplyDTO> getList(Criteria cri, int m_no) {
		log.info("getList serviseImp................. "+ cri + "//////" + m_no);
		return replyMapper.getListWithPaging(cri, m_no);
	}

	@Override
	public ReplyPageDTO getListPage(Criteria cri, int m_no) {
		log.info("getList................. "+ cri + "//////" + m_no);
		return new ReplyPageDTO(
				replyMapper.getCountByBno(m_no),
				replyMapper.getListWithPaging(cri, m_no));
	}
	

}
