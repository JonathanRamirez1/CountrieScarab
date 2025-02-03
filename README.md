# Country Comparison App

## **Descripción del Proyecto**
La aplicación **Country Comparison App** permite explorar y comparar la población de países organizados por regiones. Implementada siguiendo principios de **Clean Architecture** y el patrón **MVVM**, la app utiliza **Jetpack Compose** para la interfaz de usuario y aprovecha tecnologías modernas como **Hilt**, **Room**, **Retrofit**, y **Coroutines** para un desarrollo robusto y eficiente.

---

## **Arquitectura**
El proyecto sigue el diseño de **Clean Architecture** con las siguientes capas:

1. **Data Layer**:
   - Maneja la persistencia y el acceso a datos.
   - Tecnologías: **Room**, **Retrofit**.
   - Contiene:
     - **Repositories**: Implementan las fuentes de datos locales y remotas.
     - **Entities**: Representan las tablas de la base de datos.
     - **Models**: Representan las respuestas de la API.
     - **DAOs**: Interfaz de acceso a la base de datos.

2. **Domain Layer**:
   - Contiene la lógica de negocio.
   - **Use Cases**:
     - Ejemplo: `GetCountriesByRegion`, `GetCountryByName`.

3. **Presentation Layer**:
   - Maneja la interfaz de usuario.
   - **ViewModels**: Coordina los datos para las vistas usando flujos de datos (`StateFlow`).
   - **Composable Functions**: Implementan las pantallas con **Jetpack Compose**.

---

## **Patrón de Diseño**
**MVVM (Model-View-ViewModel):**
- **Model**:
  - Manejo de datos usando Room y Retrofit.
- **ViewModel**:
  - Utiliza flujos reactivos (`StateFlow`) para exponer datos a la UI.
- **View**:
  - Pantallas declarativas usando **Jetpack Compose**.

---

## **Dependencias Principales**
### Jetpack
- **Compose**:
  - `compose-ui`: Interfaz declarativa.
  - `material3`: Diseño Material Design 3.
  - `navigation-compose`: Navegación entre pantallas.
- **Room**:
  - Persistencia local para almacenar y consultar datos.
- **Lifecycle**:
  - Manejo del ciclo de vida en las pantallas y ViewModels.

### Red
- **Retrofit**:
  - Cliente HTTP para consumir APIs REST.
- **Gson**:
  - Serialización y deserialización de JSON.
- **OkHttp Logging Interceptor**:
  - Registro de solicitudes y respuestas HTTP.

### Inyección de Dependencias
- **Hilt**:
  - Simplifica la inyección de dependencias en Android.

### Testing
- **JUnit5**:
  - Testing unitario.
- **Mockito**:
  - Mocking de dependencias.
- **Turbine**:
  - Testing de flujos reactivos.

---

## **Cómo Correr el Proyecto**

1. **Requisitos Previos**:
   - **Android Studio Electric Eel (o más reciente)**.
   - SDK mínimo: 24.
   - JDK: 17.

2. **Clonar el Repositorio**:
   ```bash
   git clone https://github.com/JonathanRamirez1/CountrieScarab.git
