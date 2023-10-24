-- --------------------------------------------------------
-- Servidor:                     127.0.0.1
-- Versão do servidor:           8.0.31 - MySQL Community Server - GPL
-- OS do Servidor:               Win64
-- HeidiSQL Versão:              12.5.0.6677
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Copiando estrutura do banco de dados para nfe_now
CREATE DATABASE IF NOT EXISTS `nfe_now` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `nfe_now`;

-- Copiando estrutura para tabela nfe_now.notas_fiscais
CREATE TABLE IF NOT EXISTS `notas_fiscais` (
  `id` int NOT NULL AUTO_INCREMENT,
  `numero_nota_fiscal` varchar(9) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `numero_serie` varchar(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `razao_social` varchar(80) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `cnpj` varchar(14) NOT NULL,
  `endereco` varchar(125) NOT NULL,
  `valor` double NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK33wcnrbjoq1p4h4vrpla0q59s` (`numero_nota_fiscal`,`numero_serie`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Copiando dados para a tabela nfe_now.notas_fiscais: ~15 rows (aproximadamente)
INSERT INTO `notas_fiscais` (`id`, `numero_nota_fiscal`, `numero_serie`, `razao_social`, `cnpj`, `endereco`, `valor`) VALUES
	(1, '000000005', '1', 'Juliana e Maria Filmagens Ltda', '23482453000105', 'Rua Maria Siqueira, 313 - Jardim Silveira, Barueri - São Paulo', 300.85),
	(2, '000000001', '1', 'Manoel e Tânia Casa Noturna Ltda', '11473681000121', 'Rua da Linhaça, 156 - Jardim Belva - Carapicuíba - São Paulo', 2850.25),
	(3, '000000002', '1', 'Manoel e Tânia Casa Noturna Ltda', '11473681000121', 'Rua da Linhaça, 156 - Jardim Belva - Carapicuíba - São Paulo', 145.24),
	(4, '000000003', '1', 'Manoel e Tânia Casa Noturna Ltda', '11473681000121', 'Rua da Linhaça, 156 - Jardim Belva - Carapicuíba - São Paulo', 1645.85),
	(5, '000000004', '1', 'Manoel e Tânia Casa Noturna Ltda', '11473681000121', 'Rua da Linhaça, 156 - Jardim Belva - Carapicuíba - São Paulo', 685.41),
	(6, '000000006', '1', 'Juliana e Maria Filmagens Ltda', '23482453000105', 'Rua Maria Siqueira, 313 - Jardim Silveira, Barueri - São Paulo', 237.41),
	(7, '000000007', '1', 'Juliana e Maria Filmagens Ltda', '23482453000105', 'Rua Maria Siqueira, 313 - Jardim Silveira, Barueri - São Paulo', 15425.14),
	(10, '000000001', '2', 'Juliana e Maria Filmagens Ltda', '23482453000105', 'Rua Maria Siqueira, 313 - Jardim Silveira, Barueri - São Paulo', 415.15),
	(12, '000000002', '2', 'Juliana e Maria Filmagens Ltda', '23482453000105', 'Rua Maria Siqueira, 313 - Jardim Silveira, Barueri - São Paulo', 963.45),
	(13, '000000003', '2', 'Juliana e Maria Filmagens Ltda', '23482453000105', 'Rua Maria Siqueira, 313 - Jardim Silveira, Barueri - São Paulo', 147.58),
	(14, '000000004', '2', 'Juliana e Maria Filmagens Ltda', '23482453000105', 'Rua Maria Siqueira, 313 - Jardim Silveira, Barueri - São Paulo', 11478.85),
	(15, '000000001', '3', 'Juliana e Maria Filmagens Ltda', '23482453000105', 'Rua Maria Siqueira, 313 - Jardim Silveira, Barueri - São Paulo', 156.24),
	(16, '000000002', '3', 'Juliana e Maria Filmagens Ltda', '23482453000105', 'Rua Maria Siqueira, 313 - Jardim Silveira, Barueri - São Paulo', 8758.41),
	(17, '000000003', '3', 'Juliana e Maria Filmagens Ltda', '23482453000105', 'Rua Maria Siqueira, 313 - Jardim Silveira, Barueri - São Paulo', 693.14),
	(18, '000000004', '3', 'Juliana e Maria Filmagens Ltda', '23482453000105', 'Rua Maria Siqueira, 313 - Jardim Silveira, Barueri - São Paulo', 25.85);

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
