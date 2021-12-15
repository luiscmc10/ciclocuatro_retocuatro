
package ciclo4.ciclo4.Web;

import ciclo4.ciclo4.Servicios.serviciosUser;
import ciclo4.ciclo4.Modelo.User;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author Luis Mendez
 */
@RestController
@RequestMapping("/api/user")
@CrossOrigin("*")
public class userController {
    
    @Autowired
    private serviciosUser servicios;

    @GetMapping("/all")
    public List<User> getAll() {
        return servicios.getAll();
    }
    
    @GetMapping("/{id}")
    public User getUser(@PathVariable Integer id) {
        return servicios.getUser(id).orElse(null);
    }
    
    @GetMapping("/emailexist/{email}")
    public boolean existeEmail(@PathVariable("email") String email) {

        return servicios.existeEmail(email);
        
    }
    

    @GetMapping("/{email}/{password}")
    public User getEmailAndPassword(@PathVariable("email") String email, @PathVariable("password") String password) {

        User user = servicios.getExistsEmailAndPassword(email, password);
        if (user == null) {

            User noUser = new User(null, null, null, null, null,  null, null, null, null, null, null);

            return noUser;
        }
        return user;
    }
    
    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<User> save(@RequestBody User user) throws Exception {
        User u = servicios.save(user);
        
        return new ResponseEntity(u, HttpStatus.CREATED);

    }

    //No se va a utilizar por ahora
    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity update(@RequestBody User user) {
        User u = servicios.update(user);
        
        return new ResponseEntity(u, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity delete(@PathVariable("id") int Id) {
        servicios.deleteUser(Id);
        
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }


    
}
