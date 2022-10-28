CREATE TABLE treatment(id SERIAL PRIMARY KEY, national_id VARCHAR, date_of_admission DATE, disease_name VARCHAR, treatment_administered VARCHAR);

CREATE TABLE patients (id serial PRIMARY KEY,name varchar,national_id varchar,date_of_birth varchar);

CREATE TABLE hospitals(id serial PRIMARY KEY, name VARCHAR, location VARCHAR, hospitallevel VARCHAR);