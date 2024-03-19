package app.repository.implemetation;

import app.configuration.HibernateConfiguration;
import app.model.Incaltaminte;
import app.model.Magazin;
import app.model.Utilizator;
import app.repository.IncaltaminteRepository;
import app.repository.MagazinRepository;
import app.repository.UtilizatorRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

public class MagazinRepositoryImpl implements MagazinRepository {
    @Override
    public Magazin save(Magazin entity) {
        SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Integer idOnMagazinSaved = (Integer) session.save(entity);

        transaction.commit();
        session.close();

        return findById(idOnMagazinSaved);
    }

    @Override
    public Magazin update(Magazin entity) {
        // TO DO
        // Same logic - extract it somehow
        SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Integer id = entity.getIdMagazin();
        session.saveOrUpdate(entity);

        transaction.commit();
        session.close();

        return findById(id);
    }

    @Override
    public List<Magazin> findAll() {
        SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        // Native SQL - not preferred
        // Query query = session.createSQLQuery("select * from Client");

        TypedQuery<Magazin> query = session.getNamedQuery("findAllMagazine");
        List<Magazin> magazinList  = query.getResultList();

        transaction.commit();
        session.close();

        return magazinList;
    }


    @Override
    public Magazin findById(Integer id) {
        SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        // HQL - Hibernate Query Language, but use named query instead to reuse them
        // Query query = session.createQuery("from Client where id=:id");
        // query.setParameter("id", id);

        TypedQuery<Magazin> query = session.getNamedQuery("findMagazinById");
        query.setParameter("id", id);

        Magazin magazin;
        try {
            magazin = (Magazin) query.getSingleResult();
        } catch (NoResultException e) {
            magazin = null;
        }

        transaction.commit();
        session.close();

        return magazin;
    }


    @Override
    public boolean delete(Magazin entity) {
        SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Integer id = entity.getIdMagazin();
        session.delete(entity);

        transaction.commit();
        session.close();

        return findById(id) == null;
    }

}
