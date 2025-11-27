-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema Kyber
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema Kyber
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `Kyber` DEFAULT CHARACTER SET utf8mb3 ;
USE `Kyber` ;

-- -----------------------------------------------------
-- Table `Kyber`.`site`
-- -----------------------------------------------------
-- ALTERAR A TABELA PARA AUTO_INCREMENT
ALTER TABLE `Kyber`.`site` 
MODIFY COLUMN `idsite` INT NOT NULL AUTO_INCREMENT;

-- Ou recriar a tabela corretamente:
CREATE TABLE IF NOT EXISTS `Kyber`.`site` (
  `idsite` INT NOT NULL AUTO_INCREMENT,  
  `nome` VARCHAR(45) NOT NULL,
  `cidade` VARCHAR(45) NOT NULL,
  `pais` VARCHAR(45) NOT NULL,
  `propriedadeprivada` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idsite`)
) ENGINE = InnoDB;
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `Kyber`.`Rochas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Kyber`.`Rochas` (
  `idRochas` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `tipo` VARCHAR(45) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_0900_ai_ci' NOT NULL,
  `dureza` VARCHAR(45) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_0900_ai_ci' NOT NULL,
  `corPrincipal` VARCHAR(45) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_0900_ai_ci' NOT NULL,
  `isitgem` TINYINT NOT NULL,
  `site_idsite` INT NOT NULL,
  PRIMARY KEY (`idRochas`, `site_idsite`),
  UNIQUE INDEX `idRochas_UNIQUE` (`idRochas` ASC) VISIBLE,
  INDEX `fk_Rochas_site1_idx` (`site_idsite` ASC) VISIBLE,
  CONSTRAINT `fk_Rochas_site1`
    FOREIGN KEY (`site_idsite`)
    REFERENCES `Kyber`.`site` (`idsite`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `Kyber`.`minerais`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Kyber`.`minerais` (
  `idminerais` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `tipo` VARCHAR(45) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_0900_ai_ci' NOT NULL,
  `dureza` FLOAT NOT NULL,
  `cor` VARCHAR(45) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_0900_ai_ci' NOT NULL,
  `brilho` VARCHAR(45) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_0900_ai_ci' NOT NULL,
  `toxicidade` VARCHAR(45) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_0900_ai_ci' NOT NULL,
  `site_idsite` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idminerais`, `site_idsite`),
  INDEX `fk_minerais_site_idx` (`site_idsite` ASC) VISIBLE,
  CONSTRAINT `fk_minerais_site`
    FOREIGN KEY (`site_idsite`)
    REFERENCES `Kyber`.`site` (`idsite`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `Kyber`.`usuario`
-- -----------------------------------------------------
ALTER TABLE `Kyber`.`user` 
MODIFY COLUMN `iduser` INT NOT NULL AUTO_INCREMENT;

ALTER TABLE `Kyber`.`user` 
MODIFY COLUMN `iduser` INT NOT NULL AUTO_INCREMENT;


CREATE TABLE IF NOT EXISTS `Kyber`.`user` (
  `iduser` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `instituicao` VARCHAR(45) NOT NULL,
  `cargo` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`iduser`)
) ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Kyber`.`emprestimo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Kyber`.`emprestimo` (
  `idemprestimo` INT NOT NULL,
  `dataEmp` VARCHAR(45) NOT NULL,
  `dataDev` VARCHAR(45) NOT NULL,
  `usuario_idusuario` INT NOT NULL,
  PRIMARY KEY (`idemprestimo`, `usuario_idusuario`),
  INDEX `fk_emprestimo_usuario1_idx` (`usuario_idusuario` ASC) VISIBLE,
  CONSTRAINT `fk_emprestimo_usuario1`
    FOREIGN KEY (`usuario_idusuario`)
    REFERENCES `Kyber`.`usuario` (`idusuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Kyber`.`emprestimo_has_minerais`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Kyber`.`emprestimo_has_minerais` (
  `emprestimo_idemprestimo` INT NOT NULL,
  `emprestimo_usuario_idusuario` INT NOT NULL,
  `minerais_idminerais` INT UNSIGNED NOT NULL,
  `minerais_site_idsite` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`emprestimo_idemprestimo`, `emprestimo_usuario_idusuario`, `minerais_idminerais`, `minerais_site_idsite`),
  INDEX `fk_emprestimo_has_minerais_minerais1_idx` (`minerais_idminerais` ASC, `minerais_site_idsite` ASC) VISIBLE,
  INDEX `fk_emprestimo_has_minerais_emprestimo1_idx` (`emprestimo_idemprestimo` ASC, `emprestimo_usuario_idusuario` ASC) VISIBLE,
  CONSTRAINT `fk_emprestimo_has_minerais_emprestimo1`
    FOREIGN KEY (`emprestimo_idemprestimo` , `emprestimo_usuario_idusuario`)
    REFERENCES `Kyber`.`emprestimo` (`idemprestimo` , `usuario_idusuario`)
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
  `emprestimo_usuario_idusuario` INT NOT NULL,
  `Rochas_idRochas` INT NOT NULL,
  `Rochas_site_idsite` INT NOT NULL,
  PRIMARY KEY (`emprestimo_idemprestimo`, `emprestimo_usuario_idusuario`, `Rochas_idRochas`, `Rochas_site_idsite`),
  INDEX `fk_emprestimo_has_Rochas_Rochas1_idx` (`Rochas_idRochas` ASC, `Rochas_site_idsite` ASC) VISIBLE,
  INDEX `fk_emprestimo_has_Rochas_emprestimo1_idx` (`emprestimo_idemprestimo` ASC, `emprestimo_usuario_idusuario` ASC) VISIBLE,
  CONSTRAINT `fk_emprestimo_has_Rochas_emprestimo1`
    FOREIGN KEY (`emprestimo_idemprestimo` , `emprestimo_usuario_idusuario`)
    REFERENCES `Kyber`.`emprestimo` (`idemprestimo` , `usuario_idusuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_emprestimo_has_Rochas_Rochas1`
    FOREIGN KEY (`Rochas_idRochas` , `Rochas_site_idsite`)
    REFERENCES `Kyber`.`Rochas` (`idRochas` , `site_idsite`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

ALTER TABLE `Kyber`.`emprestimo` 
MODIFY COLUMN `idemprestimo` INT NOT NULL AUTO_INCREMENT;

-- Corrigir tipo de dados na tabela minerais (site_idsite deve ser INT)
ALTER TABLE `Kyber`.`minerais` 
MODIFY COLUMN `site_idsite` INT NOT NULL;
SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
