# Post-Contenido 1 Unidad 10
```Alejandra Farfan Duarte - 02230131026 - Ingenieria De Sistema ```

Aplicación Spring Boot de gestión de tareas con suite de pruebas automatizadas.

## Tecnologías
- Java 17, Spring Boot 3.2, Maven
- JUnit 5, Mockito, AssertJ
- JaCoCo para cobertura de código
- H2 en memoria para pruebas


## Configuración de H2 en memoria para pruebas

El proyecto usa **H2 en memoria** automáticamente durante las pruebas gracias a la dependencia `spring-boot-starter-test` y `h2` en el `pom.xml`. No se requiere configuración adicional: al ejecutar `mvn test`, Spring Boot detecta H2 y levanta una base de datos temporal que se destruye al finalizar.

Para verificarlo, en `src/test/resources/application.properties` se puede agregar:

```properties
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driver-class-name=org.h2.Driver
spring.jpa.hibernate.ddl-auto=create-drop
```

---

## Cómo ejecutar las pruebas

```bash
# Ejecutar todos los tests
mvn test

# Ejecutar solo una clase de prueba
mvn test -Dtest=TareaServiceTest

# Ejecutar tests y verificar cobertura mínima del 70%
mvn test jacoco:check
```
## Cómo ver el reporte de cobertura
El reporte de cobertura se genera automáticamente en:
```bash
mvn test
 target/site/jacoco/index.html
```
Ábrelo en el navegador para ver la cobertura detallada por clase y método.

## Reporte de cobertura JaCoCo

Cobertura obtenida con `mvn test`:

| Paquete | Cobertura |
|---|---|
| service | 69% |
| entity | 80% |
| controller | 87% |
| **Total** | **75%** ✅ |
![Reporte de Cobertura](captura/jacoco.png)

## Clases de prueba

| Clase | Tipo | Descripción |
|---|---|---|
| `TareaServiceTest` | Unitaria (@ExtendWith Mockito) | Prueba crear, buscar y validaciones del servicio |
| `TareaControllerTest` | Integración (@WebMvcTest) | Prueba endpoints HTTP con MockMvc |
| `TareaRepositoryTest` | Integración (@DataJpaTest) | Prueba consultas JPA con H2 en memoria |



## Estructura del proyecto
```src/
├── main/java/com/tuapellido/
│   ├── entity/
│   │   └── Tarea.java
│   ├── repository/
│   │   └── TareaRepository.java
│   ├── service/
│   │   └── TareaService.java
│   └── controller/
│       └── TareaController.java
└── test/java/com/tuapellido/
    ├── service/
    │   └── TareaServiceTest.java
    ├── controller/
    │   └── TareaControllerTest.java
    └── repository/
        └── TareaRepositoryTest.java
```