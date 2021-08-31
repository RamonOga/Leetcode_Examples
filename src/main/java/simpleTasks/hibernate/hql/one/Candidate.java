package simpleTasks.hibernate.hql.one;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor
@Data
@EqualsAndHashCode
@ToString
@Getter
@Setter
@Entity
@Table(name = "candidate")
public class Candidate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "experience")
    private String experience;
    @Column(name = "salary")
    private int salary;

}
