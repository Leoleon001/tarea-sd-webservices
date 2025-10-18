package py.una.pol.sd.repository;

//import java.math.BigInteger;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import py.una.pol.sd.model.CuentaBancaria;

@Repository
public interface CuentaBancariaRepository extends CrudRepository<CuentaBancaria, Integer> {

    List<CuentaBancaria> findAll();

    List<CuentaBancaria> findByDenominacion(String denominacion);

//    List<CuentaBancaria> findByNroCuenta(BigInteger nrocuenta);

   // CuentaBancaria findByidbanco(Integer idbanco);
}
