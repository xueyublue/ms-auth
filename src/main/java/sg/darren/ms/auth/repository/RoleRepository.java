package sg.darren.ms.auth.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import sg.darren.ms.auth.model.entity.RoleEntity;

import java.util.Optional;

@Repository
@Transactional
public interface RoleRepository extends JpaRepository<RoleEntity, Integer> {

    RoleEntity findByRoleId(String roleId);

    @Modifying
    @Query("delete from RoleEntity where roleId = :roleId")
    void deleteByRoleId(String roleId);

}
