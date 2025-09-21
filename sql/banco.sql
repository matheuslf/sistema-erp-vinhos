-- Países
CREATE TABLE paises (
                        id SERIAL PRIMARY KEY,
                        nome TEXT NOT NULL UNIQUE
);

-- Regiões (podem ter sub-regiões)
CREATE TABLE regioes (
                         id SERIAL PRIMARY KEY,
                         pais_id INT NOT NULL REFERENCES paises(id),
                         nome TEXT NOT NULL,
                         regiao_pai_id INT REFERENCES regioes(id),
                         UNIQUE (pais_id, nome)
);

-- Vinícolas / Produtores
CREATE TABLE vinicolas (
                           id SERIAL PRIMARY KEY,
                           regiao_id INT REFERENCES regioes(id),
                           nome TEXT NOT NULL,
                           site TEXT,
                           UNIQUE (nome, regiao_id)
);

-- Uvas
CREATE TABLE uvas (
                      id SERIAL PRIMARY KEY,
                      nome TEXT NOT NULL UNIQUE
);

-- Vinhos
CREATE TABLE vinhos (
                        id SERIAL PRIMARY KEY,
                        vinicola_id INT NOT NULL REFERENCES vinicolas(id),
                        nome TEXT NOT NULL,            -- Ex.: "Reserva Cabernet Sauvignon"
                        safra INT,                     -- Ex.: 2020 (pode ser NULL para NV)
                        regiao_id INT REFERENCES regioes(id), -- opcional
                        cor TEXT,                      -- Ex.: "tinto", "branco", "rosé"
                        estilo TEXT,                   -- Ex.: "tranquilo", "espumante"
                        doçura TEXT,                   -- Ex.: "seco", "meio seco", "doce"
                        teor_alcoolico NUMERIC(4,1),   -- Ex.: 13.5
                        volume_ml INT DEFAULT 750,
                        codigo_barras TEXT,
                        observacoes TEXT
);

-- Relação vinho x uvas (para blends)
CREATE TABLE vinhos_uvas (
                             vinho_id INT NOT NULL REFERENCES vinhos(id) ON DELETE CASCADE,
                             uva_id INT NOT NULL REFERENCES uvas(id),
                             percentual NUMERIC(5,2),       -- 0–100 (pode ser NULL se não souber)
                             PRIMARY KEY (vinho_id, uva_id),
                             CHECK (percentual IS NULL OR (percentual >= 0 AND percentual <= 100))
);

-- Índices simples para facilitar consultas
CREATE INDEX idx_vinhos_nome ON vinhos (nome);
CREATE INDEX idx_vinhos_safra ON vinhos (safra);
CREATE INDEX idx_vinicolas_nome ON vinicolas (nome);
CREATE INDEX idx_regioes_nome ON regioes (nome);




CREATE TABLE usuarios (
                          id SERIAL PRIMARY KEY,
                          usuario VARCHAR(50) NOT NULL UNIQUE,
                          senha VARCHAR(100) NOT NULL,
                          perfil VARCHAR(20) NOT NULL CHECK (perfil IN ('PARCIAL', 'COMPLETO'))
);

INSERT INTO usuarios (usuario, senha, perfil)
VALUES ('Administrador', md5('senha123'), 'COMPLETO');