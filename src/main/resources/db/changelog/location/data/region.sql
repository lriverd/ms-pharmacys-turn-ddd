--liquibase formatted sql
--changeset author:lriverd failOnError:true

INSERT INTO LOC_REGION (reg_id, reg_name) VALUES ('01', 'Tarapacá');
INSERT INTO LOC_REGION (reg_id, reg_name) VALUES ('02', 'Antofagasta');
INSERT INTO LOC_REGION (reg_id, reg_name) VALUES ('03', 'Atacama');
INSERT INTO LOC_REGION (reg_id, reg_name) VALUES ('04', 'Coquimbo');
INSERT INTO LOC_REGION (reg_id, reg_name) VALUES ('05', 'Valparaíso');
INSERT INTO LOC_REGION (reg_id, reg_name) VALUES ('06', 'Libertador General Bernardo O''Higgins');
INSERT INTO LOC_REGION (reg_id, reg_name) VALUES ('07', 'Maule');
INSERT INTO LOC_REGION (reg_id, reg_name) VALUES ('08', 'Biobío');
INSERT INTO LOC_REGION (reg_id, reg_name) VALUES ('09', 'La Araucanía');
INSERT INTO LOC_REGION (reg_id, reg_name) VALUES ('10', 'Los Lagos');
INSERT INTO LOC_REGION (reg_id, reg_name) VALUES ('11', 'Aysén del General Carlos Ibáñez del Campo');
INSERT INTO LOC_REGION (reg_id, reg_name) VALUES ('12', 'Magallanes y de la Antártica Chilena');
INSERT INTO LOC_REGION (reg_id, reg_name) VALUES ('13', 'Metropolitana de Santiago');
INSERT INTO LOC_REGION (reg_id, reg_name) VALUES ('14', 'Los Ríos');
INSERT INTO LOC_REGION (reg_id, reg_name) VALUES ('15', 'Arica y Parinacota');
INSERT INTO LOC_REGION (reg_id, reg_name) VALUES ('16', 'Ñuble');

GO
