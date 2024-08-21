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
    ponto_id BIGINT,

    FOREIGN KEY(ponto_id) REFERENCES ponto(id)
);

CREATE TABLE fase_livre(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    volume DOUBLE NOT NULL,
    houve_troca TINYINT NOT NULL,
    ponto_id BIGINT,

    FOREIGN KEY(ponto_id) REFERENCES ponto(id)
);

CREATE TABLE sensor_ph (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    ph DOUBLE NOT NULL,
    ponto_id BIGINT,

    FOREIGN KEY(ponto_id) REFERENCES ponto(id)
);

CREATE TABLE tq01 (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nivel VARCHAR(45) NOT NULL,
    ponto_id BIGINT,

    FOREIGN KEY(ponto_id) REFERENCES ponto(id)
);

CREATE TABLE tq02(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    sensor_ph DOUBLE NOT NULL,
    it_02_1 VARCHAR(100) NOT NULL,

    ponto_id BIGINT,
    FOREIGN KEY(ponto_id) REFERENCES ponto(id)

);

CREATE TABLE bh02 (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    horimetro INTEGER NOT NULL,
    pressao INTEGER NOT NULL,
    frequencia INTEGER NOT NULL,
    ponto_id BIGINT,
    FOREIGN KEY (ponto_id) REFERENCES ponto(id)
);

CREATE TABLE bs01_pressao (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    pressao DOUBLE NOT NULL,
    ponto_id BIGINT,
    FOREIGN KEY (ponto_id) REFERENCES ponto(id)
);

CREATE TABLE tq04_tq05(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    houve_preparo_solucao TINYINT,
    qtd_bombonas DOUBLE NOT NULL,
    kg_bombonas DOUBLE NOT NULL,
    horimetro INTEGER NOT NULL,
    hidrometro INTEGER NOT NULL,

    ponto_id BIGINT,
    FOREIGN KEY (ponto_id) REFERENCES ponto(id)

);

CREATE TABLE bomba_bc03(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    pressao DOUBLE NOT NULL,
    hidrometro DOUBLE NOT NULL,
    horimetro DOUBLE NOT NULL,

    ponto_id BIGINT,
    FOREIGN KEY (ponto_id) REFERENCES ponto(id)
);

CREATE TABLE bs01_hidrometro (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    volume INTEGER NOT NULL,
    ponto_id BIGINT,
    FOREIGN KEY (ponto_id) REFERENCES ponto(id)
);


-- Auxiliares

CREATE TABLE coleta_pmpt (
    coleta_id BIGINT,
    pm_pt_id BIGINT,
    PRIMARY KEY (coleta_id, pm_pt_id),
    FOREIGN KEY (coleta_id) REFERENCES coleta(id) ON DELETE CASCADE,
    FOREIGN KEY (pm_pt_id) REFERENCES pm_pt(id) ON DELETE CASCADE
);


CREATE TABLE coleta_colunas_carvao (
    coleta_id BIGINT,
    colunas_carvao_id BIGINT,
    PRIMARY KEY (coleta_id, colunas_carvao_id),
    FOREIGN KEY (coleta_id) REFERENCES coleta(id) ON DELETE CASCADE,
    FOREIGN KEY (colunas_carvao_id) REFERENCES colunas_carvao(id) ON DELETE CASCADE
);


CREATE TABLE coleta_bc06 (
    coleta_id BIGINT,
    bc06_id BIGINT,

    PRIMARY KEY (coleta_id, bc06_id),
    FOREIGN KEY (coleta_id) REFERENCES coleta(id) ON DELETE CASCADE,
    FOREIGN KEY (bc06_id) REFERENCES bc06(id) ON DELETE CASCADE
);


CREATE TABLE coleta_bc01 (
    coleta_id BIGINT,
    bc01_id BIGINT,
    PRIMARY KEY (coleta_id, bc01_id),
    FOREIGN KEY (coleta_id) REFERENCES coleta(id) ON DELETE CASCADE,
    FOREIGN KEY (bc01_id) REFERENCES bc01(id) ON DELETE CASCADE
);

CREATE TABLE coleta_pbs (
    coleta_id BIGINT,
    pbs_id BIGINT,
    PRIMARY KEY (coleta_id, pbs_id),
    FOREIGN KEY (coleta_id) REFERENCES coleta(id) ON DELETE CASCADE,
    FOREIGN KEY (pbs_id) REFERENCES pbs(id) ON DELETE CASCADE
);

CREATE TABLE coleta_horimetro(
    coleta_id BIGINT,
    horimetro_id BIGINT,

    PRIMARY KEY(coleta_id, horimetro_id),
    FOREIGN KEY(coleta_id) REFERENCES coleta(id) ON DELETE CASCADE,
    FOREIGN KEY(horimetro_id) REFERENCES horimetro(id) ON DELETE CASCADE
);

