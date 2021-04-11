package repository;

import entity.Product;
import org.hibernate.Session;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class ProductRepo {

    private final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ro.tutorial.lab.SD");

    public void insertNewProduct(Product product) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(product);
        em.getTransaction().commit();
        em.close();
    }

    public void removeProduct(String id) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Product product = em.find(Product.class, id);
        em.remove(product);
        em.getTransaction().commit();
        em.close();
    }

    public void updateProduct(Product product){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        Product prod2;
        prod2 = em.find(Product.class, product.getId());
        prod2.setCategory(product.getCategory());
        prod2.setDescription(product.getDescription());
        prod2.setFastingItem(product.isFastingItem());
        prod2.setName(product.getName());
        prod2.setPrice(product.getPrice());
        prod2.setQuantity(product.getQuantity());
        prod2.setWeight(product.getWeight());

        em.unwrap(Session.class).update(prod2);
        em.flush();
        em.getTransaction().commit();
        em.close();
    }

    public List<Product> findByCategory(String category)throws IllegalArgumentException{
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin( );

        CriteriaBuilder cb=em.getCriteriaBuilder();
        CriteriaQuery<Product> cq=cb.createQuery(Product.class);
        Root<Product> product=cq.from(Product.class);

        cq.where(cb.equal(product.get("category"), category));
        CriteriaQuery<Product> select = cq.select(product);
        TypedQuery<Product> tq = em.createQuery(select);
        List<Product> productList = tq.getResultList();

        em.getTransaction().commit();
        em.close();
        return productList;
    }

    public Product findByName(String name) throws IllegalArgumentException{
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin( );

        CriteriaBuilder cb=em.getCriteriaBuilder();
        CriteriaQuery<Product> cq=cb.createQuery(Product.class);
        Root<Product> product=cq.from(Product.class);

        cq.where(cb.equal(product.get("name"), name));
        CriteriaQuery<Product> select = cq.select(product);
        TypedQuery<Product> tq = em.createQuery(select);
        Product productResult = tq.getSingleResult();

        em.getTransaction().commit();
        em.close();
        return productResult;
    }

    public List<Product> findAllFoods()throws IllegalArgumentException{
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin( );

        CriteriaBuilder cb=em.getCriteriaBuilder();
        CriteriaQuery<Product> cq=cb.createQuery(Product.class);
        Root<Product> product=cq.from(Product.class);

        cq.where(cb.notEqual(product.get("category"), "Drink"));
        CriteriaQuery<Product> select = cq.select(product);
        TypedQuery<Product> tq = em.createQuery(select);
        List<Product> productList = tq.getResultList();

        em.getTransaction().commit();
        em.close();
        return productList;
    }


}
