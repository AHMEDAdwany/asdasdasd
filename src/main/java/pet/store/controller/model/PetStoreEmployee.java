package pet.store.controller.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import pet.store.entity.Employee;

@Data 
@NoArgsConstructor 
public class PetStoreEmployee {

	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeId;
    
    private String employeeFirstName;
    
    private String employeeLastName;
    
    private String employeeWorkPhone;
    
    private String employeeRole;
    
    public PetStoreEmployee(Employee employee) {
    	employeeFirstName = employee.getEmployeeFirstName();
    	employeeLastName = employee.getEmployeeLastName();
    	employeeWorkPhone = employee.getEmployeeWorkPhone();
    	employeeRole = employee.getEmployeeRole();
    	
    }
    
    
    @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "pet_store_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private PetStoreEmployee petStoreEmployee;
}