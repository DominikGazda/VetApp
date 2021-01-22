package com.vet.components.customer;

import com.vet.components.customer.exceptions.CustomerNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    private CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    public List<CustomerDto> getCustomers(){
        return customerRepository.findAll()
                .stream()
                .map(CustomerMapper::mapToDto)
                .collect(Collectors.toList());
    }

    public CustomerDto saveCustomer(CustomerDto dto){
        Customer customerToSave = CustomerMapper.mapToEntity(dto);
        Customer savedCustomer = customerRepository.save(customerToSave);
        return CustomerMapper.mapToDto(savedCustomer);
    }

    public CustomerDto getCustomerById(Long id){
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        Customer customer = optionalCustomer.orElseThrow(CustomerNotFoundException::new);
        System.out.println(customer.getAppointments());
        return CustomerMapper.mapToDto(customer);
    }

    public CustomerDto updateCustomer(Long id, CustomerDto dto){
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        Customer customerToUpdate = optionalCustomer.orElseThrow(CustomerNotFoundException::new);
        customerToUpdate.setID(dto.getID());
        customerToUpdate.setPIN(dto.getPIN());
        customerToUpdate.setFirstName(dto.getFirstName());
        customerToUpdate.setLastName(dto.getLastName());
        Customer updatedCustomer = customerRepository.save(customerToUpdate);
        return CustomerMapper.mapToDto(updatedCustomer);
    }

    public CustomerDto deleteCustomer(Long id){
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        Customer customerToDelete = optionalCustomer.orElseThrow(CustomerNotFoundException::new);
        customerRepository.delete(customerToDelete);
        return CustomerMapper.mapToDto(customerToDelete);
    }

    public void checkErrors(BindingResult result){
        List<ObjectError> errors = result.getAllErrors();
        String message = errors.stream()
                .map(ObjectError::getDefaultMessage)
                .map(String::toString)
                .collect(Collectors.joining());
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,message);
    }
}
