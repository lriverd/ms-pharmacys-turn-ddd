--liquibase formatted sql
--changeset lriveros:BNK_SP_GET_BANKS dbms:mssql runOnChange:true

/*
 Project:		LTMCLPP
 Name:			BD_CBK_REQUIREMENTS procedures
 Description: 	Stored procedure to get regions
 Version: 		1.0.0 - 03 Sep 2020

 Change History
 **************************
 Version	Date			Description
 ------  	-----------		------------------------------------
 1.0.0		03 Sep 2020 	CREATION PROCEDURES

*/
IF OBJECT_ID(N'[dbo].[BNK_GET_BANKS]', 'P') IS NOT NULL
BEGIN
   DROP PROCEDURE [dbo].[BNK_GET_BANKS]
END

SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[BNK_GET_BANKS]
AS
BEGIN
    SELECT BNK_ID, BNK_RUT, BNK_NAME FROM BNK_BANK
END
GO

IF USER_ID('usrcbkreqread') IS NOT NULL
    IF OBJECT_ID(N'[dbo].[BNK_GET_BANKS]', 'P') IS NOT NULL
        GRANT EXECUTE ON dbo.BNK_GET_BANKS TO usrcbkreqread;
