package sg.darren.ms.auth.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sg.darren.ms.auth.model.auth.AuthReqDto;
import sg.darren.ms.auth.model.auth.AuthValidateTokenResDto;
import sg.darren.ms.auth.service.AuthService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public String loginAndGenerateToken(@RequestBody AuthReqDto authRequest) {
        return authService.loginAndGenerateToken(authRequest);
    }

    @PostMapping("/validateToken")
    public AuthValidateTokenResDto validateToken() {
        return authService.validateToken();
    }

}
