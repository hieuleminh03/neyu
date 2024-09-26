package zen8.hieule.authentication_service.model;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@Entity
@NoArgsConstructor
@Table(name = "tbl_group")
public class Group extends BaseEntity<Long> {

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @OneToOne
    @JoinColumn(name = "role_id")
    private Role role;
}
