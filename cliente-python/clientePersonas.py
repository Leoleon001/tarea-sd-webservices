import sys
import datetime
import configparser
import requests
from requests.structures import CaseInsensitiveDict
import datetime
from datetime import timedelta
import os;

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

    global api_personas_url_listar, api_personas_url_crear
    try:
        api_personas_url_listar = config.get('SeccionApi', 'api_personas_url_listar')
        api_personas_url_crear = config.get('SeccionApi', 'api_personas_url_crear')
    except configparser.NoOptionError as e:
        print(f"Error: Opción faltante en configuración: {e}")
        sys.exit(1)


def listar():
    headers = CaseInsensitiveDict()
    headers["Accept"] = "application/json"
    headers["Content-Type"] = "application/json"

    datos = { }
    
    
    r = requests.get(api_personas_url_listar, headers=headers, json=datos)
    if (r.status_code == 200):
        # Validar response
        listado = r.json()
        for item in listado:
            print( "      " + str(item) )
    else:
        print( "Error " + str(r.status_code))


def crear(cedula: int, nombre: str, apellido: str):
    headers = CaseInsensitiveDict()
    headers["Accept"] = "application/json"
    headers["Content-Type"] = "application/json"

    datos = {'cedula': cedula, 
             'nombre' : nombre,
             'apellido' : apellido
            }
    
    r = requests.post(api_personas_url_crear, headers=headers, json=datos)
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

print("Primer listado de personas:")
listar()
print("________________")


print("Crear nueva persona:")
cedula = int(input("Ingrese cedula: "))
nombre = input("Ingrese nombre: ")
apellido = input("Ingrese apellido: ")
crear(cedula, nombre, apellido)


print("________________")
print("Segundo listado de personas:")
listar()

print("Finalizando " + datetime.datetime.now().strftime("%Y-%m-%d %H:%M:%S"))