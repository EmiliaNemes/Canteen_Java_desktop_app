package repository;

import entity.Customer;
import org.hibernate.Session;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class CustomerRepo {

    private final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ro.tutorial.lab.SD");

    public void insertNewCustomer(Customer customer) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(customer);
        em.getTransaction().commit();
        em.close();
    }

    public void updateCustomer(Customer customer){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        Customer cust2;
        cust2 = em.find(Customer.class, customer.getId());
        cust2.setFirstName(customer.getFirstName());
        cust2.setLastName(customer.getLastName());
        cust2.setPassword(customer.getPassword());
        cust2.setUsername(customer.getUsername());
        cust2.setEmailAddress(customer.getEmailAddress());
        cust2.setPhoneNumber(customer.getPhoneNumber());

        em.unwrap(Session.class).update(cust2);
        em.flush();
        em.getTransaction().commit();
        em.close();
    }

    public Customer findByUsernameAndPassword(String username, String password) throws IllegalArgumentException{
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin( );

        CriteriaBuilder cb=em.getCriteriaBuilder();
        CriteriaQuery<Customer> cq=cb.createQuery(Customer.class);
        Root<Customer> customer=cq.from(Customer.class);

        cq.where(cb.and(cb.equal(customer.get("username"), username),cb.equal(customer.get("password"), password)));
        return getCustomer(em, cq, customer);
    }

    private Customer getCustomer(EntityManager em, CriteriaQuery<Customer> cq, Root<Customer> customer) {
        CriteriaQuery<Customer> select = cq.select(customer);
        TypedQuery<Customer> tq = em.createQuery(select);
        Customer resultCustomer = tq.getSingleResult();

        em.getTransaction().commit();
        em.close();
        return resultCustomer;
    }


}
