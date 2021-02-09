--liquibase formatted sql
--changeset asilva:LOC_SP_GET_ALL_REGIONS dbms:mssql runOnChange:true

/*
 Project:		LTMCLPP
 Name:			BD_CBK_REQUIREMENTS procedures
 Description: 	Stored procedure to get regions
 Author:      	Luis Riveros
 Version: 		1.0.0 - 11 Ago 2020

 Change History
 **************************
 Version	Date			Description
 ------  	-----------		------------------------------------
 1.0.0		11 Ago 2020 	CREATION PROCEDURES

*/

IF OBJECT_ID(N'[dbo].[GET_ALL_REGIONS]', 'P') IS NOT NULL
BEGIN
   DROP PROCEDURE [dbo].[GET_ALL_REGIONS]
END

SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[GET_ALL_REGIONS]
AS
BEGIN
    SELECT REG_ID, REG_DESCRIPTION FROM LOC_REGION
END
GO

IF USER_ID('usrcbkreqread') IS NOT NULL
    IF OBJECT_ID(N'[dbo].[GET_ALL_REGIONS]', 'P') IS NOT NULL
        GRANT EXECUTE ON dbo.GET_ALL_REGIONS TO usrcbkreqread;
