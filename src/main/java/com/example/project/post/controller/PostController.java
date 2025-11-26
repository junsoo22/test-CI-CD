package com.example.project.post.controller;

import com.example.project.auth.dto.PrincipalUserDetails;
import com.example.project.post.dto.request.PostRequest;
import com.example.project.post.dto.request.PostUpdateRequest;
import com.example.project.post.dto.response.PostResponse;
import com.example.project.post.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/post")
@Tag(name = "게시글 API")
public class PostController {

    private final PostService postService;

    @PostMapping
    @Operation(summary = "게시글 작성", description = "게시글을 작성합니다.")
    public ResponseEntity<PostResponse> createPost(@RequestBody PostRequest dto, @AuthenticationPrincipal PrincipalUserDetails userDetails){

        Long userId=userDetails.getUserId();
        PostResponse response=postService.createPost(dto,userId);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    @Operation(summary = "게시글 조회", description = "게시글을 조회합니다.")
    public ResponseEntity<List<PostResponse>> getPost(){
        List<PostResponse> response=postService.getPost();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{postId}")    //자기가 작성한 해당 게시글만 수정해야하므로(추후에 userId도 추가)
    @Operation(summary = "게시글 수정", description = "게시글을 수정합니다.")
    public ResponseEntity<PostResponse> updatePost(@PathVariable Long postId, @RequestBody PostUpdateRequest dto,@AuthenticationPrincipal PrincipalUserDetails userDetails){
        Long userId=userDetails.getUserId();
        PostResponse response=postService.updatePost(postId,dto,userId);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{postId}")
    @Operation(summary = "게시글 삭제", description = "게시글을 삭제합니다.")
    public ResponseEntity<Void> deletePost(@PathVariable Long postId){
        postService.deletePost(postId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
