-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Hôte : 172.17.0.1:3306
-- Généré le : mer. 14 juin 2023 à 13:53
-- Version du serveur : 10.6.12-MariaDB-0ubuntu0.22.04.1
-- Version de PHP : 8.0.24

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : sae_goodfood
--

-- --------------------------------------------------------

--
-- Structure de la table goodfood_assignment
--

CREATE TABLE goodfood_assignment (
  table_id int4 NOT NULL DEFAULT 0,
  assignment_date date NOT NULL DEFAULT '1970-01-01',
  waiter_id int2 DEFAULT NULL
);

--
-- Déchargement des données de la table goodfood_assignment
--

INSERT INTO goodfood_assignment (table_id, assignment_date, waiter_id) VALUES
(10, '2021-09-10', 1),
(10, '2021-09-11', 1),
(11, '2021-09-10', 1),
(12, '2021-09-10', 1),
(17, '2021-09-10', 2),
(18, '2021-09-10', 2),
(15, '2021-09-10', 3),
(16, '2021-09-10', 3);

-- --------------------------------------------------------

--
-- Structure de la table goodfood_dishes
--

CREATE TABLE goodfood_dishes (
  dish_id int4 NOT NULL DEFAULT 0 PRIMARY KEY,
  label varchar(40) DEFAULT NULL,
  type varchar(15) DEFAULT NULL,
  price decimal(6,2) DEFAULT NULL,
  quantity int2 DEFAULT NULL
);

--
-- Déchargement des données de la table goodfood_dishes
--

INSERT INTO goodfood_dishes (dish_id, label, type, price, quantity) VALUES
(1, 'assiette de crudités', 'Entrée', '90.00', 25),
(2, 'tarte de saison', 'Dessert', '90.00', 25),
(3, 'sorbet mirabelle', 'Dessert', '90.00', 35),
(4, 'filet de boeuf', 'Viande', '90.00', 62),
(5, 'salade verte', 'Entrée', '90.00', 15),
(6, 'chevre chaud', 'Entrée', '90.00', 21),
(7, 'pate lorrain', 'Entrée', '90.00', 25),
(8, 'saumon fumé', 'Entrée', '90.00', 30),
(9, 'entrecote printaniere', 'Viande', '90.00', 58),
(10, 'gratin dauphinois', 'Plat', '90.00', 42),
(11, 'brochet à l''oseille', 'Poisson', '90.00', 68),
(12, 'gigot d''agneau', 'Viande', '90.00', 56),
(13, 'crème caramel', 'Dessert', '90.00', 15),
(14, 'munster au cumin', 'Fromage', '90.00', 18),
(15, 'filet de sole au beurre', 'Poisson', '90.00', 70),
(16, 'fois gras de lorraine', 'Entrée', '90.00', 61);

-- --------------------------------------------------------

--
-- Structure de la table goodfood_orders
--

CREATE TABLE goodfood_orders (
  reservation_id int4 NOT NULL DEFAULT 0,
  dish_id int4 NOT NULL DEFAULT 0,
  quantity int2 DEFAULT NULL
);

--
-- Déchargement des données de la table goodfood_orders
--

INSERT INTO goodfood_orders (reservation_id, dish_id, quantity) VALUES
(100, 3, 1),
(100, 4, 2),
(100, 5, 2),
(100, 13, 1),
(101, 2, 2),
(101, 3, 2),
(101, 7, 2),
(101, 12, 2),
(101, 15, 2),
(101, 16, 2),
(102, 1, 2),
(102, 2, 1),
(102, 3, 1),
(102, 10, 2),
(102, 14, 2),
(103, 2, 1),
(103, 3, 1),
(103, 9, 2),
(103, 14, 2),
(104, 3, 1),
(104, 7, 1),
(104, 11, 1),
(104, 14, 1),
(105, 3, 2),
(106, 3, 2);

-- --------------------------------------------------------

--
-- Structure de la table goodfood_reservations
--

CREATE TABLE goodfood_reservations (
  reservation_id int4 NOT NULL PRIMARY KEY,
  restaurant_id int8 NOT NULL,
  table_id int4 DEFAULT NULL,
  reservation_date timestamp DEFAULT NULL,
  people_count int2 DEFAULT NULL,
  payment_date timestamp DEFAULT NULL,
  payment_type varchar(15) DEFAULT NULL,
  order_price decimal(8,2) DEFAULT NULL
);

--
-- Déchargement des données de la table goodfood_reservations
--

INSERT INTO goodfood_reservations (reservation_id, restaurant_id, table_id, reservation_date, people_count, payment_date, payment_type, order_price) VALUES
(100, 0, 10, '2021-09-10 19:00:00', 2, '2021-09-10 20:50:00', 'Carte', NULL),
(101, 0, 11, '2021-09-10 20:00:00', 4, '2021-09-10 21:20:00', 'Chèque', NULL),
(102, 0, 17, '2021-09-10 18:00:00', 2, '2021-09-10 20:55:00', 'Carte', NULL),
(103, 0, 12, '2021-09-10 19:00:00', 2, '2021-09-10 21:10:00', 'Espèces', NULL),
(104, 0, 18, '2021-09-10 19:00:00', 1, '2021-09-10 21:00:00', 'Chèque', NULL),
(105, 0, 10, '2021-09-10 19:00:00', 2, '2021-09-10 20:45:00', 'Carte', NULL),
(106, 0, 14, '2021-10-11 19:00:00', 2, '2021-10-11 22:45:00', 'Carte', NULL);

