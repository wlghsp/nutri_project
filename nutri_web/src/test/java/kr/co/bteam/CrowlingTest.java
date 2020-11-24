package kr.co.bteam;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.co.bteam.api.domain.CrawlerDTO;
import kr.co.bteam.api.domain.NutrientDTO;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class CrowlingTest {

	@Test
	public void test() {
		Elements elements = null;
		Elements elements2 = null;
		
		try {
			String URL = "https://www.foodsafetykorea.go.kr/main.do";

			Document doc;
			doc = Jsoup.connect(URL).get();
			elements = doc.select(".alim_wrap a img");
			elements2 = doc.select(".alim_wrap a");
			log.info("a ==========" + elements2);
			ArrayList<CrawlerDTO> list = new ArrayList<>();
			for (int i = 0; i < elements.size(); i++) {
				CrawlerDTO cDto = new CrawlerDTO(); 
				String src = elements.get(i).attr("src");
				src = "https://www.foodsafetykorea.go.kr/" + src;
				cDto.setSrc(src);
				list.add(cDto);
			}
			log.info("List ========" + list);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
