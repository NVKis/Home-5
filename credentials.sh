#!/bin/sh

echo "Введите логин: "
read login

echo "Введите пароль: "
read password

echo "login=$login" > src/main/resources/application.properties
echo "password=$password" >> src/main/resources/application.properties

mvn clean test