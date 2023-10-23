IF OBJECT_ID('dbo.turma', 'U') IS NULL
BEGIN
    CREATE TABLE turma(
        codigo int IDENTITY(1,1) NOT NULL,
        inicio date NOT NULL,
        fim date NOT NULL,
        local varchar(200) NOT NULL,
        curso int,
        CONSTRAINT PK__turma_codigo PRIMARY KEY (codigo),
        CONSTRAINT FK__turma_curso_curso FOREIGN KEY (curso) REFERENCES curso (codigo)
    );
END