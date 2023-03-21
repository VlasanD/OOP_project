package app.service;

import app.model.Baggage;
import app.model.Employee;

import java.util.List;

public interface BaggageService {

    Baggage save(Baggage baggage);

    Baggage update(Baggage baggage);

    Baggage findById(Integer id);

    List<Baggage> findAll();

    boolean delete(Baggage baggage);
}
