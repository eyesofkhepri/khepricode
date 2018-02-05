package com.eyesofkhepri.springbootjooqoauth2.controller;

import com.eyesofkhepri.springbootjooqoauth2.builder.ResResultBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class MainController {

    @GetMapping("/main")
    public ResponseEntity<?> main() {
        return ResResultBuilder.result(ResResultBuilder.RESULT_SUCCESS_CD,
                ResResultBuilder.RESULT_SUCCESS_CD,
                "DD",
                HttpStatus.OK);
    }
}
