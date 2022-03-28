-- api_product_db.tb_product definition

		CREATE TABLE `tb_product` (
		  `id` bigint(20) NOT NULL AUTO_INCREMENT,
		  `name` varchar(255) NOT NULL,
		  `sku` bigint(20) NOT NULL,
		  PRIMARY KEY (`id`),
		  UNIQUE KEY `UK_sku` (`sku`)
		) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- api_product_db.tb_warehouse definition

		CREATE TABLE `tb_warehouse` (
		  `id` bigint(20) NOT NULL AUTO_INCREMENT,
		  `locality` varchar(255) NOT NULL,
		  `product_id` bigint(20) NOT NULL,
		  `quantity` int(11) NOT NULL,
		  `type` varchar(255) NOT NULL,
		  PRIMARY KEY (`id`),
		  KEY `FKproduct_id` (`product_id`),
		  CONSTRAINT `FKproduct_id` FOREIGN KEY (`product_id`) REFERENCES `tb_product` (`id`)
		) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;