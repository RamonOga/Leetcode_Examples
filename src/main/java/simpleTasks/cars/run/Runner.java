package simpleTasks.cars.run;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import simpleTasks.cars.model.Car;
import simpleTasks.cars.model.Mark;

public class Runner {
    public static void main(String[] args) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();

        Mark kia = Mark.of("KIA");
        Car rio = Car.of("RIO");
        Car cerato = Car.of("Cerato");
        Car sportage = Car.of("Stortage");
        Car seed = Car.of("Seed");
        Car sorento = Car.of("Sorento");

        Transaction transaction = null;
        try (
                SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
                Session session = sf.openSession();
        ) {
            transaction = session.beginTransaction();
            session.save(rio);
            session.save(cerato);
            session.save(sorento);
            session.save(sportage);
            session.save(seed);

            kia.addCar(session.load(Car.class, 1));
            kia.addCar(session.load(Car.class, 2));
            kia.addCar(session.load(Car.class, 3));
            kia.addCar(session.load(Car.class, 4));
            kia.addCar(session.load(Car.class, 5));

            session.save(kia);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }
}
