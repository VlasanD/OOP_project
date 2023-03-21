package app.service.implementation;

import app.model.Baggage;
import app.repository.BaggageRepository;
import app.service.BaggageService;
import app.single_point_access.RepositorySinglePointAccess;

import java.util.List;

public class BaggageServiceImpl implements BaggageService {
    private final BaggageRepository baggageRepository= RepositorySinglePointAccess.getBaggageRepository();
    @Override
    public Baggage save(Baggage baggage) {
        return baggageRepository.save(baggage);
    }

    @Override
    public Baggage update(Baggage baggage) {
        return baggageRepository.update(baggage);
    }

    @Override
    public Baggage findById(Integer id) {
        return baggageRepository.findById(id);
    }

    @Override
    public List<Baggage> findAll() {
        return baggageRepository.findAll();
    }

    @Override
    public boolean delete(Baggage baggage) {
        return baggageRepository.delete(baggage);
    }
}
