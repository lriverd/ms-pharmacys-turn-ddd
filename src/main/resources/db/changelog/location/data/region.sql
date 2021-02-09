--liquibase formatted sql
--changeset asilva:LOC_CREATE dbms:mssql

/*
 Project:		LTMCLPP
 Name:			BD_CBK_REQUIREMENTS tables
 Description: 	Table of Region insert script
 Since:         1.0.0 - 05 Ago 2020
 Version: 		1.0.0 - 05 Ago 2020

 Change History
 **************************
 Version	Date			Description
 ------  	-----------		------------------------------------
 1.0.0		05 Ago 2020		TABLE INSERTS
*/

INSERT INTO LOC_REGION (REG_ID, REG_DESCRIPTION) VALUES (1, 'Tarapacá')
INSERT INTO LOC_REGION (REG_ID, REG_DESCRIPTION) VALUES (2, 'Antofagasta')
INSERT INTO LOC_REGION (REG_ID, REG_DESCRIPTION) VALUES (3, 'Atacama')
INSERT INTO LOC_REGION (REG_ID, REG_DESCRIPTION) VALUES (4, 'Coquimbo')
INSERT INTO LOC_REGION (REG_ID, REG_DESCRIPTION) VALUES (5, 'Valparaíso')
INSERT INTO LOC_REGION (REG_ID, REG_DESCRIPTION) VALUES (6, 'Región del Libertador Gral. Bernardo OHiggins')
INSERT INTO LOC_REGION (REG_ID, REG_DESCRIPTION) VALUES (7, 'Región del Maule')
INSERT INTO LOC_REGION (REG_ID, REG_DESCRIPTION) VALUES (8, 'Región del Biobío')
INSERT INTO LOC_REGION (REG_ID, REG_DESCRIPTION) VALUES (9, 'Región de La Araucanía')
INSERT INTO LOC_REGION (REG_ID, REG_DESCRIPTION) VALUES (10, 'Región de Los Lagos')
INSERT INTO LOC_REGION (REG_ID, REG_DESCRIPTION) VALUES (11, 'Región Aisén del Gral. Carlos Ibáñez del Campo')
INSERT INTO LOC_REGION (REG_ID, REG_DESCRIPTION) VALUES (12, 'Región de Magallanes y de la Antártica Chilena')
INSERT INTO LOC_REGION (REG_ID, REG_DESCRIPTION) VALUES (13, 'Región Metropolitana de Santiago')
INSERT INTO LOC_REGION (REG_ID, REG_DESCRIPTION) VALUES (14, 'Región de Los Ríos')
INSERT INTO LOC_REGION (REG_ID, REG_DESCRIPTION) VALUES (15, 'Arica y Parinacota')

GO