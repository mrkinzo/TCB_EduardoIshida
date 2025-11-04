-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema KaiberP
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema KaiberP
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `KaiberP` ;
SHOW WARNINGS;
USE `KaiberP` ;

-- -----------------------------------------------------
-- Table `KaiberP`.`site`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `KaiberP`.`site` (
  `idsite` INT NOT NULL,
  `nome` VARCHAR(45) NOT NULL,
  `cidade` VARCHAR(45) NOT NULL,
  `pais` VARCHAR(45) NOT NULL,
  `propriedadeprivada` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idsite`, `nome`, `cidade`, `pais`, `propriedadeprivada`),
  UNIQUE INDEX `idsite_UNIQUE` (`idsite` ASC) VISIBLE)
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `KaiberP`.`Rochas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `KaiberP`.`Rochas` (
  `idRochas` INT NOT NULL AUTO_INCREMENT,
  `tipo` VARCHAR(45) NOT NULL,
  `dureza` VARCHAR(45) NOT NULL,
  `corPrincipal` VARCHAR(45) NOT NULL,
  `composicaoPrincipal` VARCHAR(45) NOT NULL,
  `isitgem` TINYINT NOT NULL,
  `site_idsite` INT NOT NULL,
  `site_nome` VARCHAR(45) NOT NULL,
  `site_cidade` VARCHAR(45) NOT NULL,
  `site_pais` VARCHAR(45) NOT NULL,
  `site_propriedadeprivada` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idRochas`, `tipo`, `dureza`, `corPrincipal`, `composicaoPrincipal`, `isitgem`, `site_idsite`, `site_nome`, `site_cidade`, `site_pais`, `site_propriedadeprivada`),
  UNIQUE INDEX `idRochas_UNIQUE` (`idRochas` ASC) VISIBLE,
  INDEX `fk_Rochas_site_idx` (`site_idsite` ASC, `site_nome` ASC, `site_cidade` ASC, `site_pais` ASC, `site_propriedadeprivada` ASC) VISIBLE,
  CONSTRAINT `fk_Rochas_site`
    FOREIGN KEY (`site_idsite` , `site_nome` , `site_cidade` , `site_pais` , `site_propriedadeprivada`)
    REFERENCES `KaiberP`.`site` (`idsite` , `nome` , `cidade` , `pais` , `propriedadeprivada`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `KaiberP`.`minerais`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `KaiberP`.`minerais` (
  `idminerais` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `tipo` VARCHAR(45) NOT NULL,
  `dureza` FLOAT NOT NULL,
  `cor` VARCHAR(45) NOT NULL,
  `brilho` VARCHAR(45) NOT NULL,
  `toxicidade` VARCHAR(45) NOT NULL,
  `site_idsite` INT NOT NULL,
  `site_nome` VARCHAR(45) NOT NULL,
  `site_cidade` VARCHAR(45) NOT NULL,
  `site_pais` VARCHAR(45) NOT NULL,
  `site_propriedadeprivada` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idminerais`, `tipo`, `dureza`, `cor`, `brilho`, `toxicidade`, `site_idsite`, `site_nome`, `site_cidade`, `site_pais`, `site_propriedadeprivada`),
  INDEX `fk_minerais_site1_idx` (`site_idsite` ASC, `site_nome` ASC, `site_cidade` ASC, `site_pais` ASC, `site_propriedadeprivada` ASC) VISIBLE,
  CONSTRAINT `fk_minerais_site1`
    FOREIGN KEY (`site_idsite` , `site_nome` , `site_cidade` , `site_pais` , `site_propriedadeprivada`)
    REFERENCES `KaiberP`.`site` (`idsite` , `nome` , `cidade` , `pais` , `propriedadeprivada`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `KaiberP`.`dureza`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `KaiberP`.`dureza` (
)
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `KaiberP`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `KaiberP`.`user` (
  `iduser` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `instituicao` VARCHAR(45) NOT NULL,
  `cargo` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`iduser`, `nome`, `instituicao`, `cargo`))
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `KaiberP`.`user_has_minerais`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `KaiberP`.`user_has_minerais` (
  `user_iduser` INT NOT NULL,
  `user_nome` VARCHAR(45) NOT NULL,
  `user_instituicao` VARCHAR(45) NOT NULL,
  `user_cargo` VARCHAR(45) NOT NULL,
  `minerais_idminerais` INT UNSIGNED NOT NULL,
  `minerais_tipo` VARCHAR(45) NOT NULL,
  `minerais_dureza` FLOAT NOT NULL,
  `minerais_cor` VARCHAR(45) NOT NULL,
  `minerais_brilho` VARCHAR(45) NOT NULL,
  `minerais_toxicidade` VARCHAR(45) NOT NULL,
  `minerais_site_idsite` INT NOT NULL,
  `minerais_site_nome` VARCHAR(45) NOT NULL,
  `minerais_site_cidade` VARCHAR(45) NOT NULL,
  `minerais_site_pais` VARCHAR(45) NOT NULL,
  `minerais_site_propriedadeprivada` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`user_iduser`, `user_nome`, `user_instituicao`, `user_cargo`, `minerais_idminerais`, `minerais_tipo`, `minerais_dureza`, `minerais_cor`, `minerais_brilho`, `minerais_toxicidade`, `minerais_site_idsite`, `minerais_site_nome`, `minerais_site_cidade`, `minerais_site_pais`, `minerais_site_propriedadeprivada`),
  INDEX `fk_user_has_minerais_minerais1_idx` (`minerais_idminerais` ASC, `minerais_tipo` ASC, `minerais_dureza` ASC, `minerais_cor` ASC, `minerais_brilho` ASC, `minerais_toxicidade` ASC, `minerais_site_idsite` ASC, `minerais_site_nome` ASC, `minerais_site_cidade` ASC, `minerais_site_pais` ASC, `minerais_site_propriedadeprivada` ASC) VISIBLE,
  INDEX `fk_user_has_minerais_user1_idx` (`user_iduser` ASC, `user_nome` ASC, `user_instituicao` ASC, `user_cargo` ASC) VISIBLE,
  CONSTRAINT `fk_user_has_minerais_user1`
    FOREIGN KEY (`user_iduser` , `user_nome` , `user_instituicao` , `user_cargo`)
    REFERENCES `KaiberP`.`user` (`iduser` , `nome` , `instituicao` , `cargo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_minerais_minerais1`
    FOREIGN KEY (`minerais_idminerais` , `minerais_tipo` , `minerais_dureza` , `minerais_cor` , `minerais_brilho` , `minerais_toxicidade` , `minerais_site_idsite` , `minerais_site_nome` , `minerais_site_cidade` , `minerais_site_pais` , `minerais_site_propriedadeprivada`)
    REFERENCES `KaiberP`.`minerais` (`idminerais` , `tipo` , `dureza` , `cor` , `brilho` , `toxicidade` , `site_idsite` , `site_nome` , `site_cidade` , `site_pais` , `site_propriedadeprivada`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `KaiberP`.`user_has_Rochas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `KaiberP`.`user_has_Rochas` (
  `user_iduser` INT NOT NULL,
  `user_nome` VARCHAR(45) NOT NULL,
  `user_instituicao` VARCHAR(45) NOT NULL,
  `user_cargo` VARCHAR(45) NOT NULL,
  `Rochas_idRochas` INT NOT NULL,
  `Rochas_tipo` VARCHAR(45) NOT NULL,
  `Rochas_dureza` VARCHAR(45) NOT NULL,
  `Rochas_corPrincipal` VARCHAR(45) NOT NULL,
  `Rochas_composicaoPrincipal` VARCHAR(45) NOT NULL,
  `Rochas_isitgem` TINYINT NOT NULL,
  `Rochas_site_idsite` INT NOT NULL,
  `Rochas_site_nome` VARCHAR(45) NOT NULL,
  `Rochas_site_cidade` VARCHAR(45) NOT NULL,
  `Rochas_site_pais` VARCHAR(45) NOT NULL,
  `Rochas_site_propriedadeprivada` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`user_iduser`, `user_nome`, `user_instituicao`, `user_cargo`, `Rochas_idRochas`, `Rochas_tipo`, `Rochas_dureza`, `Rochas_corPrincipal`, `Rochas_composicaoPrincipal`, `Rochas_isitgem`, `Rochas_site_idsite`, `Rochas_site_nome`, `Rochas_site_cidade`, `Rochas_site_pais`, `Rochas_site_propriedadeprivada`),
  INDEX `fk_user_has_Rochas_Rochas1_idx` (`Rochas_idRochas` ASC, `Rochas_tipo` ASC, `Rochas_dureza` ASC, `Rochas_corPrincipal` ASC, `Rochas_composicaoPrincipal` ASC, `Rochas_isitgem` ASC, `Rochas_site_idsite` ASC, `Rochas_site_nome` ASC, `Rochas_site_cidade` ASC, `Rochas_site_pais` ASC, `Rochas_site_propriedadeprivada` ASC) VISIBLE,
  INDEX `fk_user_has_Rochas_user1_idx` (`user_iduser` ASC, `user_nome` ASC, `user_instituicao` ASC, `user_cargo` ASC) VISIBLE,
  CONSTRAINT `fk_user_has_Rochas_user1`
    FOREIGN KEY (`user_iduser` , `user_nome` , `user_instituicao` , `user_cargo`)
    REFERENCES `KaiberP`.`user` (`iduser` , `nome` , `instituicao` , `cargo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_Rochas_Rochas1`
    FOREIGN KEY (`Rochas_idRochas` , `Rochas_tipo` , `Rochas_dureza` , `Rochas_corPrincipal` , `Rochas_composicaoPrincipal` , `Rochas_isitgem` , `Rochas_site_idsite` , `Rochas_site_nome` , `Rochas_site_cidade` , `Rochas_site_pais` , `Rochas_site_propriedadeprivada`)
    REFERENCES `KaiberP`.`Rochas` (`idRochas` , `tipo` , `dureza` , `corPrincipal` , `composicaoPrincipal` , `isitgem` , `site_idsite` , `site_nome` , `site_cidade` , `site_pais` , `site_propriedadeprivada`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
