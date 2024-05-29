-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 28, 2024 at 03:35 PM
-- Server version: 10.4.25-MariaDB
-- PHP Version: 8.0.23

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `pshub_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `order_recap`
--

CREATE TABLE `order_recap` (
  `order_id` int(11) NOT NULL,
  `nama_pelanggan` varchar(20) NOT NULL,
  `jenis_rental` varchar(10) CHARACTER SET utf8 NOT NULL,
  `harga` bigint(20) NOT NULL DEFAULT 0,
  `waktu_mulai` datetime NOT NULL,
  `waktu_selesai` datetime DEFAULT NULL,
  `user_id` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `order_recap`
--

INSERT INTO `order_recap` (`order_id`, `nama_pelanggan`, `jenis_rental`, `harga`, `waktu_mulai`, `waktu_selesai`, `user_id`) VALUES
(1, 'Jono', 'PS 5', 5000, '2024-05-28 16:42:16', '2024-05-28 16:42:26', 'mahmud'),
(2, 'Ajie', 'PS 4', 5000, '2024-05-28 16:42:54', '2024-05-28 16:46:33', 'mahmud'),
(3, 'Rhyo', 'PS 5', 5000, '2024-05-28 16:48:42', '2024-05-28 16:51:20', 'mahmud'),
(4, 'Agus', 'PS 5', 5000, '2024-05-28 17:00:11', '2024-05-28 17:00:20', 'mahmud'),
(5, 'Rama', 'PS 5', 5000, '2024-05-28 17:01:46', '2024-05-28 17:02:22', 'mahmud'),
(6, 'Wendy', 'PS 5', 5000, '2024-05-28 17:07:27', '2024-05-28 17:07:41', 'mahmud'),
(7, 'F', 'PS5', 10000, '2024-05-28 17:10:38', '2024-05-28 17:10:47', 'mahmud'),
(8, 'Siwi', 'PS4', 5000, '2024-05-28 17:11:06', '2024-05-28 17:11:10', 'mahmud'),
(9, 'A', 'PS4', 5000, '2024-05-28 17:11:54', '2024-05-28 17:12:13', 'mahmud'),
(10, 'B', 'PS5', 10000, '2024-05-28 17:12:04', '2024-05-28 17:12:27', 'mahmud'),
(11, 'Jett', 'PS5', 0, '2024-05-28 17:13:37', NULL, 'mahmud'),
(12, 'Phoenix', 'PS5', 0, '2024-05-28 17:13:43', NULL, 'mahmud');

-- --------------------------------------------------------

--
-- Table structure for table `setting`
--

CREATE TABLE `setting` (
  `remain` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `setting`
--

INSERT INTO `setting` (`remain`) VALUES
(9);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `username` varchar(20) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` varchar(20) NOT NULL DEFAULT 'karyawan'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`username`, `password`, `role`) VALUES
('mahmud', 'karyawan', 'employee'),
('zulfikar', 'admin', 'admin');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `order_recap`
--
ALTER TABLE `order_recap`
  ADD PRIMARY KEY (`order_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `order_recap`
--
ALTER TABLE `order_recap`
  MODIFY `order_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
