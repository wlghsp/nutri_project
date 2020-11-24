package kr.co.bteam.streamView.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class StreamReplyPageDTO {
	private int replyCnt;
	private List<StreamReplyDTO> list;
}
