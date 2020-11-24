package kr.co.bteam.marketBoard.domain;

import java.util.Date;

import lombok.Data;

@Data
public class ReplyDTO {
	private int rno;
	private int m_no;
	
	private String replytext;
	private String replyer;
	private Date regdate;
	private Date updatedate;
}
