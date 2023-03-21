package app.repository.implemetation;

import app.configuration.HibernateConfiguration;
import app.model.Baggage;
import app.repository.BaggageRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

public class BaggageRepositoryImpl implements BaggageRepository {

    @Override
    public Baggage save(Baggage entity) {
        SessionFactory sessionFactory= HibernateConfiguration.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Integer idOnBaggageSaved=(Integer) session.save(entity);

        transaction.commit();
        session.close();

        return findById(idOnBaggageSaved);
    }

    @Override
    public Baggage update(Baggage entity) {
        SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Integer id = entity.getIdBaggage();
        session.saveOrUpdate(entity);

        transaction.commit();
        session.close();

        return findById(id);
    }

    @Override
    public Baggage findById(Integer id) {
        SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        TypedQuery<Baggage> query = session.getNamedQuery("findBaggageById");
        query.setParameter("id", id);

        Baggage baggage;
        try {
            baggage = query.getSingleResult();
        } catch (NoResultException e) {
            baggage = null;
        }

        transaction.commit();
        session.close();

        return baggage;
    }

    @Override
    public List<Baggage> findAll() {
        SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        TypedQuery<Baggage> query = session.getNamedQuery("findAllBaggages");
        List<Baggage> baggages = query.getResultList();

        transaction.commit();
        session.close();

        return baggages;
    }

    @Override
    public boolean delete(Baggage entity) {
        SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Integer id = entity.getIdBaggage();
        session.delete(entity);

        transaction.commit();
        session.close();

        return findById(id) == null;
    }
}
