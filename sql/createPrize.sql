CREATE TABLE `price` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `value` double(8,2) NOT NULL,
  `time` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  `idstock` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `PriceStock` (`idstock`),
  CONSTRAINT `PriceStock` FOREIGN KEY (`idstock`) REFERENCES `stock` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;