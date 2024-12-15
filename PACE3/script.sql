DROP DATABASE IF EXISTS BussejadorsDB;
CREATE DATABASE IF NOT EXISTS BussejadorsDB;
USE BussejadorsDB;

CREATE TABLE LlocDeBusseig (
id_lloc INT AUTO_INCREMENT PRIMARY KEY,
nom VARCHAR(100),
ubicacio VARCHAR(100)
);

CREATE TABLE Equip (
id_equip INT AUTO_INCREMENT PRIMARY KEY,
tipus VARCHAR(100)
);

CREATE TABLE Master (
id_master INT AUTO_INCREMENT PRIMARY KEY,
nom VARCHAR(100),
especialitat VARCHAR(100)
);

CREATE TABLE Bussejador (
id_bussejador INT AUTO_INCREMENT PRIMARY KEY,
nom VARCHAR(100),
nivell VARCHAR(50),
equip_id INT,
mentor_id INT,
master_id INT,
FOREIGN KEY (equip_id) REFERENCES Equip(id_equip),
FOREIGN KEY (mentor_id) REFERENCES Bussejador(id_bussejador),
FOREIGN KEY (master_id) REFERENCES Master(id_master)
);

CREATE TABLE Visita (
id_visita INT AUTO_INCREMENT PRIMARY KEY,
bussejador_id INT,
lloc_id INT,
data DATE,
FOREIGN KEY (bussejador_id) REFERENCES Bussejador(id_bussejador),
FOREIGN KEY (lloc_id) REFERENCES LlocDeBusseig(id_lloc)
);

INSERT INTO LlocDeBusseig (nom, ubicacio) VALUES
('Cova del Dimoni', 'Costa Brava'),
('Barra del Cap de Sant Antoni', 'Alacant'),
('Reserva Marina de Tabarca', 'Alacant'),
('Vaixell Enfonsat', 'Mallorca'),
('Escull Coral·lí', 'Illes Medes');

INSERT INTO Equip (tipus) VALUES
('Equip Professional'),
('Equip Principal'),
('Equip Avançat'),
('Equip Intermedi'),
('Equip Bàsic'),
('Equip Aprenent');

INSERT INTO Master (nom, especialitat) VALUES
('Instructor Avançat', 'Immersió profunda'),
('Fotògraf Expert', 'Fotografia submarina'),
('Guia Certificat', 'Busseig nocturn');

INSERT INTO Bussejador (nom, nivell, equip_id, mentor_id, master_id) VALUES
('Laura', 'Avançat', 3, NULL, 3),
('Pau', 'Avançat', 2, NULL, 1);

INSERT INTO Bussejador (nom, nivell, equip_id, mentor_id, master_id) VALUES
('Anna', 'Principiant', 6, 1, 1),
('Carles', 'Intermedi', 4, 1, 2),
('Marc', 'Principiant', 5, 1, 1),
('Joana', 'Intermedi', null , 2, 2);

INSERT INTO Visita (bussejador_id, lloc_id) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5),
(6, 1),
(5, 3);
