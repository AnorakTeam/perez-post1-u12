# Refactorización Avanzada y Clean Code Profundo
Estudiante: José Manuel Pérez Rodríguez
Código: 1152375

Desarrollo de la primera actividad de post contenido de la unidad 12 del curso Patrones de diseño.

### Contexto
El estudiante implementa un sistema de gestión de pedidos en Spring Boot integrando cuatro patrones de diseño (Factory, Strategy, Observer y Facade), verifica el desacoplamiento entre capas con ArchUnit, y compara las métricas de calidad antes y después de la integración de patrones usando SonarQube.

## Estado inicial del análisis
| Categoría | Cantidad | Rating |
|-----------|----------|--------|
| Bugs | 0 | A |
| Vulnerabilidades | 1 | D |
| Code Smells | 2 | A |
| issues | 0 | - |
| Cobertura | 63.4% | — |

## Análisis de calidad actualizado
El análisis muestra mejoras en seguridad, confiabilidad y mantenimiento del código. Los comentarios y resultados son obtenidos directamente del dashboard.

| Categoría | Resultado | Rating | Comentario |
|-----------|-----------|--------|------------|
| Vulnerabilities | 0 | A | Security rating is A when there are no vulnerabilities. |
| Bugs | 0 | A | Reliability rating is A when there are no bugs. |
| Code Smells | 1 | A | Maintainability rating is A when the technical debt ratio is less than 5.0%. |
| Accepted issues | 0 | — | Valid issues that were not fixed. |
| Coverage | 85.7% | — | On 161 new lines to cover. |
| Duplications | 0.0% | — | On 1.2k lines. |

## Reflexión

Este proyecto demuestra cómo la integración de los patrones Factory, Strategy, Observer y Facade mejora la organización del sistema de pedidos. Al separar responsabilidades entre capa de aplicación, dominio e infraestructura, el código se vuelve más fácil de mantener, probar y extender sin afectar el comportamiento general del sistema.

## Ejecución de código y pruebas

### Código

Desde la raíz del proyecto (directorio donde está README.md)

```bash
./mvnw spring-boot:run
```
O también, utilizar la extensión de vscode "Extension Pack for Java" de Microsoft para autoconfiguración y ejecución simple.

### Pruebas
Para ejecución local con un servidor SonarQube en Docker:

```bash
mvn clean verify org.sonarsource.scanner.maven:sonar   -Dsonar.projectKey=com.empresa:   -Dsonar.projectName='pedidos'   -Dsonar.host.url=http://localhost:9000   -Dsonar.token={TOKEN_GENERADO_SONARQUBE}
```

## Capturas del dashboard (actual)

![Dashboard SonarQube fixed](docs/post1_after.png)

## Capturas del dashboard (antes)

![Dashboard SonarQube](docs/post1_before.png)