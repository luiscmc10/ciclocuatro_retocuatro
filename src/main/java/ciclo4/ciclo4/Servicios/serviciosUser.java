package ciclo4.ciclo4.Servicios;

import ciclo4.ciclo4.Modelo.User;
import ciclo4.ciclo4.Repositorio.UserRepositorio;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class serviciosUser {

    @Autowired
    private UserRepositorio metodosCrud;

    public List<User> getAll() {
        return metodosCrud.getAll();
    }

    public Optional<User> getUser(int id) {
        return metodosCrud.getUser(id);
    }

    public boolean existeEmail(String email) {
        return metodosCrud.existeEmail(email);
    }

    public User getExistsEmailAndPassword(String email, String password) {

        return metodosCrud.getExistsEmailAndPassword(email, password);
    }

    public User save(User user) {
         
        Optional<User> userIdMaximo = metodosCrud.lastUserId();
        
       
        if (user.getId() == null) {
            
            if (userIdMaximo.isEmpty())
                user.setId(1);
           
            else
                user.setId(userIdMaximo.get().getId() + 1);
        }
        
        Optional<User> e = metodosCrud.getUser(user.getId());
        if (e.isEmpty()) {
            if (existeEmail(user.getEmail())==false){
                return metodosCrud.save(user);
            }else{
                return user;
            }
        }else{
            return user;
        }
    }

    public User update(User user) {
        if (user.getId() != null) {
            Optional<User> evt = metodosCrud.getUser(user.getId());
            if (!evt.isEmpty()) {
                if (user.getIdentification() != null) {
                    evt.get().setEmail(user.getIdentification());
                }
                if (user.getName() != null) {
                    evt.get().setName(user.getName());
                }
                if (user.getBirthtDay() != null) {
                    evt.get().setBirthtDay(user.getBirthtDay());
                }
                if (user.getMonthBirthtDay() != null) {
                    evt.get().setMonthBirthtDay(user.getMonthBirthtDay());
                }
                if (user.getAddress() != null) {
                    evt.get().setAddress(user.getAddress());
                }
                if (user.getCellPhone() != null) {
                    evt.get().setCellPhone(user.getCellPhone());
                }
                if (user.getEmail() != null) {
                    evt.get().setEmail(user.getEmail());
                }
                if (user.getPassword() != null) {
                    evt.get().setPassword(user.getPassword());
                }
                if (user.getZone() != null) {
                    evt.get().setZone(user.getZone());
                }
                if (user.getType() != null) {
                    evt.get().setType(user.getType());
                }
                metodosCrud.save(evt.get());
                return evt.get();
            } else {
                return user;
            }
        } else {
            return user;
        }
    }

    public boolean deleteUser(int id) {
        Boolean del = getUser(id).map(user -> {
            metodosCrud.delete(user);
            return true;
        }).orElse(false);
        return del;
    }

}
