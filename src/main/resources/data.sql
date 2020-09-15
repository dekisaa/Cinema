INSERT INTO `cinema`.`role` (`id`, `name`) VALUES ('1', 'VIEWER');
INSERT INTO `cinema`.`role` (`id`, `name`) VALUES ('2', 'MANAGER');
INSERT INTO `cinema`.`role` (`id`, `name`) VALUES ('3', 'ADMIN');

INSERT INTO `cinema`.`movie` (`id`, `description`, `duration`, `genre`, `name`, `rating`) VALUES ('1', 'akcija i samo akcija', '140', '1', 'Avengersi', '4');
INSERT INTO `cinema`.`movie` (`id`, `description`, `duration`, `genre`, `name`, `rating`) VALUES ('2', 'Svedska akcija', '150', '0', 'Grimsby', '5');
INSERT INTO `cinema`.`movie` (`id`, `description`, `duration`, `genre`, `name`, `rating`) VALUES ('3', 'Srpska akcija', '169', '0', 'Iron Man', '3');
INSERT INTO `cinema`.`movie` (`id`, `description`, `duration`, `genre`, `name`, `rating`) VALUES ('4', 'Madjarska akcija', '183', '1', 'Hairy pottera', '5');
INSERT INTO `cinema`.`movie` (`id`, `description`, `duration`, `genre`, `name`, `rating`) VALUES ('5', 'Super je', '120', '1', 'Umri muski', '1.5');
INSERT INTO `cinema`.`movie` (`id`, `description`, `duration`, `genre`, `name`, `rating`) VALUES ('6', 'Odlican', '150', '1', 'Matrix', '2.5');

INSERT INTO `cinema`.`user` (`id`, `active`, `first_name`, `last_name`, `password`, `username`) VALUES ('1', b'1', 'user', 'user', '$2a$10$ij4Lr1YtRv.PLWDUkNdWJuPk9SjrRiDb9eHU9k7BdTLM3mBI0YqCG', 'user');
INSERT INTO `cinema`.`user` (`id`, `active`, `first_name`, `last_name`, `password`, `username`) VALUES ('2', b'1', 'manager', 'manager', '$2a$10$ij4Lr1YtRv.PLWDUkNdWJuPk9SjrRiDb9eHU9k7BdTLM3mBI0YqCG', 'manager1');

INSERT INTO `cinema`.`user_roles` (`user_id`, `role_id`) VALUES ('1', '1');
INSERT INTO `cinema`.`user_roles` (`user_id`, `role_id`) VALUES ('2', '2');

INSERT INTO `cinema`.`cinema` (`id`, `address`, `email`, `name`, `phone`, `manager_id`) VALUES ('1', 'Pere Dobrinovica 7', 'cinemplex@gmail.com', 'Cineplex', '065754782', '1');
INSERT INTO `cinema`.`cinema` (`id`, `address`, `email`, `name`, `phone`, `manager_id`) VALUES ('2', 'Djordja Jovanovica 4', 'cinestart@gmail.com', 'Cinestar', '062457897', '1');

INSERT INTO `cinema`.`hall` (`id`, `capacity`, `mark`, `cinema_id`) VALUES ('1', '32', 'A1', '1');
INSERT INTO `cinema`.`hall` (`id`, `capacity`, `mark`, `cinema_id`) VALUES ('2', '70', 'A2', '1');
INSERT INTO `cinema`.`hall` (`id`, `capacity`, `mark`, `cinema_id`) VALUES ('3', '100', 'B7', '2');

INSERT INTO `cinema`.`projection` (`id`, `date`, `num_of_reserved_cards`, `price`, `hall_id`, `movie_id`) VALUES ('1', '2020-08-08 20:15:00', '2', '123', '1', '1');
INSERT INTO `cinema`.`projection` (`id`, `date`, `num_of_reserved_cards`, `price`, `hall_id`, `movie_id`) VALUES ('2', '2020-07-07 20:15:00.000000', '2', '432', '1', '2');
INSERT INTO `cinema`.`projection` (`id`, `date`, `num_of_reserved_cards`, `price`, `hall_id`, `movie_id`) VALUES ('3', '2020-05-05 20:15:00.000000', '3', '23', '1', '3');
INSERT INTO `cinema`.`projection` (`id`, `date`, `num_of_reserved_cards`, `price`, `hall_id`, `movie_id`) VALUES ('4', '2020-09-09 20:15:00.000000', '4', '432', '1', '4');
INSERT INTO `cinema`.`projection` (`id`, `date`, `num_of_reserved_cards`, `price`, `hall_id`, `movie_id`) VALUES ('5', '2020-04-04 20:15:00.000000', '5', '543', '1', '5');
INSERT INTO `cinema`.`projection` (`id`, `date`, `num_of_reserved_cards`, `price`, `hall_id`, `movie_id`) VALUES ('6', '2020-03-03 20:15:00.000000', '6', '234', '1', '6');
INSERT INTO `cinema`.`projection` (`id`, `date`, `num_of_reserved_cards`, `price`, `hall_id`, `movie_id`) VALUES ('7', '2020-04-08 20:15:00.000000', '7', '543', '1', '3');

INSERT INTO `cinema`.`movie_viewers` (`watched_films_id`, `viewers_id`) VALUES ('1', '1');
INSERT INTO `cinema`.`movie_viewers` (`watched_films_id`, `viewers_id`) VALUES ('2', '1');

INSERT INTO `cinema`.`rate` (`id`, `value`, `movie_id`, `user_id`) VALUES ('1', '2', '1', '1');

