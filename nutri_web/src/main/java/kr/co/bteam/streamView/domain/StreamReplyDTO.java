package kr.co.bteam.streamView.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class StreamReplyDTO {
	private int mvrno;
	private int mvno;
	private String replytext;
	private String replyer;
	private Date regdate;
	private Date pdatedate;
}
