package kr.co.bteam.streamView.service.Impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.bteam.noticeBoard.Criteria;
import kr.co.bteam.streamView.domain.StreamReplyDTO;
import kr.co.bteam.streamView.domain.StreamReplyPageDTO;
import kr.co.bteam.streamView.mapper.StreamMapper;
import kr.co.bteam.streamView.mapper.StreamReplyMapper;
import kr.co.bteam.streamView.service.IStreamReplyService;

@Service
public class StreamReplyServiceImpl implements IStreamReplyService {
	
	@Autowired
	StreamReplyMapper stReplayMapper;
	
	@Autowired
	StreamMapper stMapper;
	
	private static final Logger log =
			LoggerFactory.getLogger(StreamReplyServiceImpl.class);
	
	
	@Override
	public int register(StreamReplyDTO stReplyDto) {
		 log.info("register serviseImp................. "+ stReplyDto);
		    
		    try {
		    	stMapper.updateReplyCnt(stReplyDto.getMvno(), +1);
			} catch (Exception e) {
				log.error(e.getMessage());
			}
			return stReplayMapper.insert(stReplyDto);
	}

	@Override
	public StreamReplyDTO read(int mvrno) {
	  log.info("read STserviseImp................. "+ mvrno);
		return stReplayMapper.read(mvrno);
	}

	@Override
	public int modify(StreamReplyDTO stReplyDto) {
		log.info("modify STserviseImp................. "+ stReplyDto);
		return stReplayMapper.update(stReplyDto);
	}
	
	@Transactional
	@Override
	public int remove(int mvrno) {
		log.info("remove serviseImp................. "+ mvrno);
		  
		 StreamReplyDTO stReplyDto = stReplayMapper.read(mvrno);
		try {
			stMapper.updateReplyCnt(stReplyDto.getMvno(), -1);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return stReplayMapper.delete(mvrno);
	}

	@Override
	public List<StreamReplyDTO> getList(Criteria cri, int mvno) {
		log.info("getList serviseImp................. "+ cri + "//////" + mvno);
		return stReplayMapper.getListWithPaging(cri, mvno);
	}

	@Override
	public StreamReplyPageDTO getListPage(Criteria cri, int mvno) {
		log.info("getList................. "+ cri + "//////" + mvno);
		return new StreamReplyPageDTO(
				stReplayMapper.getCountByBno(mvno),
				stReplayMapper.getListWithPaging(cri, mvno));
	}

}
