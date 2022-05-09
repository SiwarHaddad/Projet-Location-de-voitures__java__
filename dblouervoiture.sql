-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : lun. 09 mai 2022 à 04:12
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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `client`
--

INSERT INTO `client` (`idClient`, `nomClient`, `prenomClient`, `mdpClient`, `pseudo`, `telClient`, `adresseClient`) VALUES
(1, 'nom1', 'prenom1', 'mdp1', 'pseudo1', '00000000', 'adresse1'),
(3, 'aa', 'aa', 'aa', 'aa', 'aa', 'aa');

-- --------------------------------------------------------

--
-- Structure de la table `location`
--

DROP TABLE IF EXISTS `location`;
CREATE TABLE IF NOT EXISTS `location` (
  `numL` int(11) NOT NULL AUTO_INCREMENT,
  `matriculeV` varchar(30) NOT NULL,
  `idClient` varchar(30) NOT NULL,
  `date_locat` date DEFAULT NULL,
  `duree_prev` smallint(6) NOT NULL,
  PRIMARY KEY (`numL`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `location`
--

INSERT INTO `location` (`numL`, `matriculeV`, `idClient`, `date_locat`, `duree_prev`) VALUES
(3, 'TX441', '66010177', '2020-10-26', 4),
(4, 'MT005', '62991213', '2020-10-24', 2),
(5, 'BS346', '66559090', '2020-10-26', 3),
(7, 'BU671', '66050522', '2020-10-26', 1),
(11, 'MT007', '66171709', '2020-10-26', 1);

-- --------------------------------------------------------

--
-- Structure de la table `voiture`
--

DROP TABLE IF EXISTS `voiture`;
CREATE TABLE IF NOT EXISTS `voiture` (
  `idVoiture` int(11) NOT NULL AUTO_INCREMENT,
  `matricule` varchar(30) NOT NULL,
  `nom` varchar(60) NOT NULL,
  `prix_locat` decimal(9,2) DEFAULT NULL,
  `disponible` varchar(10) NOT NULL,
  PRIMARY KEY (`idVoiture`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `voiture`
--

INSERT INTO `voiture` (`idVoiture`, `matricule`, `nom`, `prix_locat`, `disponible`) VALUES
(1, 'BS345', 'BUS', '95000.00', 'oui'),
(2, 'BS346', 'BUS', '95000.00', 'non'),
(3, 'BU671', 'BUGGATI L9', '115000.00', 'non'),
(4, 'FR890', 'FERRARI ENZO', '155000.00', 'oui'),
(5, 'HI678', 'HILUX 2C', '45000.00', 'oui'),
(6, 'HU323', 'HUMMER A5', '90000.00', 'oui'),
(7, 'LM234', 'LIMOUSINE B4', '95000.00', 'oui'),
(8, 'MT005', 'MINIBUSss', '50000.00', 'non'),
(9, 'MT006', 'MINIBUS', '50000.00', 'oui'),
(10, 'MT00777', 'MINIBUS', '50000.00', 'non'),
(11, 'TX441', 'TAXI', '35000.00', 'non'),
(12, 'TX442', 'TAXI', '35000.00', 'oui'),
(13, 'TX444', 'TAXI', '35000.00', 'oui'),
(25, 'kk', 'kk', '22.00', 'mm'),
(26, 'aa', 'aa', '11.11', 'non'),
(27, 'aa', 'aa', '100.00', 'oui'),
(28, 'aabb', 'aaaa', '120.00', 'non');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
