package kr.co.bteam.streamView.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class StreamDTO {
	private int mvno ;
	private String title;
	private String content;
	private String writer;
	private Date regdate;
	private int viewcnt;
	
	private int replycnt;
}