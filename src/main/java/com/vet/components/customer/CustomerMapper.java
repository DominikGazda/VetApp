package com.vet.components.customer;

public class CustomerMapper {

    public static CustomerDto mapToDto(Customer entity){
        CustomerDto customerDto = new CustomerDto();
        customerDto.setIdCustomer(entity.getIdCustomer());
        customerDto.setID(entity.getID());
        customerDto.setPIN(entity.getPIN());
        customerDto.setFirstName(entity.getFirstName());
        customerDto.setLastName(entity.getLastName());
        return customerDto;
    }

    public static Customer mapToEntity(CustomerDto dto){
        Customer customer = new Customer();
        customer.setIdCustomer(dto.getIdCustomer());
        customer.setID(dto.getID());
        customer.setPIN(dto.getPIN());
        customer.setFirstName(dto.getFirstName());
        customer.setLastName(dto.getLastName());
        return customer;
    }
}
