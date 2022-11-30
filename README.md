# DesarrolloMovilMISO
Aqui se trabajara todo de la matería de Moviles



<div>
<h3 align="center">Vinilos app Uniandes MISO</h3>

<<<<<<< HEAD
  <p align="center">
=======
  <p align="rigth">
>>>>>>> main
    Este repositorio contiene el desarrollo implementado por el equipo para la materia Desarrollo de software en dispositivos moviles de la Maestria MISO en la universidad de Los Andes, contiene el archivo APK que se debe descargar, e instalar usando un dispositivo android con una versión mayor a la versión L (Lollipop) para que este corra en optimas condiciones.
</div>


### Prerequisitos

Se recomienda usar un computador con una RAM mayor a 8 GB o un celular con más de 10 GB de espacio disponible y con una versión de android superior a L, para evitar posibles inconvenientes con la aplicación.

### HU a desarrollar en el repo a lo largo de curso

- HU-016	Crear Artista
- HU-007	Crear Álbum
- HU-017	Crear Coleccionista
- HU-001	Consultar catalogo de Álbumes
<<<<<<< HEAD
- HU-003	Consultar el listado de Artistas
- HU-005	Consultar el listado de Coleccionistas
- HU-004	Consultar la información detallada de un Artista
- HU-002	Consultar la información detallada de un Álbum
- HU-006	Consultar la información detallada de un Coleccionista

=======
- HU-002	Consultar la información detallada de un Álbum
- HU-003	Consultar el listado de Artistas
- HU-004	Consultar la información detallada de un Artista
- HU-005	Consultar el listado de Coleccionistas
- HU-006	Consultar la información detallada de un Coleccionista

<div>
<h3 align="center">Pasos para ejecutar las pruebas exploratorias con Monkey</h3>

<p align="rigth">
  Es necesario que el dispositivo en el cual va a probar la aplicación, permita la instalación de APKs por medio de USB, en caso de que sea un dispositivo físico.
  Para generar el APK, abra el proyecto del tutorial CL17 en Android Studio, y en el menú de la parte superior seleccione la opción Build > Build Bundle(s)/APK(s) > Build APK(s). 
  Esto comenzará el proceso de empaquetado de la aplicación, el cual puede seguir en la pestaña Build de la parte inferior del IDE. Podrá ver también una alerta al finalizar el proceso, la cual indica que se generó el APK deseado y permite localizar el archivo en el sistema de archivos. 
  La alerta se ve de la siguiente forma:
  https://uniandes-my.sharepoint.com/:i:/g/personal/js_sanchezl12_uniandes_edu_co/EfxtpQcMaNFMnbPKIJtSk7kBebfYxtSmW_x3z88VjKjH5Q?e=ebE6fd

  - En la terminal del proyecto escriba el siguiente comando: adb devices <br>
  Una vez todo se encuentre adecuadamente configurado, podrá ver en la consola el listado de dispositivos con el nombre de usuario asociado, como se ve en la siguiente imagen:
  https://uniandes-my.sharepoint.com/:i:/g/personal/js_sanchezl12_uniandes_edu_co/EadvRl5McnFDii-b5cZUllEBTGmDSUlkPDFHy64upNYHwg?e=KoK6C3
  - De la lista que se muestra seleccione el ID del dispositivo en el cual desea correr el monkey
  - En la terminal digite el siguiente comando: adb -s emulator-5554 shell monkey -p com.example.movilmisodreamteam2022 -v 100
</div> 


>>>>>>> main
### Instrucciones para construccion de la App

1. Debemos clonar el repositorio con el comando  
``` git clone https://github.com/ACardenasJ/DesarrolloMovilMISO.git ```
2. Nos debemos parar en la rama de release o main  
``` git checkout release ```
3. Abrimos nuestro programa de Android Studio
4. Debemos abrir nuestro proyecto parados sobre nuestra carpeta base con nombre DesarrolloMovilMISO
5. Debemos verificar que nuestro gradle este actualizado, solo debemos darle clic sobre el icono del elefante
6. Si queremos correr la app localmente, debemos elegir un dispositivo y debemos dar clic sobre el triangulo verde
7. Si queremos generar nuestra APK, debemos dar clic sobre el menu de  
``` Build -> Build Bundle(s) / APK(s) -> Build APK(s) ```
8. Luego debemos ir a la siguiente direccion  
``` DesarrolloMovilMISO/app/build/outputs/apk/debug ```
9. Luego debemos copiar nuestra APK generada a nuestro dispositivo final (en este caso estamos usando un dispositivo fisico)
10. Luego debemos dirigirnos sobre el APK y dar clic y luego dar en Instalar.  
NOTA: El dispositivo debe tener configurado la parte poder instalar aplicaciones desconocidas
