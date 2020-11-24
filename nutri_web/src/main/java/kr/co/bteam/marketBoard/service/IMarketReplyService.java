package kr.co.bteam.marketBoard.service;

import java.util.List;

import kr.co.bteam.marketBoard.domain.ReplyDTO;
import kr.co.bteam.marketBoard.domain.ReplyPageDTO;
import kr.co.bteam.noticeBoard.Criteria;

public interface IMarketReplyService {
	public int register(ReplyDTO replyDto);
	
	public ReplyDTO read(int rno);
	
	public int modify(ReplyDTO replyDto);
	
	public int remove(int rno);
	
	public List<ReplyDTO> getList(Criteria cri, int m_no);
	
	public ReplyPageDTO getListPage(Criteria cri, int m_no);
	
}
