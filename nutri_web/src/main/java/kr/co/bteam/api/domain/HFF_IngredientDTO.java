package kr.co.bteam.api.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class HFF_IngredientDTO {
    String INTK_LIMIT;  //단위
    String SKLL_IX_IRDNT_RAWMTRL; //성분명
    String IFTKN_ATNT_MATR_CN; //섭취시 주의사항
    String DAY_INTK_HIGHLIMIT;//일일섭취량 상한
    String PRDCT_NM; //품목명
    String CRET_DTM; //등록일시
    String DAY_INTK_LOWLIMIT; //일일섭취량 하한
    String PRIMARY_FNCLTY; //주된 기능성
    String INTK_MEMO; //REMARK
    int INO;

}
