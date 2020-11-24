package kr.co.bteam.noticeBoard.service;

import java.util.List;

import kr.co.bteam.noticeBoard.Criteria;
import kr.co.bteam.noticeBoard.domain.NoticeBoardDTO;



public interface INoticeService {
	public int register(NoticeBoardDTO nbDto) throws Exception;
	public NoticeBoardDTO read(Integer nbDto) throws Exception;
	public boolean modify(NoticeBoardDTO nbDto) throws Exception;
	public boolean remove(Integer nbDto) throws Exception;
	public List<NoticeBoardDTO> listAll(Criteria cri) throws Exception;
	public int getTotalCnt(Criteria cri) throws Exception;
	public List<NoticeBoardDTO> listforHome() throws Exception;
}
