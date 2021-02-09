--liquibase formatted sql
--changeset lriveros:BNK_CREATE dbms:mssql

/*
 Project:		LTMCLPP
 Name:			BD_CBK_REQUIREMENTS tables
 Description: 	Tables of bank creation script
 Author:      	Luis Riveros
 Since:         1.0.0 - 03 Sep 2020
 Version: 		1.0.0 - 03 Sep 2020

 Change History
 **************************
 Version	Date			Description
 ------  	-----------		------------------------------------
 1.0.0		03 Sep 2020		TABLES CREATION
*/

SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

IF OBJECT_ID('dbo.BNK_BANK', 'U') IS NULL BEGIN
    CREATE TABLE BNK_BANK (
        BNK_ID VARCHAR(5) PRIMARY KEY,
        BNK_RUT VARCHAR(11) NOT NULL,
        BNK_NAME VARCHAR(100) NOT NULL
    )
END;

GO
