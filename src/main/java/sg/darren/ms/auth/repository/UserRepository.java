package sg.darren.ms.auth.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import sg.darren.ms.auth.model.entity.UserEntity;

import java.util.Optional;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    Optional<UserEntity> findByUsername(String username);

    @Modifying
    @Query("delete from UserEntity where username = :username")
    void deleteByUsername(String username);

}
