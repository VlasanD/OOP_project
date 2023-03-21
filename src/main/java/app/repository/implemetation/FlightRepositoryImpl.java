package app.repository.implemetation;

import app.configuration.HibernateConfiguration;
import app.model.Flight;
import app.repository.FlightRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

public class FlightRepositoryImpl implements FlightRepository {
    @Override
    public Flight save(Flight entity) {
        SessionFactory sessionFactory= HibernateConfiguration.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Integer idOnFlightSaved=(Integer) session.save(entity);

        transaction.commit();
        session.close();

        return findById(idOnFlightSaved);
    }

    @Override
    public Flight update(Flight entity) {
        SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Integer id = entity.getFlightId();
        session.saveOrUpdate(entity);

        transaction.commit();
        session.close();

        return findById(id);
    }

    @Override
    public Flight findById(Integer id) {
        SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        TypedQuery<Flight> query = session.getNamedQuery("findFlightById");
        query.setParameter("id", id);

        Flight flight;
        try {
            flight = (Flight) query.getSingleResult();
        } catch (NoResultException e) {
            flight = null;
        }

        transaction.commit();
        session.close();

        return flight;
    }

    @Override
    public List<Flight> findAll() {
        SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        TypedQuery<Flight> query = session.getNamedQuery("findAllFlights");
        List<Flight> flights = query.getResultList();

        transaction.commit();
        session.close();

        return flights;
    }

    @Override
    public boolean delete(Flight entity) {
        SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Integer id = entity.getFlightId();
        session.delete(entity);

        transaction.commit();
        session.close();

        return findById(id) == null;
    }

    @Override
    public Flight findByDepartureAndDestination(String departure, String destination) {
        SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        TypedQuery<Flight> query = session.getNamedQuery("findFlightByDepartureAndDestination");
        query.setParameter("departure", departure);
        query.setParameter("destination", destination);

        Flight flight;
        try {
            flight = query.getSingleResult();
        } catch (NoResultException e) {
            flight = null;
        }

        transaction.commit();
        session.close();

        return flight;
    }
}
