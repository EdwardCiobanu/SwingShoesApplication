package app.repository.implemetation;

import app.configuration.HibernateConfiguration;
import app.model.Utilizator;
import app.repository.UtilizatorRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

public class UtilizatorRepositoryImpl implements UtilizatorRepository {
    @Override
    public Utilizator save(Utilizator entity) {
        SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Integer idOnClientSaved = (Integer) session.save(entity);

        transaction.commit();
        session.close();

        return findById(idOnClientSaved);
    }

    @Override
    public Utilizator update(Utilizator entity) {
        // TO DO
        // Same logic - extract it somehow
        SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Integer id = entity.getIdUtilizator();
        session.saveOrUpdate(entity);

        transaction.commit();
        session.close();

        return findById(id);
    }

    @Override
    public List<Utilizator> findAll() {
        SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        // Native SQL - not preferred
        // Query query = session.createSQLQuery("select * from Client");

        TypedQuery<Utilizator> query = session.getNamedQuery("findAllUtilizatori");
        List<Utilizator> Clients = query.getResultList();

        transaction.commit();
        session.close();

        return Clients;
    }

    @Override
    public Utilizator findById(Integer id) {
        SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        // HQL - Hibernate Query Language, but use named query instead to reuse them
        // Query query = session.createQuery("from Client where id=:id");
        // query.setParameter("id", id);

        TypedQuery<Utilizator> query = session.getNamedQuery("findUtilizatorById");
        query.setParameter("id", id);

        Utilizator utilizator;
        try {
            utilizator = (Utilizator) query.getSingleResult();
        } catch (NoResultException e) {
            utilizator = null;
        }

        transaction.commit();
        session.close();

        return utilizator;
    }


    @Override
    public boolean delete(Utilizator entity) {
        SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Integer id = entity.getIdUtilizator();
        session.delete(entity);

        transaction.commit();
        session.close();

        return findById(id) == null;
    }

    @Override
    public Utilizator findUtilizatorByEmail(String email) {
        SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        // Used a Named Query - best solution
        TypedQuery<Utilizator> query = session.getNamedQuery("findUtilizatorByEmail");
        query.setParameter("email", email);
        Utilizator utilizator;
        try {
            utilizator = query.getSingleResult();
        } catch (NoResultException e) {
            utilizator = null;
        }


        transaction.commit();
        session.close();
        return utilizator;
    }

    @Override
    public Utilizator findUtilizatorByEmailAndPassword(String email, String password) {
        SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        // TO DO
        // Same logic - extract it somehow
        TypedQuery<Utilizator> query = session.getNamedQuery("findUtilizatorByEmailAndPassword");
        query.setParameter("email", email);
        query.setParameter("password", password);

        Utilizator utilizator;
        try {
            utilizator = query.getSingleResult();
        } catch (NoResultException e) {
            utilizator = null;
        }

        transaction.commit();
        session.close();

        return utilizator;
    }

}
