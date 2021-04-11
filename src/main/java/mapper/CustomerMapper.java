package mapper;

import dto.CustomerDto;
import entity.Customer;

public class CustomerMapper {

    public static CustomerDto entityToDto(Customer customer){
        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(customer.getId());
        customerDto.setFirstName(customer.getFirstName());
        customerDto.setLastName(customer.getLastName());
        customerDto.setUsername(customer.getUsername());
        customerDto.setPassword(customer.getPassword());
        customerDto.setEmailAddress(customer.getEmailAddress());
        customerDto.setPhoneNumber(customer.getPhoneNumber());
        return customerDto;
    }

    public static Customer dtoToEntity(CustomerDto customerDto){
        Customer customer = new Customer();
        customer.setId(customerDto.getId());
        customer.setFirstName(customerDto.getFirstName());
        customer.setLastName(customerDto.getLastName());
        customer.setUsername(customerDto.getUsername());
        customer.setPassword(customerDto.getPassword());
        customer.setEmailAddress(customerDto.getEmailAddress());
        customer.setPhoneNumber(customerDto.getPhoneNumber());
        return customer;
    }
}
