package app.service.implementation;

import app.model.Incaltaminte;
import app.model.Utilizator;
import app.repository.IncaltaminteRepository;
import app.repository.UtilizatorRepository;
import app.service.IncaltaminteService;
import app.service.UtilizatorService;
import app.single_point_access.RepositorySinglePointAccess;

import java.util.List;

public class IncaltaminteServiceImpl implements IncaltaminteService {

    private IncaltaminteRepository incaltaminteRepository = RepositorySinglePointAccess.getIncaltaminteRepository();

    @Override
    public Incaltaminte save(Incaltaminte incaltaminte) {
        return incaltaminteRepository.save(incaltaminte);
    }

    @Override
    public Incaltaminte update(Incaltaminte incaltaminte) {
        return incaltaminteRepository.update(incaltaminte);
    }

    @Override
    public List<Incaltaminte> findAll() {
        return incaltaminteRepository.findAll();
    }

    @Override
    public List<Incaltaminte> findAllIncaltaminteByProducator(String producator) {
        return incaltaminteRepository.findAllIncaltaminteByProducator(producator);
    }
    @Override
    public List<Incaltaminte> findAllIncaltaminteByPret(Integer pret) {
        return incaltaminteRepository.findAllIncaltaminteByPret(pret);
    }

    @Override
    public Incaltaminte findById(Integer id) {
        return incaltaminteRepository.findById(id);
    }

    @Override
    public Incaltaminte findIncaltaminteByProducator(String producator)
    {
        return incaltaminteRepository.findIncaltaminteByProducator(producator);
    }

    @Override
    public Incaltaminte findIncaltaminteByNume(String nume)
    {
        return incaltaminteRepository.findIncaltaminteByNume(nume);
    }

    @Override
    public boolean delete(Incaltaminte incaltaminte) {
        return incaltaminteRepository.delete(incaltaminte);
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
