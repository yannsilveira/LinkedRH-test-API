IF OBJECT_ID('dbo.funcionario', 'U') IS NULL
BEGIN
    CREATE TABLE funcionario(
        codigo int IDENTITY(1,1) NOT NULL,
        nome varchar(200) NOT NULL,
        cpf char(11) NOT NULL,
        nascimento date NOT NULL,
        cargo varchar(200) NOT NULL,
        admissao date NOT NULL,
        status bit NOT NULL,
        CONSTRAINT PK__func_codigo PRIMARY KEY (codigo)
    );
END

IF OBJECT_ID('dbo.funcionario', 'U') IS NOT NULL
BEGIN
    INSERT INTO funcionario (nome, cpf, nascimento, cargo, admissao, status) VALUES ('Roberta Aparecida', 91299982042, '1990-01-23', 'Secretário', '2022-12-01', 1);
    INSERT INTO funcionario (nome, cpf, nascimento, cargo, admissao, status) VALUES ('Luis Brito', 86540370083, '1985-02-03', 'Jardineiro', '2022-12-04', 1);
    INSERT INTO funcionario (nome, cpf, nascimento, cargo, admissao, status) VALUES ('Carlos de Sa', 12260413021, '1986-03-09', 'Taxista', '2022-12-03', 1);
    INSERT INTO funcionario (nome, cpf, nascimento, cargo, admissao, status) VALUES ('Felipe Munhoz', 92063859094, '1999-04-25', 'Marketing', '2022-12-03', 1);
    INSERT INTO funcionario (nome, cpf, nascimento, cargo, admissao, status) VALUES ('Fabiana de Paula', 89975121020, '1980-05-30', 'Professora', '2022-12-03', 1);
    INSERT INTO funcionario (nome, cpf, nascimento, cargo, admissao, status) VALUES ('Estela da Silva', 51774080001, '2000-06-24', 'Modelo', '2022-12-04', 1);
    INSERT INTO funcionario (nome, cpf, nascimento, cargo, admissao, status) VALUES ('Rosangela Medeiros', 79434098092, '1997-07-20', 'Gerente de Vendas', '2022-12-11', 1);
    INSERT INTO funcionario (nome, cpf, nascimento, cargo, admissao, status) VALUES ('Caroline do Santos', 15975938007, '1989-08-14', 'Fisiculturista', '2022-12-13', 1);
    INSERT INTO funcionario (nome, cpf, nascimento, cargo, admissao, status) VALUES ('Geraldo Tavares', 75466988049, '1975-09-12', 'Ator', '2022-12-06', 1);
    INSERT INTO funcionario (nome, cpf, nascimento, cargo, admissao, status) VALUES ('Manuela Fernandes', 08518471028, '1970-10-13', 'Dubladora', '2022-12-01', 1);
    INSERT INTO funcionario (nome, cpf, nascimento, cargo, admissao, status) VALUES ('Tiago de Sousa', 78977131057, '2001-11-01', 'Estagiário de Programação', '2022-12-22', 1);
    INSERT INTO funcionario (nome, cpf, nascimento, cargo, admissao, status) VALUES ('Sergio da Cruz', 37017172060, '1987-12-24', 'Guia Turístico', '2022-12-18', 1);
END