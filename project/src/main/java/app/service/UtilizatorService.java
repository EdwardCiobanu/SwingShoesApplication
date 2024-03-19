package app.service;

import app.model.Utilizator;

import java.util.List;

public interface UtilizatorService {

    Utilizator save(Utilizator client);

    Utilizator update(Utilizator client);

    List<Utilizator> findAll();

    Utilizator findById(Integer id);

    Utilizator findUtilizatorByEmail(String name);

    boolean delete(Utilizator client);

    Utilizator login(String name, String password);
}
