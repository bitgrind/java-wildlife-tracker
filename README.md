## Wildlife Tracker

An app for the forest service to track animals for an environmental impact study.

### Description

The Forest Service is considering a proposal from a timber company to clearcut a nearby forest of Douglas Fir. Before this proposal may be approved, they must complete an environmental impact study. This application was developed to allow Rangers to track wildlife sightings in the area.

This is designed to be easy to use and intuitive. To help develop the Ranger team as well as help track current levels of wild life and status of wildlife.

### Setup

To create the necessary databases, launch postgres, then psql, and run the following commands:

* `CREATE DATABASE wildlife_tracker;`
* `\c wildlife_tracker;`
* `CREATE TABLE animals (id serial PRIMARY KEY, name varchar);`
* `CREATE TABLE endangered_animals (id serial PRIMARY KEY, name varchar, health varchar, age varchar);`
* `CREATE TABLE sightings (id serial PRIMARY KEY, animal_name varchar, location_name varchar, ranger_name varchar, date_sighted varchar);`
* `CREATE TABLE rangers (id serial PRIMARY KEY, name varchar, employer varchar);`
* `CREATE TABLE locations (id serial PRIMARY KEY, name varchar);`
* `CREATE DATABASE wildlife_tracker_test WITH TEMPLATE wildlife_tracker;`

### License

Copyright (c) 2017 **_MIT License_**
