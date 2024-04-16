package pet.store.controller.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import pet.store.entity.Customer;
import pet.store.entity.Employee;
import pet.store.entity.PetStore;


@Data
@NoArgsConstructor
public class PetStoreData {
	
	private Long petStoreId;
    private String petStoreName;
    private String petStoreAddress;
    private String petStoreCity;
    private String petStoreState;
    private String petStoreZip;
    private String petStorePhoneNumber;
	    private Set<PetStoreCustomer> customers = new HashSet<>();
	    private Set<PetStoreEmployee> employees = new HashSet<>();
	
	    public PetStoreData(PetStore petStore) {
	    	petStoreId = petStore.getPetStoreId();
	    	petStoreName = petStore.getPetStoreName();
	    	petStoreAddress = petStore.getPetStoreAddress();
	    	petStoreCity = petStore.getPetStoreCity();
	    	petStoreState = petStore.getPetStoreState();
	    	petStoreZip = petStore.getPetStoreZip();
	    	petStorePhoneNumber = petStore.getPetStorePhoneNumber();

	    	
	    	for (Customer customer : petStore.getCustomers()) {
	    		PetStoreCustomer petStoreCustomer = new PetStoreCustomer(customer);
	    		customers.add(petStoreCustomer);
	    	}
	    	for (Employee employee : petStore.getEmployees()) {
	    		PetStoreEmployee petStoreEmployee = new PetStoreEmployee(employee);
	    		employees.add(petStoreEmployee);
	    	}
	    }
	    
	    @Data
	    @NoArgsConstructor
	    static class PetStoreEmployee { 
	    	private Long employeeId;
		    private String employeeFirstName;  
		    private String employeeLastName;  
		    private String employeeWorkPhoneNumber;
		    private String employeeRole;
		
	    
	    public PetStoreEmployee(Employee employee) {
	    	employeeId = employee.getEmployeeId();
	    	employeeFirstName = employee.getEmployeeFirstName();
	    	employeeLastName = employee.getEmployeeLastName();
	    	employeeWorkPhoneNumber = employee.getEmployeeWorkPhoneNumber() ;
	    	employeeRole = employee.getEmployeeRole();
	    			
	    			
	    
	    	
}
}
	    @Data
	    @NoArgsConstructor
	    static class PetStoreCustomer {
	    
	    private Long customerId;
	    private String customerFristName;
	    private String customerLastName;
	    private String customerEmail;
	    
	    
	  public PetStoreCustomer (Customer customer){
	    customerId = customer.getCustomerId();
	    		customerFristName = customer.getCustomerFristName();
	    		customerLastName = customer.getCustomerLastName();
	    		customerEmail = customer.getCustomerEmail();
	    	
	    }
	    }
		
}
