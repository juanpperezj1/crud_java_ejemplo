DROP DATABASE FAQS;
CREATE DATABASE FAQS;
USE FAQS;


	CREATE TABLE faqs(
		id  				int(3) NOT NULL AUTO_INCREMENT,
		pregunta 		varchar(400) NOT NULL,
		respuesta 			varchar(400) NOT NULL,
		PRIMARY KEY (id)
    );

     
     
     
