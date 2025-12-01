SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema Kyber
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `Kyber`;
CREATE SCHEMA IF NOT EXISTS `Kyber` DEFAULT CHARACTER SET utf8mb3;
USE `Kyber`;

-- -----------------------------------------------------
-- Table `Kyber`.`site`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Kyber`.`site` (
  `idsite` INT NOT NULL AUTO_INCREMENT,  
  `nome` VARCHAR(45) NOT NULL,
  `cidade` VARCHAR(45) NOT NULL,
  `estado` VARCHAR(45) NOT NULL,
  `pais` VARCHAR(45) NOT NULL,
  `propriedadeprivada` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idsite`)
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `Kyber`.`Rochas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Kyber`.`Rochas` (
  `idRochas` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `tipo` VARCHAR(45) NOT NULL,
  `dureza` VARCHAR(45) NOT NULL,
  `corPrincipal` VARCHAR(45) NOT NULL,
  `isitgem` TINYINT NOT NULL,
  `site_idsite` INT NOT NULL,
  PRIMARY KEY (`idRochas`),
  INDEX `fk_Rochas_site_idx` (`site_idsite` ASC) VISIBLE,
  CONSTRAINT `fk_Rochas_site`
    FOREIGN KEY (`site_idsite`)
    REFERENCES `Kyber`.`site` (`idsite`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `Kyber`.`minerais`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Kyber`.`minerais` (
  `idminerais` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `tipo` VARCHAR(45) NOT NULL,
  `dureza` FLOAT NOT NULL,
  `cor` VARCHAR(45) NOT NULL,
  `brilho` VARCHAR(45) NOT NULL,
  `toxicidade` VARCHAR(45) NOT NULL,
  `site_idsite` INT NOT NULL,
  PRIMARY KEY (`idminerais`),
  INDEX `fk_minerais_site_idx` (`site_idsite` ASC) VISIBLE,
  CONSTRAINT `fk_minerais_site`
    FOREIGN KEY (`site_idsite`)
    REFERENCES `Kyber`.`site` (`idsite`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `Kyber`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Kyber`.`user` (
  `iduser` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `instituicao` VARCHAR(45) NOT NULL,
  `cargo` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`iduser`)
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `Kyber`.`emprestimo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Kyber`.`emprestimo` (
  `idemprestimo` INT NOT NULL AUTO_INCREMENT,
  `dataEmp` VARCHAR(45) NOT NULL,
  `dataDev` VARCHAR(45) NOT NULL,
  `usuario_iduser` INT NOT NULL,
  PRIMARY KEY (`idemprestimo`),
  INDEX `fk_emprestimo_user_idx` (`usuario_iduser` ASC) VISIBLE,
  CONSTRAINT `fk_emprestimo_user`
    FOREIGN KEY (`usuario_iduser`)
    REFERENCES `Kyber`.`user` (`iduser`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `Kyber`.`emprestimo_has_minerais`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Kyber`.`emprestimo_has_minerais` (
  `emprestimo_idemprestimo` INT NOT NULL,
  `minerais_idminerais` INT NOT NULL,
  PRIMARY KEY (`emprestimo_idemprestimo`, `minerais_idminerais`),
  INDEX `fk_emprestimo_has_minerais_minerais_idx` (`minerais_idminerais` ASC) VISIBLE,
  INDEX `fk_emprestimo_has_minerais_emprestimo_idx` (`emprestimo_idemprestimo` ASC) VISIBLE,
  CONSTRAINT `fk_emprestimo_has_minerais_emprestimo`
    FOREIGN KEY (`emprestimo_idemprestimo`)
    REFERENCES `Kyber`.`emprestimo` (`idemprestimo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_emprestimo_has_minerais_minerais`
    FOREIGN KEY (`minerais_idminerais`)
    REFERENCES `Kyber`.`minerais` (`idminerais`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `Kyber`.`emprestimo_has_Rochas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Kyber`.`emprestimo_has_Rochas` (
  `emprestimo_idemprestimo` INT NOT NULL,
  `Rochas_idRochas` INT NOT NULL,
  PRIMARY KEY (`emprestimo_idemprestimo`, `Rochas_idRochas`),
  INDEX `fk_emprestimo_has_Rochas_Rochas_idx` (`Rochas_idRochas` ASC) VISIBLE,
  INDEX `fk_emprestimo_has_Rochas_emprestimo_idx` (`emprestimo_idemprestimo` ASC) VISIBLE,
  CONSTRAINT `fk_emprestimo_has_Rochas_emprestimo`
    FOREIGN KEY (`emprestimo_idemprestimo`)
    REFERENCES `Kyber`.`emprestimo` (`idemprestimo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_emprestimo_has_Rochas_Rochas`
    FOREIGN KEY (`Rochas_idRochas`)
    REFERENCES `Kyber`.`Rochas` (`idRochas`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb3;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;