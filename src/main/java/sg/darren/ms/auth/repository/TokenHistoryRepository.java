package sg.darren.ms.auth.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sg.darren.ms.auth.model.entity.TokenHistoryEntity;

@Repository
@Transactional
public interface TokenHistoryRepository extends JpaRepository<TokenHistoryEntity, Integer> {

}
