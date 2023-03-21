package app.repository.implemetation;

import app.configuration.HibernateConfiguration;
import app.model.Booking;
import app.model.Flight;
import app.model.Passenger;
import app.repository.BookingRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

public class BookingRepositoryImpl implements BookingRepository {
    @Override
    public Booking findByPassenger(Passenger passenger) {
        SessionFactory sessionFactory= HibernateConfiguration.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        TypedQuery<Booking> query = session.getNamedQuery("findBookingByPassenger");
        query.setParameter("passenger", passenger);

        Booking booking;
        try {
            booking = query.getSingleResult();
        } catch (NoResultException e) {
            booking = null;
        }

        transaction.commit();
        session.close();

        return booking;
    }

    @Override
    public Booking save(Booking entity) {
        SessionFactory sessionFactory= HibernateConfiguration.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Integer idOnBookingSaved=(Integer) session.save(entity);

        transaction.commit();
        session.close();

        return findById(idOnBookingSaved);
    }

    @Override
    public Booking update(Booking entity) {
        SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Integer id = entity.getIdBooking();
        session.saveOrUpdate(entity);

        transaction.commit();
        session.close();

        return findById(id);
    }

    @Override
    public Booking findById(Integer id) {
        SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        TypedQuery<Booking> query = session.getNamedQuery("findBookingById");
        query.setParameter("id", id);

        Booking booking;
        try {
            booking = query.getSingleResult();
        } catch (NoResultException e) {
            booking = null;
        }

        transaction.commit();
        session.close();

        return booking;
    }

    @Override
    public List<Booking> findAll() {
        SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        TypedQuery<Booking> query = session.getNamedQuery("findAllBookings");
        List<Booking> bookings = query.getResultList();

        transaction.commit();
        session.close();

        return bookings;
    }

    @Override
    public boolean delete(Booking entity) {
        SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Integer id = entity.getIdBooking();
        session.delete(entity);

        transaction.commit();
        session.close();

        return findById(id) == null;
    }
}
