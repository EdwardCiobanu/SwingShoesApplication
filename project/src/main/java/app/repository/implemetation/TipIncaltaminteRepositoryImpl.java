package app.repository.implemetation;

import app.configuration.HibernateConfiguration;
import app.model.Incaltaminte;
import app.model.TipIncaltaminte;
import app.model.Utilizator;
import app.repository.IncaltaminteRepository;
import app.repository.TipIncaltaminteRepository;
import app.repository.UtilizatorRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

public class TipIncaltaminteRepositoryImpl implements TipIncaltaminteRepository {
    @Override
    public TipIncaltaminte save(TipIncaltaminte entity) {
        SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Integer idOnTipIncaltaminteSaved = (Integer) session.save(entity);

        transaction.commit();
        session.close();

        return findById(idOnTipIncaltaminteSaved);
    }

    @Override
    public TipIncaltaminte update(TipIncaltaminte entity) {
        // TO DO
        // Same logic - extract it somehow
        SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Integer id = entity.getIdTipIncaltaminte();
        session.saveOrUpdate(entity);

        transaction.commit();
        session.close();

        return findById(id);
    }

    @Override
    public List<TipIncaltaminte> findAll() {
        SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        // Native SQL - not preferred
        // Query query = session.createSQLQuery("select * from Client");

        TypedQuery<TipIncaltaminte> query = session.getNamedQuery("findAllTipIncaltaminte");
        List<TipIncaltaminte> tipIncaltaminteList  = query.getResultList();

        transaction.commit();
        session.close();

        return tipIncaltaminteList;
    }

    @Override
    public TipIncaltaminte findById(Integer id) {
        SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        // HQL - Hibernate Query Language, but use named query instead to reuse them
        // Query query = session.createQuery("from Client where id=:id");
        // query.setParameter("id", id);

        TypedQuery<TipIncaltaminte> query = session.getNamedQuery("findTipIncaltaminteById");
        query.setParameter("id", id);

        TipIncaltaminte tipIncaltaminte;
        try {
            tipIncaltaminte = (TipIncaltaminte) query.getSingleResult();
        } catch (NoResultException e) {
            tipIncaltaminte = null;
        }

        transaction.commit();
        session.close();

        return tipIncaltaminte;
    }


    @Override
    public boolean delete(TipIncaltaminte entity) {
        SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Integer id = entity.getIdTipIncaltaminte();
        session.delete(entity);

        transaction.commit();
        session.close();

        return findById(id) == null;
    }

    public List<TipIncaltaminte> findAllIncaltaminteByDisponibilitate(Integer disponibilitate) {
        SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        // Used a Named Query - best solution
        TypedQuery<TipIncaltaminte> query = session.getNamedQuery("findAllTipIncaltaminteByDisponibilitate");
        query.setParameter("disponibilitate", disponibilitate);
        List<TipIncaltaminte> tipIncaltaminteList  = query.getResultList();


        transaction.commit();
        session.close();
        return tipIncaltaminteList;
    }

}
