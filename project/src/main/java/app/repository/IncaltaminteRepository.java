package app.repository;


import app.model.Incaltaminte;

import java.util.List;

public interface IncaltaminteRepository extends CRUDRepository<Incaltaminte, Integer> {
    Incaltaminte findIncaltaminteByProducator(String producator);
    List<Incaltaminte> findAllIncaltaminteByProducator(String producator);
    Incaltaminte findIncaltaminteByNume(String nume);
    List<Incaltaminte> findAllIncaltaminteByPret(Integer pret);
}
