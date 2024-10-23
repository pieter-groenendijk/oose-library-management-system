#!/bin/bash

cd "$(dirname "$(realpath "$0")")" || exit


source "library/config.sh"
source "library/file-generation.sh"

function generateFunctionalDesign() {
    add "${DOCUMENTS_DIRECTORY}/functional-design-parts/header.md"
    incrementHeadingLevelPrefix

    add "${DOCUMENTS_DIRECTORY}/requirements.md"
    add "${DOCUMENTS_DIRECTORY}/domain-model/readme.md"
    add "${DOCUMENTS_DIRECTORY}/usecases/readme.md"
    incrementHeadingLevelPrefix

    addDirectory "${DOCUMENTS_DIRECTORY}/usecases/singles"
    decrementHeadingLevelPrefix

    decrementHeadingLevelPrefix
}

generate "${DOCUMENTS_DIRECTORY}/target/functional-design.md" generateFunctionalDesign


cd - || exit
