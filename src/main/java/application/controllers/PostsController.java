package application.controllers;

import application.models.dto.CommentDto;
import application.models.dto.MessageResponseDto;
import application.models.dto.PostDto;
import application.models.requests.CommentRequest;
import application.models.requests.PostRequest;
import application.models.responses.GeneralListResponse;
import application.models.responses.GeneralResponse;
import application.service.PostsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/post")
@RequiredArgsConstructor
public class PostsController {

    private final PostsService postsService;

    @GetMapping
    public ResponseEntity<GeneralListResponse<PostDto>> searchPosts(
            @RequestParam(value = "text") String text,
            @RequestParam(value = "author", required = false) String author,
            @RequestParam(value = "date_from", required = false) Long dateFrom,
            @RequestParam(value = "date_to", required = false) Long dateTo,
            @RequestParam(value = "tags", required = false) List<String> tags,
            @RequestParam(value = "offset", defaultValue = "0", required = false) int offset,
            @RequestParam(value = "itemPerPage", defaultValue = "20", required = false) int itemPerPage) {

        log.info("searchPosts(): start():");
        GeneralListResponse<PostDto> listResponse = new GeneralListResponse<>(postsService.getPosts(text, author, dateFrom, dateTo, tags), offset, itemPerPage);
        log.debug("searchPosts(): text = {}, author = {}, dateFrom = {}, dateTo = {}, tags = {}", text, author, dateFrom, dateTo, tags);
        log.debug("searchPosts(): responseList = {}", listResponse);
        log.info("searchPosts(): finish():");
        return ResponseEntity.ok(listResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GeneralResponse<PostDto>> getPost(@PathVariable int id) {

        log.info("getPostById(): start():");
        GeneralResponse<PostDto> generalResponse = new GeneralResponse<>(postsService.getPostResponse(id));
        log.debug("getPostById(): postId = {}", id);
        log.debug("getPostById(): response = {}", generalResponse);
        log.info("getPostById(): finish():");
        return ResponseEntity.ok(generalResponse);
    }

    @GetMapping("/{id}/comments")
    public ResponseEntity<GeneralListResponse<CommentDto>> getComments(
            @PathVariable String id,
            @RequestParam(value = "offset", defaultValue = "0", required = false) int offset,
            @RequestParam(value = "itemPerPage", defaultValue = "20", required = false) int itemPerPage) {

        log.info("getComments(): start():");
        GeneralListResponse<CommentDto> generalListResponse = new GeneralListResponse<>(postsService.getCommentsResponse(id), offset, itemPerPage);
        log.debug("getComments(): commentId = {}", id);
        log.debug("getComments(): responseList = {}", generalListResponse);
        log.info("getComments(): finish():");
        return ResponseEntity.ok(generalListResponse);
    }

    @PostMapping("/{id}/comments")
    public ResponseEntity<GeneralResponse<CommentDto>> postComment(@PathVariable String id,
                                                                   @RequestBody CommentRequest commentRequest) {
        log.info("setComment(): start():");
        GeneralResponse<CommentDto> generalResponse = new GeneralResponse<>(postsService.setComment(id, commentRequest));
        log.debug("setComment(): commentId = {}, requestBody = {}", id, commentRequest);
        log.debug("setComment(): response = {}", generalResponse);
        log.info("setComment(): finish():");
        return ResponseEntity.ok(generalResponse);
    }

    @PutMapping("/{id}/comments/{comment_id}")
    public ResponseEntity<GeneralResponse<CommentDto>> editComment(@RequestBody CommentRequest request,
                                                                   @PathVariable String id,
                                                                   @PathVariable int commentId) {
        log.info("editComment(): start():");
        GeneralResponse<CommentDto> generalResponse = new GeneralResponse<>(postsService.editComment(request, id, commentId));
        log.debug("editComment(): commentId = {}, requestBody = {}", id, request);
        log.debug("editComment(): response = {}", generalResponse);
        log.info("editComment(): finish():");
        return ResponseEntity.ok(generalResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GeneralResponse<PostDto>> editPost(@RequestBody PostRequest postRequest,
                                                             @PathVariable int id) {
        log.info("editPost(): start():");
        GeneralResponse<PostDto> generalResponse = new GeneralResponse<>(postsService.editPost(postRequest, id));
        log.debug("editPost(): postId = {}, requestBody = {}", id, postRequest);
        log.debug("editPost(): response = {}", generalResponse);
        log.info("editPost(): finish():");
        return ResponseEntity.ok(generalResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GeneralResponse<MessageResponseDto>> deletePost(@PathVariable int id) {

        log.info("deletePost(): start():");
        GeneralResponse<MessageResponseDto> generalResponse = new GeneralResponse<>(postsService.deletePost(id));
        log.debug("deletePost(): postId = {}", id);
        log.debug("deletePost(): response = {}", generalResponse);
        log.info("deletePost(): finish():");
        return ResponseEntity.ok(generalResponse);
    }

    @DeleteMapping("/{id}/comments/{comment_id}")
    public ResponseEntity<GeneralResponse<HashMap<String, Integer>>> deleteComment(@PathVariable String id,
                                                                                   @PathVariable int commentId) {
        log.info("deleteComment(): start():");
        GeneralResponse<HashMap<String, Integer>> generalResponse = new GeneralResponse<>(postsService.deleteComment(id, commentId));
        log.debug("deleteComment(): commentId = {}", commentId);
        log.debug("deleteComment(): response = {}", generalResponse);
        log.info("deleteComment(): finish():");
        return ResponseEntity.ok(generalResponse);
    }
}
