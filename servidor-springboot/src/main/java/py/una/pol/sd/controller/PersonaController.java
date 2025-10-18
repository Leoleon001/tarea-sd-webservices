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
import py.una.pol.sd.model.Persona;
import py.una.pol.sd.service.PersonaService;

@RestController
@RequestMapping("/personas")
public class PersonaController {

	@Autowired
	PersonaService personaService;

	@GetMapping("/saludo")
	public String index() {
		return "Hola mundo caluroso de Springboot";
	}

    @PostMapping(value = "/listClients", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE} )
    public ResponseEntity<List<Persona>> getPersonas(@RequestBody(required = false) Juego juego) 
	{

		List<Persona> r = personaService.getPersonas();
		
		return new ResponseEntity<>(r, HttpStatus.OK);
    }

	@PostMapping(value = "/leaderBoard", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE} )
    public ResponseEntity<List<Persona>> getLeaderBoard(@RequestBody(required = false) Juego juego) 
	{

		List<Persona> r = personaService.getPersonas();
		
		r.sort((p1, p2) -> p2.getDineroganado().compareTo(p1.getDineroganado()));

		return new ResponseEntity<>(r, HttpStatus.OK);
    }

	@PostMapping(value = "/crear", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> create(@RequestBody Persona per) {
		
		System.out.println("Persona recepcionada "+ per.toString());

		if(per != null && per.getCedula() != null) {
			System.out.println("Persona recepcionada "+ per.getNombre());
			
			personaService.crear(per); 

			return new ResponseEntity<>("Se recepcionó correctamente la persona: " + per.toString(), HttpStatus.OK);
		}else{

			System.out.println("Datos mal enviados por el cliente");
			return new ResponseEntity<>("Debe enviar el campo cédula. ", HttpStatus.BAD_REQUEST);
		}


        
    }



}
