<p align="center">
  <a href="README.md">English</a> | <strong>Español</strong>
</p>

# Algorithms Java Mastery

Proyecto académico en **Java 21** que conecta algoritmos y estructuras de datos
con especificaciones formales, razonamiento de correctitud, análisis de
complejidad, pruebas automatizadas y experimentos de rendimiento seleccionados.
Desarrolla fundamentos útiles para backend mediante ejemplos ejecutables y
documentados.

La referencia académica principal es *Introduction to Algorithms* (CLRS).
Las explicaciones, los contratos y las implementaciones se desarrollan para
este repositorio; cada revisión identifica las fuentes y las ediciones consultadas.

## Alcance y evidencia actuales

La secuencia de implementaciones seleccionadas y la revisión ordenada de la
documentación hasta **19 — CI/CD** están completas dentro del alcance registrado.
El [cierre de trazabilidad](docs/00-project/12-closing-traceability-review.md)
reconcilia las diecinueve revisiones integradas, el código actual y la evidencia de CI.

Estado verificado: **2026-10-29**, en `main` con commit `8d98012`.

| Evidencia | Estado verificado |
|---|---|
| Código Java de producción | 100 archivos en 13 paquetes de algoritmos |
| Suite normal / con cobertura | 710 ejecuciones de pruebas JUnit |
| Suite del perfil benchmark | 723 ejecuciones, incluidas 13 pruebas de fixtures |
| Contratos de shell en Linux | 20 casos independientes de JUnit |
| Registro de JMH | 115 métodos en 40 clases |
| Combinaciones predeterminadas de parámetros | 803 casos de configuración; inventario, no 803 resultados medidos |
| Verificación en GitHub | Ambos jobs aprobados en la [ejecución 40880667469](https://github.com/Ana-Alvarez-dev/Algorithms-Java-Mastery/actions/runs/40880667469) |

Repetir un build no aumenta el número de pruebas distintas. Las pruebas, la
cobertura, los argumentos matemáticos y los tiempos aportan evidencias diferentes;
completar esta revisión no certifica universalmente la correctitud del proyecto.

## Método de estudio e ingeniería

1. Definir el problema, las entradas y salidas, las precondiciones y las postcondiciones.
2. Identificar límites válidos, rechazos y reglas de modificación y propiedad de datos.
3. Diseñar la estrategia y escribir pseudocódigo independiente del lenguaje.
4. Trazar manualmente casos representativos.
5. Argumentar correctitud y terminación mediante invariantes o inducción.
6. Analizar tiempo, espacio auxiliar, costes de salida y supuestos del modelo.
7. Implementar en Java y probar el contrato observable con JUnit y AssertJ.
8. Incorporar JMH cuando exista una pregunta empírica justificada; conservar configuración e incertidumbre.
9. Enlazar código, pruebas, experimentos y conclusiones técnicas.

Consultá la [metodología de estudio](docs/00-project/03-study-methodology.md) y la
[documentación del proyecto](docs/00-project/README.md).

## Módulos

La documentación técnica de los módulos está en inglés.

| Módulo | Contenido |
|---|---|
| [00 — Proyecto](docs/00-project/README.md) | Metodología, arquitectura, estándares y trazabilidad |
| [01 — Fundamentos](docs/01-foundations/README.md) | Especificaciones, correctitud, invariantes y terminación |
| [02 — Complejidad](docs/02-complexity/README.md) | O, Ω, Θ; tiempo, espacio y modelos de coste |
| [03 — Arrays](docs/03-arrays/README.md) | Acceso, recorrido, agregación, modificaciones y sumas prefijas |
| [04 — Búsqueda](docs/04-searching/README.md) | Búsqueda lineal y binaria, límites, rangos y valores más cercanos |
| [05 — Ordenamiento](docs/05-sorting/README.md) | Métodos elementales, merge sort y quicksort |
| [06 — Listas enlazadas](docs/06-linked-lists/README.md) | Estructuras simples, dobles y circulares |
| [07 — Pilas y colas](docs/07-stacks-and-queues/README.md) | Estructuras LIFO, FIFO y deque; aplicaciones |
| [08 — Tablas hash](docs/08-hash-tables/README.md) | Encadenamiento, sondeo, colisiones y aplicaciones |
| [09 — Árboles](docs/09-trees/README.md) | BST, AVL, min-heaps, recorridos y métricas |
| [10 — Grafos](docs/10-graphs/README.md) | Representaciones, BFS/DFS, ordenamiento, Dijkstra y Kruskal |
| [11 — Recursión](docs/11-recursion/README.md) | Contratos recursivos, aritmética exacta y referencias iterativas |
| [12 — Divide y vencerás](docs/12-divide-and-conquer/README.md) | Subarray máximo, inversiones y selección |
| [13 — Algoritmos voraces](docs/13-greedy-algorithms/README.md) | Actividades, partición de intervalos, Huffman y contraejemplos de cambio de monedas |
| [14 — Backtracking](docs/14-backtracking/README.md) | Permutaciones, combinaciones, N reinas y decisiones sobre subconjuntos |
| [15 — Programación dinámica](docs/15-dynamic-programming/README.md) | Fibonacci, mochila 0/1 y LCS sobre unidades UTF-16 |
| [16 — Testing](docs/16-testing/README.md) | Regresiones, oráculos independientes y cobertura |
| [17 — Benchmarking](docs/17-benchmarking/README.md) | Protocolos JMH, resultados registrados e interpretación |
| [18 — Linux](docs/18-linux-environment/README.md) | Entorno, Bash, permisos, procesos, Java, Maven y Git |
| [19 — CI/CD](docs/19-ci-cd/README.md) | Verificación en GitHub, preparación de paquetes e identidad de artefactos |

## Estructura del código y herramientas

| Ruta | Responsabilidad |
|---|---|
| `src/main/java` | Algoritmos, estructuras de datos y tipos auxiliares |
| `src/test/java` | Pruebas funcionales con JUnit y AssertJ |
| `src/jmh/java` | Benchmarks JMH y preparación de datos |
| `src/jmh-test/java` | Pruebas de fixtures activas solo con el perfil benchmark |
| `docs` | Especificaciones, pruebas de correctitud, análisis, revisiones e investigaciones |
| `scripts/linux` | Chequeo del entorno y pruebas de contratos de shell |
| `.github/workflows/maven-ci.yml` | Workflow de CI con dos jobs y Java 21 |

El [POM](pom.xml) configura Java release 21, JUnit Jupiter 5.12.2, AssertJ 3.27.3,
JMH 1.37 y JaCoCo 0.8.13 opcional. El wrapper selecciona **Maven 3.9.11**;
no hace falta instalar Maven globalmente. El primer uso puede descargar Maven
y dependencias. Seleccionar el runtime y definir el bytecode objetivo son
comprobaciones diferentes.

## Compilar y ejecutar pruebas

Usá un **JDK 21** instalado, con `java` y `javac`, y alineá JAVA_HOME y PATH.
Comenzá desde la raíz del repositorio. Ejecutá cada comando como punto de
verificación y detenete si falla. Windows sigue soportado; no necesitás WSL
local para utilizar la CI de GitHub.

### Windows PowerShell

```powershell
.\mvnw.cmd -version
.\mvnw.cmd -DfailIfNoTests=true clean verify
```

### Linux Bash

```bash
bash scripts/linux/check-environment.sh
bash scripts/linux/test-check-environment.sh
./mvnw -version
./mvnw -DfailIfNoTests=true clean verify
```

Maven debe mostrar Java 21. La suite normal tiene 710 ejecuciones en el estado
identificado. La [guía del entorno](docs/18-linux-environment/implementation-and-verification-guide.md)
detalla la configuración de Linux.

## Cobertura

El perfil opcional genera HTML y XML en `target/site/jacoco`, ignorado por Git.

```powershell
.\mvnw.cmd -DfailIfNoTests=true -Pcoverage clean verify
```

```bash
./mvnw -DfailIfNoTests=true -Pcoverage clean verify
```

Abrí `target/site/jacoco/index.html` después de ese build. La cobertura registra
código ejecutado; no demuestra la calidad de las aserciones ni todos los caminos
posibles. No hay un umbral porcentual configurado. Consultá la
[evidencia de Testing](docs/16-testing/verification-report.md).

## Preparación y descubrimiento de benchmarks

El perfil benchmark ejecuta 723 pruebas y crea `target/benchmarks.jar`.
Los comandos siguientes descubren un método y sus cinco tamaños; no lo miden.
JAVA_HOME debe seleccionar el JDK 21 deseado.

```powershell
.\mvnw.cmd -DfailIfNoTests=true -Pbenchmark clean package
& "$env:JAVA_HOME\bin\java.exe" -jar .\target\benchmarks.jar '^org\.anaalvarezdev\.algorithms\.arrays\.ArrayAggregationBenchmark\.sum$' -lp
```

```bash
./mvnw -DfailIfNoTests=true -Pbenchmark clean package
"$JAVA_HOME/bin/java" -jar target/benchmarks.jar '^org\.anaalvarezdev\.algorithms\.arrays\.ArrayAggregationBenchmark\.sum$' -lp
```

Seguí la [guía de benchmarks](docs/17-benchmarking/implementation-and-benchmark-guide.md)
para una ejecución controlada seleccionada, la captura de código y cambios
locales, el hash del JAR, la configuración efectiva y los diagnósticos separados.
Un `clean` posterior elimina los archivos generados antes, incluido el JAR.
Conservá la evidencia necesaria antes de limpiar y ejecutá la cobertura por
separado de las mediciones de rendimiento.

## CI y límites de la evidencia

[GitHub Actions](https://github.com/Ana-Alvarez-dev/Algorithms-Java-Mastery/actions/workflows/maven-ci.yml)
ejecuta primero pruebas y cobertura; luego prepara un paquete de benchmarks
sin instrumentación de cobertura, con procedencia y descubrimiento. Conserva
informes y paquetes de forma temporal. El [mapa del workflow](docs/19-ci-cd/workflow-contracts.md)
distingue los controles implementados de los recuentos observados, las subidas
de archivos y las reglas de protección del repositorio.

Las investigaciones conservan sus unidades, runtime, atribución del código e
incertidumbre originales. Algunas matrices cubren subconjuntos; las mediciones
antiguas de Arrays carecen de metadatos y tienen dos entradas que exceden el
contrato de suma en int. El registro de Sorting con JDK 25 permanece separado
de Java 21. El registro corregido de agregación conserva la ausencia del hash
del JAR original. Consultá el [índice de resultados](docs/17-benchmarking/README.md)
y los [límites del cierre](docs/00-project/12-closing-traceability-review.md).

## Asistencia de IA, referencias y cambios

La IA apoya explicaciones, revisiones, alternativas y escritura técnica.
Las fuentes académicas y la documentación oficial siguen siendo primarias;
el razonamiento y el código aceptados requieren comprensión y validación humana.
Las pruebas y las mediciones deben ejecutarse: una respuesta de IA no es
evidencia por sí misma. En trabajos evaluados se aplican las reglas de la materia.

Consultá la [bibliografía central](docs/00-project/10-references.md), los
[estándares de ingeniería](docs/00-project/08-engineering-standards.md) y el
[flujo de desarrollo](docs/00-project/09-development-workflow.md). Los cambios
se trabajan en ramas y PR concretos, con evidencia de contratos y pruebas,
preservando la historia experimental.

Consultá [CONTRIBUTING.md](CONTRIBUTING.md) para conocer los pasos de contribución
y los requisitos de verificación. [CHANGELOG.md](CHANGELOG.md) resume los cambios
relevantes y enlaza su historia y evidencia. Ambos documentos técnicos están en inglés.

## Política de idioma y autora

El inglés es el idioma principal del código y de la documentación técnica.
Este README y [README.md](README.md) ofrecen entradas equivalentes al proyecto;
actualizá ambos cuando cambien el alcance, los recuentos, los comandos o la
navegación compartidos. Los módulos y los identificadores Java conservan sus
nombres existentes en inglés.

**Ana María Alvarez** — estudiante de Ingeniería en Sistemas y Desarrollo de
Software, enfocada en backend con Java.

GitHub: [@Ana-Alvarez-dev](https://github.com/Ana-Alvarez-dev)
