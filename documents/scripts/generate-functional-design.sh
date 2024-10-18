#!/bin/bash

cd "$(dirname "$(realpath "$0")")" || exit

source "library/config.sh"

OUTPUT_PATH="${DOCUMENTS_DIRECTORY}/target/functional-design.md"

# Documents that should be included, in order, are:
# 1. Requirements
# 2. Domain Model
# 3. Usecase diagrams
# 4. Fully dressed usecases
# 5. System Sequence diagrams

function empty() {
    true > "$OUTPUT_PATH"
}

function addNewLine() {
    printf "\n" >> "$OUTPUT_PATH"
}

function add() {
    local filePath="$1"
    
    cat "$filePath" >> "$OUTPUT_PATH"
    addNewLine
    addNewLine
}

function addDirectory() {
    local directoryPath="$1"

    for file in "$directoryPath"/*; do
        add "$file"
    done 
}

empty

add "${DOCUMENTS_DIRECTORY}/requirements.md"
add "${DOCUMENTS_DIRECTORY}/domain-model/readme.md"
add "${DOCUMENTS_DIRECTORY}/usecases/readme.md"
addDirectory "${DOCUMENTS_DIRECTORY}/usecases/singles"


cd - || exit
