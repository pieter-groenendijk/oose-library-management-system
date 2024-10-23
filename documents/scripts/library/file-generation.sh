#!/bin/bash

outputPath="${DOCUMENTS_DIRECTORY}/target/functional-design.md"
currentHeadingLevelPrefix=""

function setOutputPath() {
    local path="$1"

    outputPath="$path"
}

function empty() {
    true > "$outputPath"
}

function addNewLine() {
    printf "\n" >> "$outputPath"
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

        echo "$line" >> "$outputPath"
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

function generate() {
    local outputPath="$1"
    local generateFunctionName="$2"

    setOutputPath "$outputPath"
    empty

    "$generateFunctionName"
}