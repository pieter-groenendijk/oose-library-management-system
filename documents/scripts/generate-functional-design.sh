#!/bin/bash

cd "$(dirname "$(realpath "$0")")" || exit

source "library/config.sh"

OUTPUT_PATH="${DOCUMENTS_DIRECTORY}/target/functional-design.md"
currentHeadingLevelPrefix=""

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

function incrementHeadingLevelPrefix() {
    currentHeadingLevelPrefix="${currentHeadingLevelPrefix}#"
}

function decrementHeadingLevelPrefix() {
    currentHeadingLevelPrefix="${currentHeadingLevelPrefix:1}"
}

function maybeAddHeadingPrefix() {
    local line="$1"

    if [[ "$line" =~ ^#+ ]]; then
        line="${currentHeadingLevelPrefix}${line}"
    fi

    echo "$line"
}

function add() {
    local filePath="$1"
    
    while IFS= read -r line; do 
        line=$(maybeAddHeadingPrefix "$line")

        echo "$line" >> "$OUTPUT_PATH"
    done < "$filePath" 

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


add "${DOCUMENTS_DIRECTORY}/functional-design-parts/header.md"
incrementHeadingLevelPrefix

add "${DOCUMENTS_DIRECTORY}/requirements.md"
add "${DOCUMENTS_DIRECTORY}/domain-model/readme.md"
add "${DOCUMENTS_DIRECTORY}/usecases/readme.md"
incrementHeadingLevelPrefix

addDirectory "${DOCUMENTS_DIRECTORY}/usecases/singles"
decrementHeadingLevelPrefix

decrementHeadingLevelPrefix

cd - || exit
