CREATE DATABASE report;

CREATE TABLE access_log (
    oid INT(11) NOT NULL AUTO_INCREMENT,
    log_date TIMESTAMP,
    ip_address VARCHAR(32),
    request VARCHAR(32),
    status VARCHAR(8),
    user_agent  VARCHAR(256),
    PRIMARY KEY (oid)
);

CREATE TABLE blocked_ip (
    oid INT(11) NOT NULL AUTO_INCREMENT,
    ip_address VARCHAR(32),
    reason VARCHAR(256),
    PRIMARY KEY (oid)
);