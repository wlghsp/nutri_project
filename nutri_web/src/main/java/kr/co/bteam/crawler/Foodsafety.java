package kr.co.bteam.crawler;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import kr.co.bteam.api.domain.CrawlerDTO;


public class Foodsafety {
	private static final Logger log = LoggerFactory.getLogger(Foodsafety.class);
	
	public ArrayList<CrawlerDTO> FoodsafetyInfo() throws Exception {
		Elements elements = null;
		ArrayList<CrawlerDTO> list = new ArrayList<>();
		try {
			String URL = "https://www.foodsafetykorea.go.kr/main.do";

			Document doc;
			doc = Jsoup.connect(URL).get();
			elements = doc.select(".alim_wrap a img");
			for (int i = 0; i < elements.size(); i++) {
				CrawlerDTO cDto = new CrawlerDTO(); 
				String src = elements.get(i).attr("src");
				src = "https://www.foodsafetykorea.go.kr" + src;
				cDto.setSrc(src);
				list.add(cDto);
			}
			log.info("List ========" + list);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
//	public Object FoodsafetyInfo() throws Exception {
//		Elements elements = null;
//		try {
//			String URL = "https://www.foodsafetykorea.go.kr/main.do";
//
//			Document doc;
//			doc = Jsoup.connect(URL).get();
//			elements = doc.select(".alim_wrap a");
//			
//			
//			log.info("List ========" + elements);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return elements;
//	}
}
