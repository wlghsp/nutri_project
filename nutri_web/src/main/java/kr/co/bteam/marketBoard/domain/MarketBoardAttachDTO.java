package kr.co.bteam.marketBoard.domain;

import lombok.Data;

@Data
public class MarketBoardAttachDTO {
	private String uuid;
	private String uploadPath;
	private String fileName;
	private boolean filetype;
	
	private int m_no;
}
