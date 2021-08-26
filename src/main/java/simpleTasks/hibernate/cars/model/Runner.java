package simpleTasks.hibernate.cars.model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.ArrayList;
import java.util.List;

public class Runner {
    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        List<Mark> list = new ArrayList<>();

        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            Transaction tran = session.beginTransaction();

            list = session.createQuery(
                    "select distinct m from Mark m join fetch m.carList"
            ).list();

            tran.commit();
            session.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }

        /*for (Car car : list.get(0).getCarList()) {
            System.out.println(car);
        }*/

        for (Mark mark : list) {
            for (Car car : mark.getCarList()) {
                System.out.println(car);
            }
        }
    }

    private static void insert (Session session) {
        Mark kia = Mark.of("KIA");
        Mark bmw = Mark.of("BMW");
        Car rio = Car.of("Rio", kia);
        Car cerato = Car.of("Cerato", kia);
        Car sorento = Car.of("Sorento", kia);
        Car passat = Car.of("Passat", bmw);
        Car passatCC = Car.of("Passat CC", bmw);
        Car tuareg = Car.of("Tuareg", bmw);
        session.save(kia);
        session.save(bmw);

        session.save(rio);
        session.save(cerato);
        session.save(sorento);
        session.save(passat);
        session.save(passatCC);
        session.save(tuareg);

    }
}
