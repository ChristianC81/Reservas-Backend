# MAVEN
maven -> versión 17

# DEPENDENCIAS 
spring-boot-starter-data-jpa 
spring-boot-starter-web 
spring-boot-devtools 
spring-boot-starter-test
spring-boot-starter-validation
spring-boot-starter-mail
spring-security-crypto
springdoc-openapi-starter-webmvc-ui -> versión: 2.0.3
lombok
postgresql

# REQUISITOS PREVIOS
Realizar el proceso de instalación de herramientas proporcionadas en el manual de configuración

# Descarga el proyecto
Descarga el proyecto backend desde el repositorio o el archivo proporcionado.

# CONEXION A LA BASE DE DATOS
 1. Instalar postgresql -> a partir de la versión 12
 2. Crea una base de datos vacía en tu servidor de base de datos con el nombre "bd_salonreservas"
 3. Actualiza las credenciales de acceso y otros parámetros de la base de datos en el archivo de configuración application.properties
 4. Ejecutar el proyecto

 # SE DEBE CREAR EL ROL POR DEFECTO EN LA BASE DE DATOS PARA REGISTRAR USUARIOS
 1. EJECUTAR EL SCRIPT SQL "script_rol.sql" EN LA BASE DE DATOS
 
 # SE DEBE CREAR LOS TIPOS DE MULTIMEDIA
 1. EJECUTAR EL SCRIPT SQL "script_tipo_multimedia.sql" EN LA BASE DE DATOS

# PRUEBAS
Una vez que el proyecto esté en ejecución, puedes probar su funcionamiento accediendo a las rutas o endpoints definidos en la aplicación backend. Puedes utilizar herramientas como Swagger accediendo a la URL http://localhost:8080/swagger-ui/index.html#/ u otras herramientas externas como Postman, Insomnia para enviar solicitudes HTTP y verificar las respuestas.