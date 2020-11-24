package kr.co.bteam.myHealth.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class monthDTO {
	private int month;
	private int oneWeekPush;
	private int twoWeekPush;
	private int treeWeekPush;
	private int fourWeekPush;
	private int fiveWeekPush;
	private int oneWeekStanddown;
	private int twoWeekStanddown;
	private int treeWeekStanddown;
	private int fourWeekStanddown;
	private int fiveWeekStanddown;
}
