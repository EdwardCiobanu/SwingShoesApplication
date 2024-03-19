package app.repository;


import app.model.Incaltaminte;
import app.model.TipIncaltaminte;

import java.util.List;

public interface TipIncaltaminteRepository extends CRUDRepository<TipIncaltaminte, Integer> {

    List<TipIncaltaminte> findAllIncaltaminteByDisponibilitate(Integer disponibilitate);
}
