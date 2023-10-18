-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 18, 2023 at 03:13 PM
-- Server version: 10.4.27-MariaDB
-- PHP Version: 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `parking`
--

-- --------------------------------------------------------

--
-- Table structure for table `parking_price`
--

CREATE TABLE `parking_price` (
  `id` bigint(10) NOT NULL,
  `street_name` varchar(255) NOT NULL,
  `price` bigint(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `parking_price`
--

INSERT INTO `parking_price` (`id`, `street_name`, `price`) VALUES
(2, 'Jakarta', 13),
(1, 'Java', 15);

-- --------------------------------------------------------

--
-- Table structure for table `parking_registration`
--

CREATE TABLE `parking_registration` (
  `id` bigint(10) NOT NULL,
  `street_name` varchar(255) NOT NULL,
  `licence_plate_number` varchar(15) NOT NULL,
  `request_start_time` datetime(6) DEFAULT NULL,
  `request_end_time` datetime(6) DEFAULT NULL,
  `amount` bigint(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `parking_registration`
--

INSERT INTO `parking_registration` (`id`, `street_name`, `licence_plate_number`, `request_start_time`, `request_end_time`, `amount`) VALUES
(1, 'Java', 'abcf', '2023-10-13 10:42:22.000000', '2023-10-13 10:46:57.000000', -15),
(2, 'Jakarta', 'dl0396', '2023-10-13 11:19:28.000000', '2023-10-13 11:23:07.000000', 39),
(3, 'Jakarta', 'DL7C5877', '2023-10-13 15:06:41.000000', NULL, NULL),
(4, 'Jakarta', 'DL12345', '2023-10-13 17:19:20.000000', '2023-10-13 17:19:42.000000', 0),
(5, 'Jakarta', 'DL6565', '2023-10-16 07:54:02.000000', '2023-10-16 07:55:11.000000', 13),
(6, 'Java', 'DL9988', '2023-10-16 14:13:54.000000', '2023-10-16 14:15:35.000000', 15),
(7, 'Java', 'DL9999', '2023-10-16 14:19:50.000000', '2023-10-16 14:26:22.000000', 90);

-- --------------------------------------------------------

--
-- Table structure for table `unregistered_licence_plate_number`
--

CREATE TABLE `unregistered_licence_plate_number` (
  `id` int(10) NOT NULL,
  `street_name` varchar(255) NOT NULL,
  `licence_plate_number` varchar(50) NOT NULL,
  `date_of_observation` datetime(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `unregistered_licence_plate_number`
--

INSERT INTO `unregistered_licence_plate_number` (`id`, `street_name`, `licence_plate_number`, `date_of_observation`) VALUES
(1, 'Java', 'DL6787', '2023-10-18 18:27:27.000000'),
(2, 'Jakarta', 'DL00099', '2023-10-18 18:27:27.000000');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `parking_price`
--
ALTER TABLE `parking_price`
  ADD PRIMARY KEY (`street_name`,`id`),
  ADD UNIQUE KEY `id` (`id`);

--
-- Indexes for table `parking_registration`
--
ALTER TABLE `parking_registration`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `unregistered_licence_plate_number`
--
ALTER TABLE `unregistered_licence_plate_number`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `parking_price`
--
ALTER TABLE `parking_price`
  MODIFY `id` bigint(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `parking_registration`
--
ALTER TABLE `parking_registration`
  MODIFY `id` bigint(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `unregistered_licence_plate_number`
--
ALTER TABLE `unregistered_licence_plate_number`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
