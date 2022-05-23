#!/bin/bash
export $(cat .env | xargs)
java -jar build/libs/UnitProject.jar
