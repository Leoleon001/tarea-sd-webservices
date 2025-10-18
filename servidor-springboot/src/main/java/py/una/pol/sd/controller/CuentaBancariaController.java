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

import py.una.pol.sd.model.CuentaBancaria;
import py.una.pol.sd.service.CuentaBancariaService;

@RestController
@RequestMapping("/CuentaBancaria")
public class CuentaBancariaController {

	@Autowired
	CuentaBancariaService cuentaBancariaService;

	@GetMapping("/saludo")
	public String index() {
		return "Ha entrado en la carpeta de Cuenta Bancaria";
	}

	@PostMapping(value = "/listar", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<CuentaBancaria>> getCuentas(@RequestBody(required = false) CuentaBancaria cuenta) 
	{
		
		if (cuenta == null){
			cuenta = new CuentaBancaria();
			cuenta.setIdbanco(0);
		}else{	
			if (cuenta.getIdBanco() == null){
				cuenta.setIdbanco(0);
			}
		}
		List<CuentaBancaria> r = cuentaBancariaService.getCuentas(cuenta.getIdBanco());
		
		return new ResponseEntity<>(r, HttpStatus.OK);
	}

	@PostMapping(value = "/crear", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> create(@RequestBody CuentaBancaria cuenta) {

		if(cuenta != null && cuenta.getIdBanco() != null) {
			System.out.println("Cuenta recepcionada "+ cuenta.getDenominacion());
			
			cuentaBancariaService.crearCuenta(cuenta); 

			return new ResponseEntity<>("Se recepcionó correctamente la persona: " + cuenta.toString(), HttpStatus.OK);
		}else{

			System.out.println("Datos mal enviados por el cliente");
			return new ResponseEntity<>("Debe enviar el id del banco. ", HttpStatus.BAD_REQUEST);
		}
        
    }



}
