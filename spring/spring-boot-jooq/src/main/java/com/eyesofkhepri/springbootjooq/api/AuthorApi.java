package com.eyesofkhepri.springbootjooq.api;

import com.eyesofkhepri.springbootjooq.core.builder.ResResultBuilder;
import com.eyesofkhepri.springbootjooq.service.AuthorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class AuthorApi {

    @Autowired
    private AuthorService authService;

    @GetMapping("/api/author")
    public ResponseEntity<?> realTimeDspStat() {

        authService.printAuthList();

        return ResResultBuilder.result(ResResultBuilder.RESULT_SUCCESS_CD,
                ResResultBuilder.RESULT_SUCCESS_CD,
                "DD",
                HttpStatus.OK);
    }
}
