#!/bin/bash

if [ $# -eq 0 ]
  then
    echo "No arguments supplied"
    echo "Run test scenarios"
    mvn clean test
else
    echo "Run test scenarios with JVM arguments"
    mvn clean test $1
fi

