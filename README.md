## Acetato API


Implementar un micro-servicio API REST, utilizando JAVA con VertX y  RX (ReactiveX) frameworks. Quiero aplicar las mejores practicas y ultimas tecnologias para la construccion de micro-servicios.

En cuanto a diseño y arquitectura, utilizamos SOLID, KISS y Domain Driven Design (DDD), desarrollando niveles de abstracción por capas de afuera hacia adentro.

Desarrollo esta pensado, en que el día de manana tenemos que modificar la capa de web/framework Vertx lo podemos cambiar, y el `CORE` queda intacto, al igual si queremos implementar otra base de datos, por ejemplo una relacional, podríamos hacerlo sin afectar el `CORE`


![DDD Example](https://bazaglia.com/img/onion-architecture.png)  

El centro vendria ser el core del sistema, es por lo que el servicio existe va a ser lo más importante y lo que tendríamos que tener más foco. 

|    Capa (Layer)   |                   package             |
|-------------------|---------------------------------------|
|Entities           |core.domain                            |
|Use Cases          |core.action                            |
|Interface, Adapters|core.domain                            |
|Repositories       |core.infrastructure                    |
|Domain Service     |core.infrastructure.services.converter |
|API http           |delivery.http       |


#### Aclaraciones 

* En la entidad de TRACK, se tomo en cuenta el campo en duracion siempre en segundos, lo que si queremos una duracion formateada no tenemos que guardar el campo dos veces en la base de datos, con crear una funciona format tenemos.
* Se Genero un  error de tracks y album devolverá algunos campos en null, [se creó un ticket](https://github.com/danielbernalo/acetato/issues/6) intencionalmente para cerrar el ciclo de desarrollo y manejo de tickets en git.


#### Mejoras

* En caso de alto trafico y concurrencias, se recomienda crear subdominios y gneerar  un servicio CQRS para manejar comandos y eventos, Vertx tiene un event bus con el que podemos comunicarnos por eventos entre los subdominios. 
* En cuanto a infra, se puede pensar ya que tenemos esto separado por subdominios independientes, podriamos crear un servicio por cada subdominio y escalarlo segun su trafico.

  
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

#### Album

|  Metodo  |              Endpoint              |       Descripcion        |
|----------|------------------------------------|--------------------------|
|GET       |[/albums](#listAlbums)              | Lista todos los albums   |
|GET       |[/album/:albumId](#getAlbum)        | Obtiene uno por id       |
|POST      |[/album](#addAlbum)                 | Agrega un album          |
|DELETE    |[/album/:albumId](#deleteAlbum)     | Elimina uno por id       |
|PUT       |[/album](#updateAlbum)              | Actualiza un album       |

##### Listar todos los albumes <a name="listAlbums"></a>


```
curl -X GET 'http://localhost:8080/albums'
```

##### Response success

```json
[
    {
        "album_id": "5f9a331472ab0a5e3519465c",
        "title": "No es verdad",
        "release_date": 1603759420,
        "artists": [
            {
                "_id": "5f95c35b5f72bf2dceb4916e",
                "name": "Jaime Jack Jr."
            },
            {
                "_id": "5f95c35b5f72bf2dceb4916e",
                "name": "Brame Jack Jr."
            }
        ],
        "tracks": [
            {
                "_id": "",
                "title": "You are Beautiful",
                "album": null,
                "artists": null,
                "duration": null,
                "disc_number": null,
                "track_number": null
            }
        ],
        "type": "ALBUM"
    }
]
```

##### Obtener un Album por ID <a name="getAlbum"></a>

```
curl -X GET 'http://localhost:8080/album/:albumId'
```

##### Response success

```json
    {
        "album_id": "5f9a331472ab0a5e3519465c",
        "title": "No es verdad",
        "release_date": 1603759420,
        "artists": [
            {
                "_id": "5f95c35b5f72bf2dceb4916e",
                "name": "Jaime Jack Jr."
            },
            {
                "_id": "5f95c35b5f72bf2dceb4916e",
                "name": "Brame Jack Jr."
            }
        ],
        "tracks": [
            {
                "_id": "",
                "title": "You are Beautiful",
                "album": null,
                "artists": null,
                "duration": null,
                "disc_number": null,
                "track_number": null
            }
        ],
        "type": "ALBUM"
    }
```

##### Response failure

Status code : `404`

```json
{
    "error": "Album Not Found"
}
```

##### Crear un nuevo album <a name="addAlbum"></a>

```
curl  -X POST 'http://localhost:8080/albums' \
--header 'Content-Type: application/json' \
-d '{
        "title": "No es verdad",
        "release_date": 1603759420,
        "artists": [
            {
                "_id": "5f95c35b5f72bf2dceb4916e",
                "name": "Jaime Jack Jr."
            }
        ],
        "tracks": [
             {
                "_id": "5f95c35b5f72bf2dceb4916e",
                "title": "You are Beautiful"
            }
        ],
        "type": "ALBUM"
    }'
```

##### Response success

Status code : `201` 

Sin cuerpo

##### Eliminar album <a name="deleteAlbum"></a>

```
curl  -X DELETE 'http://localhost:8080/album/:albumId'
```

##### Response success

Status code : `204` 

Sin cuerpo.


##### Actualizar album <a name="updateAlbum"></a>

```
curl -X PUT 'http://localhost:8080/album' \
--header 'Content-Type: application/json' \
-d '{
        "_id": "5f9a331472ab0a5e3519465c",
        "title": "No es verdad",
        "release_date": 1603759420,
        "artists": [
            {
                "_id": "5f95c35b5f72bf2dceb4916e",
                "name": "Jaime Jack Jr."
            }
        ],
        "tracks": [
             {
                "_id": "5f95c35b5f72bf2dceb4916e",
                "title": "You are Beautiful"
            }
        ],
        "type": "ALBUM"
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


#### Track

|  Metodo  |              Endpoint              |       Descripcion        |
|----------|------------------------------------|--------------------------|
|GET       |[/tracks](#listTracks)              | Lista todos los tracks   |
|GET       |[/track/:trackId](#getTrack)        | Obtiene uno por id       |
|POST      |[/track](#addTrack)                 | Agrega un track          |
|DELETE    |[/track/:trackId](#deleteTrack)     | Elimina uno por id       |
|PUT       |[/track](#updateTrack)              | Actualiza un track       |

##### Listar todos los trackes <a name="listTracks"></a>


```
curl -X GET 'http://localhost:8080/tracks'
```

##### Response success

```json
[
    {
        "_id": "5f9a3d189ac58a6d51d57087",
        "title": "Track Nro. 11",
        "album": {
            "_id": "5f9a331472ab0a5e3519465c",
            "title": "No es verdad",
            "release_date": null,
            "artists": null,
            "tracks": null,
            "type": "ALBUM"
        },
        "artists": [
            {
                "_id": "5f95c35b5f72bf2dceb4916e",
                "name": "Jaime Jack Jr."
            },
            {
                "_id": "5f95c35b5f72bf2dceb4916e",
                "name": "Brame Jack Jr."
            }
        ],
        "duration": 120,
        "disc_number": 1,
        "track_number": 11
    }
]
```

##### Obtener un Track por ID <a name="getTrack"></a>

```
curl -X GET 'http://localhost:8080/track/:trackId'
```

##### Response success

```json
    {
        "_id": "5f9a3d189ac58a6d51d57087",
        "title": "Track Nro. 11",
        "album": {
            "_id": "5f9a331472ab0a5e3519465c",
            "title": "No es verdad",
            "release_date": null,
            "artists": null,
            "tracks": null,
            "type": "ALBUM"
        },
        "artists": [
            {
                "_id": "5f95c35b5f72bf2dceb4916e",
                "name": "Jaime Jack Jr."
            },
            {
                "_id": "5f95c35b5f72bf2dceb4916e",
                "name": "Brame Jack Jr."
            }
        ],
        "duration": 120,
        "disc_number": 1,
        "track_number": 11
    }
```

##### Response failure

Status code : `404`

```json
{
    "error": "Track Not Found"
}
```

##### Crear un nuevo track <a name="addTrack"></a>

```
curl  -X POST 'http://localhost:8080/tracks' \
--header 'Content-Type: application/json' \
-d '{
        "title": "Track Nro. 1",
        "album": {
            "_id": "5f9a331472ab0a5e3519465c",
            "title": "No es verdad",
               "type": "ALBUM"
        },
        "artists": [
            {
                    "_id": "5f95c35b5f72bf2dceb4916e",
                    "name": "Jaime Jack Jr."
                },
                {
                    "_id": "5f95c35b5f72bf2dceb4916e",
                    "name": "Brame Jack Jr."
                }
        ],
        "duration": 120,
        "disc_number": 1,
        "track_number": 11
    
     
    }'
```

##### Response success

Status code : `201` 

Sin cuerpo

##### Eliminar track <a name="deleteTrack"></a>

```
curl  -X DELETE 'http://localhost:8080/track/:trackId'
```

##### Response success

Status code : `204` 

Sin cuerpo.


##### Actualizar track <a name="updateTrack"></a>

```
curl -X PUT 'http://localhost:8080/track' \
--header 'Content-Type: application/json' \
-d '{
            "_id": "5f9a331472ab0a5e3519465c",
            "title": "Track Nro. 1",
            "album": {
                "_id": "5f9a331472ab0a5e3519465c",
                "title": "No es verdad",
                   "type": "ALBUM"
            },
            "artists": [
                {
                        "_id": "5f95c35b5f72bf2dceb4916e",
                        "name": "Jaime Jack Jr."
                    },
                    {
                        "_id": "5f95c35b5f72bf2dceb4916e",
                        "name": "Brame Jack Jr."
                    }
            ],
            "duration": 120,
            "disc_number": 1,
            "track_number": 11
        
         
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
