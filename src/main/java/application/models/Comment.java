package application.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Comment {
    private int id;
    private long time;

    @JsonProperty("post_id")
    private String postId;

    @JsonProperty("parent_id")
    private int parentId;

    @JsonProperty("author_id")
    private int authorId;

    @JsonProperty("comment_text")
    private String commentText;

    @JsonProperty("is_blocked")
    private boolean isBlocked;
}
