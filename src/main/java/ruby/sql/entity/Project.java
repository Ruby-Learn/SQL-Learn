package ruby.sql.entity;

import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String projectName;
    @CreatedDate
    private LocalDateTime startAt;

    @ManyToOne
    private Team team;
}
