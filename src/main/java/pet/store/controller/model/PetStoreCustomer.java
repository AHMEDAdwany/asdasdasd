package pet.store.controller.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import pet.store.entity.Customer;
import pet.store.entity.PetStore;

@Data 
@NoArgsConstructor

public class PetStoreCustomer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;
    
    private String customerFirstName;
    
    private String customerLastName;
    
    private String customerEmail;
    
    public PetStoreCustomer(Customer customer) {
    	customerFirstName = customer.getCustomerFirstName();
    	customerLastName = customer.getCustomerLastName();
    	customerEmail = customer.getCustomerEmail();
    }
    
    @ManyToMany(mappedBy = "customers", cascade = CascadeType.PERSIST)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<PetStoreCustomer> petStores = new HashSet<>();
}
	
	


