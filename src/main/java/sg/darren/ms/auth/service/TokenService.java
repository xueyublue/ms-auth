package sg.darren.ms.auth.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sg.darren.ms.auth.model.entity.TokenEntity;
import sg.darren.ms.auth.repository.TokenRepository;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final TokenRepository tokenRepository;
    private final JwtTokenService jwtTokenService;
    private final TokenHistoryService tokenHistoryService;

    public void create(String token) {
        String username = jwtTokenService.extractUsername(token);
        Date issueDate = jwtTokenService.extractIssueAt(token);
        Date expiryDate = jwtTokenService.extractExpiration(token);
        TokenEntity tokenEntity = tokenRepository.save(TokenEntity.builder()
                .username(username)
                .token(token)
                .issueDate(issueDate)
                .expiryDate(expiryDate)
                .build());
        tokenHistoryService.createHistory(tokenEntity);
    }

}
