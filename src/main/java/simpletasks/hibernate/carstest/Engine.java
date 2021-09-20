package simpletasks.hibernate.carstest;

import lombok.*;

import javax.persistence.*;

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

    private String name;


    public Engine(int id, String name) {
        this.id = id;
        this.name = name;
    }

   /* public List<Car> getCarList() {
        return carList;
    }*/
}
