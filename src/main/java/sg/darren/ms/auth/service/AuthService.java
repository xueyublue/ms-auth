package sg.darren.ms.auth.service;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import sg.darren.ms.auth.exception.UnauthorizedException;
import sg.darren.ms.auth.model.auth.AuthReqDto;
import sg.darren.ms.auth.model.auth.AuthValidateTokenResDto;
import sg.darren.ms.auth.model.user.CustUserDetails;

import java.util.Date;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private final JwtTokenService jwtTokenService;
    private final AuthenticationManager authenticationManager;
    private final HttpServletRequest httpServletRequest;
    private final UserService userService;

    public String loginAndGenerateToken(AuthReqDto dto) {
        Authentication auth = new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword());
        Authentication authentication = authenticationManager.authenticate(auth);
        if (authentication.isAuthenticated()) {
            int tokenValid = userService.getHighestTokenValid(dto.getUsername());
            return jwtTokenService.generateToken(dto.getUsername(), tokenValid);
        } else {
            throw new UnauthorizedException("Unauthorized.");
        }
    }

    public AuthValidateTokenResDto validateToken() {
        // extract token
        String token = httpServletRequest.getHeader("Authorization").substring(7);
        // extract authentication
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (Objects.isNull(authentication)
                || authentication.getName() == null
                || Objects.isNull(authentication.getPrincipal())) {
            throw new UnauthorizedException("Unauthorized.");
        }
        CustUserDetails userDetails = (CustUserDetails) authentication.getPrincipal();
        // build response
        return AuthValidateTokenResDto.builder()
                .username(userDetails.getUsername())
                .token(token)
                .tokenIssuedDate(jwtTokenService.extractIssueAt(token))
                .tokenExpiryDate(jwtTokenService.extractExpiration(token))
                .roles(userDetails.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toList()))
                .accountExpired(!userDetails.isAccountNonExpired())
                .accountLocked(!userDetails.isAccountNonLocked())
                .credentialsExpired(!userDetails.isCredentialsNonExpired())
                .accountDisabled(!userDetails.isEnabled())
                .validationDate(new Date())
                .build();
    }

}
