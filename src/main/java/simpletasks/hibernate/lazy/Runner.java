package simpletasks.hibernate.lazy;

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
        List<Category> list = new ArrayList<>();


        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            Transaction tran = session.beginTransaction();

            /*Category category = Category.of("Category1");
            Task task1 = Task.of("Task1", category);
            Task task2 = Task.of("Task2", category);
            Task task3 = Task.of("Task3", category);

            session.persist(category);
            session.persist(task1);
            session.persist(task2);
            session.persist(task3);*/
            list = session.createQuery(
                    "select distinct c from Category c join fetch c.tasks"
            ).list();
            tran.commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }

        for (Task task : list.get(0).getTasks()) {
            System.out.println(task);
        }

    }
}