CREATE TABLE coleta_filtro_cartucho(
    coleta_id BIGINT,
    filtro_cartucho_id BIGINT,
    PRIMARY KEY(coleta_id, filtro_cartucho_id),
    FOREIGN KEY(coleta_id) REFERENCES coleta(id) ON DELETE CASCADE,
    FOREIGN KEY(filtro_cartucho_id) REFERENCES filtro_cartucho(id) ON DELETE CASCADE


);

CREATE TABLE coleta_cd (
    coleta_id BIGINT,
    cd_id BIGINT,
    PRIMARY KEY(coleta_id, cd_id),
    FOREIGN KEY(coleta_id) REFERENCES coleta(id) ON DELETE CASCADE,
    FOREIGN KEY(cd_id) REFERENCES cd(id) ON DELETE CASCADE
);

CREATE TABLE coleta_fase_livre (
    coleta_id BIGINT,
    fase_livre_id BIGINT,
    PRIMARY KEY(coleta_id, fase_livre_id),
    FOREIGN KEY(coleta_id) REFERENCES coleta(id) ON DELETE CASCADE,
    FOREIGN KEY(fase_livre_id) REFERENCES fase_livre(id) ON DELETE CASCADE
);

CREATE TABLE coleta_ph (
    coleta_id BIGINT,
    sensor_ph_id BIGINT,
    PRIMARY KEY(coleta_id, sensor_ph_id),
    FOREIGN KEY(coleta_id) REFERENCES coleta(id) ON DELETE CASCADE,
    FOREIGN KEY(sensor_ph_id) REFERENCES sensor_ph(id) ON DELETE CASCADE
);

CREATE TABLE coleta_tq01 (
    coleta_id BIGINT,
    tq01_id BIGINT,
    PRIMARY KEY(coleta_id, tq01_id),
    FOREIGN KEY(coleta_id) REFERENCES coleta(id) ON DELETE CASCADE,
    FOREIGN KEY(tq01_id) REFERENCES tq01(id) ON DELETE CASCADE
);

CREATE TABLE coleta_tq02 (
    coleta_id BIGINT,
    tq02_id BIGINT,
    PRIMARY KEY(coleta_id, tq02_id),
    FOREIGN KEY(coleta_id) REFERENCES coleta(id) ON DELETE CASCADE,
    FOREIGN KEY(tq02_id) REFERENCES tq02(id) ON DELETE CASCADE
);

CREATE TABLE coleta_bh02 (
    coleta_id BIGINT,
    bh02_id BIGINT,
    PRIMARY KEY (coleta_id, bh02_id),
    FOREIGN KEY (coleta_id) REFERENCES coleta(id) ON DELETE CASCADE,
    FOREIGN KEY (bh02_id) REFERENCES bh02(id) ON DELETE CASCADE
);

CREATE TABLE coleta_bs01pressao (
    coleta_id BIGINT,
    bs01pressao_id BIGINT,
    PRIMARY KEY(coleta_id, bs01pressao_id),
    FOREIGN KEY (coleta_id) REFERENCES coleta(id) ON DELETE CASCADE,
    FOREIGN KEY (bs01pressao_id) REFERENCES bs01_pressao(id) ON DELETE CASCADE
);

CREATE TABLE coleta_tq04_tq05(
    coleta_id BIGINT,
    tq04_tq05_id BIGINT,
    PRIMARY KEY(coleta_id, tq04_tq05_id),
    FOREIGN KEY (tq04_tq05_id) REFERENCES tq04_tq05(id) ON DELETE CASCADE,
    FOREIGN KEY (coleta_id) REFERENCES coleta(id) ON DELETE CASCADE
);

CREATE TABLE coleta_bomba_bc03(
     coleta_id BIGINT,
     bomba_bc03_id BIGINT,
     PRIMARY KEY(coleta_id, bomba_bc03_id),
     FOREIGN KEY (bomba_bc03_id) REFERENCES bomba_bc03(id) ON DELETE CASCADE,
     FOREIGN KEY (coleta_id) REFERENCES coleta(id) ON DELETE CASCADE
);

CREATE TABLE coleta_bs01hidrometro (
    coleta_id BIGINT,
    bs01hidrometro_id BIGINT,
    PRIMARY KEY (coleta_id, bs01hidrometro_id),
    FOREIGN KEY (coleta_id) REFERENCES coleta(id) ON DELETE CASCADE,
    FOREIGN KEY (bs01hidrometro_id) REFERENCES bs01_hidrometro(id) ON DELETE CASCADE
);