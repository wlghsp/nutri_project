package kr.co.bteam.noticeBoard.persistence;

import java.util.List;

import kr.co.bteam.noticeBoard.Criteria;
import kr.co.bteam.noticeBoard.domain.NoticeBoardDTO;



public interface INoticeBoradDAO {
	public int create(NoticeBoardDTO nbDto) throws Exception;
	public NoticeBoardDTO read(Integer nbDto) throws Exception;
	public List<NoticeBoardDTO> listforHome() throws Exception;
	public int update(NoticeBoardDTO nbDto) throws Exception;
	public int delete(Integer nbDto) throws Exception;
	public List<NoticeBoardDTO> listAll(Criteria cri) throws Exception;
	public int getTotalCnt(Criteria cri)throws Exception;
	
}
