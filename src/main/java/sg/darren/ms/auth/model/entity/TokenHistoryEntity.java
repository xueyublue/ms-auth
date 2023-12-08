package sg.darren.ms.auth.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;

import java.util.Date;

@Entity
@Table(name = "tbl_token_history")
@DynamicUpdate
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TokenHistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", nullable = false, updatable = false)
    private String username;

    @Column(name = "token", nullable = false, updatable = false, unique = true)
    private String token;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "issue_date", updatable = false)
    private Date issueDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "expiry_date", updatable = false)
    private Date expiryDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "token_create_date", updatable = false)
    private Date tokenCreateDate;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    @Column(name = "last_used_date", updatable = false)
    private Date lastUsedDate;

    @Column(name = "client_address", updatable = false)
    private String clientAddress;

    @Column(name = "client_browser", updatable = false)
    private String clientBrowser;

    @Column(name = "client_os", updatable = false)
    private String clientOs;

    @Column(name = "client_details", updatable = false)
    private String clientDetails;

}
