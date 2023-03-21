package app.service.implementation;

import app.model.Passenger;
import app.repository.PassengerRepository;
import app.service.PassengerService;
import app.single_point_access.RepositorySinglePointAccess;

import java.util.List;

public class PassengerServiceImpl implements PassengerService {
    PassengerRepository passengerRepository= RepositorySinglePointAccess.getPassengerRepository();
    @Override
    public Passenger save(Passenger passenger) {
        return passengerRepository.save(passenger);
    }

    @Override
    public Passenger update(Passenger passenger) {
        return passengerRepository.update(passenger);
    }

    @Override
    public Passenger findById(Integer id) {
        return passengerRepository.findById(id);
    }

    @Override
    public List<Passenger> findAll() {
        return passengerRepository.findAll();
    }

    @Override
    public boolean delete(Passenger passenger) {
        return passengerRepository.delete(passenger);
    }

    @Override
    public Passenger findByName(String firstName, String lastName) {
        return passengerRepository.findByName(firstName, lastName);
    }
}
