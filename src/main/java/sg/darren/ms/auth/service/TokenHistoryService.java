package sg.darren.ms.auth.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sg.darren.ms.auth.model.entity.TokenEntity;
import sg.darren.ms.auth.model.entity.TokenHistoryEntity;
import sg.darren.ms.auth.repository.TokenHistoryRepository;

@Service
@RequiredArgsConstructor
public class TokenHistoryService {

    private final TokenHistoryRepository tokenHistoryRepository;
    private final HttpServletRequestService httpServletRequestService;

    public void createHistory(TokenEntity tokenEntity) {
        tokenHistoryRepository.save(TokenHistoryEntity.builder()
                .username(tokenEntity.getUsername())
                .token(tokenEntity.getToken())
                .issueDate(tokenEntity.getIssueDate())
                .expiryDate(tokenEntity.getExpiryDate())
                .tokenCreateDate(tokenEntity.getCreateDate())
                .clientAddress(httpServletRequestService.getClientAddress())
                .clientBrowser(httpServletRequestService.getClientBrowser())
                .clientOs(httpServletRequestService.getClientOS())
                .clientDetails(httpServletRequestService.getClientDetails())
                .build());
    }

}
