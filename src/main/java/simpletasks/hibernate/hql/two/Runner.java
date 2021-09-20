package simpletasks.hibernate.hql.two;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class Runner {

    private final static StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();
    private static SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();

    public static void main(String[] args) {

        Student student1 = Student.of("name1", 12, "msk");
        Student student2 = Student.of("name2", 21, "spb");
        Account account1 = Account.of("name1_acc");
        Account account2 = Account.of("name2_acc");
        Book book1 = Book.of("book1", "pub house 1");
        Book book2 = Book.of("book2", "pub house 2");
        Book book3 = Book.of("book3", "pub house 3");
        Book book4 = Book.of("book4", "pub house 4");
        account1.addBook(book1);
        account1.addBook(book2);
        account2.addBook(book3);
        account2.addBook(book4);
        student1.setAccount(account1);
        student2.setAccount(account2);

        Transaction transaction = null;

        try(Session session = sf.openSession()) {
            transaction = session.beginTransaction();
            session.save(book1);
            session.save(book2);
            session.save(book3);
            session.save(book4);
            session.save(account1);
            session.save(account2);
            session.save(student1);
            session.save(student2);
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

    }
}
