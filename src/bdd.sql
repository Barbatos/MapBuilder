-- phpMyAdmin SQL Dump
-- version 3.4.11.1deb2
-- http://www.phpmyadmin.net
--
-- Client: localhost
-- Généré le: Jeu 12 Décembre 2013 à 12:35
-- Version du serveur: 5.5.31
-- Version de PHP: 5.4.4-14+deb7u5

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données: `mapbuilder`
--

-- --------------------------------------------------------

--
-- Structure de la table `horaire`
--

CREATE TABLE IF NOT EXISTS `horaire` (
  `jSemaine` tinyint(1) NOT NULL AUTO_INCREMENT,
  `heure` tinyint(2) NOT NULL,
  `minute` tinyint(2) NOT NULL,
  `periode` tinyint(3) NOT NULL,
  `IdStation` int(11) NOT NULL,
  PRIMARY KEY (`jSemaine`,`heure`,`minute`,`periode`,`IdStation`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=2 ;

--
-- Contenu de la table `horaire`
--

INSERT INTO `horaire` (`jSemaine`, `heure`, `minute`, `periode`, `IdStation`) VALUES
(1, 5, 0, 1, 30),
(1, 5, 10, 1, 30),
(1, 5, 20, 1, 30),
(1, 5, 40, 1, 30),
(1, 5, 50, 1, 30),
(1, 6, 0, 1, 30),
(1, 6, 10, 1, 30);

-- --------------------------------------------------------

--
-- Structure de la table `ligne`
--

CREATE TABLE IF NOT EXISTS `ligne` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `nom` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `couleurR` smallint(3) NOT NULL,
  `couleurG` smallint(3) NOT NULL,
  `couleurB` smallint(3) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=5 ;

--
-- Contenu de la table `ligne`
--

INSERT INTO `ligne` (`id`, `nom`, `couleurR`, `couleurG`, `couleurB`) VALUES
(1, 'A Campus 2', 242, 111, 35),
(2, 'A Ifs Jean Vilar', 242, 111, 35),
(3, 'B Hérouville St Clair', 162, 37, 45),
(4, 'B Grâce de Dieu', 162, 37, 45);

-- --------------------------------------------------------

--
-- Structure de la table `ligne-transport`
--

CREATE TABLE IF NOT EXISTS `ligne-transport` (
  `idLigne` int(10) NOT NULL,
  `idTransport` int(10) NOT NULL,
  PRIMARY KEY (`idLigne`,`idTransport`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Contenu de la table `ligne-transport`
--

INSERT INTO `ligne-transport` (`idLigne`, `idTransport`) VALUES
(1, 2),
(2, 2),
(3, 2),
(4, 2);

-- --------------------------------------------------------

--
-- Structure de la table `moyentransport`
--

CREATE TABLE IF NOT EXISTS `moyentransport` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `nom` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=5 ;

--
-- Contenu de la table `moyentransport`
--

INSERT INTO `moyentransport` (`id`, `nom`) VALUES
(1, 'Bus'),
(2, 'Tramway'),
(3, 'Métro'),
(4, 'Train');

-- --------------------------------------------------------

--
-- Structure de la table `station`
--

CREATE TABLE IF NOT EXISTS `station` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `nom` varchar(150) COLLATE utf8_unicode_ci NOT NULL,
  `coordX` smallint(4) NOT NULL,
  `coordY` smallint(4) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=35 ;

--
-- Contenu de la table `station`
--

INSERT INTO `station` (`id`, `nom`, `coordX`, `coordY`) VALUES
(1, 'Maréchal Juin', 136, 89),
(2, 'Côte de Nacre', 154, 96),
(3, 'Citis', 185, 101),
(4, 'Claud Bloch / Campus 4', 168, 116),
(5, 'CHU', 154, 155),
(6, 'Copernic', 141, 200),
(7, 'Calvaire St-Pierre', 112, 201),
(8, 'CROUS-SUAPS', 110, 230),
(9, 'Université', 95, 269),
(10, 'Place de la Mare', 78, 286),
(11, 'Quatrans', 89, 300),
(12, 'St-Pierre', 107, 320),
(13, 'Bernières', 117, 330),
(14, 'Résistance', 123, 347),
(15, 'Quai de Juillet', 130, 365),
(16, 'Gare SNCF', 143, 378),
(17, 'Bd. Leroy', 172, 424),
(18, 'Lux-Victor Lépine', 180, 447),
(19, 'Guynemer', 162, 458),
(20, 'Poincaré', 161, 490),
(21, 'Liberté', 150, 525),
(22, 'Concorde', 148, 563),
(23, 'Modigliani', 119, 572),
(24, 'Ifs Jean Vilar', 84, 607),
(25, 'Hérouville St-Clair', 317, 193),
(26, 'Café des Images', 302, 206),
(27, 'Académie', 285, 200),
(28, 'Château d''Eau', 257, 191),
(29, 'Pierre Heuzé', 212, 209),
(30, 'Caen Campus 2', 129, 64),
(31, 'Cité U Lebisey', 176, 196),
(32, 'Aviation', 121, 497),
(33, 'Rostand-Fresnel', 96, 501),
(34, 'Caen Grâce de Dieu', 48, 497);

-- --------------------------------------------------------

--
-- Structure de la table `station-ligne`
--

CREATE TABLE IF NOT EXISTS `station-ligne` (
  `idLigne` int(10) NOT NULL,
  `idStation` int(10) NOT NULL,
  `ordre` int(10) NOT NULL,
  PRIMARY KEY (`idLigne`,`idStation`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Contenu de la table `station-ligne`
--

INSERT INTO `station-ligne` (`idLigne`, `idStation`, `ordre`) VALUES
(1, 1, 24),
(1, 2, 23),
(1, 3, 22),
(1, 4, 21),
(1, 5, 20),
(1, 6, 19),
(1, 7, 18),
(1, 8, 17),
(1, 9, 16),
(1, 10, 15),
(1, 11, 14),
(1, 12, 13),
(1, 13, 12),
(1, 14, 11),
(1, 15, 10),
(1, 16, 9),
(1, 17, 8),
(1, 18, 7),
(1, 19, 6),
(1, 20, 5),
(1, 21, 4),
(1, 22, 3),
(1, 23, 2),
(1, 24, 1),
(1, 30, 25),
(2, 1, 2),
(2, 2, 3),
(2, 3, 4),
(2, 4, 5),
(2, 5, 6),
(2, 6, 7),
(2, 7, 8),
(2, 8, 9),
(2, 9, 10),
(2, 10, 11),
(2, 11, 12),
(2, 12, 13),
(2, 13, 14),
(2, 14, 15),
(2, 15, 16),
(2, 16, 17),
(2, 17, 18),
(2, 18, 19),
(2, 19, 20),
(2, 20, 21),
(2, 21, 22),
(2, 22, 23),
(2, 23, 24),
(2, 24, 25),
(2, 30, 1),
(3, 6, 18),
(3, 7, 17),
(3, 8, 16),
(3, 9, 15),
(3, 10, 14),
(3, 11, 13),
(3, 12, 12),
(3, 13, 11),
(3, 14, 10),
(3, 15, 9),
(3, 16, 8),
(3, 17, 7),
(3, 18, 6),
(3, 19, 5),
(3, 20, 4),
(3, 25, 24),
(3, 26, 23),
(3, 27, 22),
(3, 28, 21),
(3, 29, 20),
(3, 31, 19),
(3, 32, 3),
(3, 33, 2),
(3, 34, 1),
(4, 6, 7),
(4, 7, 8),
(4, 8, 9),
(4, 9, 10),
(4, 10, 11),
(4, 11, 12),
(4, 12, 13),
(4, 13, 14),
(4, 14, 15),
(4, 15, 16),
(4, 16, 17),
(4, 17, 18),
(4, 18, 19),
(4, 19, 20),
(4, 20, 21),
(4, 25, 1),
(4, 26, 2),
(4, 27, 3),
(4, 28, 4),
(4, 29, 5),
(4, 31, 6),
(4, 32, 22),
(4, 33, 23),
(4, 34, 24);

-- --------------------------------------------------------

--
-- Structure de la table `station-zone`
--

CREATE TABLE IF NOT EXISTS `station-zone` (
  `idStation` int(10) NOT NULL,
  `idZone` int(10) NOT NULL,
  PRIMARY KEY (`idStation`,`idZone`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Contenu de la table `station-zone`
--

INSERT INTO `station-zone` (`idStation`, `idZone`) VALUES
(1, 1),
(2, 1),
(3, 1),
(4, 1),
(5, 1),
(6, 1),
(7, 1),
(8, 1),
(9, 1),
(10, 1),
(11, 1),
(12, 1),
(13, 1),
(14, 1),
(15, 1),
(16, 1),
(17, 1),
(18, 1),
(19, 1),
(20, 1),
(21, 1),
(22, 1),
(23, 1),
(24, 1),
(25, 1),
(26, 1),
(27, 1),
(28, 1),
(29, 1),
(30, 1),
(31, 1),
(32, 1),
(33, 1),
(34, 1);

-- --------------------------------------------------------

--
-- Structure de la table `ville`
--

CREATE TABLE IF NOT EXISTS `ville` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `nom` varchar(150) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=3 ;

--
-- Contenu de la table `ville`
--

INSERT INTO `ville` (`id`, `nom`) VALUES
(1, 'Caen'),
(2, 'Paris');

-- --------------------------------------------------------

--
-- Structure de la table `zone`
--

CREATE TABLE IF NOT EXISTS `zone` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `nom` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `couleurR` smallint(3) NOT NULL,
  `couleurG` smallint(3) NOT NULL,
  `couleurB` smallint(3) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=2 ;

--
-- Contenu de la table `zone`
--

INSERT INTO `zone` (`id`, `nom`, `couleurR`, `couleurG`, `couleurB`) VALUES
(1, 'Agglomération Caennaise', 200, 150, 150);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
