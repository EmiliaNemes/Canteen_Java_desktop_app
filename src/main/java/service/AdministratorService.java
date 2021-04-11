package service;

import dto.AdministratorDto;
import entity.Administrator;
import exception.*;
import mapper.AdministratorMapper;
import repository.AdministratorRepo;
import utils.ApplicationUtils;

import javax.persistence.NoResultException;
import java.util.logging.Logger;

public class AdministratorService {

    private final static Logger log = Logger.getLogger(AdministratorService.class.getName());

    private final AdministratorRepo adminRepo;

    public AdministratorService(){
        this.adminRepo = new AdministratorRepo();
    }

    public AdministratorDto getAdminByUsernameAndPassword(String username, String password) throws NoResultException{
        Administrator admin = adminRepo.findByUsernameAndPassword(username, password);
        return AdministratorMapper.entityToDto(admin);
    }

    public void getAdminByUsername(String username) throws NoResultException {
        Administrator admin = adminRepo.findByUsername(username);
        AdministratorMapper.entityToDto(admin);
    }


    public void addAdmin(AdministratorDto adminDto) throws InvalidFirstNameException, InvalidLastNameException, InvalidEmailException, InvalidPhoneNumberException, InvalidUsernameException, InvalidPasswordException {
            if (!ApplicationUtils.isFirstNameValid(adminDto.getFirstName())){
                log.warning("Invalid first name");
                throw new InvalidFirstNameException(CustomExceptionMessages.INVALID_FIRST_NAME_MESSAGE);
            }

            if (!ApplicationUtils.isLastNameValid(adminDto.getLastName())) {
                log.warning("Invalid last name");
                throw new InvalidLastNameException(CustomExceptionMessages.INVALID_LAST_NAME_MESSAGE);
            }

            if (!ApplicationUtils.isEmailValid(adminDto.getEmailAddress())) {
                log.warning("Invalid email address");
                throw new InvalidEmailException(CustomExceptionMessages.INVALID_EMAIL_MESSAGE);
            }

            if (!ApplicationUtils.isPhoneNumberValid(adminDto.getPhoneNumber())) {
                log.warning("Invalid phone number");
                throw new InvalidPhoneNumberException(CustomExceptionMessages.INVALID_PHONE_MESSAGE);
            }

            if (!ApplicationUtils.isAdminUsernameValid(adminDto.getUsername())) {
                log.warning("Invalid username");
                throw new InvalidUsernameException(CustomExceptionMessages.INVALID_USERNAME_MESSAGE);
            }

            if (!ApplicationUtils.isPasswordValid(adminDto.getPassword())) {
                log.warning("Invalid password");
                throw new InvalidPasswordException(CustomExceptionMessages.INVALID_PASSWORD_MESSAGE);
            }

            Administrator admin = AdministratorMapper.dtoToEntity(adminDto);
            admin.setId(ApplicationUtils.generateUUID());
            adminRepo.insertNewAdministrator(admin);
            log.info("New administrator successfully added");
    }

    public void updateAdministrator (AdministratorDto adminDto) throws InvalidFirstNameException, InvalidLastNameException, InvalidEmailException, InvalidPhoneNumberException, InvalidPasswordException {
        if (!ApplicationUtils.isFirstNameValid(adminDto.getFirstName())){
            log.warning("Invalid first name");
            throw new InvalidFirstNameException(CustomExceptionMessages.INVALID_FIRST_NAME_MESSAGE);
        }

        if (!ApplicationUtils.isLastNameValid(adminDto.getLastName())) {
            log.warning("Invalid last name");
            throw new InvalidLastNameException(CustomExceptionMessages.INVALID_LAST_NAME_MESSAGE);
        }

        if (!ApplicationUtils.isEmailValid(adminDto.getEmailAddress())) {
            log.warning("Invalid email address");
            throw new InvalidEmailException(CustomExceptionMessages.INVALID_EMAIL_MESSAGE);
        }

        if (!ApplicationUtils.isPhoneNumberValid(adminDto.getPhoneNumber())) {
            log.warning("Invalid phone number");
            throw new InvalidPhoneNumberException(CustomExceptionMessages.INVALID_PHONE_MESSAGE);
        }

        if (!ApplicationUtils.isPasswordValid(adminDto.getPassword())) {
            log.warning("Invalid password");
            throw new InvalidPasswordException(CustomExceptionMessages.INVALID_PASSWORD_MESSAGE);
        }

        adminRepo.updateAdministrator(AdministratorMapper.dtoToEntity(adminDto));
        log.info("Administrator successfully updated");
    }

}
