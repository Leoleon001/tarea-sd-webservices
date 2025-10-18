package py.una.pol.sd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import py.una.pol.sd.model.CuentaBancaria;
import py.una.pol.sd.repository.CuentaBancariaRepository;

@Service
public class CuentaBancariaService {


    @Autowired
    CuentaBancariaRepository repository;
    List<CuentaBancaria> cuentas;
    CuentaBancaria cuenta;
    public List<CuentaBancaria> getCuentas(int idBanco){
        Boolean found = false;
        cuentas = repository.findAll();
        System.out.println("idBanco: " + idBanco);
        if ( idBanco != 0 ) {
            for (CuentaBancaria cuenta : cuentas) {
                if (cuenta.getIdBanco() == idBanco) {  
                    cuentas = new java.util.ArrayList<CuentaBancaria>();
                    cuentas.add(cuenta);
                    found = true;
                    break;
                }
                
            }
            if ( !found ) {
                cuentas = new java.util.ArrayList<CuentaBancaria>();
            }
        }
        
        return cuentas;

    }
    
    public CuentaBancaria crearCuenta(CuentaBancaria p){

        return repository.save(p);
    }

}
