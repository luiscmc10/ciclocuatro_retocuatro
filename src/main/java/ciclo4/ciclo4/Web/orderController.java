
package ciclo4.ciclo4.Web;

import ciclo4.ciclo4.Modelo.Order;
import ciclo4.ciclo4.Servicios.serviciosOrder;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Luis Mendez
 */
@RestController
@RequestMapping("/api/order")
@CrossOrigin("*")
public class orderController {
    
        @Autowired
    private serviciosOrder servicios;

    @GetMapping("/all")
    public List<Order> getOrder() {
        return servicios.getAll();
    }
    
    @GetMapping("/{id}")
    public Optional<Order> Order(@PathVariable("id") Integer id) {
        return servicios.getOrder(id);
    }

    @GetMapping("/salesman/{id}")
    public List<Order> salesMan(@PathVariable("id") Integer id) {
        return servicios.SalesMan(id);
    }

    @GetMapping("/zona/{zona}")
    public List<Order> getZona(@PathVariable("zona") String zona) {
        return servicios.findByZone(zona);
    }
   
    @GetMapping("/date/{date}/{id}")
    public List<Order> getDate(@PathVariable("date") String date, @PathVariable("id") Integer id) {
        return servicios.ordersSalesManByDate(date,id);
    }
    
    @GetMapping("/state/{state}/{id}")
    public List<Order> getState(@PathVariable("state") String state, @PathVariable("id") Integer id) {
        return servicios.ordersSalesManByState(state,id);
    }
    
    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public Order save(@RequestBody Order order) throws Exception {
        return servicios.save(order);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public Order update(@RequestBody Order order) {
        return servicios.update(order);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean delete(@PathVariable("id") Integer id) {
        return servicios.deleteOrder(id);
    
    }
}
