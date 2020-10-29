# NXASTUDIOS

## Acetato API

#### Prueba tecnica


Implementar un micro-servicio API REST para la empresa nxstudios, utilizando JAVA con VertX y  RX (ReactiveX) frameworks. Quiero aplicar las mejores practicas y ultimas tecnologias para la construcion de micro-servicios.

En cuanto a diseno y arquitectura, utlizaremos Domain Driven Design (DDD), desarrollando niveles de abstracion por capas de afuera hacia adentro. 

![DDD Example](https://bazaglia.com/img/onion-architecture.png)  

El centro vendria ser el core de dominio del sistema, es por lo que el servicio existe y vendria a ser lo mas importante. 

|    Capa (Layer)   |                   package             |
|-------------------|---------------------------------------|
|Entities           |core.domain                            |
|Use Cases          |core.action                            |
|Interface, Adapters|core.domain                            |
|Repositories       |core.infrastructure                    |
|Domain Service     |core.infrastructure.services.converter |
|API http           |delivery.http       |
  
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

Para correr test, como lo mencionamos anteriormente, desde la terminal en la raíz del proyecto ejecuta lo siguiente:
```
./gradlew test
```

Si queremos buildear el proyecto podremos ejecutar lo siguiente: 

```
./gradlew jar
```

Para ejecutar el proyecto recien buildeado, ejecutamos lo siguiente:

```
export REPOSITORY="MONGO"
export MONGO_URI="mongodb+srv://acetato_user:kxrxdZctDlLz23CJ@cluster-serviberza.zburx.mongodb.net/acetato?retryWrites=true&w=majority"

java -jar build/libs/acetato-1.0.0.jar  
```

Nota: Las credenciales expuestas en la variable de entorno `MONGO_URI` son solo para fines de esta prueba, luego seran removidas, por seguridad.


### API Endpoints

#### Artist

|  Metodo  |              Endpoint              |       Descripcion        |
|----------|------------------------------------|--------------------------|
|GET       |[/artists](#listArtists)            | Lista todos los artistas |
|GET       |[/artist/:artistId](#getArtist)     | Obtiene uno por id       |
|POST      |[/artist](#addArtist)               | Agrega un artistas       |
|DELETE    |[/artist/:artistId](#deleteArtist)  | Elimina uno por id       |
|PUT       |[/artist](#updateArtist)            | Actualiza un artista     |

##### Listar todos los artistas <a name="listArtists"></a>


```
curl -X GET 'http://localhost:8080/artists'
```

##### Response success

```json
[
    {
        "artist_id": "5f95c35b5f72bf2dceb4916e",
        "name": "Jaime Jack Jr."
    },
    {
        "artist_id": "5f95c5b96836a96cdf1721db",
        "name": "Elvis"
    },
    {
        "artist_id": "5f96296d2f590315aeeccbd8",
        "name": "Shakira, Jr."
    }
]
```

##### Obtener un Artista por ID <a name="getArtist"></a>

```
curl -X GET 'http://localhost:8080/artist/:artistId'
```

##### Response success

```json

    {
        "artist_id": "5f95c35b5f72bf2dceb4916e",
        "name": "Jaime Jack Jr."
    }
```

##### Response failure

Status code : `404`

```json
{
    "error": "Artist Not Found"
}
```

##### Crear un nuevo artista <a name="addArtist"></a>

```
curl  -X POST 'http://localhost:8080/artists' \
--header 'Content-Type: application/json' \
-d '{
    "name": "Metallica"
}'
```

##### Response success

Status code : `201` 

Sin cuerpo

##### Eliminar artista <a name="deleteArtist"></a>

```
curl  -X DELETE 'http://localhost:8080/artist/:artistId'
```

##### Response success

Status code : `204` 

Sin cuerpo.


##### Actualizar artista <a name="updateArtist"></a>

```
curl -X PUT 'http://localhost:8080/artist' \
--header 'Content-Type: application/json' \
-d '{
    "_id": "5f96296d2f590315aeeccbd8",
    "name": "Shakira, Jr."
}'
```
Parametro obligatorio: `_id`

##### Response success

Status code : `201` 

Sin cuerpo.

##### Response failure 

Status Code: `400`

```json
{
    "error": "Parameter: _id is required."
}
```
