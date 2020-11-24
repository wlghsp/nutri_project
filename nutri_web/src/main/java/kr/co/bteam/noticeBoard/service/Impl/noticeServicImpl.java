package kr.co.bteam.noticeBoard.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.bteam.noticeBoard.Criteria;
import kr.co.bteam.noticeBoard.domain.NoticeBoardDTO;
import kr.co.bteam.noticeBoard.persistence.INoticeBoradDAO;
import kr.co.bteam.noticeBoard.service.INoticeService;



@Service
public class noticeServicImpl implements INoticeService{

	@Autowired
	private INoticeBoradDAO nbDao;
	
	@Override
	public int register(NoticeBoardDTO nbDto) throws Exception {
		return nbDao.create(nbDto);
	}

	@Override
	public NoticeBoardDTO read(Integer nbDto) throws Exception {
		return nbDao.read(nbDto);
	}

	@Override
	public boolean modify(NoticeBoardDTO nbDto) throws Exception {
		return nbDao.update(nbDto) ==1;
	}

	@Override
	public boolean remove(Integer nbDto) throws Exception {
		return nbDao.delete(nbDto) == 1;
	}

	@Override
	public List<NoticeBoardDTO> listAll(Criteria cri) throws Exception {
		return nbDao.listAll(cri);
	}

	@Override
	public int getTotalCnt(Criteria cri) throws Exception {
		return nbDao.getTotalCnt(cri);
	}

	@Override
	public List<NoticeBoardDTO> listforHome() throws Exception {
		return nbDao.listforHome();
	}

}
