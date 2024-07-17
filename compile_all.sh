#!/bin/bash

# Set the source directory
SRC_DIR="src/main/kotlin"

# List of all Kotlin files to compile
FILES=(
    "com/example/model/MC/Dijkstra.kt"
    "com/example/model/MC/main.kt"
    "com/example/model/MC/NodeStructure.kt"
    "com/example/model/MC/floor1.kt"
    "com/example/model/MC/floor2.kt"
    "com/example/model/MC/floor3.kt"
    "com/example/model/MC/floor4.kt"
    "com/example/model/MC/floor5.kt"
    "com/example/model/MC/floor6.kt"
)

# Compile all the Kotlin files
echo "Compiling Kotlin files..."
kotlinc ${FILES[@]/#/$SRC_DIR/} -include-runtime -d build/classes

if [ $? -eq 0 ]; then
    echo "Compilation successful."
else
    echo "Compilation failed."
    exit 1
fi
