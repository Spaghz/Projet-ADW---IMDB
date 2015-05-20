-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Client :  127.0.0.1
-- Généré le :  Mer 20 Mai 2015 à 20:03
-- Version du serveur :  5.6.17
-- Version de PHP :  5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données :  `adwprojectpublish`
--

-- --------------------------------------------------------

--
-- Structure de la table `award`
--

CREATE TABLE IF NOT EXISTS `award` (
  `idCelebrity` int(11) NOT NULL,
  `idMovie` int(11) NOT NULL,
  `awardName` varchar(256) NOT NULL,
  `awardDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`idCelebrity`,`idMovie`,`awardName`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `celebrity`
--

CREATE TABLE IF NOT EXISTS `celebrity` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `firstName` varchar(64) NOT NULL,
  `lastName` varchar(64) NOT NULL,
  `biography` text NOT NULL,
  `birthDate` date NOT NULL,
  `pictureURI` varchar(64) NOT NULL,
  `rank` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

-- --------------------------------------------------------

--
-- Structure de la table `celebrityupdates`
--

CREATE TABLE IF NOT EXISTS `celebrityupdates` (
  `updateNumber` int(11) NOT NULL AUTO_INCREMENT,
  `celebrityId` int(11) NOT NULL,
  PRIMARY KEY (`updateNumber`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

-- --------------------------------------------------------

--
-- Structure de la table `movies`
--

CREATE TABLE IF NOT EXISTS `movies` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `releaseDate` date NOT NULL,
  `cost` int(11) NOT NULL,
  `posterURI` varchar(32) NOT NULL,
  `idDirector` int(11) DEFAULT NULL,
  `synopsis` text NOT NULL,
  `rank` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

-- --------------------------------------------------------

--
-- Structure de la table `movieupdates`
--

CREATE TABLE IF NOT EXISTS `movieupdates` (
  `updateNumber` int(11) NOT NULL AUTO_INCREMENT,
  `movieId` int(11) NOT NULL,
  `title` varchar(64) NOT NULL,
  `releaseDate` date NOT NULL,
  `cost` int(11) NOT NULL,
  `posterURI` varchar(64) NOT NULL,
  `idDirector` int(11) DEFAULT NULL,
  `synopsis` text NOT NULL,
  PRIMARY KEY (`updateNumber`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `news`
--

CREATE TABLE IF NOT EXISTS `news` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(64) NOT NULL,
  `idAuthor` int(11) NOT NULL,
  `news` text NOT NULL,
  `illustration` varchar(64) NOT NULL,
  `date` timestamp NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `play`
--

CREATE TABLE IF NOT EXISTS `play` (
  `idCelebrity` int(11) NOT NULL,
  `idMovie` int(11) NOT NULL,
  PRIMARY KEY (`idCelebrity`,`idMovie`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `playupdates`
--

CREATE TABLE IF NOT EXISTS `playupdates` (
  `updateNumber` int(11) NOT NULL AUTO_INCREMENT,
  `idCelebrity` int(11) NOT NULL,
  `idMovie` int(11) NOT NULL,
  PRIMARY KEY (`updateNumber`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `produce`
--

CREATE TABLE IF NOT EXISTS `produce` (
  `idCelebrity` int(11) NOT NULL,
  `idMovie` int(11) NOT NULL,
  PRIMARY KEY (`idCelebrity`,`idMovie`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `rates`
--

CREATE TABLE IF NOT EXISTS `rates` (
  `idUser` int(11) NOT NULL,
  `idMovie` int(11) NOT NULL,
  `rate` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`idUser`,`idMovie`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `rights`
--

CREATE TABLE IF NOT EXISTS `rights` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idUser` int(11) NOT NULL,
  `isAdmin` tinyint(1) NOT NULL,
  `isPro` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

-- --------------------------------------------------------

--
-- Structure de la table `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nickName` varchar(64) NOT NULL,
  `salt` varbinary(20) NOT NULL,
  `password` varchar(60) NOT NULL,
  `email` varchar(255) NOT NULL,
  `firstName` varchar(50) NOT NULL,
  `lastName` varchar(50) NOT NULL,
  `birthDate` date NOT NULL,
  `pictureURI` varchar(32) DEFAULT NULL,
  `subscribeDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
