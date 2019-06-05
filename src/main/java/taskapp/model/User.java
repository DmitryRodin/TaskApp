package taskapp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import taskapp.model.snapshots.UserSnapshot;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.EnumType.*;

/**
 * JavaBean that represent a User
 */
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Enumerated(STRING)
    private Role roles;

}
