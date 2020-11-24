package kr.co.bteam.lombok;

import jdk.nashorn.internal.runtime.logging.Logger;
import lombok.*;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Logger
public class LombokTest {
	private String id;
	private String name;
	private int age;
	private boolean gender;
	private boolean check;
	private double height;
}
