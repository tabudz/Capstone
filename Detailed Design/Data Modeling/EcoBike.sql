SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema ecodatabase
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `ecodatabase`;

-- -----------------------------------------------------
-- Schema ecodatabase
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `ecodatabase` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `ecodatabase` ;

-- -----------------------------------------------------
-- Table `ecodatabase`.`dock`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ecodatabase`.`dock` ;
CREATE TABLE IF NOT EXISTS `ecodatabase`.`dock` (
    `dockID` CHAR(10) NOT NULL,
    `name` VARCHAR(45) NOT NULL,
    `address` VARCHAR(45) NOT NULL,
    `area` VARCHAR(45) NOT NULL,
    `numberOfdockingPoints` INT(11) NOT NULL,
    PRIMARY KEY (`dockID`)
)  ENGINE=INNODB DEFAULT CHARACTER SET=UTF8MB4 COLLATE = UTF8MB4_0900_AI_CI;




-- -----------------------------------------------------
-- Table `ecodatabase`.`bike`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ecodatabase`.`bike` ;
CREATE TABLE IF NOT EXISTS `ecodatabase`.`bike` (
    `bikeCode` INT(11) NOT NULL,
    `isInUse` INT(10) NOT NULL,
    `value` INT(11) NOT NULL,
    `licensePlate` VARCHAR(45) NOT NULL,
    `dockID` CHAR(10) NOT NULL,
    `type` VARCHAR(45) NOT NULL,
    `numSaddle` INT(11) NOT NULL,
    `numPedals` INT(11) NOT NULL,
    `numBicycleSeat` INT(11) NOT NULL,
    `remainBattery` FLOAT(11) NULL,
    `maxTime` FLOAT(11) NULL,
    `motor` VARCHAR(45) NULL,
    PRIMARY KEY (`bikeCode`),
    CONSTRAINT `dockID` FOREIGN KEY (`dockID`)
        REFERENCES `ecodatabase`.`dock` (`dockID`)
)  ENGINE=INNODB DEFAULT CHARACTER SET=UTF8MB4 COLLATE = UTF8MB4_0900_AI_CI;


-- -----------------------------------------------------
-- Table `ecodatabase`.`rentbiketransaction`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ecodatabase`.`rentbiketransaction` ;
CREATE TABLE IF NOT EXISTS `ecodatabase`.`rentbiketransaction` (
    `rentalCode` VARCHAR(45) NOT NULL,
    `bikeCode` INT(11) NOT NULL,
    `type` VARCHAR(45) NOT NULL,
    `rentBikeCost` INT(11) NOT NULL,
    `owner` VARCHAR(45) NOT NULL,
    `rentTime` VARCHAR(45) NOT NULL,
    `returnTime` VARCHAR(45) NULL DEFAULT NULL,
    `deposit` INT(11) NULL DEFAULT NULL,
    PRIMARY KEY (`rentalCode`),
    CONSTRAINT `bikeCode` FOREIGN KEY (`bikeCode`)
        REFERENCES `ecodatabase`.`bike` (`bikeCode`)
)  ENGINE=INNODB DEFAULT CHARACTER SET=UTF8MB4 COLLATE = UTF8MB4_0900_AI_CI;

-- -----------------------------------------------------
-- Table `ecodatabase`.`paymenttransaction`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ecodatabase`.`paymenttransaction` ;

CREATE TABLE IF NOT EXISTS `ecodatabase`.`paymenttransaction` (
    `rentalCode` VARCHAR(45) NOT NULL,
    `cardCode` VARCHAR(45) NOT NULL,
    `owner` VARCHAR(45) NOT NULL,
    `transactionContent` VARCHAR(45) NOT NULL,
    `amount` INT(11) NOT NULL,
    `time` VARCHAR(45) NOT NULL,
    `day` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`time` , `day` , `cardCode`),
    CONSTRAINT `rentalCode` FOREIGN KEY (`rentalCode`)
        REFERENCES `ecodatabase`.`rentbiketransaction` (`rentalCode`)
)  ENGINE=INNODB DEFAULT CHARACTER SET=UTF8MB4 COLLATE = UTF8MB4_0900_AI_CI;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

DROP TABLE IF EXISTS `ecodatabase`.`card`;
CREATE TABLE IF NOT EXISTS `ecodatabase`.`card` (
    `cardCode` VARCHAR(45) NOT NULL,
    `owner` VARCHAR(45) NOT NULL,
    `CVV` INT(10) NOT NULL,
    `expiredDate` INT(10) NOT NULL,
    PRIMARY KEY (`cardCode`)
)  ENGINE=INNODB DEFAULT CHARACTER SET=UTF8MB4 COLLATE = UTF8MB4_0900_AI_CI;

