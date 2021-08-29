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
@Table(name = "car")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "car_name")
    private String name;

    @OneToMany
    @JoinColumn(name = "engine_id")
    private Engine engine;

    @ManyToMany(mappedBy = "car")
    private List<Driver> driverList = new ArrayList<>();

    public Car(int id, String name, Engine engine) {
        this.id = id;
        this.name = name;
        this.engine = engine;
    }
}
