DROP TABLE IF EXISTS post2tag;

CREATE TABLE post2tag
(
    id      SERIAL NOT NULL,
    post_id INT    NOT NULL,
    tag_id  INT    NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (post_id) REFERENCES post (id) ON DELETE RESTRICT,
    FOREIGN KEY (tag_id) REFERENCES tag (id) ON DELETE RESTRICT
);
