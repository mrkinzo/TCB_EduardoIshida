-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
SHOW WARNINGS;
-- -----------------------------------------------------
-- Schema Kyber
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema Kyber
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `Kyber` DEFAULT CHARACTER SET utf8mb3 ;
SHOW WARNINGS;
USE `Kyber` ;

-- -----------------------------------------------------
-- Table `Kyber`.`site`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Kyber`.`site` (
  `idsite` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `cidade` VARCHAR(45) NOT NULL,
  `país` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idsite`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `Kyber`.`Rochas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Kyber`.`Rochas` (
  `idRochas` INT NOT NULL,
  `tipo` VARCHAR(45) NOT NULL,
  `dureza` VARCHAR(45) NOT NULL,
  `corPrincipal` VARCHAR(45) NOT NULL,
  `isitgem` TINYINT NOT NULL COMMENT 'Assumed to mean \"is it a gem\" (0 or 1)',
  `emprestado` VARCHAR(45) NOT NULL,
  `site_idsite` INT NOT NULL,
  PRIMARY KEY (`idRochas`, `site_idsite`),
  INDEX `fk_Rochas_site_idx` (`site_idsite` ASC) VISIBLE,
  CONSTRAINT `fk_Rochas_site`
    FOREIGN KEY (`site_idsite`)
    REFERENCES `Kyber`.`site` (`idsite`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `Kyber`.`dureza`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Kyber`.`dureza` (
  `iddureza` INT NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`iddureza`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `Kyber`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Kyber`.`user` (
  `iduser` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `instituicao` VARCHAR(45) NOT NULL,
  `cargo` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`iduser`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `Kyber`.`emprestimo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Kyber`.`emprestimo` (
  `idemprestimo` INT NOT NULL,
  `user_iduser` INT NOT NULL,
  `dataDev` VARCHAR(45) NOT NULL,
  `dataEmp` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idemprestimo`, `user_iduser`),
  INDEX `fk_emprestimo_user1_idx` (`user_iduser` ASC) VISIBLE,
  CONSTRAINT `fk_emprestimo_user1`
    FOREIGN KEY (`user_iduser`)
    REFERENCES `Kyber`.`user` (`iduser`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `Kyber`.`minerais`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Kyber`.`minerais` (
  `idminerais` INT UNSIGNED NOT NULL,
  `tipo` VARCHAR(45) NOT NULL,
  `dureza` FLOAT NOT NULL,
  `cor` VARCHAR(45) NOT NULL,
  `brilho` VARCHAR(45) NOT NULL,
  `toxicidade` VARCHAR(45) NOT NULL,
  `site_idsite` INT NOT NULL,
  `emprestado` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idminerais`, `site_idsite`),
  INDEX `fk_minerais_site1_idx` (`site_idsite` ASC) VISIBLE,
  CONSTRAINT `fk_minerais_site1`
    FOREIGN KEY (`site_idsite`)
    REFERENCES `Kyber`.`site` (`idsite`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;

SHOW WARNINGS;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
