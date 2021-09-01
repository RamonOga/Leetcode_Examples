package simpleTasks.hibernate.hql.one;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Data
@EqualsAndHashCode
@ToString
@Entity
@Table(name= "vacancybase")
public class VacancyBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    public VacancyBase(int id, String name) {
        this.name = name;
        this.id = id;
    }

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Vacancy> vacancyList = new ArrayList<>();

    public void addVacancy(Vacancy vacancy) {
        vacancyList.add(vacancy);
    }

}
