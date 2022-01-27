DROP TABLE IF EXISTS journal_comptable;
CREATE TABLE journal_comptable (
                code VARCHAR(5) NOT NULL,
                libelle VARCHAR(150) NOT NULL,
                CONSTRAINT journal_comptable_pk PRIMARY KEY (code)
);

DROP TABLE IF EXISTS sequence_ecriture_comptable;
CREATE TABLE sequence_ecriture_comptable (
                journal_code VARCHAR(5) NOT NULL,
                annee INTEGER NOT NULL,
                derniere_valeur INTEGER NOT NULL,
                CONSTRAINT sequence_ecriture_comptable_pk PRIMARY KEY (journal_code, annee)
);

DROP TABLE IF EXISTS ecriture_comptable;
CREATE TABLE ecriture_comptable (
                id INTEGER NOT NULL,
                journal_code VARCHAR(5) NOT NULL,
                reference VARCHAR(30),
                date TIMESTAMP NOT NULL,
                libelle VARCHAR(200) NOT NULL,
                CONSTRAINT ecriture_comptable_pk PRIMARY KEY (id)
);

DROP TABLE IF EXISTS compte_comptable;
CREATE TABLE compte_comptable (
                numero INTEGER NOT NULL,
                libelle VARCHAR(150) NOT NULL,
                CONSTRAINT compte_comptable_pk PRIMARY KEY (numero)
);

DROP TABLE IF EXISTS ligne_ecriture_comptable;
CREATE TABLE ligne_ecriture_comptable (
                ecriture_id INTEGER NOT NULL,
                ligne_id INTEGER NOT NULL,
                compte_comptable_numero INTEGER NOT NULL,
                libelle VARCHAR(200),
                debit NUMERIC(15,2),
                credit NUMERIC(15,2),
                CONSTRAINT ligne_ecriture_comptable_pk PRIMARY KEY (ecriture_id, ligne_id)
);

