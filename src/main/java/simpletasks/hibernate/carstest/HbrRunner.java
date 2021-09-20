package simpletasks.hibernate.carstest;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HbrRunner {

    private final static StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();
    private final static SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();

    public static void main(String[] args) {

        Engine koreanEngine = new Engine(0, "koreanEngine");
        Engine vagEngine = new Engine(0, "vagEngine");
        Car kia = new Car(0, "kia", koreanEngine);
        Car hyundai = new Car(0, "hyundai", koreanEngine);
        Car bmw = new Car(0, "bmw", vagEngine);
        Car audi = new Car(0, "audi", vagEngine);
        Driver driver1 = new Driver(0, "Driver1");
        Driver driver2 = new Driver(0, "Driver2");

        kia.addDriver(driver1);
        hyundai.addDriver(driver1);
        bmw.addDriver(driver2);
        audi.addDriver(driver2);

        Transaction transaction = null;

        try(Session session = sf.openSession())
             {
            transaction = session.beginTransaction();
            session.save(koreanEngine);
            session.save(vagEngine);
            session.save(driver1);
            session.save(driver2);
            session.save(kia);
            session.save(hyundai);
            session.save(bmw);
            session.save(audi);
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

}
