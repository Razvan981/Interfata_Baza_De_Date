

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

-- -----------------------------------------------------------


--
-- Database: `EvidentaProduselorHypermarket`
--

create database EvidentaProduselorHypermarket;
use EvidentaProduselorHypermarket;


--
-- Table structure for table `Produs`
--

-- --------------------------------------------------------


CREATE TABLE `Produs` (
  `Cod_produs` varchar(10) NOT NULL,
  `Nume` varchar(30) NOT NULL,
  `Nr_buc_stoc` int(7) UNSIGNED NOT NULL,
  `Pret` double UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


--
-- Dumping data for table `Produs`
--

INSERT INTO `Produs` (`Cod_produs`, `Nume`, `Pret`, `Nr_buc_stoc`) VALUES
('abc1', 'ulei', 3, 200),
('def2', 'zahar', 2.5, 250),
('ghi3', 'apa', 2, 300),
('jkl4', 'kinder', 1.5, 130),
('mno5', 'paine', 1, 70),
('pqr6', 'iaurt', 2.3, 20),
('stw7', 'smantana', 9, 15);


-- -----------------------------------------------------------

--
-- Table structure for table `Client`
--

CREATE TABLE `Client` (
  `CNP` varchar(14) NOT NULL,
  `Nume` varchar(30) NOT NULL,
  `Prenume` varchar(30) NOT NULL,
  `Adresa` varchar(45) NOT NULL,
  `Nr_telefon` varchar(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Client`
--

INSERT INTO `Client` (`CNP`, `Nume`, `Prenume`, `Adresa`, `Nr_telefon`) VALUES
('1980122160089', 'Ionescu', 'Andrei', 'str Eustatiu Stoenescu, nr 1', '0747547382'),
('1284122360051', 'Cauc', 'Ion', 'str Eustatiu Stoenescu, nr 3', '0447447586'),
('2980322864055', 'Popa', 'Maria', 'str Eustatiu Stoenescu, nr 4', '0747447440'),
('1780122460082', 'Ivan', 'Andrei', 'str Eustatiu Stoenescu, nr 5', '0744547484'),
('1880122560081', 'Marinescu', 'Laurentiu', 'str Eustatiu Stoenescu, nr 6', '0721517321'),
('1680122660084', 'Avramescu', 'Matei', 'str Eustatiu Stoenescu, nr 7', '0749929399'),
('2930122764083', 'Popescu', 'Andreea', 'str Eustatiu Stoenescu, nr 2', '0744245383');

-- -----------------------------------------------------------

--
-- Table structure for table `Comanda`
--

CREATE TABLE `Comanda` (
  `ID_comanda` int(6) UNSIGNED NOT NULL,
  `Nr_comanda` int(6) UNSIGNED NOT NULL,
  `CNP_client` varchar(14) NOT NULL,
  `data_comanda` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Comanda`
--

INSERT INTO `Comanda` (`ID_comanda`, `Nr_comanda`, `CNP_client`, `data_comanda`) VALUES
(101, 100, '1980122160089', '2001-01-01' ),
(102, 200, '1284122360051', '2002-02-02' ),
(103, 300, '2980322864055', '2003-03-03' ),
(104, 400, '1780122460082', '2004-04-04' ),
(105, 500, '1880122560081', '2005-05-05' ),
(106, 600, '1680122660084', '2006-06-06' ),
(107, 700, '2930122764083', '2007-07-07' );


-- ---------------------------------------------------------

--
-- Table structure for table `Detalii_comanda`
--

CREATE TABLE `Detalii_comanda` (
  `ID_Detalii_comanda` int(6) UNSIGNED NOT NULL,
  `ID_comanda` int(6) UNSIGNED NOT NULL,
  `Cod_produs` varchar(10) NOT NULL,
  `Nr_buc_comandate` int(6) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Detalii_comanda`
--

INSERT INTO `Detalii_comanda` (`ID_Detalii_comanda`, `ID_comanda`, `Cod_produs`, `Nr_buc_comandate`) VALUES
(1, 101, 'abc1', 300),
(2, 102, 'def2', 150),
(3, 103, 'ghi3', 50),
(4, 104, 'jkl4', 120),
(5, 105, 'mno5', 25),
(6, 106, 'pqr6', 15),
(7, 107, 'stw7', 70);

-- ---------------------------------------------------------



--
-- Indexes for table `Produs`
--
ALTER TABLE `Produs`
  ADD PRIMARY KEY (`Cod_produs`);

--
-- Indexes for table `Client`
--
ALTER TABLE `Client`
  ADD PRIMARY KEY (`CNP`);
  
--
-- Indexes for table `Comanda`
--
ALTER TABLE `Comanda`
  ADD PRIMARY KEY (`ID_comanda`);
  
--
-- Indexes for table `Detalii_comanda`
--
ALTER TABLE `Detalii_comanda`
  ADD PRIMARY KEY (`ID_Detalii_comanda`);
  
  


ALTER TABLE `Comanda` ADD FOREIGN KEY (`CNP_client`) REFERENCES `Client` (`CNP`) 
	ON DELETE RESTRICT ON UPDATE RESTRICT;
	
	
ALTER TABLE `Detalii_comanda` ADD FOREIGN KEY (`ID_comanda`) REFERENCES `Comanda` (`ID_comanda`) 
	ON DELETE RESTRICT ON UPDATE RESTRICT;
	
	
ALTER TABLE `Detalii_comanda` ADD FOREIGN KEY (`Cod_produs`) REFERENCES `Produs` (`Cod_produs`) 
	ON DELETE RESTRICT ON UPDATE RESTRICT;


  
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
