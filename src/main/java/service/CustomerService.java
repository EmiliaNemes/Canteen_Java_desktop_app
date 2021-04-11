package service;

import dto.CustomerDto;
import entity.Customer;
import exception.*;
import mapper.CustomerMapper;
import repository.CustomerRepo;
import utils.ApplicationUtils;

import javax.persistence.NoResultException;
import java.util.logging.Logger;

public class CustomerService {

    private final static Logger log = Logger.getLogger(CustomerService.class.getName());

    private final CustomerRepo customerRepo;

    public CustomerService(){
        this.customerRepo= new CustomerRepo();
    }

    public void addCustomer(CustomerDto customerDto) throws InvalidFirstNameException, InvalidLastNameException, InvalidEmailException, InvalidPhoneNumberException, InvalidUsernameException, InvalidPasswordException {
        if (!ApplicationUtils.isFirstNameValid(customerDto.getFirstName())){
            log.warning("Invalid first name");
            throw new InvalidFirstNameException(CustomExceptionMessages.INVALID_FIRST_NAME_MESSAGE);
        }

        if (!ApplicationUtils.isLastNameValid(customerDto.getLastName())) {
            log.warning("Invalid last name");
            throw new InvalidLastNameException(CustomExceptionMessages.INVALID_LAST_NAME_MESSAGE);
        }

        if (!ApplicationUtils.isEmailValid(customerDto.getEmailAddress())) {
            log.warning("Invalid email address");
            throw new InvalidEmailException(CustomExceptionMessages.INVALID_EMAIL_MESSAGE);
        }

        if (!ApplicationUtils.isPhoneNumberValid(customerDto.getPhoneNumber())) {
            log.warning("Invalid phone number");
            throw new InvalidPhoneNumberException(CustomExceptionMessages.INVALID_PHONE_MESSAGE);
        }

        if (!ApplicationUtils.isAdminUsernameValid(customerDto.getUsername())) {
            log.warning("Invalid username");
            throw new InvalidUsernameException(CustomExceptionMessages.INVALID_USERNAME_MESSAGE);
        }

        if (!ApplicationUtils.isPasswordValid(customerDto.getPassword())) {
            log.warning("Invalid password");
            throw new InvalidPasswordException(CustomExceptionMessages.INVALID_PASSWORD_MESSAGE);
        }

        Customer customer= CustomerMapper.dtoToEntity(customerDto);
        customer.setId(ApplicationUtils.generateUUID());

        customerRepo.insertNewCustomer(customer);
        log.info("New customer successfully added");
    }

    public CustomerDto getCustomerByUsernameAndPassword(String username, String password) throws NoResultException{
        Customer customer = customerRepo.findByUsernameAndPassword(username, password);
        return CustomerMapper.entityToDto(customer);
    }

    public void updateCustomer (CustomerDto customerDto) throws InvalidFirstNameException, InvalidLastNameException, InvalidEmailException, InvalidPhoneNumberException, InvalidPasswordException {
        if (!ApplicationUtils.isFirstNameValid(customerDto.getFirstName())){
            log.warning("Invalid first name");
            throw new InvalidFirstNameException(CustomExceptionMessages.INVALID_FIRST_NAME_MESSAGE);
        }

        if (!ApplicationUtils.isLastNameValid(customerDto.getLastName())) {
            log.warning("Invalid last name");
            throw new InvalidLastNameException(CustomExceptionMessages.INVALID_LAST_NAME_MESSAGE);
        }

        if (!ApplicationUtils.isEmailValid(customerDto.getEmailAddress())) {
            log.warning("Invalid email address");
            throw new InvalidEmailException(CustomExceptionMessages.INVALID_EMAIL_MESSAGE);
        }

        if (!ApplicationUtils.isPhoneNumberValid(customerDto.getPhoneNumber())) {
            log.warning("Invalid phone number");
            throw new InvalidPhoneNumberException(CustomExceptionMessages.INVALID_PHONE_MESSAGE);
        }

        if (!ApplicationUtils.isPasswordValid(customerDto.getPassword())) {
            log.warning("Invalid password");
            throw new InvalidPasswordException(CustomExceptionMessages.INVALID_PASSWORD_MESSAGE);
        }

        customerRepo.updateCustomer(CustomerMapper.dtoToEntity(customerDto));
        log.info("Customer successfully updated");
    }

}
