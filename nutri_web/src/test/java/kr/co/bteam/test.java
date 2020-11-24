package kr.co.bteam;

import java.io.FileInputStream;
import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import kr.co.bteam.api.domain.NutrientDTO;
import kr.co.bteam.api.mapper.Nutrient_Mapper;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class test {
	
	@Autowired
	private Nutrient_Mapper mapper;
	
	@Test
	public void test() {
		JsonParser  parser = new JsonParser();
		System.out.println("왔어요");
		try {
			String filePath = "D:\\project_spring\\Bteam\\src\\main\\java\\kr\\co\\bteam\\api\\domain\\data.json"; // 대상
																													// 파일
			FileInputStream fileStream = null; // 파일 스트림

			fileStream = new FileInputStream(filePath);// 파일 스트림 생성
			// 버퍼 선언

			byte[] readBuffer = new byte[fileStream.available()];
			while (fileStream.read(readBuffer) != -1) {
			}

			fileStream.close(); // 스트림 닫기

			String JsonByteArray = new String(readBuffer, "utf-8");

			
			 
			JsonArray  jarr = new JsonArray();
			JsonArray obj = (JsonArray) parser.parse(JsonByteArray);
			ArrayList<NutrientDTO> list = new ArrayList<>();
			
			for (int i = 0; i < obj.size(); i++) {
				NutrientDTO dto =new NutrientDTO();
				
				JsonObject object = (JsonObject) obj.get(i);
				String nutrient = object.get("nutrient").getAsString();
				String gender = object.get("gender").getAsString();
				String age = object.get("age").getAsString();
				String Average_Intake = object.get("Average_Intake").getAsString();
				String Recommended_Intake = object.get("Recommended_Intake").getAsString();
				String Sufficient_intake = object.get("Sufficient_intake").getAsString();
				String Maximum_Intake = object.get("Maximum_Intake").getAsString();
				dto.setNutrient(nutrient);
				dto.setGender(gender);
				dto.setAge(age);
				dto.setAverage_Intake(Average_Intake);
				dto.setRecommended_Intake(Recommended_Intake);
				dto.setSufficient_intake(Sufficient_intake);
				dto.setMaximum_Intake(Maximum_Intake);
				
				list.add(dto);
			}
			
			System.out.println("list==============================="+ list.get(0));
			
			for (int i = 0; i < list.size(); i++) {
				NutrientDTO dto = list.get(i);
				log.info(dto);
				mapper.insert(dto);
			}

		} catch (Exception e) {
		
			System.out.println("에러났어요");
			 e.printStackTrace();
		}

	}

}
