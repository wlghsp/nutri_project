package kr.co.bteam.marketBoard.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ReplyPageDTO {
	private int replyCnt;
	private List<ReplyDTO> list;
}
