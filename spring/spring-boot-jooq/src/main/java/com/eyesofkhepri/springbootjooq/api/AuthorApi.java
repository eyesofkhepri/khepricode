package com.eyesofkhepri.springbootjooq.api;

import com.eyesofkhepri.springbootjooq.core.builder.ResResultBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

@Slf4j
@RestController
public class AuthorApi {

    @GetMapping("/api/author")
    public ResponseEntity<?> realTimeDspStat() {
        return ResResultBuilder.result(ResResultBuilder.RESULT_SUCCESS_CD,
                ResResultBuilder.RESULT_SUCCESS_CD,
                "DD",
                HttpStatus.OK);
    }
}
