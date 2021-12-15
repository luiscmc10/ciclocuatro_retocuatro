package ciclo4.ciclo4.Interface;


import ciclo4.ciclo4.Modelo.clothe;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

@Component
public interface interfaceClothe extends MongoRepository<clothe, String> {

    
}
