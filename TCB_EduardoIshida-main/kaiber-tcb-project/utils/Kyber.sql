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
SHOW WARNINGS;
USE `Kyber` ;

-- -----------------------------------------------------
-- Table `Kyber`.`site`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Kyber`.`site` (
  `idsite` INT NOT NULL,
  `nome` VARCHAR(45) NOT NULL,
  `cidade` VARCHAR(45) NOT NULL,
  `pais` VARCHAR(45) NOT NULL,
  `visitavel` TINYINT NOT NULL,
  PRIMARY KEY (`idsite`, `nome`, `cidade`, `pais`, `visitavel`),
  UNIQUE INDEX `idsite_UNIQUE` (`idsite` ASC) VISIBLE)
ENGINE = InnoDB;

SHOW WARNINGS;

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
  PRIMARY KEY (`idRochas`, `tipo`, `dureza`, `corPrincipal`, `isitgem`, `site_idsite`),
  UNIQUE INDEX `idRochas_UNIQUE` (`idRochas` ASC) VISIBLE,
  INDEX `fk_Rochas_site1_idx` (`site_idsite` ASC) VISIBLE,
  CONSTRAINT `fk_Rochas_site1`
    FOREIGN KEY (`site_idsite`)
    REFERENCES `Kyber`.`site` (`idsite`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;

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
  PRIMARY KEY (`idminerais`, `tipo`, `dureza`, `cor`, `brilho`, `toxicidade`, `site_idsite`),
  INDEX `fk_minerais_site1_idx` (`site_idsite` ASC) VISIBLE,
  CONSTRAINT `fk_minerais_site1`
    FOREIGN KEY (`site_idsite`)
    REFERENCES `Kyber`.`site` (`idsite`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `Kyber`.`dureza`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Kyber`.`dureza` (
)
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `Kyber`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Kyber`.`user` (
  `iduser` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `instituicao` VARCHAR(45) NOT NULL,
  `cargo` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`iduser`, `nome`, `instituicao`, `cargo`))
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `Kyber`.`emprestimo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Kyber`.`emprestimo` (
  `idemprestimo` INT NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`idemprestimo`))
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `Kyber`.`user_has_emprestimo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Kyber`.`user_has_emprestimo` (
  `user_iduser` INT NOT NULL,
  `user_nome` VARCHAR(45) NOT NULL,
  `user_instituicao` VARCHAR(45) NOT NULL,
  `user_cargo` VARCHAR(45) NOT NULL,
  `emprestimo_idemprestimo` INT NOT NULL,
  PRIMARY KEY (`user_iduser`, `user_nome`, `user_instituicao`, `user_cargo`, `emprestimo_idemprestimo`),
  INDEX `fk_user_has_emprestimo_emprestimo1_idx` (`emprestimo_idemprestimo` ASC) VISIBLE,
  INDEX `fk_user_has_emprestimo_user1_idx` (`user_iduser` ASC, `user_nome` ASC, `user_instituicao` ASC, `user_cargo` ASC) VISIBLE,
  CONSTRAINT `fk_user_has_emprestimo_user1`
    FOREIGN KEY (`user_iduser` , `user_nome` , `user_instituicao` , `user_cargo`)
    REFERENCES `Kyber`.`user` (`iduser` , `nome` , `instituicao` , `cargo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_emprestimo_emprestimo1`
    FOREIGN KEY (`emprestimo_idemprestimo`)
    REFERENCES `Kyber`.`emprestimo` (`idemprestimo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `Kyber`.`emprestimo_has_minerais`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Kyber`.`emprestimo_has_minerais` (
  `emprestimo_idemprestimo` INT NOT NULL,
  `minerais_idminerais` INT UNSIGNED NOT NULL,
  `minerais_tipo` VARCHAR(45) NOT NULL,
  `minerais_dureza` FLOAT NOT NULL,
  `minerais_cor` VARCHAR(45) NOT NULL,
  `minerais_brilho` VARCHAR(45) NOT NULL,
  `minerais_toxicidade` VARCHAR(45) NOT NULL,
  `minerais_site_idsite` INT NOT NULL,
  PRIMARY KEY (`emprestimo_idemprestimo`, `minerais_idminerais`, `minerais_tipo`, `minerais_dureza`, `minerais_cor`, `minerais_brilho`, `minerais_toxicidade`, `minerais_site_idsite`),
  INDEX `fk_emprestimo_has_minerais_minerais1_idx` (`minerais_idminerais` ASC, `minerais_tipo` ASC, `minerais_dureza` ASC, `minerais_cor` ASC, `minerais_brilho` ASC, `minerais_toxicidade` ASC, `minerais_site_idsite` ASC) VISIBLE,
  INDEX `fk_emprestimo_has_minerais_emprestimo1_idx` (`emprestimo_idemprestimo` ASC) VISIBLE,
  CONSTRAINT `fk_emprestimo_has_minerais_emprestimo1`
    FOREIGN KEY (`emprestimo_idemprestimo`)
    REFERENCES `Kyber`.`emprestimo` (`idemprestimo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_emprestimo_has_minerais_minerais1`
    FOREIGN KEY (`minerais_idminerais` , `minerais_tipo` , `minerais_dureza` , `minerais_cor` , `minerais_brilho` , `minerais_toxicidade` , `minerais_site_idsite`)
    REFERENCES `Kyber`.`minerais` (`idminerais` , `tipo` , `dureza` , `cor` , `brilho` , `toxicidade` , `site_idsite`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `Kyber`.`emprestimo_has_Rochas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Kyber`.`emprestimo_has_Rochas` (
  `emprestimo_idemprestimo` INT NOT NULL,
  `Rochas_idRochas` INT NOT NULL,
  `Rochas_tipo` VARCHAR(45) NOT NULL,
  `Rochas_dureza` VARCHAR(45) NOT NULL,
  `Rochas_corPrincipal` VARCHAR(45) NOT NULL,
  `Rochas_isitgem` TINYINT UNSIGNED NOT NULL,
  `Rochas_site_idsite` INT NOT NULL,
  PRIMARY KEY (`emprestimo_idemprestimo`, `Rochas_idRochas`, `Rochas_tipo`, `Rochas_dureza`, `Rochas_corPrincipal`, `Rochas_isitgem`, `Rochas_site_idsite`),
  INDEX `fk_emprestimo_has_Rochas_Rochas1_idx` (`Rochas_idRochas` ASC, `Rochas_tipo` ASC, `Rochas_dureza` ASC, `Rochas_corPrincipal` ASC, `Rochas_isitgem` ASC, `Rochas_site_idsite` ASC) VISIBLE,
  INDEX `fk_emprestimo_has_Rochas_emprestimo1_idx` (`emprestimo_idemprestimo` ASC) VISIBLE,
  CONSTRAINT `fk_emprestimo_has_Rochas_emprestimo1`
    FOREIGN KEY (`emprestimo_idemprestimo`)
    REFERENCES `Kyber`.`emprestimo` (`idemprestimo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_emprestimo_has_Rochas_Rochas1`
    FOREIGN KEY (`Rochas_idRochas` , `Rochas_tipo` , `Rochas_dureza` , `Rochas_corPrincipal` , `Rochas_isitgem` , `Rochas_site_idsite`)
    REFERENCES `Kyber`.`Rochas` (`idRochas` , `tipo` , `dureza` , `corPrincipal` , `isitgem` , `site_idsite`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
