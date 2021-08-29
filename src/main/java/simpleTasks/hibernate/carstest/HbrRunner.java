package simpleTasks.hibernate.carstest;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.SessionFactoryBuilder;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HbrRunner {

    final static StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();

    public static void main(String[] args) {

        Engine koreanEngine = new Engine(0, "koreanEngine");
        Engine vagEngine = new Engine(0, "vagEngine");
        Car kia = new Car(0, "kia", koreanEngine);
        Car hyundai = new Car(0, "hyundai", koreanEngine);
        Car bmw = new Car(0, "bmw", vagEngine);
        Car audi = new Car(0, "audi", vagEngine);
        Driver driver1 = new Driver(0, "Driver1");
        Driver driver2 = new Driver(0, "Driver2");
        Driver driver3 = new Driver(0, "Driver3");

        driver1.addCar(kia);
        driver1.addCar(hyundai);
        driver2.addCar(bmw);
        driver2.addCar(audi);
        driver3.addCar(hyundai);
        driver3.addCar(bmw);

        Transaction transaction = null;

        try(SessionFactory sf = new MetadataSources(registry)
                .buildMetadata().buildSessionFactory();
            Session session = sf.openSession()) {
            transaction = session.beginTransaction();
            session.save(koreanEngine);
            session.save(vagEngine);
            session.save(kia);
            session.save(hyundai);
            session.save(bmw);
            session.save(audi);
            session.save(driver1);
            session.save(driver2);
            session.save(driver3);
            transaction.commit();;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

}
