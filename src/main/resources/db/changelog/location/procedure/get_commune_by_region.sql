--liquibase formatted sql
--changeset asilva:LOC_SPGET_COMUNAS_BY_REG_ID dbms:mssql runOnChange:true

/*
 Project:		LTMCLPP
 Name:			BD_CBK_REQUIREMENTS procedures
 Description: 	Stored procedure to get communes by region id
 Version: 		1.0.0 - 11 Ago 2020

 Change History
 **************************
 Version	Date			Description
 ------  	-----------		------------------------------------
 1.0.0		11 Ago 2020 	CREATION PROCEDURES

*/

IF OBJECT_ID(N'[dbo].[GET_COMUNAS_BY_REG_ID]', 'P') IS NOT NULL
BEGIN
   DROP PROCEDURE [dbo].[GET_COMUNAS_BY_REG_ID]
END

SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[GET_COMUNAS_BY_REG_ID]
    @RegionKey INT
AS
BEGIN
    SELECT lr.REG_ID, lr.REG_DESCRIPTION, lc.COM_ID, lc.COM_DESCRIPTION
    FROM loc_comuna lc
    JOIN LOC_PROVINCIA lp on lc.PRO_ID = lp.PRO_ID
    JOIN LOC_REGION lr on lp.REG_ID = lr.REG_ID
    WHERE lr.REG_ID = @RegionKey
END
GO

IF USER_ID('usrcbkreqread') IS NOT NULL
    IF OBJECT_ID(N'[dbo].[GET_COMUNAS_BY_REG_ID]', 'P') IS NOT NULL
        GRANT EXECUTE ON dbo.GET_COMUNAS_BY_REG_ID TO usrcbkreqread;