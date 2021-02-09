--liquibase formatted sql
--changeset asilva:LOC_CREATE dbms:mssql

/*
 Project:		LTMCLPP
 Name:			BD_CBK_REQUIREMENTS tables
 Description: 	Tables of Location creation script
 Since:         1.0.0 - 05 Ago 2020
 Version: 		1.0.0 - 05 Ago 2020

 Change History
 **************************
 Version	Date			Description
 ------  	-----------		------------------------------------
 1.0.0		05 Ago 2020		TABLES CREATION
*/

SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

IF OBJECT_ID('dbo.LOC_REGION', 'U') IS NULL BEGIN
    CREATE TABLE LOC_REGION (
        REG_ID INT PRIMARY KEY,
        REG_DESCRIPTION VARCHAR(100) NOT NULL
    )
END;

IF OBJECT_ID('dbo.LOC_PROVINCIA', 'U') IS NULL BEGIN
    CREATE TABLE LOC_PROVINCIA (
        PRO_ID INT PRIMARY KEY,
        REG_ID INT NOT NULL,
        PRO_DESCRIPTION VARCHAR(100) NOT NULL,
        FOREIGN KEY (REG_ID) REFERENCES LOC_REGION (REG_ID)
    )
END;

IF OBJECT_ID('dbo.LOC_COMUNA', 'U') IS NULL BEGIN
    CREATE TABLE LOC_COMUNA (
        COM_ID INT PRIMARY KEY,
        PRO_ID INT NOT NULL,
        COM_DESCRIPTION VARCHAR(100) NOT NULL,
        FOREIGN KEY (PRO_ID) REFERENCES LOC_PROVINCIA (PRO_ID)
    )
END;

GO
