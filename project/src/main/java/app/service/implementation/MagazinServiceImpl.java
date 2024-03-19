package app.service.implementation;

import app.model.Incaltaminte;
import app.model.Magazin;
import app.model.TipIncaltaminte;
import app.model.Utilizator;
import app.repository.IncaltaminteRepository;
import app.repository.MagazinRepository;
import app.repository.TipIncaltaminteRepository;
import app.repository.UtilizatorRepository;
import app.service.IncaltaminteService;
import app.service.MagazinService;
import app.service.TipIncaltaminteService;
import app.service.UtilizatorService;
import app.single_point_access.RepositorySinglePointAccess;

import java.util.List;

public class MagazinServiceImpl implements MagazinService {

    private MagazinRepository magazinRepository = RepositorySinglePointAccess.getMagazinRepository();

    @Override
    public Magazin save(Magazin magazin) {
        return magazinRepository.save(magazin);
    }

    @Override
    public Magazin update(Magazin magazin) {
        return magazinRepository.update(magazin);
    }

    @Override
    public List<Magazin> findAll() {
        return magazinRepository.findAll();
    }

    @Override
    public Magazin findById(Integer id) {
        return magazinRepository.findById(id);
    }


    @Override
    public boolean delete(Magazin magazin) {
        return magazinRepository.delete(magazin);
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
