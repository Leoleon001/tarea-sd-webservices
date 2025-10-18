import sys
import datetime
import configparser
import requests
from requests.structures import CaseInsensitiveDict
import datetime
from datetime import timedelta
import os;
import random


#Variables globales para verificacion
api_personas_url_base = None
script_dir = os.path.dirname(os.path.abspath(__file__))
archivo_config = os.path.join(script_dir, 'ConfigFile.properties')

def cargar_variables():
    config = configparser.RawConfigParser()
    
    # Verificar si el archivo existe
    if not os.path.exists(archivo_config):
        print(f"Error: El archivo de configuración '{archivo_config}' no existe")
        sys.exit(1)
    
    config.read(archivo_config)
    
    # Verificar si la sección existe
    if not config.has_section('SeccionApi'):
        print(f"Error: La sección 'SeccionApi' no existe en el archivo de configuración")
        print("Secciones encontradas:", config.sections())
        sys.exit(1)

    global api_personas_url_listar, api_personas_url_crear, api_juegos_url_listar, api_juegos_url_crear,api_juegos_url_listar, api_juegos_url_crear,api_cuentabancaria_url_listar, api_cuentabancaria_url_crear
    try:
        api_personas_url_listar = config.get('SeccionApi', 'api_personas_url_listar')
        api_personas_url_crear = config.get('SeccionApi', 'api_personas_url_crear')
        api_juegos_url_listar = config.get('SeccionApi', 'api_juegos_url_listar')
        api_juegos_url_crear = config.get('SeccionApi', 'api_juegos_url_crear')
        api_cuentabancaria_url_listar = config.get('SeccionApi', 'api_cuentabancaria_url_listar')
        api_cuentabancaria_url_crear = config.get('SeccionApi', 'api_cuentabancaria_url_crear')
        
    except configparser.NoOptionError as e:
        print(f"Error: Opción faltante en configuración: {e}")
        sys.exit(1)


def listarPersonas(cedula):
    headers = CaseInsensitiveDict()
    headers["Accept"] = "application/json"
    headers["Content-Type"] = "application/json"

    if cedula != 0 :
        datos = { 'cedula': cedula }
    else:
        datos = { }
    
    
    
    r = requests.get(api_personas_url_listar, headers=headers, json=datos)
    if (r.status_code == 200):
        # Validar response
        listado = r.json()
        for item in listado:
            print( "      " + str(item) )
    else:
        print( "Error " + str(r.status_code))

def listarPersonasInterno(cedula: int):
    headers = CaseInsensitiveDict()
    headers["Accept"] = "application/json"
    headers["Content-Type"] = "application/json"

    if cedula != 0 :
        datos = { 'cedula': cedula }
    else:
        datos = { }
    
    global respuesta
    respuesta = requests.get(api_personas_url_listar, headers=headers, json=datos)
    if (respuesta.status_code == 200):
        # Validar response
        listado = respuesta.json()
    else:
        print( "Error " + str(respuesta.status_code))


def listarJuegos(idJuego):
    headers = CaseInsensitiveDict()
    headers["Accept"] = "application/json"
    headers["Content-Type"] = "application/json"

    if idJuego != 0 :
        datos = { 'id': idJuego }
    else:
        datos = { }
    
    
    r = requests.post(api_juegos_url_listar, headers=headers, json=datos)
    if (r.status_code == 200):
        # Validar response
        listado = r.json()
        for item in listado:
            print( "      " + str(item) )
    else:
        print( "Error " + str(r.status_code))


def listarJuegosInterno(idJuego):
    headers = CaseInsensitiveDict()
    headers["Accept"] = "application/json"
    headers["Content-Type"] = "application/json"

    if idJuego != 0 :
        datos = { 'id': idJuego }
    else:
        datos = { }
    
    global respuesta
    respuesta = requests.post(api_juegos_url_listar, headers=headers, json=datos)
    if (respuesta.status_code == 200):
        # Validar response
        listado = respuesta.json()
        for item in listado:
            print( "      " + str(item) )
    else:
        print( "Error " + str(r.status_code))


def listarCuentaBancaria():
    headers = CaseInsensitiveDict()
    headers["Accept"] = "application/json"
    headers["Content-Type"] = "application/json"

    datos = { }
    
    
    r = requests.post(api_cuentabancaria_url_listar, headers=headers, json=datos)
    if (r.status_code == 200):
        # Validar response
        listado = r.json()
        for item in listado:
            print( "      " + str(item) )
    else:
        print( "Error " + str(r.status_code))

def crearPersona(cedula: int, nombre: str, apellido: str,dineroapostado: float,dineroganado: float ):
    headers = CaseInsensitiveDict()
    headers["Accept"] = "application/json"
    headers["Content-Type"] = "application/json"

    datos = {'cedula': cedula, 
             'nombre' : nombre,
             'apellido' : apellido,
             'dineroapostado': dineroapostado,
             'dineroganado':dineroganado
            }
    
    r = requests.post(api_personas_url_crear, headers=headers, json=datos)
    if (r.status_code >= 200 and r.status_code < 300):
        # Validar response
       """print(r)"""
        
    else:
        print( "Error " + str(r.status_code))
        print(str(r.json()))

