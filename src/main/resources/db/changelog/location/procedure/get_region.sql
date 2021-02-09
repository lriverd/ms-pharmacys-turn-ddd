--liquibase formatted sql
--changeset asilva:LOC_SP_GET_REGION dbms:mssql runOnChange:true

/*
 Project:		LTMCLPP
 Name:			BD_CBK_REQUIREMENTS procedures
 Description: 	Stored procedure to get regions
 Version: 		1.0.0 - 11 Ago 2020

 Change History
 **************************
 Version	Date			Description
 ------  	-----------		------------------------------------
 1.0.0		11 Ago 2020 	CREATION PROCEDURES

*/

IF OBJECT_ID(N'[dbo].[GET_REGION]', 'P') IS NOT NULL
BEGIN
   DROP PROCEDURE [dbo].[GET_REGION]
END

SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[GET_REGION]
    @RegionId INT
AS
BEGIN
    SELECT REG_ID, REG_DESCRIPTION FROM LOC_REGION WHERE REG_ID = @RegionId
END
GO

IF USER_ID('usrcbkreqread') IS NOT NULL
    IF OBJECT_ID(N'[dbo].[GET_REGION]', 'P') IS NOT NULL
        GRANT EXECUTE ON dbo.GET_REGION TO usrcbkreqread;
