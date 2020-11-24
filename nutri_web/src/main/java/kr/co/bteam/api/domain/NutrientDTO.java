package kr.co.bteam.api.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class NutrientDTO {
	private String nutrient; 
	private String gender; 
	private String age; 
	private String Average_Intake; 
	private String Recommended_Intake; 
	private String Sufficient_intake; 
	private String Maximum_Intake;
}
