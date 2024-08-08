CREATE TABLE Excel(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome_excel VARCHAR(100) NOT NULL,
    data_coleta VARCHAR(100) NOT NULL
);

CREATE TABLE Coleta(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    tecnico VARCHAR(100) NOT NULL,
    data_coleta VARCHAR(100) NOT NULL,
    hora_inicio VARCHAR(100) NOT NULL,
    hora_fim VARCHAR(100) NOT NULL
);


CREATE TABLE Ponto (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    localizacao VARCHAR(100) NOT NULL,
    excel VARCHAR(100) NOT NULL,
    status_enum VARCHAR(100) NOT NULL,
    fk_excel BIGINT,

    FOREIGN KEY(fk_excel) REFERENCES Excel(id)
);


CREATE TABLE colunas_carvao(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    fk_ponto BIGINT,
    fk_coleta BIGINT,
    pressao_c01 DOUBLE NOT NULL,
    pressao_c02 DOUBLE NOT NULL,
    pressao_c03 DOUBLE NOT NULL,
    pressao_saida DOUBLE NOT NULL,
    houve_troca_carvao TINYINT NOT NULL,
    houve_retrolavagem TINYINT NOT NULL,

    FOREIGN KEY(fk_ponto) REFERENCES Ponto(id),
    FOREIGN KEY(fk_coleta) REFERENCES Coleta(id)
);