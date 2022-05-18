-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : mer. 18 mai 2022 à 07:26
-- Version du serveur :  5.7.31
-- Version de PHP : 7.3.21

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `dblouervoiture`
--
CREATE DATABASE IF NOT EXISTS `dblouervoiture` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `dblouervoiture`;

-- --------------------------------------------------------

--
-- Structure de la table `client`
--

DROP TABLE IF EXISTS `client`;
CREATE TABLE IF NOT EXISTS `client` (
  `idClient` int(6) NOT NULL AUTO_INCREMENT,
  `nomClient` varchar(50) NOT NULL,
  `prenomClient` varchar(50) NOT NULL,
  `mdpClient` varchar(8) NOT NULL,
  `pseudo` varchar(50) NOT NULL,
  `telClient` varchar(8) NOT NULL,
  `adresseClient` varchar(90) NOT NULL,
  PRIMARY KEY (`idClient`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `client`
--

INSERT INTO `client` (`idClient`, `nomClient`, `prenomClient`, `mdpClient`, `pseudo`, `telClient`, `adresseClient`) VALUES
(5, 'admin', 'admin', 'mdpAdmin', 'admin', '00000000', 'adresse Admin\r\n'),
(13, 'nom1', 'prenom1', 'mdp1', 'pseudo1', '11111111', 'adresse1'),
(14, 'nom2', 'prenom2', 'mdp2', 'pseudo2', '22222222', 'adresse2');

-- --------------------------------------------------------

--
-- Structure de la table `location`
--

DROP TABLE IF EXISTS `location`;
CREATE TABLE IF NOT EXISTS `location` (
  `idLocation` int(11) NOT NULL AUTO_INCREMENT,
  `idVoiture` int(11) NOT NULL,
  `idClient` int(6) NOT NULL,
  `date_locat` date DEFAULT NULL,
  `duree_prev` smallint(6) NOT NULL,
  `total_locat` decimal(12,2) NOT NULL,
  PRIMARY KEY (`idLocation`),
  KEY `fk_LocationVoiture` (`idVoiture`),
  KEY `fk_LocationClient` (`idClient`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `location`
--

INSERT INTO `location` (`idLocation`, `idVoiture`, `idClient`, `date_locat`, `duree_prev`, `total_locat`) VALUES
(25, 12, 14, '2022-05-10', 4, '55.22'),
(26, 7, 13, '2022-05-17', 3, '120.00'),
(28, 2, 13, '2022-05-16', 1, '555.00'),
(29, 12, 14, '2022-05-19', 2, '70000.00'),
(30, 7, 14, '2022-05-20', 2, '190000.00');

-- --------------------------------------------------------

--
-- Structure de la table `voiture`
--

DROP TABLE IF EXISTS `voiture`;
CREATE TABLE IF NOT EXISTS `voiture` (
  `idVoiture` int(11) NOT NULL AUTO_INCREMENT,
  `matricule` varchar(30) NOT NULL,
  `nom` varchar(60) NOT NULL,
  `prix_locat` decimal(12,2) DEFAULT NULL,
  `disponible` varchar(10) NOT NULL,
  PRIMARY KEY (`idVoiture`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `voiture`
--

INSERT INTO `voiture` (`idVoiture`, `matricule`, `nom`, `prix_locat`, `disponible`) VALUES
(1, 'BS345', 'BUS', '95000.00', 'oui'),
(2, 'BS346', 'BUS', '95000.00', 'oui'),
(3, 'BU671', 'BUGGATI L9', '115000.00', 'oui'),
(4, 'FR890', 'FERRARI ENZO', '155000.00', 'oui'),
(5, 'HI678', 'HILUX 2C', '45000.00', 'non'),
(6, 'HU323', 'HUMMER A5', '90000.00', 'oui'),
(7, 'LM234', 'LIMOUSINE B4', '95000.00', 'non'),
(8, 'MT005', 'MINIBUSss', '50000.00', 'oui'),
(9, 'MT006', 'MINIBUS', '50000.00', 'oui'),
(10, 'MT00777', 'MINIBUS', '50000.00', 'non'),
(11, 'TX441', 'TAXI', '35000.00', 'oui'),
(12, 'TX442', 'TAXI', '35000.00', 'oui'),
(13, 'TX444', 'TAXI', '35000.00', 'oui'),
(32, 'PP765', 'MAX5m', '90.00', 'oui'),
(33, 'JJ50M', 'NJ5lo', '450.00', 'oui');

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `location`
--
ALTER TABLE `location`
  ADD CONSTRAINT `fk_LocationClient` FOREIGN KEY (`idClient`) REFERENCES `client` (`idClient`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_LocationVoiture` FOREIGN KEY (`idVoiture`) REFERENCES `voiture` (`idVoiture`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
