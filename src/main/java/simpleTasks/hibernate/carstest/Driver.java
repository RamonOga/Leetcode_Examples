package simpleTasks.hibernate.carstest;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@NoArgsConstructor
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



    public List<Car> getCarList() {
        return carList;
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

    public void addCar(Car car) {
        this.carList.add(car);
    }
}
