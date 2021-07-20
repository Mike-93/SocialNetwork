DROP TABLE IF EXISTS post_like;

CREATE TABLE post_like
(
    id        SERIAL    NOT NULL,
    time      TIMESTAMP NOT NULL,
    person_id INT       NOT NULL,
    post_id   INT       NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (person_id) REFERENCES person (id) ON DELETE RESTRICT,
    FOREIGN KEY (post_id) REFERENCES post (id) ON DELETE RESTRICT
);