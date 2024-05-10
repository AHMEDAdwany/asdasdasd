package pet.store.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pet.store.controller.model.PetStoreCustomer;
import pet.store.controller.model.PetStoreData;
import pet.store.controller.model.PetStoreEmployee;
import pet.store.dao.CustomerDao;
import pet.store.dao.EmployeeDao;
import pet.store.dao.PetStoreDao;
import pet.store.entity.Customer;
import pet.store.entity.Employee;
import pet.store.entity.PetStore;

@Service


public class PetStoreService {
	
	@Autowired	
	private PetStoreDao petStoreDao;
	
	@Autowired 
	private EmployeeDao employeeDao;
	
	@Autowired 
	private CustomerDao customerDao;
	
	
	//@Autowired
  //  private PetStoreService petStoreService;
	
	//private PetStoreData petStoreData;
      
	@Transactional(readOnly = false)
    public PetStoreData savePetStore(PetStoreData petStoreData) {
        PetStore petStore = findOrCreatePetStore(petStoreData.getPetStoreId());

        copyPetStoreFields(petStore, petStoreData);
        
        //PetStore savedPetStore = petStoreDao.save(petStore);
        
        return new PetStoreData(petStoreDao.save(petStore));
    }
	
	  private void copyPetStoreFields(PetStore petStore, PetStoreData petStoreData) {
		  petStore.setPetStoreName(petStoreData.getPetStoreName());
		  petStore.setPetStoreAddress(petStoreData.getPetStoreAddress());
	      petStore.setPetStoreCity(petStoreData.getPetStoreCity());
	      petStore.setPetStoreId(petStoreData.getPetStoreId());
	      petStore.setPetStoreZip(petStoreData.getPetStoreZip());
	      petStore.setPetStorePhoneNumber(petStoreData.getPetStorePhoneNumber());
	      
	      
	    }

    private PetStore findOrCreatePetStore(Long petStoreId) {
        if (Objects.isNull(petStoreId)) {
            return new PetStore(); 
        } else {
        	return findPetStoreById(petStoreId);
           
        }
    }

    
    	@Transactional (readOnly = false)
		public PetStoreData retrievePetStoreById(Long petStoreId) {
			//findPetStoreById(petStoreId);
			PetStore PetStore = findPetStoreById(petStoreId);
			return new PetStoreData(PetStore);

		}
    
        private PetStore findPetStoreById(Long petStoreId) {
 
    	return petStoreDao.findById(petStoreId)
    	.orElseThrow(() -> new NoSuchElementException("PetStore with ID = "+ petStoreId + " was not found. "));
    	
    }
        
        
    @Transactional(readOnly = false)
	public PetStoreEmployee saveEmployee(Long petStoreId, PetStoreEmployee petStoreEmployee) {
		
		PetStore petStore = findPetStoreById(petStoreId);
		Long employeeId = petStoreEmployee.getEmployeeId();
		Employee employee = findOrCreateEmployee(petStoreId, employeeId);
		
		copyEmployeeFields(employee, petStoreEmployee);
		employee.setPetStore(petStore);
		petStore.getEmployees().add(employee);
		
		return new PetStoreEmployee(employeeDao.save(employee));
	}
	
    public Employee findOrCreateEmployee(Long petStoreId, Long employeeId) {
        if (employeeId == null) {
            return new Employee();
        } else {
            return findEmployeeById(petStoreId, employeeId);
        }
    }
    
        @Transactional(readOnly = false)
        public Employee findEmployeeById(Long petStoreId, Long employeeId) {
            Employee employee = employeeDao.findById(employeeId).orElseThrow(() -> new NoSuchElementException("Employee not found"));
            if (!employee.getPetStore().getPetStoreId().equals(petStoreId)) {
                throw new IllegalArgumentException("Employee does not belong to the given pet store");
            }
            return employee;
        }
        
        public void copyEmployeeFields(Employee employee, PetStoreEmployee petStoreEmployee) {
            employee.setEmployeeFirstName(petStoreEmployee.getEmployeeFirstName());
            employee.setEmployeeLastName(petStoreEmployee.getEmployeeLastName());
            employee.setEmployeeWorkPhone(petStoreEmployee.getEmployeeWorkPhone());
            employee.setEmployeeRole(petStoreEmployee.getEmployeeRole());
            
        }
        
       @Transactional (readOnly = false)
        public List<PetStoreData> retreiveAllPetStores(){
        	
    	   return petStoreDao.findAll()
    	   .stream()
    	   .map(PetStoreData::new)
    	   .toList();
    	   
    	   
    	   
    	   
    	   
    	   
    
      }


		@Transactional
		public void deletePetStoreById(Long petStoreId) {
			PetStore petStore = findPetStoreById(petStoreId);
			petStoreDao.delete(petStore);
		
				
	}
	
		@Transactional(readOnly = false)
		public PetStoreCustomer saveCustomer(PetStoreCustomer petStoreCustomer, Long petStoreId) {
		    PetStore petStore = findPetStoreById(petStoreId);
		    Long customerId = petStoreCustomer.getCustomerId();
		    Customer customer = findOrCreateCustomer(customerId);
		    
		    copyCustomerFields(customer, petStoreCustomer);
		    customer.getPetStores().add(petStore); 
		    petStore.getCustomers().add(customer);
		    
		    return new PetStoreCustomer(customerDao.save(customer));
		}

		public Customer findOrCreateCustomer(Long customerId) {
		    if (customerId == null) {
		        return new Customer();
		    } else {
		        return findCustomerById(customerId);
		    }
		}

		@Transactional(readOnly = false)
		public Customer findCustomerById(Long customerId) {
		    return customerDao.findById(customerId)
		            .orElseThrow(() -> new NoSuchElementException("Customer with ID = " + customerId + " not found"));
		}

		public void copyCustomerFields(Customer customer, PetStoreCustomer petStoreCustomer) {
		    customer.setCustomerFirstName(petStoreCustomer.getCustomerFirstName());
		    customer.setCustomerLastName(petStoreCustomer.getCustomerLastName());
		    customer.setCustomerEmail(petStoreCustomer.getCustomerEmail());
		
		}
	    
	    
}
		
	
