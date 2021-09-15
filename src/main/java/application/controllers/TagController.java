package application.controllers;

import application.models.Tag;
import application.models.requests.TagRequest;
import application.models.responses.GeneralListResponse;
import application.models.responses.GeneralResponse;
import application.service.PostsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@Slf4j
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class TagController {

    private final PostsService postsService;

    @GetMapping("/tags")
    public ResponseEntity<GeneralListResponse<Tag>> getTags(
            @RequestParam(value = "tag", required = false) String tag,
            @RequestParam(value = "offset", required = false) int offset,
            @RequestParam(value = "itemPerPage", defaultValue = "20", required = false) int itemPerPage) {

        log.info("getTags(): start():");
        GeneralListResponse<Tag> generalListResponse = new GeneralListResponse<>(postsService.getTags(), offset, itemPerPage);
        log.debug("getTags(): tag ={}", tag);
        log.debug("getTags(): responseList = {}", generalListResponse);
        log.info("getTags(): finish():");
        return ResponseEntity.ok(generalListResponse);
    }

    @PostMapping("/tags")
    public ResponseEntity<GeneralResponse<Tag>> setTag(@RequestBody TagRequest request) {

        log.info("setTag(): start():");
        GeneralResponse<Tag> response = new GeneralResponse<>(postsService.setTag(request));
        log.debug("setTag(): requestBody = {}", request);
        log.debug("setTag(): response = {}", response);
        log.info("setTag(): finish():");
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/tags")
    public ResponseEntity<GeneralResponse<HashMap<String, String>>> deleteTag(@RequestParam int id) {

        log.info("deleteTag(): start():");
        GeneralResponse <HashMap<String, String>> generalResponse = new GeneralResponse<>(postsService.deleteTag(id));
        log.debug("deleteTag(): tagId = {}", id);
        log.debug("deleteTag(): response = {}", generalResponse);
        log.info("deleteTag(): finish():");
        return ResponseEntity.ok(generalResponse);
    }
}
