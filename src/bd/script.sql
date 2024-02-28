CREATE SCHEMA ControleVacinas;

CREATE TABLE ADMINISTRADOR(
	NUMEROCNS_ADM INT NOT NULL UNIQUE,
    NOME varchar(45),
    DATANASCIMENTO date,
    CPF varchar(45),
    SENHA varchar(45),
    PRIMARY KEY(NUMEROCNS_ADM));

CREATE TABLE CIDADAO(
	NUMEROCNS_CID INT NOT NULL UNIQUE,
    NOME varchar(45),
    DATANASCIMENTO date,
    CPF varchar(45),
    PRIMARY KEY(NUMEROCNS_CID));

CREATE TABLE UNIDADESAUDE(
	ID_US INT NOT NULL UNIQUE,
    NOME varchar(45),
    RUA varchar(45),
    BAIRRO varchar(45),
    CIDADE varchar(45),
    ESTADO varchar(45),
    TELEFONE varchar(45),
    PRIMARY KEY(ID_US));
    
CREATE TABLE FUNCIONARIO(
	NUMEROCNS_FUN INT NOT NULL UNIQUE,
    NOME varchar(45),
    DATANASCIMENTO date,
    CPF varchar(45),
    SENHA varchar(45),
    ID_US INT,
    PRIMARY KEY(NUMEROCNS_FUN),
    FOREIGN KEY(ID_US) references UNIDADESAUDE(ID_US));
    
CREATE TABLE LOTE(
	LOTE_CODIGO varchar(45) NOT NULL UNIQUE,
    NOMEVACINA varchar(45),
    LABORATORIO varchar(45),
    ID_US INT,
    PRIMARY KEY(LOTE_CODIGO),
    FOREIGN KEY(ID_US) references UNIDADESAUDE(ID_US));

CREATE TABLE REGISTROVACINA(
	ID_REGISTRO INT NOT NULL UNIQUE,
    DATA date,
    LOTE_CODIGO varchar(45),
    NUMEROCNS_CID INT,
    NUMEROCNS_FUN INT,
    DOSE INT,
    ID_US INT,
    PRIMARY KEY(ID_REGISTRO),
    FOREIGN KEY(LOTE_CODIGO) references LOTE(LOTE_CODIGO),
    FOREIGN KEY(NUMEROCNS_CID) references CIDADAO(NUMEROCNS_CID),
    FOREIGN KEY(NUMEROCNS_FUN) references FUNCIONARIO(NUMEROCNS_FUN),
    FOREIGN KEY(ID_US) references UNIDADESAUDE(ID_US));