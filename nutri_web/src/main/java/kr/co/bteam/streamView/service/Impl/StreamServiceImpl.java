package kr.co.bteam.streamView.service.Impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.bteam.noticeBoard.Criteria;
import kr.co.bteam.streamView.domain.StreamDTO;
import kr.co.bteam.streamView.mapper.StreamMapper;
import kr.co.bteam.streamView.service.IStreamService;

@Service
public class StreamServiceImpl implements IStreamService{
	
	@Autowired
	StreamMapper stMapper;
	
	private static final Logger log = 
			LoggerFactory.getLogger(StreamServiceImpl.class);
	
	
	@Override
	public int register(StreamDTO mvDto) {
		int result = stMapper.create(mvDto);
		return result;
	}

	@Override
	public StreamDTO read(Integer mvno) {
		return stMapper.read(mvno);
	}

	@Override
	public int modify(StreamDTO mvDto) {
		return stMapper.update(mvDto);
	}

	@Override
	public int remove(Integer mvno) {
		return stMapper.delete(mvno);
	}

	@Override
	public List<StreamDTO> listAll(Criteria cri) {
		return stMapper.getListwithPaging(cri);
	}

	@Override
	public List<StreamDTO> listAllApp() {
		return stMapper.listAllApp();
	}

	@Override
	public int getTotalCnt(Criteria cri) {
		return stMapper.getTotalCnt(cri);
	}

}
