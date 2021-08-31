package simpleTasks.hibernate.hql.one;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;
import java.util.function.Function;


public class Runner implements AutoCloseable{
    final static StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();
    private static SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();


    public static void main(String[] args) {

        Candidate can1 = new Candidate(0,"Junior","little",1000);
        Candidate can2 = new Candidate(0,"Middle", "Acerage", 1500);
        Candidate can3 = new Candidate(0,"Senior", "Lot", 2500);



       /* add(can1);
        add(can2);
        add(can3);*/
        System.out.println(findAll());
        System.out.println(findById(6));
        System.out.println(findByName("Senior"));
        updateName(6, "Markelov Roman");
        System.out.println(findById(6));
        updateSalary(6, 3500);
        System.out.println(findById(6));
        deleteById(4);
        System.out.println(findAll());

        }

    private static <T> T tx (Function<Session, T> command) {
        Transaction transaction = null;
        try(Session session = sf.openSession()) {
            transaction = session.beginTransaction();
            T rsl = command.apply(session);
            transaction.commit();
            return rsl;
        } catch (Exception e) {
         /*   if (transaction != null) {
                transaction.rollback();
            }*/
            e.printStackTrace();
            throw e;
        }
    }

    public static void updateSalary(int id, int newSalary ) {
         tx(session -> session.createQuery(
                 "update simpleTasks.hibernate.hql.one.Candidate set " +
                         "salary = :newSalary where id = :canId")
                 .setParameter("newSalary", newSalary)
                 .setParameter("canId", id)
                 .executeUpdate());
    }

    public static void updateName(int id, String newName ) {
        tx(session -> session.createQuery(
                "update simpleTasks.hibernate.hql.one.Candidate " +
                        " set name = :newName where id = :canId")
                .setParameter("newName", newName)
                .setParameter("canId", id)
                .executeUpdate());

    }

    public static List<Candidate> findAll() {
            return tx(session -> session
                    .createQuery("from simpleTasks.hibernate.hql.one.Candidate", Candidate.class)
                    .list());

    }

    public static Candidate findById(int id) {
        return tx(session -> session.createQuery(
                "from Candidate where id = :param", Candidate.class)
                .setParameter("param", id)
                .uniqueResult());


    }

    public static Candidate findByName(String name) {
        return tx(session -> session.createQuery(
                "from Candidate where name = :param", Candidate.class)
                .setParameter("param", name)
                .setMaxResults(1)
                .uniqueResult());

    }



    public static void deleteById(int id) {
        tx(session -> session.createQuery("delete Candidate where id = :canId")
                .setParameter("canId", id)
                .executeUpdate());
    }

    public static void add(Candidate candidate) {
        tx(session -> session.save(candidate));
    }

    @Override
    public void close() throws Exception {
        sf.close();
    }
}