INSERT INTO `dock` VALUES
('HLU','?????i h???c Lu???t H?? N???i','S??? 1, ???????ng Nguy???n Ch?? Thanh','H?? N???i',20),
('HUST','?????i h???c B??ch Khoa H?? N???i','S??? 1, ?????i C??? Vi???t, Qu???n Hai B?? Tr??ng','H?? N???i',20),
('TNL','Lord of Thunder','Ph??? H???ng Mai,Qu???n Hai B?? Tr??ng','H?? N???i',20),
('NEU','?????i h???c Kinh t??? qu???c d??n','S??? 18, ???????ng Gi???i Ph??ng, Qu???n Hai B?? Tr??ng','H?? N???i',20),
('UTC','?????i h???c Giao th??ng v???n t???i','S??? 1, ???????ng C???u Gi???y','H?? N???i',20),
('XDA','?????i h???c X??y d???ng','S??? 10, ???????ng Gi???i Ph??ng','H?? N???i',4),
('DSL','B1 B??ch Khoa','S??? 1, Ph??? T??? Quang B???u','H?? N???i',20),
('C1','C1 B??ch Khoa H?? N???i','S??? 1, ?????i C??? Vi???t, Qu???n Hai B?? Tr??ng','H?? N???i',20),
('D6','D6 B??ch Khoa H?? N???i','s??? 1, ???????ng Gi???i Ph??ng','H?? N???i',20),
('VINC','Vincom Ph???m Ng???c Th???ch','S??? 18, ???????ng Ph???m Ng???c Th???ch','H?? N???i',20),
('FTU','?????i h???c Ngo???i Th????ng','S??? 1, ???????ng L??ng','H?? N???i',20),
('TMU','?????i h???c Th????ng M???i','79 ???????ng H??? T??ng M???u, Mai D???ch, C???u Gi???y','H?? N???i',20);




INSERT INTO `bike` VALUES
(20220000,0,10000000,'XDD-200000','HUST',"Xe ?????p ????n th?????ng",1,1,1,null, null, null),
(20220001,0,1200000,'XDD-200001','HUST', "Xe ?????p ????n th?????ng",1,1,1,null, null, null),
(20220002,0,2000000,'XMD-200002','NEU',"Xe ?????p ????n th?????ng",1,1,1,null, null, null),
(20220003,0,2000000,'XMD-200003','TNL',"Xe ?????p ????n th?????ng",1,1,1,null, null, null),
(20220004,0,1200000,'XDD-200004','XDA',"Xe ?????p ????n th?????ng",1,1,1,null, null, null),
(20220005,0,2000000,'XMD-200005','XDA',"Xe ?????p ????i th?????ng",2,2,1,null, null, null),
(20220006,0,1200000,'XDD-200006','UTC',"Xe ?????p ????i th?????ng",2,2,1,null, null, null),
(20220007,0,1200000,'XDD-200007','HLU',"Xe ?????p ????i th?????ng",2,2,1,null, null, null),
(20220008,0,2000000,'XMD-200008','HLU',"Xe ?????p ????i th?????ng",2,2,1,null, null, null),
(20220009,0,2000000,'XMD-200009','UTC',"Xe ?????p ????i th?????ng",2,2,1,null, null, null),
(20220010,0,2000000,'XMD-200010','HUST',"Xe ?????p ????i th?????ng",2,2,1,null, null, null),
(20220011,0,2000000,'XMD-200011','DSL',"Xe ?????p ????n th?????ng",1,1,1,null, null, null),
(20220012,0,1200000,'XDD-200012','C1',"Xe ?????p ????n th?????ng",1,1,1,null, null, null),
(20220013,0,2000000,'XMD-200013','DSL',"Xe ?????p ????n th?????ng",1,1,1,null, null, null),
(20220014,0,2000000,'XMD-200014','FTU',"Xe ?????p ????n th?????ng",1,1,1,null, null, null),
(20220015,0,1200000,'XDD-200015','TMU',"Xe ?????p ????n th?????ng",1,1,1,null, null, null),                                   
(20220017,0,1200000,'XDD-200017','DSL',"Xe ?????p ????n ??i???n",1,1,1,100, 10, "C??"),
(20220018,0,1200000,'XDD-200018','C1',"Xe ?????p ????n ??i???n",1,1,1,100, 10, "C??"),
(20220019,0,2000000,'XMD-200019','VINC',"Xe ?????p ????n ??i???n",1,1,1,100, 10, "C??"),
(20220020,0,2000000,'XMD-200020','D6',"Xe ?????p ????n ??i???n",1,1,1,100, 10, "C??"),
(20220021,0,1200000,'XDD-200021','HUST',"Xe ?????p ????n ??i???n",1,1,1,100, 10, "C??"),
(20220022,0,2000000,'XMD-200022','NEU',"Xe ?????p ????n ??i???n",1,1,1,100, 10, "C??"),
(20220023,0,2000000,'XMD-200023','TNL',"Xe ?????p ????n ??i???n",1,1,1,100, 10, "C??");




INSERT INTO `rentbiketransaction` VALUES
('202200012022-12-12 10:09:39',20220001,'Xe ?????p ??i???n',25000,'Group 8','2022-12-12 10:09:39','2022-12-7 11:10:34', 480000),
('202200012022-12-12 12:09:39',20220006,'Xe m??y ??i???n',25000,'Group 8','2022-12-12 12:09:39','2022-12-12 13:09:54', 480000);

INSERT INTO `paymenttransaction` VALUES
('202200012022-12-12 10:09:39','118131_group8_2022','Group 8','Tr??? ti???n c???c',480000,'10:09:54','2022-12-12'),
('202200012022-12-12 10:09:39','118131_group8_2022','Group 8','Ho??n ti???n',455000,'11:10:34','2022-12-12'),
('202200062022-12-12 12:09:39','118131_group8_2022','Group 8','Tr??? ti???n c???c',480000,'12:09:54','2022-12-12'),
('202200062022-12-12 12:09:39','118131_group8_2022','Group 8','Ho??n ti???n',455000,'13:09:54','2022-12-12');

