-- MySQL Workbench Synchronization
-- Generated: 2025-11-17 08:28
-- Model: New Model
-- Version: 1.0
-- Project: Name of the project
-- Author: Unknown

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

ALTER SCHEMA `Kyber`  DEFAULT CHARACTER SET utf8  DEFAULT COLLATE utf8_general_ci ;

ALTER TABLE `Kyber`.`Rochas` 
DROP FOREIGN KEY `fk_Rochas_site`;

ALTER TABLE `Kyber`.`minerais` 
DROP FOREIGN KEY `fk_minerais_site1`;

ALTER TABLE `Kyber`.`Rochas` 
CHARACTER SET = utf8 , COLLATE = utf8_general_ci ;

ALTER TABLE `Kyber`.`minerais` 
CHARACTER SET = utf8 , COLLATE = utf8_general_ci ;

ALTER TABLE `Kyber`.`site` 
CHARACTER SET = utf8 , COLLATE = utf8_general_ci ;

CREATE TABLE IF NOT EXISTS `Kyber`.`dureza` (
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `Kyber`.`user` (
  `iduser` INT(11) NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `instituicao` VARCHAR(45) NOT NULL,
  `cargo` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`iduser`, `nome`, `instituicao`, `cargo`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `Kyber`.`emprestimo` (
  `idemprestimo` INT(11) NOT NULL,
  PRIMARY KEY (`idemprestimo`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `Kyber`.`user_has_emprestimo` (
  `user_iduser` INT(11) NOT NULL,
  `user_nome` VARCHAR(45) NOT NULL,
  `user_instituicao` VARCHAR(45) NOT NULL,
  `user_cargo` VARCHAR(45) NOT NULL,
  `emprestimo_idemprestimo` INT(11) NOT NULL,
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
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `Kyber`.`emprestimo_has_minerais` (
  `emprestimo_idemprestimo` INT(11) NOT NULL,
  `minerais_idminerais` INT(10) UNSIGNED NOT NULL,
  `minerais_tipo` VARCHAR(45) NOT NULL,
  `minerais_dureza` FLOAT(11) NOT NULL,
  `minerais_cor` VARCHAR(45) NOT NULL,
  `minerais_brilho` VARCHAR(45) NOT NULL,
  `minerais_toxicidade` VARCHAR(45) NOT NULL,
  `minerais_site_idsite` INT(11) NOT NULL,
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
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `Kyber`.`emprestimo_has_Rochas` (
  `emprestimo_idemprestimo` INT(11) NOT NULL,
  `Rochas_idRochas` INT(11) NOT NULL,
  `Rochas_tipo` VARCHAR(45) NOT NULL,
  `Rochas_dureza` VARCHAR(45) NOT NULL,
  `Rochas_corPrincipal` VARCHAR(45) NOT NULL,
  `Rochas_isitgem` TINYINT(4) NOT NULL,
  `Rochas_site_idsite` INT(11) NOT NULL,
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
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

ALTER TABLE `Kyber`.`Rochas` 
ADD CONSTRAINT `fk_Rochas_site`
  FOREIGN KEY (`site_idsite`)
  REFERENCES `Kyber`.`site` (`idsite`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `Kyber`.`minerais` 
ADD CONSTRAINT `fk_minerais_site1`
  FOREIGN KEY (`site_idsite`)
  REFERENCES `Kyber`.`site` (`idsite`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
