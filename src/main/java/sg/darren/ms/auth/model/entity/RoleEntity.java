package sg.darren.ms.auth.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;
import sg.darren.ms.auth.model.enums.TokenValidUnitEnum;

import java.util.Date;

@Entity
@Table(name = "tbl_role")
@DynamicUpdate
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "role_id", nullable = false, updatable = false, unique = true)
    private String roleId;

    @Column(name = "role_name", nullable = false)
    private String roleName;

    @Column(name = "token_valid_value", nullable = false)
    private int tokenValidValue;

    @Enumerated(EnumType.STRING)
    @Column(name = "token_valid_unit", nullable = false)
    private TokenValidUnitEnum tokenValidUnit;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    @Column(name = "create_date", updatable = false)
    private Date createDate;

    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    @Column(name = "update_date")
    private Date updateDate;

}
