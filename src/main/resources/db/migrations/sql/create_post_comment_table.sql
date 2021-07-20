DROP TABLE IF EXISTS post_comment;

CREATE TABLE post_comment
(
    id           SERIAL    NOT NULL,
    time         TIMESTAMP NOT NULL,
    post_id      INT       NOT NULL,
    parent_id    INT,
    author_id    INT       NOT NULL,
    comment_text TEXT      NOT NULL,
    is_blocked   BOOLEAN   NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (post_id) REFERENCES post (id) ON DELETE RESTRICT,
    FOREIGN KEY (parent_id) REFERENCES post_comment (id) ON DELETE RESTRICT,
    FOREIGN KEY (author_id) REFERENCES person (id) ON DELETE RESTRICT
);