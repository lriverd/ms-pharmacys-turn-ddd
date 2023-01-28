--liquibase formatted sql
--changeset author:lriverd failOnError:true

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
