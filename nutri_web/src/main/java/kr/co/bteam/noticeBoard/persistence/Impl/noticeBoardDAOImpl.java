package kr.co.bteam.noticeBoard.persistence.Impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.bteam.noticeBoard.Criteria;
import kr.co.bteam.noticeBoard.domain.NoticeBoardDTO;
import kr.co.bteam.noticeBoard.persistence.INoticeBoradDAO;

@Repository
public class noticeBoardDAOImpl implements INoticeBoradDAO {

	@Autowired
	private SqlSession session;
	
	@Override
	public int create(NoticeBoardDTO nbDto) throws Exception {
		return session.insert("noticeBoardMapper.create", nbDto);	
		
	}

	@Override
	public NoticeBoardDTO read(Integer nbDto) throws Exception {
		return session.selectOne("noticeBoardMapper.read", nbDto);
	}

	@Override
	public int update(NoticeBoardDTO nbDto) throws Exception {
		return session.update("noticeBoardMapper.update",nbDto);
	}

	@Override
	public int delete(Integer nbDto) throws Exception {
		return session.delete("noticeBoardMapper.delete",nbDto);
	}

	@Override
	public List<NoticeBoardDTO> listAll(Criteria cri) throws Exception {
		return session.selectList("noticeBoardMapper.getListWithPaging", cri);
	}

	@Override
	public int getTotalCnt(Criteria cri) throws Exception {
		return session.selectOne("noticeBoardMapper.getTotalCnt", cri);
	}

	@Override
	public List<NoticeBoardDTO> listforHome() throws Exception {
		return session.selectList("noticeBoardMapper.listforHome");
	}

}
