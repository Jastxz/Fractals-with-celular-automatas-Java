#!/bin/bash

# Generar el JAR con shadowJar
echo "Generando JAR con todas las dependencias..."
./gradlew shadowJar

# Verificar que el JAR se generó correctamente
if [ ! -f "build/libs/FractalsCellularAutomata-1.0.0.jar" ]; then
    echo "Error: No se pudo generar el JAR. Verifique los errores anteriores."
    exit 1
fi

# Encontrar la ruta de la instalación de Java
JAVA_PATH=$(dirname $(dirname $(readlink -f $(which java))))
echo "Usando Java desde: $JAVA_PATH"

# Crear directorio para el resultado
mkdir -p dist

# Ejecutar jpackage
echo "Generando ejecutable Debian con jpackage..."
jpackage \
  --input build/libs \
  --dest dist \
  --main-jar FractalsCellularAutomata-1.0.0.jar \
  --main-class com.jastxz.fractals.MainInteraction \
  --name "Fractals Cellular Automata" \
  --app-version 1.0.0 \
  --vendor "Jastxz" \
  --description "Fractal generator using cellular automata" \
  --icon resources/favicon.ico \
  --runtime-image $JAVA_PATH

# Verificar que el ejecutable se generó correctamente
if [ $? -eq 0 ]; then
    echo "¡Ejecutable generado con éxito!"
    echo "El archivo se encuentra en: dist/Fractals Cellular Automata-1.0.0.deb"
else
    echo "Error al generar el ejecutable. Verifique los errores anteriores."
    exit 1
fi
