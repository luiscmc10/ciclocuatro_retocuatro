
package ciclo4.ciclo4.Servicios;

import ciclo4.ciclo4.Modelo.Order;
import ciclo4.ciclo4.Repositorio.OrderRepositorio;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Luis Mendez
 */
@Service
public class serviciosOrder {

    @Autowired
    private OrderRepositorio metodosCrud;

    public List<Order> getAll() {
        return metodosCrud.getAll();
    }

    public Optional<Order> getOrder(int id) {
        return metodosCrud.getOrder(id);
    }

    public Order save(Order order) {
        //obtiene el maximo id existente en la coleccion
        Optional<Order> orderIdMaxima = metodosCrud.lastUserId();
        
        //si el id de la orden que se recibe como parametro es nulo, entonces valida el maximo id existente en base de datos
        if (order.getId() == null) {
            //valida el maximo id generado, si no hay ninguno aun el primer id sera 1
            if (orderIdMaxima.isEmpty())
                order.setId(1);
            //si retorna informacion suma 1 al maximo id existente y lo asigna como el codigo de la orden
            else
                order.setId(orderIdMaxima.get().getId() + 1);
        }
        
        Optional<Order> e = metodosCrud.getOrder(order.getId());
        if (e.isEmpty()) {
            return metodosCrud.save(order);            
        }else{
            return order;
        }        
    }

    public Order update(Order order) {
        if (order.getId() != null) {
            Optional<Order> evt = metodosCrud.getOrder(order.getId());
            if (!evt.isEmpty()) {
                if (order.getRegisterDay()!= null) {
                    evt.get().setRegisterDay(order.getRegisterDay());
                }
                if (order.getStatus()!= null) {
                    evt.get().setStatus(order.getStatus());
                }
                if (order.getSalesMan()!= null) {
                    evt.get().setSalesMan(order.getSalesMan());
                }
                if (order.getProducts()!= null) {
                    evt.get().setProducts(order.getProducts());
                }
                if (order.getQuantities()!= null) {
                    evt.get().setQuantities(order.getQuantities());
                }
                metodosCrud.save(evt.get());
                return evt.get();
            } else {
                return order;
            }
        } else {
            return order;
        }
    }

    public boolean deleteOrder(int id) {
        Boolean del = getOrder(id).map(order -> {
            metodosCrud.delete(order);
            return true;
        }).orElse(false);
        return del;
    }
    
    //Ordenes de pedido asociadas a los asesores de una zona
    public List<Order> findByZone(String zona) {
        return metodosCrud.findByZone(zona);
    }
   
    public List<Order> SalesMan(int id) {
        return metodosCrud.ordersSalesManByID(id); 
    }

     public List<Order> ordersSalesManByDate(String dateStr, int id) {
        return metodosCrud.ordersSalesManByDate(dateStr, id);
    }

    public List<Order> ordersSalesManByState(String state, Integer id) {
         return metodosCrud.ordersSalesManByState(state, id);
     }
     
}
