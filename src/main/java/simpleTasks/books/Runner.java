package simpleTasks.books;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import simpleTasks.books.Author;
import simpleTasks.books.Book;

public class Runner {
    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();

            Book book1 = Book.of("book1");
            Book book2 = Book.of("book2");
            Book book3 = Book.of("book3");

            Author author1 = Author.of("Author1");
            author1.addBook(book1);
            author1.addBook(book2);
            Author author2 = Author.of("Author2");
            author2.addBook(book2);
            author2.addBook(book3);

            /*session.persist(author1);
            session.persist(author2);*/


            Author removeAuthor = session.get(Author.class, 1);
            session.remove(removeAuthor);

            session.getTransaction().commit();
            session.close();
        }  catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}