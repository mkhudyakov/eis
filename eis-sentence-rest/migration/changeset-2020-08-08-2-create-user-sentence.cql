USE eis;

CREATE TABLE user_sentences (
    user_id text,
    date timestamp,
    sentence text,
    PRIMARY KEY (user_id, date)
) WITH CLUSTERING ORDER BY (date DESC);