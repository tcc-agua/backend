CREATE TABLE excel(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,

    nome_excel VARCHAR(100) NOT NULL,
    data_coleta VARCHAR(100) NOT NULL
);

CREATE TABLE coleta(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,

    tecnico VARCHAR(100) NOT NULL,
    data_coleta VARCHAR(100) NOT NULL,
    hora_inicio VARCHAR(100) NOT NULL,
    hora_fim VARCHAR(100) NOT NULL

);


CREATE TABLE ponto (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,

    nome VARCHAR(100) NOT NULL,
    localizacao VARCHAR(100) NOT NULL,
    excel VARCHAR(100) NOT NULL,
    status_enum VARCHAR(100) NOT NULL,

    fk_excel BIGINT,

    FOREIGN KEY(fk_excel) REFERENCES Excel(id)
);

-- Pontos

CREATE TABLE colunas_carvao(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,

    pressao_c01 DOUBLE NOT NULL,
    pressao_c02 DOUBLE NOT NULL,
    pressao_c03 DOUBLE NOT NULL,
    pressao_saida DOUBLE NOT NULL,
    houve_troca_carvao TINYINT NOT NULL,
    houve_retrolavagem TINYINT NOT NULL,

    ponto_id BIGINT,

    FOREIGN KEY(ponto_id) REFERENCES Ponto(id)
);


CREATE TABLE pm_pt(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,

    nivel_agua DOUBLE NOT NULL,
    nivel_oleo DOUBLE NOT NULL,
    fl_remo_manual DOUBLE NOT NULL,

    ponto_id BIGINT,

    FOREIGN KEY(ponto_id) REFERENCES Ponto(id)
);

-- Auxiliares

CREATE TABLE coleta_pmpt(
    coleta_id BIGINT,
    pm_pt_id BIGINT

    PRIMARY KEY(coleta_id, pm_pt_id),

    FOREIGN KEY(coleta_id) REFERENCES coleta(id),
    FOREIGN KEY(pm_pt_id) REFERENCES pm_pt(id)
);


CREATE TABLE coleta_colunas_carvao(
    coleta_id BIGINT,
    colunas_carvao_id BIGINT,

    PRIMARY KEY(coleta_id, colunas_carvao_id),

    FOREIGN KEY(coleta_id) REFERENCES coleta(id),
    FOREIGN KEY(colunas_carvao_id) REFERENCES colunas_carvao(id)
);