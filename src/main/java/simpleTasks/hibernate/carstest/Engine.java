package simpleTasks.hibernate.carstest;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

   /* public List<Car> getCarList() {
        return carList;
    }*/
}
