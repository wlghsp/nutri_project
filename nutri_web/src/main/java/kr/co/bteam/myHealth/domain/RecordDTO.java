package kr.co.bteam.myHealth.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RecordDTO {
	private int pushup; 
	private int standdown; 
	private String updatedate;
	private String nickname;
}
