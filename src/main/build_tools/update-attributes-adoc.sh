#!/bin/env bash
set -eu

function _identify_target_artifactId() {
  grep -E '<artifactId>.*</artifactId>' pom.xml | head --lines=1 | sed 's/artifactId//g' |sed 's![ \t</>]*!!g'
}

function _identify_target_version() {
  grep -E '<version>.*</version>' pom.xml | head --lines=1 | sed 's/version//g' |sed 's![ \t</>]*!!g'
}

function update_attributes_adoc() {
  printf ":%s-version: %s" "$(_identify_target_artifactId)" "$(_identify_target_version)" > "src/site/asciidoc/attributes.adoc"
}

update_attributes_adoc

