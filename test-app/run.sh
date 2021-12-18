#!/bin/bash

make

mv ../target ./target

docker-compose build

docker-compose up