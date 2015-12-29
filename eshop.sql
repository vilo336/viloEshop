-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Hostiteľ: 127.0.0.1
-- Čas generovania: Út 29.Dec 2015, 14:48
-- Verzia serveru: 10.1.8-MariaDB
-- Verzia PHP: 5.6.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Databáza: `eshop`
--

-- --------------------------------------------------------

--
-- Štruktúra tabuľky pre tabuľku `order`
--

CREATE TABLE `order` (
  `id_order` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `id_product` int(11) NOT NULL,
  `pocet_kusov` int(11) NOT NULL,
  `cena` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Sťahujem dáta pre tabuľku `order`
--

INSERT INTO `order` (`id_order`, `id_user`, `id_product`, `pocet_kusov`, `cena`) VALUES
(1, 0, 2, 34, 45),
(2, 0, 2, 23, 529),
(3, 0, 2, 34, 782),
(4, 0, 2, 3, 69),
(5, 3, 2, 34, 782),
(6, 3, 1, 34, 0);

-- --------------------------------------------------------

--
-- Štruktúra tabuľky pre tabuľku `product`
--

CREATE TABLE `product` (
  `id_product` int(11) NOT NULL,
  `nazov` varchar(255) NOT NULL,
  `kategoria` varchar(255) NOT NULL,
  `cena` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Sťahujem dáta pre tabuľku `product`
--

INSERT INTO `product` (`id_product`, `nazov`, `kategoria`, `cena`) VALUES
(3, 'lenovo  ', 'notebook', 430),
(4, 'asus X52r', 'notebook', 450),
(5, 'canon 45v', 'fotoaparat', 250),
(6, 'benq     ', 'cd      ', 1.2),
(7, 'ventilator', 'biela     ', 12.5),
(8, 'sygic       ', 'navigacia', 90.4);

-- --------------------------------------------------------

--
-- Štruktúra tabuľky pre tabuľku `user`
--

CREATE TABLE `user` (
  `id_user` int(11) NOT NULL,
  `meno` varchar(255) NOT NULL,
  `priezvisko` varchar(255) NOT NULL,
  `vek` int(11) NOT NULL,
  `email` varchar(255) NOT NULL,
  `login` varchar(255) NOT NULL,
  `heslo` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Sťahujem dáta pre tabuľku `user`
--

INSERT INTO `user` (`id_user`, `meno`, `priezvisko`, `vek`, `email`, `login`, `heslo`) VALUES
(1, 'dfghdfgj', 'gjdfh', 34, 'gdhj', 'fdsgsdfh', 'ghj'),
(2, 'hfjytj', 'fhjyjf', 34, 'fjyfgyj', 'ghfgj', 'hjfyu'),
(3, 'adadm', 'sangala', 32, 'sado33@sado.sk', 'sado', 'sadi');

--
-- Kľúče pre exportované tabuľky
--

--
-- Indexy pre tabuľku `order`
--
ALTER TABLE `order`
  ADD PRIMARY KEY (`id_order`),
  ADD KEY `id_user` (`id_user`),
  ADD KEY `id_user_2` (`id_user`),
  ADD KEY `id_product` (`id_product`);

--
-- Indexy pre tabuľku `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`id_product`);

--
-- Indexy pre tabuľku `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id_user`);

--
-- AUTO_INCREMENT pre exportované tabuľky
--

--
-- AUTO_INCREMENT pre tabuľku `order`
--
ALTER TABLE `order`
  MODIFY `id_order` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT pre tabuľku `product`
--
ALTER TABLE `product`
  MODIFY `id_product` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
--
-- AUTO_INCREMENT pre tabuľku `user`
--
ALTER TABLE `user`
  MODIFY `id_user` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
