package kr.co.bteam.streamView.service;

import java.util.List;

import kr.co.bteam.noticeBoard.Criteria;
import kr.co.bteam.streamView.domain.StreamReplyDTO;
import kr.co.bteam.streamView.domain.StreamReplyPageDTO;

public interface IStreamReplyService {
	public int register(StreamReplyDTO stReplyDto);
	
	public StreamReplyDTO read(int mvrno);
	
	public int modify(StreamReplyDTO stReplyDto);
	
	public int remove(int mvrno);
	
	public List<StreamReplyDTO> getList(Criteria cri, int mvno);
	
	public StreamReplyPageDTO getListPage(Criteria cri, int mvno);
}
