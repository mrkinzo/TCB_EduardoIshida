-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema Kyber
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema Kyber
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `Kyber` DEFAULT CHARACTER SET utf8 ;
USE `Kyber` ;

-- -----------------------------------------------------
-- Table `Kyber`.`site`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Kyber`.`site` (
  `idsite` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `cidade` VARCHAR(45) NOT NULL,
  `pais` VARCHAR(45) NOT NULL,
  `visitavel` TINYINT NOT NULL,
  PRIMARY KEY (`idsite`),
  UNIQUE INDEX `idsite_UNIQUE` (`idsite` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Kyber`.`Rochas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Kyber`.`Rochas` (
  `idRochas` INT NOT NULL AUTO_INCREMENT,
  `tipo` VARCHAR(45) NOT NULL,
  `dureza` VARCHAR(45) NOT NULL,
  `corPrincipal` VARCHAR(45) NOT NULL,
  `isitgem` TINYINT UNSIGNED NOT NULL,
  `site_idsite` INT NOT NULL,
  PRIMARY KEY (`idRochas`, `site_idsite`),
  UNIQUE INDEX `idRochas_UNIQUE` (`idRochas` ASC) VISIBLE,
  INDEX `fk_Rochas_site1_idx` (`site_idsite` ASC) VISIBLE,
  CONSTRAINT `fk_Rochas_site1`
    FOREIGN KEY (`site_idsite`)
    REFERENCES `Kyber`.`site` (`idsite`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Kyber`.`minerais`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Kyber`.`minerais` (
  `idminerais` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `tipo` VARCHAR(45) NOT NULL,
  `dureza` FLOAT NOT NULL,
  `cor` VARCHAR(45) NOT NULL,
  `brilho` VARCHAR(45) NOT NULL,
  `toxicidade` VARCHAR(45) NOT NULL,
  `site_idsite` INT NOT NULL,
  PRIMARY KEY (`idminerais`, `site_idsite`),
  INDEX `fk_minerais_site1_idx` (`site_idsite` ASC) VISIBLE,
  CONSTRAINT `fk_minerais_site1`
    FOREIGN KEY (`site_idsite`)
    REFERENCES `Kyber`.`site` (`idsite`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Kyber`.`dureza`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Kyber`.`dureza` (
)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Kyber`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Kyber`.`user` (
  `iduser` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `instituicao` VARCHAR(45) NOT NULL,
  `cargo` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`iduser`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Kyber`.`emprestimo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Kyber`.`emprestimo` (
  `idemprestimo` INT NOT NULL AUTO_INCREMENT,
  `dataEmp` DATE NOT NULL,
  `dataDev` DATE NOT NULL,
  `status` VARCHAR(45) NOT NULL,
  `user_iduser` INT NOT NULL,
  PRIMARY KEY (`idemprestimo`, `user_iduser`),
  INDEX `fk_emprestimo_user1_idx` (`user_iduser` ASC) VISIBLE,
  CONSTRAINT `fk_emprestimo_user1`
    FOREIGN KEY (`user_iduser`)
    REFERENCES `Kyber`.`user` (`iduser`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Kyber`.`emprestimo_has_minerais`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Kyber`.`emprestimo_has_minerais` (
  `emprestimo_idemprestimo` INT NOT NULL,
  `emprestimo_user_iduser` INT NOT NULL,
  `minerais_idminerais` INT UNSIGNED NOT NULL,
  `minerais_site_idsite` INT NOT NULL,
  PRIMARY KEY (`emprestimo_idemprestimo`, `emprestimo_user_iduser`, `minerais_idminerais`, `minerais_site_idsite`),
  INDEX `fk_emprestimo_has_minerais_minerais1_idx` (`minerais_idminerais` ASC, `minerais_site_idsite` ASC) VISIBLE,
  INDEX `fk_emprestimo_has_minerais_emprestimo1_idx` (`emprestimo_idemprestimo` ASC, `emprestimo_user_iduser` ASC) VISIBLE,
  CONSTRAINT `fk_emprestimo_has_minerais_emprestimo1`
    FOREIGN KEY (`emprestimo_idemprestimo` , `emprestimo_user_iduser`)
    REFERENCES `Kyber`.`emprestimo` (`idemprestimo` , `user_iduser`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_emprestimo_has_minerais_minerais1`
    FOREIGN KEY (`minerais_idminerais` , `minerais_site_idsite`)
    REFERENCES `Kyber`.`minerais` (`idminerais` , `site_idsite`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Kyber`.`emprestimo_has_Rochas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Kyber`.`emprestimo_has_Rochas` (
  `emprestimo_idemprestimo` INT NOT NULL,
  `emprestimo_user_iduser` INT NOT NULL,
  `Rochas_idRochas` INT NOT NULL,
  `Rochas_site_idsite` INT NOT NULL,
  PRIMARY KEY (`emprestimo_idemprestimo`, `emprestimo_user_iduser`, `Rochas_idRochas`, `Rochas_site_idsite`),
  INDEX `fk_emprestimo_has_Rochas_Rochas1_idx` (`Rochas_idRochas` ASC, `Rochas_site_idsite` ASC) VISIBLE,
  INDEX `fk_emprestimo_has_Rochas_emprestimo1_idx` (`emprestimo_idemprestimo` ASC, `emprestimo_user_iduser` ASC) VISIBLE,
  CONSTRAINT `fk_emprestimo_has_Rochas_emprestimo1`
    FOREIGN KEY (`emprestimo_idemprestimo` , `emprestimo_user_iduser`)
    REFERENCES `Kyber`.`emprestimo` (`idemprestimo` , `user_iduser`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_emprestimo_has_Rochas_Rochas1`
    FOREIGN KEY (`Rochas_idRochas` , `Rochas_site_idsite`)
    REFERENCES `Kyber`.`Rochas` (`idRochas` , `site_idsite`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
