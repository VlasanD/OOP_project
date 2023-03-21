package app.repository.implemetation;

import app.configuration.HibernateConfiguration;
import app.model.Employee;
import app.repository.EmployeeRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

public class EmployeeRepositoryImpl implements EmployeeRepository {

    @Override
    public Employee save(Employee entity) {
        SessionFactory sessionFactory= HibernateConfiguration.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Integer idOnEmployeeSaved=(Integer) session.save(entity);

        transaction.commit();
        session.close();

        return findById(idOnEmployeeSaved);
    }

    @Override
    public Employee update(Employee entity) {
        SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Integer id = entity.getIdEmployee();
        session.saveOrUpdate(entity);

        transaction.commit();
        session.close();

        return findById(id);
    }

    @Override
    public Employee findById(Integer id) {
        SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        TypedQuery<Employee> query = session.getNamedQuery("findEmployeeById");
        query.setParameter("id", id);

        Employee employee;
        try {
            employee = (Employee) query.getSingleResult();
        } catch (NoResultException e) {
            employee = null;
        }

        transaction.commit();
        session.close();

        return employee;
    }

    @Override
    public List<Employee> findAll() {
        SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        TypedQuery<Employee> query = session.getNamedQuery("findAllEmployees");
        List<Employee> employees = query.getResultList();

        transaction.commit();
        session.close();

        return employees;
    }

    @Override
    public boolean delete(Employee entity) {
        SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Integer id = entity.getIdEmployee();
        session.delete(entity);

        transaction.commit();
        session.close();

        return findById(id) == null;
    }

    @Override
    public Employee findByName(String firstName, String lastName) {
        SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        TypedQuery<Employee> query = session.getNamedQuery("findEmployeeByName");
        query.setParameter("firstName", firstName);
        query.setParameter("lastName", lastName);

        Employee employee;
        try {
            employee = query.getSingleResult();
        } catch (NoResultException e) {
            employee = null;
        }

        transaction.commit();
        session.close();

        return employee;
    }

    @Override
    public Employee findByUsernameAndPassword(String username, String password) {
        SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        TypedQuery<Employee> query = session.getNamedQuery("findEmployeeByUsernameAndPassword");
        query.setParameter("username", username);
        query.setParameter("password", password);

        Employee employee;
        try {
            employee = query.getSingleResult();
        } catch (NoResultException e) {
            employee = null;
        }

        transaction.commit();
        session.close();

        return employee;
    }
}
