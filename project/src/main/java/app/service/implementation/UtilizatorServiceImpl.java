package app.service.implementation;

import app.model.Utilizator;
import app.repository.UtilizatorRepository;
import app.service.UtilizatorService;
import app.single_point_access.RepositorySinglePointAccess;

import java.util.List;

public class UtilizatorServiceImpl implements UtilizatorService {

    private UtilizatorRepository utilizatorRepository = RepositorySinglePointAccess.getUtilizatorRepository();

    @Override
    public Utilizator save(Utilizator client) {
        return utilizatorRepository.save(client);
    }

    @Override
    public Utilizator update(Utilizator client) {
        return utilizatorRepository.update(client);
    }

    @Override
    public List<Utilizator> findAll() {
        return utilizatorRepository.findAll();
    }

    @Override
    public Utilizator findById(Integer id) {
        return utilizatorRepository.findById(id);
    }

    @Override
    public Utilizator findUtilizatorByEmail(String name)
    {
        return utilizatorRepository.findUtilizatorByEmail(name);
    }

    @Override
    public boolean delete(Utilizator utilizator) {
        return utilizatorRepository.delete(utilizator);
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
    @Override
    public Utilizator login(String email, String password) {
        return utilizatorRepository.findUtilizatorByEmailAndPassword(email, password);
    }
}
