package simpletasks.hibernate.hql.two;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@NoArgsConstructor
@Data
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String publishingHouse;

    public static Book of(String name, String publishingHouse) {
        Book b = new Book();
        b.name = name;
        b.publishingHouse = publishingHouse;
        return b;
    }
}
