package kr.co.bteam.marketBoard.domain;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MarketBoardDTO {
	private Integer m_no;
	private String m_title;
	private String m_content;
	private String m_writer;
	private Date m_regdate;
	private int m_viewcnt;
	
	private int m_replycnt;
	
	private List<MarketBoardAttachDTO> attachList;
}
