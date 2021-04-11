package repository;

import entity.Administrator;
import org.hibernate.Session;

import javax.persistence.*;
import javax.persistence.criteria.*;

public class AdministratorRepo {

    private final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ro.tutorial.lab.SD");

    public void insertNewAdministrator(Administrator administrator) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(administrator);
        em.getTransaction().commit();
        em.close();
    }

    public void updateAdministrator(Administrator admin){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        Administrator admin2;
        admin2 = em.find(Administrator.class, admin.getId());
        admin2.setFirstName(admin.getFirstName());
        admin2.setLastName(admin.getLastName());
        admin2.setPassword(admin.getPassword());
        admin2.setUsername(admin.getUsername());

        em.unwrap(Session.class).update(admin2);
        em.flush();
        em.getTransaction().commit();
        em.close();
    }


    public Administrator findByUsernameAndPassword(String username, String password) throws IllegalArgumentException{
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin( );

        CriteriaBuilder cb=em.getCriteriaBuilder();
        CriteriaQuery<Administrator> cq=cb.createQuery(Administrator.class);
        Root<Administrator> admin=cq.from(Administrator.class);

        cq.where(cb.and(cb.equal(admin.get("username"), username),cb.equal(admin.get("password"), password)));
        return getAdministrator(em, cq, admin);
    }


    public Administrator findByUsername(String username) throws IllegalArgumentException{
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin( );

        CriteriaBuilder cb=em.getCriteriaBuilder();
        CriteriaQuery<Administrator> cq=cb.createQuery(Administrator.class);
        Root<Administrator> admin=cq.from(Administrator.class);

        cq.where(cb.equal(admin.get("username"), username));
        return getAdministrator(em, cq, admin);
    }

    private Administrator getAdministrator(EntityManager em, CriteriaQuery<Administrator> cq, Root<Administrator> admin) throws NoResultException {
        CriteriaQuery<Administrator> select = cq.select(admin);
        TypedQuery<Administrator> tq = em.createQuery(select);

        Administrator resultAdmin = tq.getSingleResult();

        em.getTransaction().commit();
        em.close();
        return resultAdmin;
    }
}
