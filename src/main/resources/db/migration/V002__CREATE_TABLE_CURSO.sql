IF OBJECT_ID('dbo.curso', 'U') IS NULL
BEGIN
    CREATE TABLE curso(
        codigo int IDENTITY(1,1) NOT NULL,
        nome varchar(100) NOT NULL,
        descricao varchar(4000) NOT NULL,
        duracao int NOT NULL,
        CONSTRAINT PK__curso_codigo PRIMARY KEY (codigo)
    );
END