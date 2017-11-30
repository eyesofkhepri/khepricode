package com.eyesofkhepri.springbootjooq.core.builder;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

/**
 * 표준으로 잡은 ResponseEntity형태의 Http응답의 결과값을 생성하도록 도와주는 Builder Helper 클래스
 * @author eyesofkhpri (eyesofkhpri@gmail.com)
 * @since 2017.11.30
 * @version 0.1
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *  수정일      수정자   수정내용
 *  ----------  ------   ---------------------------
 *  2017.11.30  eyesofkhpri    최초 작성
 *
 * </pre>
 */

@Data
public class ResResultBuilder {
    // Response Result 코드: 이 코드 정보를 통해서 서비스에서 어떤 상황인지 알 수 있다.
    public static final String RESULT_SUCCESS_CD = "0000";
    public static final String RESULT_FAILUE_CD = "9999";

    // Response Result 메세지
    public static final String RESULT_SUCCESS_MSG = "success";
    public static final String RESULT_FAILUE_MSG = "failure";

    /**
     * ResponseEntity를 반환할때 resultCd, resultMsg를 추가적으로 넣어주기 위해서 사용하는 기능
     * @param resultCd : 결과코드
     * @param resultMsg : 결과 메세지
     * @param body : 데이터
     * @param status : 상태값
     * @return
     */
    public static ResponseEntity<?> result(String resultCd, String resultMsg, Object body, HttpStatus status) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("resultCd", resultCd);
        resultMap.put("resultMsg", resultMsg);
        resultMap.put("result", body);

        return new ResponseEntity<>(resultMap, status);
    }

    /**
     * ResponseEntity를 반환할때 resultCd, resultMsg를 추가적으로 넣어주기 위해서 사용하는 기능
     * @param resultCd
     * @param resultMsg
     * @param body
     * @param page : 페이징 객체
     * @param status
     * @return
     */
    public static ResponseEntity<?> resultPage(String resultCd, String resultMsg, Object body, Object page, HttpStatus status) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("resultCd", resultCd);
        resultMap.put("resultMsg", resultMsg);
        resultMap.put("page", page);
        resultMap.put("result", body);

        return new ResponseEntity<>(resultMap, status);
    }

    public static ResponseEntity<?> resultPageTotal(String resultCd, String resultMsg, Object body, Object page, Object total, HttpStatus status) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("resultCd", resultCd);
        resultMap.put("resultMsg", resultMsg);
        resultMap.put("page", page);
        resultMap.put("total", total);
        resultMap.put("result", body);

        return new ResponseEntity<>(resultMap, status);
    }

}
