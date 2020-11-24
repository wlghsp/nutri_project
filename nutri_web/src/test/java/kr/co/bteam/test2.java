package kr.co.bteam;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Test;

public class test2 {

	@Test
	public void test() throws IOException {
		String filePath = "D:\\project_spring\\Bteam\\src\\main\\java\\kr\\co\\bteam\\api\\domain\\data.json"; // 대상
		// 파일
		FileInputStream fileStream = null; // 파일 스트림

		try {
			fileStream = new FileInputStream(filePath);
			byte[] readBuffer = new byte[fileStream.available()];
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}// 파일 스트림 생성


	}

}
