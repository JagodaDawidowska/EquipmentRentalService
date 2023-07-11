# EquipmentRentalService
backend service for my engineers thesis


## Table of Contents
* [General Info](#general-information)
* [Technologies Used](#technologies-used)
* [Features](#features)
* [Project Status](#project-status)
* [Structure](#structure)
* [Database](#database)
* [SWAGGER](#swagger)

## General Information
This project includes a REST API-based backend for a microservice for renting exercise equipment.
The service was developed using the SpringBoot framework, a modern solution for developing microservices.
This backend includes:
- Authentication and Authorization - the Spring Security Module and JWT Tokens, password decoding/encoding feature
- ORM - JPA specification, and Hibernate implementation for managing database activities and generating unique database requests
- modern project architecture
- controllers and HTTP request handling
- business logic to handle the functionalities of the application.
- dynamic documentation
- DTO models

## Technologies Used
- SpringBoot - serverside application for business
  logic, communication between client and database. 
- MySQL - database. 
- Language - Java
- JWT - Security
- SWAGGER - Dynamic documentation
- ORM - Hibernate
- Lombok - Libary to help eliminate boiler plate code in models classes

## Features
The functionalities the application fulfills: 
- Equipment rental and return by the user.
- Monitoring the availability and rentals of equipment.
- Generating rental histories for a specific user.
- Providing feedback to the rental administrator.
- Registering new users.
- Adding and removing equipment by the administrator.
- Displaying user information and their reviews.

## Project Status
Project is: In progress (Unit tests).

## Structure
![poprawionystuctSerwis drawio](https://github.com/JagodaDawidowska/EquipmentRentalService/assets/107955890/498e5b16-a65a-4487-b9c0-db0e8b1e722c)

## Database
![diagrambazy](https://github.com/JagodaDawidowska/EquipmentRentalService/assets/107955890/180a8c07-566c-460d-9966-45d90d8ae623)

## SWAGGER
![swagger](https://github.com/JagodaDawidowska/EquipmentRentalService/assets/107955890/eaa75b00-cf02-4cd6-8ae7-4414ea28cedf)




