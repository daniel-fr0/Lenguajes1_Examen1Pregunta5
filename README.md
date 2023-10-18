# Simulación de programas, intérpretes y traductores

Este proyecto es un simulador de definición de programas, interpretadores y traductores. Su objetivo es comprobar si se pueden ejecutar programas de acuerdo a los traductores e interpretes que se definan. Inicialmente, la máquina solo es capaz de ejecutar programas escritos en el lenguaje `LOCAL`. Sin embargo, se pueden definir traductores e interpretes para otros lenguajes.

## Estructura del Proyecto

Este es un proyecto de Java construido con Maven que incluye un programa principal y una suite de pruebas. Asegúrate de seguir las instrucciones a continuación para ejecutar el programa y las pruebas, así como para instalar las dependencias necesarias.


## Requisitos Previos

Asegúrate de tener instalados los siguientes requisitos previos en tu sistema:

1. Java 11: Este proyecto requiere Java en su versión 11. Puedes verificar tu versión de Java ejecutando `java -version`. Si no tienes Java 11 instalado, sigue los pasos a continuación para instalarlo:

   - **En Ubuntu/Debian:**
     ```shell
     sudo apt update
     sudo apt install openjdk-11-jdk
     ```

   - **En macOS con Homebrew:**
     ```shell
     brew tap adoptopenjdk/openjdk
     brew cask install adoptopenjdk11
     ```

   - **En Windows:** Descarga el instalador desde [AdoptOpenJDK](https://adoptopenjdk.net/) o [Oracle](https://www.oracle.com/java/technologies/javase-downloads.html).

2. Maven: Asegúrate de tener Maven instalado. Puedes verificarlo ejecutando `mvn -version`. Si no está instalado, sigue los pasos a continuación para instalarlo:

   - **En Ubuntu/Debian:**
     ```shell
     sudo apt update
     sudo apt install maven
     ```

   - **En macOS con Homebrew:**
     ```shell
     brew install maven
     ```

   - **En Windows:** Descarga el instalador desde [el sitio web de Apache Maven](https://maven.apache.org/download.cgi).

## Ejecución del Programa Principal

Para ejecutar el programa principal simplemente se debe ejecutar el script `computadora` en la terminal:

```shell
./computadora
```

## Ejecución de las Pruebas

Para ejecutar las pruebas simplemente se debe ejecutar el script `runTests` en la terminal:

```shell
./runTests
```

El script tambien cuenta con la opción `clean` para limpiar los archivos generados por las pruebas:
```shell
./runTests clean
```