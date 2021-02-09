<p align="center">
<b>
API PORTABILIDAD PYME</br>
MS-CBK-PORTABILITY
</b>
</p>

<p align="center">
<b>
https://bitbucket.agile.bns/projects/LTMCLPP/repos/portalpyme_configuration/browse/ms-cbk-portability
</b>
</p>

## Table of Contents
* [Descripción](#descripcion)
* [Tecnologías](#tecnolog�as)
* [¿Cómo instalar/ejecutar?](#¿como-se-instala?)
* [Pre- Requisitos](#pre-requisitos)
* [¿Cómo configurar?](#como-configuar)
* [Dependencias](#dependencias)

## Descripción
**ms-pharmacy** .

## Tecnologías
* **Java 1.8**
* **SpringBoot 1.5.22.RELEASE**
* **Gradle 6.4.1**

## ¿Cómo instalar/ejecutar?
```sh
 ./gradlew bootRun
```

### Variables de entorno de base de datos
Las conexiones JNDI requeridas son hacia bases de datos Sybase, AS400 y Sql Server
```sh
#Local
No se requieren jndi, springboot realiza conexion directa a las bases de datos

#Servidores WAS (DA1, PA5, . . . , PROD)
Sql Server: jdbc/BD_CBK_REQUIREMENTS (reader)
            jdbc/BD_CBK_REQUIREMENTS_DBO (owner)

```
### Como testear la aplicación

```sh
./gradlew test
```

### Como construir la aplicación
```sh
./gradlew build
```

### Como ejecutar la aplicación
```sh
./gradlew bootRun
```

## Pre- Requisitos
* Java 1.8

## Como configurar la aplicación
...


## Dependencias
* Servicio MS-Equifax
* Datapower Bancos
* Servicio ms-enterpise (Adminstrador)
* Servicio notificación de correos
* Servicio OMDm
