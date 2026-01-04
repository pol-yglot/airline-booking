package com.travel.agency.controller;

import lombok.Getter;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173")
public class MainController {

    /**
     * @auther ysy
     * @desc index
     * @since 26.01.04
     * */
    @GetMapping("/")
    public String index() {
        return "index";
    }

    /**
     * @auther ysy
     * @desc 메인페이지
     * @since 26.01.04
     * */
    @GetMapping("/main")
    public ResponseEntity<Map<String, String>> main() {
        Map<String, String> result = new HashMap<>();
        result.put("message", "main api ok");
        return ResponseEntity.ok(result);
    }
}
