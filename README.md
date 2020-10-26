# NXASTUDIOS

## Acetato API

#### Prueba tecnica


Implementar un micro-servicio API REST para la empresa nxstudios, utilizando JAVA con VertX y  RX (ReactiveX) frameworks. Quiero aplicar las mejores practicas y ultimas tecnologias para la construcion de micro-servicios.


### Dependiencias
| Name       | Version |
|------------|---------|
| JRE/Java   | [8u261](https://www.java.com/es/download/) |
| Gradle     | [6.3.0](https://github.com/gradle/gradle/releases/tag/v6.3.0)   |
| Vertx      | [3.8.3](https://github.com/eclipse-vertx/vert.x/releases/tag/3.8.3)   |
| RxKotlin   | [2.2.20](https://github.com/ReactiveX/RxJava/releases/tag/v2.2.20)   |

### Como ejecutar la app

#### Utilizando Docker (recomendado)
Desde un terminal busca la carpeta raiz del proyecto y ejecuta lo siguiente: 
```
docker build --rm -t acetato .
```

Para ejecutarlo:
```
docker run -p 8080:8080 -e REPOSITORY=MONGO -e MONGO_URI=mongodb+srv://acetato_user:kxrxdZctDlLz23CJ@cluster-serviberza.zburx.mongodb.net/acetato?retryWrites=true&w=majority -t acetato acetato
```

Nota: Las credenciales expuestas en la variable de entorno `MONGO_URI` son solo para fines de esta prueba, luego seran removidas, por seguridad.
 
#### Utilizando gradle

Desde un terminal busca la carpeta raiz del proyecto y ejecuta lo siguiente:
```
./gradlew run
```

Para los test, como lo mencionamos anteriormente, desde la terminal en la raiz del proyecto ejecuta lo siguiente:
```
./gradlew test
```

Si queremos buildear el proyecto podremos ejecutar lo siguiente: 

```
./gradlew jar
```

Para ejecutar el proyecto recien buildeado, ejecutamos lo siguiente:

```
export REPOSITORY=MONGO
export MONGO_URI=mongodb+srv://acetato_user:kxrxdZctDlLz23CJ@cluster-serviberza.zburx.mongodb.net/acetato?retryWrites=true&w=majority

java -jar build/libs/acetato-1.0-SNAPSHOT.jar  
```

Nota: Las credenciales expuestas en la variable de entorno `MONGO_URI` son solo para fines de esta prueba, luego seran removidas, por seguridad.


### API Endpoints

#### Artist

|Metodo|Endpoint|Descripcion|
|-------|--------|-----|
|GET|/artists|Lista todos los artistas|
|GET|/artist/:artistId|Obtiene uno por id|
|POST|/artists|Agrega un artistas|
|DELETE|/artist/:artistId|Elimina uno por id|
|PUT|/artist|Actualiza un artista|

