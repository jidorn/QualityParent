drop database socialquality;
create database socialquality;
use socialquality;
CREATE TABLE personne (
	id int(10) auto_increment NOT NULL PRIMARY KEY,
	nom VARCHAR(50) NOT NULL,
	prenom VARCHAR(50) NOT NULL,
	mail VARCHAR(150) NOT NULL,
	mdp VARCHAR(20) NOT NULL,
	date_naissance DATETIME NOT NULL
);
INSERT INTO personne VALUES
(1,'toto','toto','toto@afcepf.fr','toto','2012-12-21'),
(2,'toto2','toto2','toto2@afcepf.fr','toto2','2012-12-21'),
(3,'toto3','toto3','toto3@afcepf.fr','toto3','2012-12-21'),
(4,'admin','admin','admin@afcepf.fr','admin','2012-12-21'),
(5,'user','user','user@afcepf.fr','user','2012-12-21');
CREATE TABLE message (
	id int(10) auto_increment NOT NULL PRIMARY KEY,
	date_creation DATETIME NOT NULL,
	contenu TEXT NOT NULL,
	id_destinataire int(10) NOT NULL,
	id_expediteur int(10) NOT NULL,
	KEY FK_Destinataire (id_destinataire),
	KEY FK_Expediteur (id_expediteur),
	CONSTRAINT FK_Destinataire FOREIGN KEY (id_destinataire)
			   REFERENCES personne (id),
	CONSTRAINT FK_Expediteur FOREIGN KEY (id_expediteur)
			   REFERENCES personne (id)
);
INSERT INTO message VALUES
(1,'2016-02-17 16:35:00','bla bla bla',1,2),
(2,'2016-02-17 16:35:00','bla bla bla',2,1),
(3,'2016-02-17 16:35:00','bla bla bla',3,4),
(4,'2016-02-17 16:35:00','bla bla bla',4,5),
(5,'2016-02-17 16:35:00','bla bla bla',5,4),
(6,'2016-02-17 16:35:00','car',5,1);
