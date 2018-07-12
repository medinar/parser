DROP TABLE access_log IF EXISTS;

CREATE TABLE access_log (
    oid BIGINT IDENTITY NOT NULL PRIMARY KEY,
    log_date VARCHAR(32),
    ip_address VARCHAR(32),
    request VARCHAR(32),
    status VARCHAR(8),
    user_agent VARCHAR(256),
);
