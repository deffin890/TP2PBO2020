-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Mar 26, 2020 at 09:51 AM
-- Server version: 10.1.13-MariaDB
-- PHP Version: 5.6.23

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_peminjaman`
--

-- --------------------------------------------------------

--
-- Table structure for table `tbl_peminjaman`
--

CREATE TABLE `tbl_peminjaman` (
  `kode_buku` varchar(100) NOT NULL,
  `nama_buku` varchar(100) NOT NULL,
  `nama_peminjam` varchar(100) NOT NULL,
  `tanggal_pinjam` varchar(100) NOT NULL,
  `tanggal_balik` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_peminjaman`
--

INSERT INTO `tbl_peminjaman` (`kode_buku`, `nama_buku`, `nama_peminjam`, `tanggal_pinjam`, `tanggal_balik`) VALUES
('B-01', 'Indonesia', 'Deffin', '13/03/2020', '24/03/2020'),
('B-02', 'PPKN', 'Bayu', '14/03/2020', '20/03/2020'),
('B-03', 'Inggris', 'Irenanda', '27/03/2020', '01/04/2020'),
('B-04', 'Ilmu Komputer', 'Azis', '03/03/2020', '06/03/2020'),
('B-05', 'IPA', 'Alyya', '04/03/2020', '07/03/2020');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tbl_peminjaman`
--
ALTER TABLE `tbl_peminjaman`
  ADD PRIMARY KEY (`kode_buku`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
