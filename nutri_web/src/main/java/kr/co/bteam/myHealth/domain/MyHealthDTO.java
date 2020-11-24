package kr.co.bteam.myHealth.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MyHealthDTO {
	private String nutrient; 
	private String gender; 
	private String age; 
	private String average_intake;
	private String recommended_intake; 
	private String sufficient_intake; 
	private String maximum_intake;
}
