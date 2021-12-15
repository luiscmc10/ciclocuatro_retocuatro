package ciclo4.ciclo4.Web;

import ciclo4.ciclo4.Modelo.clothe;
import ciclo4.ciclo4.Servicios.serviciosClothe;
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

@RestController
@RequestMapping("/api/clothe")
@CrossOrigin("*")
public class clotheController {

    @Autowired
    private serviciosClothe servicios;

    @GetMapping("/all")
    public List<clothe> getClothe() {
        return servicios.getAll();
    }
    
    @GetMapping("/{reference}")
    public Optional<clothe> Clothe(@PathVariable("reference") String reference) {
        return servicios.getClothe(reference);
    }

    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public clothe save(@RequestBody clothe clothe) throws Exception {
        return servicios.create(clothe);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public clothe update(@RequestBody clothe clothe) {
        return servicios.update(clothe);
    }

    @DeleteMapping("/{reference}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean delete(@PathVariable("reference") String reference) {
        return servicios.delete(reference);
    
    }
    
}
