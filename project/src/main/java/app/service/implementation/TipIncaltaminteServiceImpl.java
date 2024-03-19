package app.service.implementation;

import app.model.Incaltaminte;
import app.model.TipIncaltaminte;
import app.model.Utilizator;
import app.repository.IncaltaminteRepository;
import app.repository.TipIncaltaminteRepository;
import app.repository.UtilizatorRepository;
import app.service.IncaltaminteService;
import app.service.TipIncaltaminteService;
import app.service.UtilizatorService;
import app.single_point_access.RepositorySinglePointAccess;

import java.util.List;

public class TipIncaltaminteServiceImpl implements TipIncaltaminteService {

    private TipIncaltaminteRepository tipIncaltaminteRepository = RepositorySinglePointAccess.getTipIncaltaminteRepository();

    @Override
    public TipIncaltaminte save(TipIncaltaminte tipIncaltaminte) {
        return tipIncaltaminteRepository.save(tipIncaltaminte);
    }

    @Override
    public TipIncaltaminte update(TipIncaltaminte tipIncaltaminte) {
        return tipIncaltaminteRepository.update(tipIncaltaminte);
    }

    @Override
    public List<TipIncaltaminte> findAll() {
        return tipIncaltaminteRepository.findAll();
    }

    @Override
    public TipIncaltaminte findById(Integer id) {
        return tipIncaltaminteRepository.findById(id);
    }


    @Override
    public boolean delete(TipIncaltaminte tipIncaltaminte) {
        return tipIncaltaminteRepository.delete(tipIncaltaminte);
    }

    @Override
    public List<TipIncaltaminte> findAllTipIncaltaminteByDisponibilitate(Integer disponibilitate) {
        return tipIncaltaminteRepository.findAllIncaltaminteByDisponibilitate(disponibilitate);
    }

    //    @Override
//    public void addCar(Client Client, Car car) {
//        if (Client.getCars() == null) {
//            Client.setCars(new ArrayList<>());
//        }
//
//        if (car.getId() == null || carRepository.findById(car.getId()) == null) {
//            car = carRepository.save(car);
//        }
//
//        Client.getCars().add(car);
//
//        ClientRepository.update(Client);
//
//    }
//
}
