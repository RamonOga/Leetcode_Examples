package simpleTasks.hibernate.carstest;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "car")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "engine_id", foreignKey = @ForeignKey(name = "ENGINE_ID_FK") )
    private Engine engine;

    /*@ElementCollection(targetClass=Driver.class)*/
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "history_owner",
            joinColumns = { @JoinColumn(name = "driver_id",
                    nullable = false, updatable = false) },
            inverseJoinColumns = { @JoinColumn(name = "car_id",
                    nullable = false, updatable = false ) })
    private List<Driver> driverList = new ArrayList<>();

    public Car(int id, String name, Engine engine) {
        this.id = id;
        this.name = name;
        this.engine = engine;
    }

    public List<Driver> getDriverList() {
        return driverList;
    }

    public void setDriverList(List<Driver> driverList) {
        this.driverList = driverList;
    }

    public void addDriver(Driver driver) {
        driverList.add(driver);
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

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }
}
