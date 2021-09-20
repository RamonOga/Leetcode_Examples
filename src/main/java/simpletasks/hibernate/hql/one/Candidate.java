package simpletasks.hibernate.hql.one;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@Data
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "candidate")
public class Candidate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String experience;
    private int salary;
    @OneToOne
    private VacancyBase base;

    public Candidate(int id, String name, String experience, int salary, VacancyBase base) {
        this.id = id;
        this.name = name;
        this.experience = experience;
        this.salary = salary;
        this.base = base;
    }
}
