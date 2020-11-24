package kr.co.bteam.api.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class HFF_MaterialDTO {
	   String HF_FNCLTY_MTRAL_RCOGN_NO; //인정번호
	   String PRMS_DT; // 인정 일자
	   String BSSH_NM; //업체명
	   String INDUTY_NM; //업종
	   String ADDR;  // 주소
	   String APLC_RAWMTRL_NM; //신청원료명
	   String FNCLTY_CN; //기능성 내용
	   String DAY_INTK_CN; //1일 섭취량
	   String IFTKN_ATNT_MATR_CN; //섭취시 주의사항
	   int MNO;
	
}
