package com.example.harbor_login;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class GatewayTestController {
    @GetMapping("/test")
    public String test() {
        return "성공";
    }
}
