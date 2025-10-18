package py.una.pol.sd.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import py.una.pol.sd.model.Juego;

@Repository
public interface JuegoRepository extends CrudRepository<Juego, Integer> {

    List<Juego> findAll();

    List<Juego> findByNombre(String nombre);

    List<Juego> findByPorcentaje(String porcentaje);

    Juego findById(long id);
}
