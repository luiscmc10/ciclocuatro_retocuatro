package ciclo4.ciclo4.Interface;

import ciclo4.ciclo4.Modelo.User;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface interfaceUser extends MongoRepository<User, Integer>/*CrudRepository<User, Integer>*/ {

    public User findAllByEmailAndPassword(String email,String password);


    public Optional<User> findByEmail(String email);


    public Optional<User> findTopByOrderByIdDesc();
   
   


    
}
