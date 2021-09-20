package simpletasks.hibernate.hql.one;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;
import java.util.function.Function;


public class Runner implements AutoCloseable{
    private final static StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();
    private static SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();


    public static void main(String[] args) {

        /*Vacancy vacancy1 = new Vacancy(0, "Junior Dev");
        Vacancy vacancy2 = new Vacancy(0, "Middle Dev");
        Vacancy vacancy3 = new Vacancy(0, "Senior Dev");
        Vacancy vacancy4 = new Vacancy(0, "TeamLead");

        VacancyBase vacancyBase = new VacancyBase(0, "Java Developers Base");
        vacancyBase.addVacancy(vacancy1);
        vacancyBase.addVacancy(vacancy2);
        vacancyBase.addVacancy(vacancy3);
        vacancyBase.addVacancy(vacancy4);


        Candidate can1 = new Candidate(0,"Junior","little",1000, vacancyBase);
        Candidate can2 = new Candidate(0,"Middle", "Average", 1500, vacancyBase);
        Candidate can3 = new Candidate(0,"Senior", "Lot", 2500, vacancyBase);

        addVacancy(vacancy1);
        addVacancy(vacancy2);
        addVacancy(vacancy3);
        addVacancy(vacancy4);

        addBase(vacancyBase);

        addCandidate(can1);
        addCandidate(can2);
        addCandidate(can3);*/

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
                 "update simpletasks.hibernate.hql.one.Candidate set " +
                         "salary = :newSalary where id = :canId")
                 .setParameter("newSalary", newSalary)
                 .setParameter("canId", id)
                 .executeUpdate());
    }

    public static void updateName(int id, String newName ) {
        tx(session -> session.createQuery(
                "update simpletasks.hibernate.hql.one.Candidate " +
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
                "select distinct c from Candidate c " +
                        "join fetch c.base b " +
                        "join fetch b.vacancyList " +
                        "where c.id = :cId", Candidate.class
        ).setParameter("cId", 1)
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

    public static void addCandidate(Candidate candidate) {
        tx(session -> session.save(candidate));
    }

    public static void addVacancy(Vacancy vacancy) {
        tx(session -> session.save(vacancy));
    }

    public static void addBase(VacancyBase base) {
        tx(session -> session.save(base));
    }

    @Override
    public void close() throws Exception {
        sf.close();
    }
}
