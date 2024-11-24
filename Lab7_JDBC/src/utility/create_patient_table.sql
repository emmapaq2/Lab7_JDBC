CREATE TABLE `patients` (
  `id` int NOT NULL AUTO_INCREMENT,
  `f_name` varchar(45) DEFAULT NULL,
  `l_name` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `age` int DEFAULT NULL,
  PRIMARY KEY (`id`)
);

SELECT * FROM medical_db.patients;

INSERT INTO `medical_db`.`patients` (`f_name`, `l_name`, `email`, `age`) VALUES ('Emma', 'Frimpong', 'em@gmail.com', '115');



