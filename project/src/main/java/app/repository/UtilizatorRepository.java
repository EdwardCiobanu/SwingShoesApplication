package app.repository;


import app.model.Utilizator;

public interface UtilizatorRepository extends CRUDRepository<Utilizator, Integer> {
    Utilizator findUtilizatorByEmail(String name);

    Utilizator findUtilizatorByEmailAndPassword(String name, String password);
}
