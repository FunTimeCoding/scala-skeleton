#!/bin/sh -e

mkdir -p build/class
scalac -d build/class src/ScalaSkeleton.scala
./run-style-check.sh --ci-mode