def crearCuentaBancaria(idbanco: int, denominacion: str, nrocuenta: int, saldo: float, cuentaPadre: bool):
    headers = CaseInsensitiveDict()
    headers["Accept"] = "application/json"
    headers["Content-Type"] = "application/json"

    datos = {'idbanco': idbanco, 
             'denominacion' : denominacion,
             'nrocuenta' : nrocuenta,
             'saldo' : saldo,
             'cuentaPadre' : cuentaPadre
            }
    
    r = requests.post(api_cuentabancaria_url_crear, headers=headers, json=datos)
    if (r.status_code >= 200 and r  .status_code < 300):
        # Validar response
        print(r)
        
    else:
        print( "Error " + str(r.status_code))
        print(str(r.json()))

def crearJuego(id: int, nombre: str, porcentaje: float):
    headers = CaseInsensitiveDict()
    headers["Accept"] = "application/json"
    headers["Content-Type"] = "application/json"

    datos = {'id': id, 
             'nombre' : nombre,
             'porcentaje' : porcentaje
            }
    
    r = requests.post(api_juegos_url_crear, headers=headers, json=datos)
    if (r.status_code >= 200 and r.status_code < 300):
        # Validar response
        print(r)
        
    else:
        print( "Error " + str(r.status_code))
        print(str(r.json()))



#######################################################
######  Procesamiento principal
#######################################################
print("Iniciando " + datetime.datetime.now().strftime("%Y-%m-%d %H:%M:%S"))
cargar_variables()

continuar = True
while continuar:
    print("------------------------------------------------------------------")
    print("Seleccione una opción:")
    print("10. Listar Personas")
    print("20. Crear Persona")
    print("21. Realizar juego")
    print("22. Imprimir leaderboard")
    print("30. Listar Juegos")
    print("40. Crear Juego")
    print("50. Listar Cuenta Bancaria")
    print("60. Crear Cuenta Bancaria")
    print("99. Salir")
    
    opcion = input("Ingrese el número de la opción deseada: ")
    
    if opcion == '10':
        print("Listado de Personas:")
        listarPersonas(0)
    elif opcion == '20':
        cedula = int(input("Ingrese cedula: "))
        nombre = input("Ingrese nombre: ")
        apellido = input("Ingrese apellido: ")
        dineroapostado = int(input("Ingrese dinero apostado: "))
        dineroganado = int(input("Ingrese dinero ganado: "))
        crearPersona(cedula, nombre, apellido,dineroapostado,dineroganado)
    elif opcion == '21':
        cedula = int(input('Ingrese la cedula del jugador:'))
        listarJuegosInterno(0)
        idjuego = int(input('Ingrese la ID del juego:'))
        dineroapostado = float(input('Ingrese el dinero a apostar:'))
        listado = respuesta.json()

        porcentaje = listado[idjuego-1].get('porcentaje')
        rand = random.random() * 100

        if rand <= porcentaje:
            ganancia = dineroapostado * 2
            print(f'Felicidades! Ha ganado {ganancia}')
        else:
            ganancia = 0
            print('Lo siento, la proxima seguro sera.')

        listarPersonasInterno(cedula)
        listadoPersonas = respuesta.json()
        for item in listadoPersonas:
            if item.get('cedula') == cedula:
                if item.get('dineroapostado') is not None:
                    dineroapostadoTotal = int(item.get('dineroapostado')) + dineroapostado
                else:
                    dineroapostadoTotal = dineroapostado

                if item.get('dineroganado') is not None:    
                    dineroganadoTotal = int(item.get('dineroganado')) + ganancia
                else:
                    dineroganadoTotal = ganancia
                
                crearPersona(cedula, item.get('nombre'), item.get('apellido'),dineroapostadoTotal,dineroganadoTotal)
        
        print('Juego finalizado con éxito!.')        

        
    elif opcion == '30':
        print("Cargar cuerpo:")
        idJuego = int(input("ID del juego (Vacio todos):"))
        listarJuegos(idJuego)

    elif opcion == '40':
        id = int(input("Ingrese ID del juego: "))
        nombre = input("Ingrese nombre del juego: ")
        porcentaje = float(input("Ingrese porcentaje del juego: "))
        crearJuego(id, nombre, porcentaje)
    elif opcion == '50':
        print("Listado de Cuenta Bancaria:")
        listarCuentaBancaria()
    elif opcion == '60':
        print("Crear Cuenta Bancaria:")
        idbanco = int(input("Ingrese ID del banco: "))
        denominacion = input("Ingrese denominacion: ")
        nrocuenta = int(input("Ingrese numero de cuenta: "))    
        saldo = float(input("Ingrese saldo inicial: "))
        print('Es cuenta padre? (s/n):')
        escuenta = input().lower()
        if escuenta == 's':
            cuentaPadre = True
        else:
            cuentaPadre = False

        crearCuentaBancaria()

    elif opcion == '99':
        continuar = False
    else:
        print("Opción no válida. Por favor, intente de nuevo.")



print("Finalizando " + datetime.datetime.now().strftime("%Y-%m-%d %H:%M:%S"))