
package ciclo4.ciclo4.Repositorio;

import ciclo4.ciclo4.Interface.interfaceOrder;
import ciclo4.ciclo4.Modelo.Order;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import org.springframework.stereotype.Repository;

/**
 *
 * @author Luis Mendez
 */
@Repository
public class OrderRepositorio {

    @Autowired
    private interfaceOrder crud;

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Order> getAll() {
        return (List<Order>) crud.findAll();
    }

    public Optional<Order> getOrder(int id) {
        return crud.findById(id);
    }

    public Order save(Order order) {
        return crud.save(order);
    }

    public void delete(Order order) {
        crud.delete(order);
    }

    public Optional<Order> lastUserId() {
        return crud.findTopByOrderByIdDesc();
    }

    public List<Order> findByZone(String zona) {
        return crud.findByZone(zona);
    }

    public List<Order> SalesMan(int id) {
        return crud.findSalesManById(id);
    }

    public List<Order> ordersSalesManByDate(String dateStr, int id) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        Query query = new Query();
        Criteria criterio = Criteria.where("registerDay")
                .gte(LocalDate.parse(dateStr,dtf).minusDays(1).atStartOfDay())
                .lt(LocalDate.parse(dateStr,dtf).plusDays(2).atStartOfDay())
                        .and("salesMan.id").is(id);
        query.addCriteria(criterio);
        List<Order> orders = mongoTemplate.find(query, Order.class);
        return orders;
    }

    public List<Order> ordersSalesManByState(String state, Integer id) {
        Query query = new Query();
        Criteria criterio = Criteria.where("salesMan.id").is(id).and("status").is(state);
        query.addCriteria(criterio);
        List<Order> orders = mongoTemplate.find(query, Order.class);
        return orders;
    }

    public List<Order> ordersSalesManByID(Integer id) {
        Query query = new Query();
        Criteria criterio = Criteria.where("salesMan.id").is(id);
        query.addCriteria(criterio);
        List<Order> orders = mongoTemplate.find(query, Order.class);
        return orders;
    }

}
