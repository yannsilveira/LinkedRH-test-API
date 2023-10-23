IF OBJECT_ID('dbo.turma_participante', 'U') IS NULL
BEGIN
    CREATE TABLE turma_participante(
        codigo int IDENTITY(1,1) NOT NULL,
        turma int,
        funcionario int,
        CONSTRAINT PK__turma_participante_codigo PRIMARY KEY (codigo),
        CONSTRAINT FK__turma_participanete_turma_codigo FOREIGN KEY (turma) REFERENCES turma (codigo),
        CONSTRAINT FK__turma_participanete_funcionario_codigo FOREIGN KEY (funcionario) REFERENCES funcionario (codigo)
    );
END