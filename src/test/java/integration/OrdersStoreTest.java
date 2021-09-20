package integration;

import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import simpletasks.integration.OrderItem;
import simpletasks.integration.OrdersStore;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class OrdersStoreTest {
    private BasicDataSource pool = new BasicDataSource();

    @Before
    public void setUp() throws SQLException {
        pool.setDriverClassName("org.hsqldb.jdbcDriver");
        pool.setUrl("jdbc:hsqldb:mem:tests;sql.syntax_pgs=true");
        pool.setUsername("sa");
        pool.setPassword("");
        pool.setMaxTotal(2);
        StringBuilder builder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream("./db/create_test_table.sql")))
        ) {
            br.lines().forEach(line -> builder.append(line).append(System.lineSeparator()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        pool.getConnection().prepareStatement(builder.toString()).executeUpdate();
    }

    @After
    public void shutDown()  throws SQLException {

        StringBuilder builder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream("./db/drop_test_table.sql")))
        ) {
            br.lines().forEach(line -> builder.append(line).append(System.lineSeparator()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        pool.getConnection().prepareStatement(builder.toString()).executeUpdate();
    }

    @Test
    public void whenSaveOrderAndFindAllOneRowWithDescription() {
        OrdersStore store = new OrdersStore(pool);

        store.save(OrderItem.of("name1", "description1"));

        List<OrderItem> all = (List<OrderItem>) store.findAll();

        assertThat(all.size(), is(1));
        assertThat(all.get(0).getDescription(), is("description1"));
        assertThat(all.get(0).getId(), is(1));
    }

    @Test
    public void whenSaveOrderAndUpdateOneRowWithDescription() {
        OrdersStore store = new OrdersStore(pool);
        store.save(OrderItem.of("name1", "description1"));
        OrderItem updateOrder = OrderItem.of("New name", "New description");

        updateOrder.setId(1);
        store.update(updateOrder);

        OrderItem order = store.findById(1);


        assertThat(order.getDescription(), is("New description"));
        assertThat(order.getName(), is("New name"));
        assertThat(order.getId(), is(1));
    }

    @Test
    public void whenSaveOrderAndFindByIdOneRowWithDescription() {
        OrdersStore store = new OrdersStore(pool);

        store.save(OrderItem.of("name1", "description1"));
        OrderItem order = store.findById(1);

        assertThat(order.getDescription(), is("description1"));
        assertThat(order.getName(), is("name1"));
        assertThat(order.getId(), is(1));
    }
}