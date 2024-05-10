package pet.store.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import pet.store.controller.model.PetStoreCustomer;
import pet.store.controller.model.PetStoreData;
import pet.store.controller.model.PetStoreEmployee;
import pet.store.service.PetStoreService;

@RestController
@Slf4j
public class PetStoreController {

	@Autowired
	private PetStoreService petStoreService;

	@PostMapping("/pet_store")
	@ResponseStatus(code = HttpStatus.CREATED)
	public PetStoreData createPetStore(@RequestBody PetStoreData petStoreData) {
		log.info("Creating Pet Store {}", petStoreData);
		 
		return petStoreService.savePetStore(petStoreData);
	}
	
	@PutMapping("/{petStoreId}")
	public PetStoreData updatePetStore(@PathVariable Long petStoreId, @RequestBody PetStoreData petStoreData) {
	
		petStoreData.setPetStoreId(petStoreId);
	log.info("Updating Pet Store {}", petStoreData);
	
	return petStoreService.savePetStore(petStoreData);
}


	@DeleteMapping("/pet_store")
	public void deleteAllPetStores( ) {
		log.info("Attempting to delete all stores");
		throw new UnsupportedOperationException("Deleting all pet stores is not allowed");
	}
	
	@DeleteMapping("/pet_store/{petStoreId}")
    public Map<String, String> deletePetStore(@PathVariable Long petStoreId ){
    
    	log.info("Deleting location with ID =" + petStoreId + ".");
    	petStoreService.deletePetStoreById(petStoreId); 
    	
    	return Map.of("message", "Store with ID=" + petStoreId + " was deleted successfully");
    }


    @Autowired
    public PetStoreController(PetStoreService petStoreService) {
        this.petStoreService = petStoreService;
    }

    @PostMapping("/pet_store/{petStoreId}/employee")
    @ResponseStatus(HttpStatus.CREATED)
    public PetStoreEmployee addEmployeeToPetStore(@RequestBody PetStoreEmployee employee, @PathVariable Long petStoreId) {
        log.info("Adding employee to pet store with ID {}: {}", petStoreId, employee);
        return petStoreService.saveEmployee(petStoreId, employee);
    }
    
//    
//    @RestController
//    @RequestMapping("/pet_store")
//    public class EmployeeController {
//
//        @Autowired
//        private PetStoreService petStoreService;
//
//        @PostMapping("/{petStoreId}/employee")
//        @ResponseStatus(HttpStatus.CREATED)
//        public PetStoreEmployee addEmployeeToStore(@PathVariable Long petStoreId, @RequestBody PetStoreEmployee employee) {
//           
//            System.out.println("Employee details: " + employee.toString());
//            
//     
//            return petStoreService.saveEmployee(petStoreId, employee);
//        }
//    }
    @GetMapping("/pet_store")
    public List<PetStoreData> retreiveAllPetStores()  {
    	
    	log.info("Retrieving all Pet Stores" );
    	
    	return petStoreService.retreiveAllPetStores();
    }
    @RestController
    @RequestMapping("/pet_store")
    public class CustomerController {

        @Autowired
        private PetStoreService petStoreService;

        @PostMapping("/{petStoreId}/customer")
        @ResponseStatus(HttpStatus.CREATED)
        public PetStoreCustomer addCustomerToStore(@PathVariable Long petStoreId, @RequestBody PetStoreCustomer customer) {
           
            System.out.println("Customer details: " + customer.toString());
            
            return petStoreService.saveCustomer(customer, petStoreId);
        }
    }
    
    
    @GetMapping("/pet_store/{petStoreId}/")
    public PetStoreData retrievePetStoreById(@PathVariable Long petStoreId) { 
    	log.info("Retrieving PetStore with ID = {}", petStoreId );
    
    
    return petStoreService.retrievePetStoreById(petStoreId);
    }
    
    
}
