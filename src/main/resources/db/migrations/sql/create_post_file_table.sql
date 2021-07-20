DROP TABLE IF EXISTS post_file;

CREATE TABLE post_file
(
    id      SERIAL NOT NULL,
    post_id INT    NOT NULL,
    name    TEXT,
    path    TEXT,
    PRIMARY KEY (id),
    FOREIGN KEY (post_id) REFERENCES post (id) ON DELETE RESTRICT
);