package app.repository.implemetation;

import app.configuration.HibernateConfiguration;
import app.model.Passenger;
import app.repository.PassengerRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

public class PassengerRepositoryImpl implements PassengerRepository {
    @Override
    public Passenger save(Passenger entity) {
        SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Integer idOnPassengerSaved = (Integer) session.save(entity);

        transaction.commit();
        session.close();

        return findById(idOnPassengerSaved);
    }

    @Override
    public Passenger update(Passenger entity) {
        SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Integer id = entity.getIdPassenger();
        session.saveOrUpdate(entity);

        transaction.commit();
        session.close();

        return findById(id);
    }

    @Override
    public Passenger findById(Integer id) {
        SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        TypedQuery<Passenger> query = session.getNamedQuery("findPassengerById");
        query.setParameter("id", id);

        Passenger passenger;
        try {
            passenger = (Passenger) query.getSingleResult();
        } catch (NoResultException e) {
            passenger = null;
        }

        transaction.commit();
        session.close();

        return passenger;
    }

    @Override
    public List<Passenger> findAll() {
        SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        TypedQuery<Passenger> query = session.getNamedQuery("findAllPassengers");
        List<Passenger> persons = query.getResultList();

        transaction.commit();
        session.close();

        return persons;
    }

    @Override
    public boolean delete(Passenger entity) {
        SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Integer id = entity.getIdPassenger();
        session.delete(entity);

        transaction.commit();
        session.close();

        return findById(id) == null;
    }

    @Override
    public Passenger findByName(String firstName, String lastName) {
        SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        TypedQuery<Passenger> query = session.getNamedQuery("findPassengerByName");
        query.setParameter("lastName", lastName);
        query.setParameter("firstName", firstName);

        Passenger passenger;
        try {
            passenger = query.getSingleResult();
        } catch (NoResultException e) {
            passenger = null;
        }

        transaction.commit();
        session.close();

        return passenger;
    }
}