-- --------------------------------------------------------

--
-- Structure de la table goodfood_restaurants
--

CREATE TABLE goodfood_restaurants (
  id int8 NOT NULL PRIMARY KEY,
  name text NOT NULL,
  address text NOT NULL,
  latitude float NOT NULL,
  longitude float NOT NULL
);

--
-- Déchargement des données de la table goodfood_restaurants
--

INSERT INTO goodfood_restaurants (id, name, address, latitude, longitude) VALUES
(1, 'Vache burger', '15 rue de la Primatiale, 54000 Nancy', 48.6906, 6.18556),
(2, 'Les petits oignons', '48 Rue Jeanne d''Arc, 54000 Nancy', 48.6864, 6.17128),
(3, 'Angelùzzo', '45 Rue Saint-Dizier, 54000 Nancy', 48.6905, 6.18286),
(4, 'Le bouche à oreille', '42 Rue des Carmes, 54000 Nancy', 48.6907, 6.18099),
(5, 'Voyou', '20 Rue Stanislas, 54000 Nancy', 48.6931, 6.18157),
(6, 'Vins et Tartines', '25 Bis Rue des Ponts, 54000 Nancy', 48.6885, 6.18195),
(7, 'Le K', '7 Rue Saint-Julien, 54000 Nancy', 48.692, 6.1842);

-- --------------------------------------------------------

--
-- Structure de la table goodfood_tables
--

CREATE TABLE goodfood_tables (
  table_id int4 NOT NULL DEFAULT 0 PRIMARY KEY,
  seat_count int2 DEFAULT NULL
);

--
-- Déchargement des données de la table goodfood_tables
--

INSERT INTO goodfood_tables (table_id, seat_count) VALUES
(10, 4),
(11, 6),
(12, 8),
(13, 4),
(14, 6),
(15, 4),
(16, 4),
(17, 6),
(18, 2),
(19, 4);

-- --------------------------------------------------------

--
-- Structure de la table goodfood_waiter
--

CREATE TABLE goodfood_waiter (
  waiter_id int2 NOT NULL DEFAULT 0 PRIMARY KEY,
  email varchar(255) DEFAULT NULL,
  password varchar(255) DEFAULT NULL,
  waiter_name varchar(25) DEFAULT NULL,
  role varchar(20) DEFAULT NULL
);

--
-- Déchargement des données de la table goodfood_waiter
--

INSERT INTO goodfood_waiter (waiter_id, email, password, waiter_name, role) VALUES
(1, 'user1@mail.com', 'motdepasse', 'Tutus Peter', 'gestionnaire'),
(2, 'user2@mail.com', 'motdepasse', 'Lilo Vito', 'serveur'),
(3, 'user3@mail.com', '$ab#;??m$$$$$2', 'Don Carl', 'serveur'),
(4, 'user4@mail.com', '$cd#;??m$$$$$3', 'Leo Jon', 'serveur'),
(5, 'user5@mail.com', '$mm#;??m$$$$$4', 'Dean Geak', 'gestionnaire');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table goodfood_assignment
--
ALTER TABLE goodfood_assignment
  ADD PRIMARY KEY (table_id,assignment_date),
  ADD FOREIGN KEY (waiter_id) REFERENCES goodfood_waiter(waiter_id);

--
-- Index pour la table goodfood_orders
--
ALTER TABLE goodfood_orders
  ADD PRIMARY KEY (reservation_id,dish_id),
  ADD FOREIGN KEY (dish_id) REFERENCES goodfood_dishes(dish_id);

--
-- AUTO_INCREMENT pour la table goodfood_reservations
--
ALTER TABLE goodfood_reservations
  ALTER COLUMN reservation_id SET NOT NULL;

--
-- AUTO_INCREMENT pour la table goodfood_restaurants
--
ALTER TABLE goodfood_restaurants
  ALTER COLUMN id SET NOT NULL;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table goodfood_assignment
--
ALTER TABLE goodfood_assignment
  ADD CONSTRAINT goodfood_assignment_ibfk_1 FOREIGN KEY (table_id) REFERENCES goodfood_tables (table_id),
  ADD CONSTRAINT goodfood_assignment_ibfk_2 FOREIGN KEY (waiter_id) REFERENCES goodfood_waiter (waiter_id);

--
-- Contraintes pour la table goodfood_orders
--
ALTER TABLE goodfood_orders
  ADD CONSTRAINT goodfood_orders_ibfk_1 FOREIGN KEY (dish_id) REFERENCES goodfood_dishes (dish_id);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
