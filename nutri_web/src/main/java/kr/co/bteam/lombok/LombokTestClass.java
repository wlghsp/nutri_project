package kr.co.bteam.lombok;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
// Default 생성자
@NoArgsConstructor

// 생성자
@AllArgsConstructor

// getter&setter&toString()
@Data
public class LombokTestClass {

	private String id;
	private String name;
	private int age;
	private char gender;
	private boolean check;
	private double height;
}
