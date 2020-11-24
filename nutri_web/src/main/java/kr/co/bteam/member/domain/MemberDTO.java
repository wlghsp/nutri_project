package kr.co.bteam.member.domain;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MemberDTO {
	private Integer meno;
	private String email;
	private String passwd;
	private String gender;
	private String nickname;
	private String dateofbirth;
	private Date indate;
	private String useyn;
	private String image_path;
	private String user_key;    
	
}
