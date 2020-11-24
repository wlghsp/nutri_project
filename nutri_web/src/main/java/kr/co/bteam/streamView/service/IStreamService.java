package kr.co.bteam.streamView.service;

import java.util.List;

import kr.co.bteam.noticeBoard.Criteria;
import kr.co.bteam.streamView.domain.StreamDTO;

public interface IStreamService {
	public int register(StreamDTO mvDto);
	public StreamDTO read(Integer mvno);
	public int modify(StreamDTO mvDto);
	public int remove(Integer mvno);
	public List<StreamDTO> listAll(Criteria cri);
	public List<StreamDTO> listAllApp();
	public int getTotalCnt(Criteria cri); 
	
}
