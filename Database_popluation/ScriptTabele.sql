CREATE DATABASE  IF NOT EXISTS `cantina` 
USE `cantina`;

-- Table structure for table `administrator`
--

DROP TABLE IF EXISTS `administrator`;

CREATE TABLE `administrator` (
  `id` varchar(255) NOT NULL,
  `firstName` varchar(255) NOT NULL,
  `lastName` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_esogmqxeek1uwdyhxvubme3qf` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


--
-- Dumping data for table `administrator`
--

LOCK TABLES `administrator` WRITE;

UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;

CREATE TABLE `customer` (
  `id` varchar(255) NOT NULL,
  `emailAddress` varchar(255) NOT NULL,
  `firstName` varchar(255) NOT NULL,
  `lastName` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `phoneNumber` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_irnrrncatp2fvw52vp45j7rlw` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;

UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;

CREATE TABLE `product` (
  `id` varchar(255) NOT NULL,
  `category` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `fastingItem` bit(1) NOT NULL,
  `imageURL` longblob,
  `name` varchar(255) NOT NULL,
  `price` double NOT NULL,
  `quantity` int NOT NULL,
  `weight` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;

INSERT INTO `product` VALUES ('3e4d089e-2b87-11eb-a2a2-f8b46ab33ec8','Ciorba','Carne de porc, orez, morcov, ceapa, telina, pastarnac, patrunjel, ardei, smantana, ulei, condimente.',_binary '\0',NULL,'Ciorba ardeleneasca',2.8,300,400),('3e4d9bf7-2b87-11eb-a2a2-f8b46ab33ec8','Ciorba','Smantana, burta de vita, rasol de vita, pastarnac, telina, oua, morcov, ceapa, otet.',_binary '\0',NULL,'Ciorba burta',7,300,400),('3e4dfb0f-2b87-11eb-a2a2-f8b46ab33ec8','Ciorba','Conopida, morcov, telina, usturoi, ulei, marar, iaurt , ou , condimente.',_binary '\0',NULL,'Ciorba Conopida',1.8,300,400),('3e4e65c6-2b87-11eb-a2a2-f8b46ab33ec8','Ciorba','Fasole boabe, morcov, patrunjel, telina, ceapa, ardei gras, condimente',_binary '',NULL,'Ciorba fasole boabe',2,300,400),('3e4ed8ce-2b87-11eb-a2a2-f8b46ab33ec8','Ciorba','Fasole verde taiata, morcov, telina, ardei rosu, ulei rosii, usturoi, verdeata, condimente.',_binary '',NULL,'Ciorba fasole verde',2.1,300,400),('3e4f2cd7-2b87-11eb-a2a2-f8b46ab33ec8','Ciorba','Ceapa, morcov, telina, ardei, cartofi, rosii, fasole verde taiata, ulei, verdeata, condimente',_binary '',NULL,'Ciorba legume',1.8,300,400),('3e4f828b-2b87-11eb-a2a2-f8b46ab33ec8','Ciorba','Varza, morcov, telina, ceapa, ulei, ardei, condimente',_binary '',NULL,'Ciorba varza',1.7,300,400),('3e4fdefb-2b87-11eb-a2a2-f8b46ab33ec8','Ciorba','Fidea, ceapa, morcov, ardei capia, fidea, pastarnac, ulei , patrunjel, condimente.',_binary '',NULL,'Supa fidea',1.4,300,400),('3e503cb4-2b87-11eb-a2a2-f8b46ab33ec8','Ciorba','Paste fainoase, morcov, ceapa, ulei, pastarnac, telina, legume uscate, patrunjel, condimente.',_binary '',NULL,'Supa paste fainoase',1.5,300,400),('3e509984-2b87-11eb-a2a2-f8b46ab33ec8','Desert','Lapte, zahar, ulei, oua, amoniac, faina, sare, unt, lamiae, esenta vanilie.',_binary '\0',NULL,'Alba-Ca-Zapada',2.5,50,120),('3e50fc02-2b87-11eb-a2a2-f8b46ab33ec8','Desert','Aluat foietaj, lapte, oua, zahar tos, amidon, zahar pudra vanilat, sare, zahar vanilat.',_binary '\0',NULL,'Cremes',3,50,135),('3e5160d1-2b87-11eb-a2a2-f8b46ab33ec8','Desert','Faina,lapte,unt,oua,sare,zahar,pastaie de vanilie, fiscal lichida.',_binary '\0',NULL,'Ecler',2.5,50,90),('3e51bff8-2b87-11eb-a2a2-f8b46ab33ec8','Desert','Lapte, taietei lati, oua, zahar, zahar vanilat burbon, stafide, aluat foietaj, branza de vaca, coaja de lamiae, sare.',_binary '\0',NULL,'Vargabeles',3.35,50,300),('3e52141f-2b87-11eb-a2a2-f8b46ab33ec8','Desert','Oua, zahar pudra, faina, unt topit, sare, cacao, unt 80% grasime, zahar vanilat, cacao, zahar tos.',_binary '\0',NULL,'Tort Dobos',2.5,50,120),('3e526a0f-2b87-11eb-a2a2-f8b46ab33ec8','Fel Principal',NULL,_binary '\0',NULL,'Carnat',2.5,45,100),('3e52c753-2b87-11eb-a2a2-f8b46ab33ec8','Fel Principal','Felii de cas uscat, oua, faina, pesmet, sare piper, ulei.',_binary '\0',NULL,'Cas-pane',3,45,110),('3e53245a-2b87-11eb-a2a2-f8b46ab33ec8','Fel Principal','Carne tocata, miez paine alba, usturoi, ou, sare, piper, faina, patrunjel verde, ulei, bulion de rosii, ceapa, condimente. ',_binary '\0',NULL,'Chiftelute marinate',2.65,40,170),('3e538ce8-2b87-11eb-a2a2-f8b46ab33ec8','Fel Principal','Piept de pui dezosat, sunca presata, cascaval, oua, faina, sare, piper, ulei.',_binary '\0',NULL,'Cordon bleu',5.35,40,135),('3e53e91f-2b87-11eb-a2a2-f8b46ab33ec8','Fel Principal',NULL,_binary '\0',NULL,'Cotlet porc',4.9,70,120),('3e54435b-2b87-11eb-a2a2-f8b46ab33ec8','Fel Principal','Ficatei de pui, ceapa, ulei, patrunjel verde, sare, piper.',_binary '\0',NULL,'Ficatei',1.3,50,110),('3e549771-2b87-11eb-a2a2-f8b46ab33ec8','Fel Principal','Piept de pui, ceapa rosie , ardei rosu, ardei verde, dovlecel.',_binary '\0',NULL,'Frigarui pui',3.7,90,90),('3e54f05c-2b87-11eb-a2a2-f8b46ab33ec8','Fel Principal','Carne de porc, rozmarin, boia dulce, cimbru, sare, ulei, ceapa, ardei rosu, foi dafin, usturoi.',_binary '\0',NULL,'Friptura porc',2.9,80,120),('3e557225-2b87-11eb-a2a2-f8b46ab33ec8','Fel Principal','Branza nesarata, lapte, paste, iaurt grecesc, sare, spanac.',_binary '\0',NULL,'Macaroane branza',2,40,250),('3e5612eb-2b87-11eb-a2a2-f8b46ab33ec8','Fel Principal',NULL,_binary '\0',NULL,'Mici',1.5,120,50),('3e567ac3-2b87-11eb-a2a2-f8b46ab33ec8','Fel Principal',NULL,_binary '\0',NULL,'Oua ochiuri',1.5,75,80),('3e56d819-2b87-11eb-a2a2-f8b46ab33ec8','Fel Principal',NULL,_binary '\0',NULL,'Pastrav prajit',5.7,60,100),('3e572f7f-2b87-11eb-a2a2-f8b46ab33ec8','Fel Principal',NULL,_binary '\0',NULL,'Piept de pui la gratar',4.1,85,100),('3e5788da-2b87-11eb-a2a2-f8b46ab33ec8','Fel Principal',NULL,_binary '\0',NULL,'Pulpa pui',3,70,100),('3e57e405-2b87-11eb-a2a2-f8b46ab33ec8','Fel Principal','Piept de pui, faina, pesmet, oua, lapte, sare, ulei, condimente.',_binary '\0',NULL,'Snitel pui',3.5,140,140),('3e58447d-2b87-11eb-a2a2-f8b46ab33ec8','Fel Principal','Piept de pui, oua, faina, sare, piper, nucsoara, pesmet, bacon, cascaval, ulei.',_binary '\0',NULL,'Snitel pui elvetian',3.4,150,160),('3e58aadb-2b87-11eb-a2a2-f8b46ab33ec8','Fel Principal','Varza murata, carne toccata de porc, ceapa rosie, orez, paprika dulce, cimbru, bulion de rosii, kaizer, sare, piper. ',_binary '\0',NULL,'Varza a la Cluj',3.4,100,250),('3e59207d-2b87-11eb-a2a2-f8b46ab33ec8','Garnitura',NULL,_binary '',NULL,'Cartofi natur',1.25,60,250),('3e597a11-2b87-11eb-a2a2-f8b46ab33ec8','Garnitura',NULL,_binary '',NULL,'Cartofi prajiti',2,75,200),('3e59d2e6-2b87-11eb-a2a2-f8b46ab33ec8','Garnitura',NULL,_binary '',NULL,'Cartofi taranesti',1.4,65,250),('3e5a2aef-2b87-11eb-a2a2-f8b46ab33ec8','Garnitura',NULL,_binary '',NULL,'Cous-cous',1.2,40,200),('3e5a840b-2b87-11eb-a2a2-f8b46ab33ec8','Garnitura',NULL,_binary '',NULL,'Legume mexicane',1.4,40,150),('3e5afb24-2b87-11eb-a2a2-f8b46ab33ec8','Garnitura',NULL,_binary '',NULL,'Mamaliga',0.3,60,200),('3e5b73a1-2b87-11eb-a2a2-f8b46ab33ec8','Garnitura',NULL,_binary '\0',NULL,'Mamaliga cu branza',1.5,55,250),('3e5bd642-2b87-11eb-a2a2-f8b46ab33ec8','Garnitura',NULL,_binary '',NULL,'Mazare',2.5,50,200),('3e5c3929-2b87-11eb-a2a2-f8b46ab33ec8','Garnitura',NULL,_binary '',NULL,'Orez',1,75,200),('3e5c943c-2b87-11eb-a2a2-f8b46ab33ec8','Garnitura',NULL,_binary '\0',NULL,'Piure',1.5,80,250),('3e5cf903-2b87-11eb-a2a2-f8b46ab33ec8','Garnitura','Spanac proaspat, faina, usturoi, ulei de floarea soarelui, suc de lamiae proaspata, sare.',_binary '',NULL,'Spanac',1.5,50,200),('3e5d57dc-2b87-11eb-a2a2-f8b46ab33ec8','Salata',NULL,_binary '',NULL,'Castraveti murati',0.9,75,100),('3e5dc57d-2b87-11eb-a2a2-f8b46ab33ec8','Salata',NULL,_binary '',NULL,'Salata conopida',0.8,70,150),('3e5e30e7-2b87-11eb-a2a2-f8b46ab33ec8','Salata',NULL,_binary '',NULL,'Salata gogosari',0.9,75,100),('3e5ea311-2b87-11eb-a2a2-f8b46ab33ec8','Salata',NULL,_binary '',NULL,'Salata sfecla rosie',1.2,50,150),('3e5f040f-2b87-11eb-a2a2-f8b46ab33ec8','Salata',NULL,_binary '',NULL,'Salata varza',1,100,150),('3e5f703f-2b87-11eb-a2a2-f8b46ab33ec8','Altele',NULL,_binary '',NULL,'Ardei iute',0.5,140,25),('3e5fd58c-2b87-11eb-a2a2-f8b46ab33ec8','Altele',NULL,_binary '\0',NULL,'Mujdei usturoi',0.8,120,50),('3e603cfb-2b87-11eb-a2a2-f8b46ab33ec8','Altele',NULL,_binary '',NULL,'Mustar',0.2,110,25),('3e60a26b-2b87-11eb-a2a2-f8b46ab33ec8','Altele',NULL,_binary '',NULL,'Paine',0.54,600,100),('3e60fb9d-2b87-11eb-a2a2-f8b46ab33ec8','Drink',NULL,_binary '',NULL,'Apa minerala',2.2,250,500),('3e6148af-2b87-11eb-a2a2-f8b46ab33ec8','Drink',NULL,_binary '',NULL,'Apa plata',2.2,240,500),('3e619e35-2b87-11eb-a2a2-f8b46ab33ec8','Drink',NULL,_binary '',NULL,'Mirinda portocale',4.5,200,500),('3e61f38f-2b87-11eb-a2a2-f8b46ab33ec8','Drink',NULL,_binary '',NULL,'Pepsi',3.5,200,500),('3e6249b2-2b87-11eb-a2a2-f8b46ab33ec8','Drink',NULL,_binary '',NULL,'Prigat lamaie',3.8,200,500);

UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;


CREATE TABLE `user` (
  `id` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;

UNLOCK TABLES;


-- Dump completed on 2020-11-21  1:42:17
