CREATE TABLE excel (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    data_coleta VARCHAR(100)
);


CREATE TABLE coleta (
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
    status VARCHAR(100) NOT NULL,
    excel_id BIGINT,
    FOREIGN KEY (excel_id) REFERENCES excel(id)
);


CREATE TABLE colunas_carvao (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    pressao_c01 DOUBLE NOT NULL,
    pressao_c02 DOUBLE NOT NULL,
    pressao_c03 DOUBLE NOT NULL,
    pressao_saida DOUBLE NOT NULL,
    houve_troca_carvao TINYINT NOT NULL,
    houve_retrolavagem TINYINT NOT NULL,
    ponto_id BIGINT,
    FOREIGN KEY (ponto_id) REFERENCES ponto(id)
);


CREATE TABLE pm_pt (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nivel_agua DOUBLE NOT NULL,
    nivel_oleo DOUBLE NOT NULL,
    fl_remo_manual DOUBLE NOT NULL,
    ponto_id BIGINT,
    FOREIGN KEY (ponto_id) REFERENCES ponto(id)
);


CREATE TABLE bc06 (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    pressao VARCHAR(45) NOT NULL,
    horimetro VARCHAR(45) NOT NULL,
    ponto_id BIGINT,

    FOREIGN KEY (ponto_id) REFERENCES ponto(id)
);


CREATE TABLE bc01 (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    horimetro INT NOT NULL,
    pressao DOUBLE NOT NULL,
    frequencia INT NOT NULL,
    vazao DOUBLE NOT NULL,
    volume INT NOT NULL,
    ponto_id BIGINT,
    FOREIGN KEY (ponto_id) REFERENCES ponto(id)
);

CREATE TABLE pbs (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    pressao DOUBLE NOT NULL,
    pulsos DOUBLE NOT NULL,
    nivel_oleo DOUBLE NOT NULL,
    nivel_agua DOUBLE NOT NULL,
    vol_rem_oleo DOUBLE NOT NULL,
    ponto_id BIGINT,
    FOREIGN KEY (ponto_id) REFERENCES ponto(id)
);

CREATE TABLE horimetro(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    horimetro VARCHAR(45) NOT NULL,
    ponto_id BIGINT,

    FOREIGN KEY(ponto_id) REFERENCES ponto(id)
);

CREATE TABLE filtro_cartucho(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    pressao_entrada DOUBLE NOT NULL,
    pressao_saida DOUBLE NOT NULL,
    ponto_id BIGINT,

    FOREIGN KEY(ponto_id) REFERENCES ponto(id)
);

CREATE TABLE cd (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    tipo_rede VARCHAR(45) NOT NULL,
    pressao DOUBLE NOT NULL,
    hidrometro INT NOT NULL,

    FOREIGN KEY(ponto_id) REFERENCES ponto(id)
);

-- Auxiliares

CREATE TABLE coleta_pmpt (
    coleta_id BIGINT,
    pm_pt_id BIGINT,
    PRIMARY KEY (coleta_id, pm_pt_id),
    FOREIGN KEY (coleta_id) REFERENCES coleta(id),
    FOREIGN KEY (pm_pt_id) REFERENCES pm_pt(id)
);


CREATE TABLE coleta_colunas_carvao (
    coleta_id BIGINT,
    colunas_carvao_id BIGINT,
    PRIMARY KEY (coleta_id, colunas_carvao_id),
    FOREIGN KEY (coleta_id) REFERENCES coleta(id),
    FOREIGN KEY (colunas_carvao_id) REFERENCES colunas_carvao(id)
);


CREATE TABLE coleta_bc06 (
    coleta_id BIGINT,
    bc06_id BIGINT,

    PRIMARY KEY (coleta_id, bc06_id),
    FOREIGN KEY (coleta_id) REFERENCES coleta(id),
    FOREIGN KEY (bc06_id) REFERENCES bc06(id)
);


CREATE TABLE coleta_bc01 (
    coleta_id BIGINT,
    bc01_id BIGINT,
    PRIMARY KEY (coleta_id, bc01_id),
    FOREIGN KEY (coleta_id) REFERENCES coleta(id),
    FOREIGN KEY (bc01_id) REFERENCES bc01(id)
);

CREATE TABLE coleta_pbs (
    coleta_id BIGINT,
    pbs_id BIGINT,
    PRIMARY KEY (coleta_id, pbs_id),
    FOREIGN KEY (coleta_id) REFERENCES coleta(id),
    FOREIGN KEY (pbs_id) REFERENCES pbs(id)
);

CREATE TABLE coleta_horimetro(
    coleta_id BIGINT,
    horimetro_id BIGINT,

    PRIMARY KEY(coleta_id, horimetro_id),
    FOREIGN KEY(coleta_id) REFERENCES coleta(id),
    FOREIGN KEY(horimetro_id) REFERENCES horimetro(id)
);

CREATE TABLE coleta_filtro_cartucho(
    coleta_id BIGINT,
    filtro_cartucho_id BIGINT,
    PRIMARY KEY(coleta_id, filtro_cartucho_id),
    FOREIGN KEY(coleta_id) REFERENCES coleta(id),
    FOREIGN KEY(filtro_cartucho_id) REFERENCES filtro_cartucho(id)


);

CREATE TABLE coleta_cd (
    coleta_id BIGINT,
    cd_id BIGINT,
    PRIMARY KEY(coleta_id, cd_id),
    FOREIGN KEY(coleta_id) REFERENCES coleta(id),
    FOREIGN KEY(cd_id) REFERENCES cd(id)
);