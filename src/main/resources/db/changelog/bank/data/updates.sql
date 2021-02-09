--liquibase formatted sql
--changeset lriveros:BNK_UPDATES dbms:mssql runOnChange:true

/*
 Project:		LTMCLPP
 Name:			BD_CBK_REQUIREMENTS tables
 Description: 	Table of Banks update
 Author:      	Luis Riveros
 Since:         1.0.0 - 11 Sep 2020
 Version: 		1.0.0 - 11 Sep 2020
*/

--ITAU RUT UPDATE
UPDATE BNK_BANK
    SET BNK_RUT = '97023000-9'
    WHERE  BNK_ID = '8'

GO