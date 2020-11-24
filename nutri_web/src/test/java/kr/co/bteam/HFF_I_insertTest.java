package kr.co.bteam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import kr.co.bteam.api.domain.HFF_IngredientDTO;
import kr.co.bteam.api.mapper.IHFF_Mapper;
import lombok.extern.log4j.Log4j;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class HFF_I_insertTest {
	@Autowired
	private IHFF_Mapper mapper;
	
	@Test
	public void test() throws IOException, ParseException {
		String key = "0b69bed70fd540aea6df";
		String apiUrl = "http://openapi.foodsafetykorea.go.kr/api/"+ key +"/I2710/json/1/99";
		StringBuilder urlBuilder = new StringBuilder(apiUrl);
		URL url = new URL(urlBuilder.toString());
			
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
    
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
        String result= sb.toString();
            
        JSONParser parser = new JSONParser(); 
		JSONObject obj = (JSONObject) parser.parse(result); 
		JSONObject I2710 = (JSONObject) obj.get("I2710"); 

		JSONArray jarr = (JSONArray) I2710.get("row");
		
		Gson gson = new Gson();
		Type listType = new TypeToken<ArrayList<HFF_IngredientDTO>>() {			
		}.getType();
		ArrayList<HFF_IngredientDTO> row = gson.fromJson(jarr.toString(), listType);
		ArrayList<HFF_IngredientDTO> row2 = new ArrayList<>(new HashSet<HFF_IngredientDTO>(row));
		log.info(row2);
		
		int re;
		for (int i = 0; i < row2.size(); i++) {
			
			re=mapper.Iinsert(row2.get(i));
			log.info("result =========================>" + re);
		}
		
	}
	

}
