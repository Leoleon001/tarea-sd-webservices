package py.una.pol.sd.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import py.una.pol.sd.model.Juego;
import py.una.pol.sd.repository.JuegoRepository;

@Service
public class JuegoService {

    private List<Juego> listajuegos;
    @Autowired
    JuegoRepository repository;
    
    public List<Juego> getJuegos(int id){
        Boolean found = false;
        listajuegos = repository.findAll();
        if ( id != 0 ) {
            for (Juego juego : listajuegos) {
                if (juego.getId() == id) {  
                    listajuegos = new java.util.ArrayList<Juego>();
                    listajuegos.add(juego);
                    found = true;
                    break;
                }
                
            }
            if ( !found ) {
                listajuegos = new java.util.ArrayList<Juego>();
            }
        }

        return listajuegos;
    }
    
    public Juego crear(Juego p){

        return repository.save(p);
    }

}
