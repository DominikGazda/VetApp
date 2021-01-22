package com.vet.components.customer;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    private CustomerService customerService;

    public CustomerController(CustomerService customerService){
        this.customerService = customerService;
    }

    @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CustomerDto> getCustomers(){
        return customerService.getCustomers();
    }

    @PostMapping(path = "",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomerDto> saveCustomer(@Valid @RequestBody CustomerDto dto, BindingResult result){
        if(result.hasErrors())
            customerService.checkErrors(result);
        if(dto.getIdCustomer() != null)
            throw new ResponseStatusException(HttpStatus.CONFLICT,"Customer cannot have id");
        CustomerDto savedCustomer = customerService.saveCustomer(dto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedCustomer.getIdCustomer())
                .toUri();
        return ResponseEntity.created(location).body(savedCustomer);
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CustomerDto getCustomerById(@PathVariable Long id){
        return customerService.getCustomerById(id);
    }

    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomerDto> updateCustomer(@PathVariable Long id,@Valid @RequestBody CustomerDto dto, BindingResult result){
        if(result.hasErrors())
            customerService.checkErrors(result);
        if(!id.equals(dto.getIdCustomer()))
            throw new ResponseStatusException(HttpStatus.CONFLICT,"Customer must have id same as path variable");
        CustomerDto updatedCustomer = customerService.updateCustomer(id, dto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .build()
                .toUri();
        return ResponseEntity.created(location).body(updatedCustomer);
    }

    @DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CustomerDto deleteCustomer(@PathVariable Long id){
        return customerService.deleteCustomer(id);
    }
}
