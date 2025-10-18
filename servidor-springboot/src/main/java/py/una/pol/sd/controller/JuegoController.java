package py.una.pol.sd.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import py.una.pol.sd.model.Juego;
import py.una.pol.sd.service.JuegoService;

@RestController
@RequestMapping("/juegos")
public class JuegoController {

	@Autowired
	JuegoService juegoService;

	@GetMapping("/saludo")
	public String index() {
		return "Encontrada la Ruta de Juegos";
	}

    @PostMapping(value = "/listGames", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE} )
    public ResponseEntity<List<Juego>> getJuego(@RequestBody(required = false) Juego juego) 
	{
		
		if (juego == null){
			juego = new Juego();
			juego.setId(0);
		} else {	
			if (juego.getId() == null){
				juego.setId(0);
			}
		}

		List<Juego> r = juegoService.getJuegos(juego.getId());

		return new ResponseEntity<>(r, HttpStatus.OK);
    }


	@PostMapping(value = "/crear", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> create(@RequestBody Juego jue) {


		if(jue != null && jue.getId() != null) {
			System.out.println("Juego recepcionada "+ jue.getNombre());
			
			juegoService.crear(jue); 

			return new ResponseEntity<>("Se recepcionó correctamente el juego: " + jue.toString(), HttpStatus.OK);
		}else{

			System.out.println("Datos mal enviados por el cliente");
			return new ResponseEntity<>("Debe enviar el campo cédula. ", HttpStatus.BAD_REQUEST);
		}

    }

}
