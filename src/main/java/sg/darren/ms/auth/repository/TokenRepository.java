package sg.darren.ms.auth.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sg.darren.ms.auth.model.entity.TokenEntity;

@Repository
@Transactional
public interface TokenRepository extends JpaRepository<TokenEntity, Integer> {

    TokenEntity findByToken(String token);

    TokenEntity findByUsername(String username);

}
