# MoviesApp

1. Las capas de la aplicación (por ejemplo capa de persistencia, vistas, red, negocio, etc) y qué clases pertenecen a cual.
2. La responsabilidad de cada clase creada.

## Capa Data
 En esta capa se manejan la persitencia  y la red de los datos del negocio.
- **db->** En este paquete se almacenan la clases encargadas de la base de datos
    - MoviesDao.kt ->  Database Access Object - clase para acceder a los datos de la base de datos.
    - MoviesDatabase.kt -> Clase que gestiona las entidades de la base de datos.
- **entities ->** En este paquete se almacenan las clases de modelos, mappers.
    - mappers
        - MoviesDataMapper.kt -> clase encargada de convertir la respuesta de la base de datos.
    - model
        - ResponseListMovies.kt -> archivo donde se almacenan las clases ResponseListMovies, Movies, Dates que mapean la respuesta del servicio web de peliculas
        - ResponseListMovies.kt -> archivo donde se almacenan las clases ResponseListMovieVideos, Video que mapean la respuesta del servicio web de videos
    - utils -> paquete que maneja archivos para el manejo de los estados de las peticiones
        - LiveDataExtensions.kt
        - Resource.kt
        - ResourceState.kt
- **network**
    - MoviesApi.kt -> interface para los endpoints de las peticiones
- **repositories**
    - MoviesCacheImpl.kt -> Implementación del repositorio que hace el llamado de peticiones en base de datos.
    - MoviesRemoteImpl.kt -> Implementación del repositorio que hace el llamado de peticiones del api.
    - MoviesRepositoryImpl.kt -> Implementación del repositorio que hace el llamado de peticiones en base de datos y api.
                  
## Capa Domain 
En esta capa se maneja la lógica del negocio.
- **repositories ->** paquete donde se almacenaran las interfaces para acceder a los repositorios de datos.
    - MoviesRepository.kt -> interface para acceder a la implementación del repositorio.
- **uc ->** casos de usos con los cuales interactua la capa de presentación.
    - ListMoviesUC.kt -> caso de uso para obtener la lista de peliculas por tipo(TopRated, Upcoming, Popular).
    - GetMovieVideosUC.kt -> caso de uso para obtener los videos de una pelicula.
    - GetMovieUC.kt -> caso de uso para obtener el detalle de una pelicula.
    
## Capa Presentation
En esta capa se manejan la interfaz gráfica de la aplicación.
  - **di ->** paquete que maneja la clases para la inyeccción de dependencias con Koin.
      - ManagerNetwork.kt -> clase que maneja la instancia de retrofit para las peticiones http.
      - Modules.kt -> clase que maneja los modulos que se inyectaran en la aplicación.
      - TestApp.kt -> clase de aplicación en donde se inciaran los módulos de la inyeccón de dependecias.
  - **movie ->** paquete que maneja las clases del detalle de una pelicula.
      - MovieActivity.kt -> Actividad del detalle de una pelicula.
      - MovieViewModel.kt -> ViewModel del detalle de una pelicula.  
  - **movie_video ->** paquete que maneja las clases del detalle de un video.
      - MovieVideoActivity.kt -> Actividad del detalle de un video.
  - **movie_videos ->** paquete que maneja las clases de la lista de videos de una pelicula.
      - MovieVideosActivity.kt -> Actividad de la lista de videos de una pelicula.
      - MovieVideosAdapter.kt -> Adaptador de la lista de videos de una pelicula.
      - MovieVideosViewModel.kt -> ViewModel de la lista de videos de una pelicula.
  - **movies ->** paquete que maneja las clases de la lista de peliculas.
      - MoviesActivity.kt -> Actividad de la lista de peliculas.
      - MoviesAdapter.kt -> Adaptador de la lista de peliculas.
      - MoviesViewModel.kt -> ViewModel de la lista de peliculas.
  - **splash ->** paquete que maneja las clases de la actividad de inicio.
      - SplashActivity.kt -> Actividad de inicio que muestra animación en lottie.
  - **utils ->** paquete que maneja clases utilitarias o comunes.
      - BaseActivity.kt -> Actividad base de las clases de actividad de la applicación.
      
      
## Responda y escriba dentro del Readme con las siguientes preguntas:

1. En qué consiste el principio de responsabilidad única? Cuál es su propósito?
    - El principio de responsabilidad única consiste en que cada clase que se cree debe tener una única responsabilidad, 
    es decir que cada clase debe ser creada con unobjetivo especifico y debe ejecutar funcionalidades proporcionadas por el software.
    El proposito del este principio es separar los comportamientos de tal manera que en una clase se debe contener una única 
    razón para cambiar, si una clase contiene mas de un mótivo de cambio entonces se deben dividir las responsabilidades.    

2. Qué características tiene, según su opinión, un “buen” código o código limpio?
  - En mi opinión un código limpio debe tener una arquitectura propuesta, debe estar basado en patrones y principios de desarrollo de software tales como :
      - Cada porción del código se debe encargar de una funcionalidad especifica (Principio de responsabilidad única)
      - El código se debe leer fácil (principio KISS)
      - El código no se debe repetir (principio DRY)
      - El código debe ser descriptivo.
      - Debe tener pruebas unitarias
      - Debe depender los menos posible de librerías y dependencias externas.
      - El código debe ser legible.
      
  Todas esta crácteristicas a traves de la experiencia las he podido comprobar en diferentes sistemas con la aplicación de código limpio y de buenas prácticas sugeridas por Bob Martin "El tio Bob."
   
   
# Clean Code Architecture

Para el desarrollo de este proyecto utilice clean code como se describe en este gráfico :

![Clean Code Architecture](https://estebanbarrios.dev/img/clean-code.png)

