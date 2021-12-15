
package ciclo4.ciclo4.Modelo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;


/**
 *
 * @author Luis Mendez
 */
@Document(collection = "clothe")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class clothe{
    @Id
    private String reference;
    private String category;
    private String size;
    private String description;
    private boolean availability = true;
    private double price;
    private int quantity;
    private String photography; 

}
