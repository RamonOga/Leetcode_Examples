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
@Table(name = "driver")
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "driver_name")
    private String name;


   @ElementCollection(targetClass=Car.class)
    private List<Car> carList = new ArrayList<>();

    public Driver (int id, String name) {
        this.id = id;
        this.name = name;
    }

    public void addCar(Car car) {
        this.carList.add(car);
    }
}
