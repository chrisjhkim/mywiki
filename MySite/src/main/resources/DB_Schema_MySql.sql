CREATE TABLE `tag_info` (
  `tagNo` int NOT NULL AUTO_INCREMENT,
  `tagName` varchar(45) NOT NULL,
  PRIMARY KEY (`tagNo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `tag_board_relation` (
  `tagNo` int NOT NULL,
  `boardNo` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


