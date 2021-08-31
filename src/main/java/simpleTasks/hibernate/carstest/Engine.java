package simpleTasks.hibernate.carstest;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Data
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "engine")
public class Engine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "engine_name")
    private String name;

    @OneToMany(mappedBy = "engine", cascade = CascadeType.ALL)
    private List<Car> carList = new ArrayList<>();



    public Engine(int id, String name) {
        this.id = id;
        this.name = name;
    }

   /* public List<Car> getCarList() {
        return carList;
    }*/
}
