package simpletasks.hibernate.carstest;

import lombok.*;

import javax.persistence.*;


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

    private String name;


 /*  @ElementCollection(targetClass=Car.class)
    private List<Car> carList = new ArrayList<>();*/

    public Driver (int id, String name) {
        this.id = id;
        this.name = name;
    }

   /* public void addCar(Car car) {
        this.carList.add(car);
    }*/
}
