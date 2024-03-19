package app.repository.implemetation;

import app.configuration.HibernateConfiguration;
import app.model.Incaltaminte;
import app.model.Utilizator;
import app.repository.IncaltaminteRepository;
import app.repository.UtilizatorRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

public class IncaltaminteRepositoryImpl implements IncaltaminteRepository {
    @Override
    public Incaltaminte save(Incaltaminte entity) {
        SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Integer idOnIncaltaminteSaved = (Integer) session.save(entity);

        transaction.commit();
        session.close();

        return findById(idOnIncaltaminteSaved);
    }

    @Override
    public Incaltaminte update(Incaltaminte entity) {
        // TO DO
        // Same logic - extract it somehow
        SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Integer id = entity.getIdIncaltaminte();
        session.saveOrUpdate(entity);

        transaction.commit();
        session.close();

        return findById(id);
    }

    @Override
    public List<Incaltaminte> findAll() {
        SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        // Native SQL - not preferred
        // Query query = session.createSQLQuery("select * from Client");

        TypedQuery<Incaltaminte> query = session.getNamedQuery("findAllIncaltaminte");
        List<Incaltaminte> incaltaminteList  = query.getResultList();

        transaction.commit();
        session.close();

        return incaltaminteList;
    }

    public List<Incaltaminte> findAllIncaltaminteByProducator(String producator) {
        SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        // Used a Named Query - best solution
        TypedQuery<Incaltaminte> query = session.getNamedQuery("findAllIncaltaminteByProducator");
        query.setParameter("producator", producator);
        List<Incaltaminte> incaltaminteList  = query.getResultList();


        transaction.commit();
        session.close();
        return incaltaminteList;
    }

    public List<Incaltaminte> findAllIncaltaminteByPret(Integer pret) {
        SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        // Used a Named Query - best solution
        TypedQuery<Incaltaminte> query = session.getNamedQuery("findAllIncaltaminteByPret");
        query.setParameter("pret", pret);
        List<Incaltaminte> incaltaminteList  = query.getResultList();


        transaction.commit();
        session.close();
        return incaltaminteList;
    }

    @Override
    public Incaltaminte findById(Integer id) {
        SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        // HQL - Hibernate Query Language, but use named query instead to reuse them
        // Query query = session.createQuery("from Client where id=:id");
        // query.setParameter("id", id);

        TypedQuery<Incaltaminte> query = session.getNamedQuery("findIncaltaminteById");
        query.setParameter("id", id);

        Incaltaminte incaltaminte;
        try {
            incaltaminte = (Incaltaminte) query.getSingleResult();
        } catch (NoResultException e) {
            incaltaminte = null;
        }

        transaction.commit();
        session.close();

        return incaltaminte;
    }


    @Override
    public boolean delete(Incaltaminte entity) {
        SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Integer id = entity.getIdIncaltaminte();
        session.delete(entity);

        transaction.commit();
        session.close();

        return findById(id) == null;
    }

    @Override
    public Incaltaminte findIncaltaminteByProducator(String producator) {
        SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        // Used a Named Query - best solution
        TypedQuery<Incaltaminte> query = session.getNamedQuery("findIncaltaminteByProducator");
        query.setParameter("producator", producator);
        Incaltaminte incaltaminte;
        try {
            incaltaminte = query.getSingleResult();
        } catch (NoResultException e) {
            incaltaminte = null;
        }


        transaction.commit();
        session.close();
        return incaltaminte;
    }

    @Override
    public Incaltaminte findIncaltaminteByNume(String nume) {
        SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        // Used a Named Query - best solution
        TypedQuery<Incaltaminte> query = session.getNamedQuery("findIncaltaminteByNume");
        query.setParameter("nume", nume);
        Incaltaminte incaltaminte;
        try {
            incaltaminte = query.getSingleResult();
        } catch (NoResultException e) {
            incaltaminte = null;
        }


        transaction.commit();
        session.close();
        return incaltaminte;
    }

}
