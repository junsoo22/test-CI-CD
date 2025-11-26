package com.example.project.post.service;

import com.example.project.post.converter.PostConverter;
import com.example.project.post.dto.request.PostRequest;
import com.example.project.post.dto.request.PostUpdateRequest;
import com.example.project.post.dto.response.PostResponse;
import com.example.project.post.entity.Post;
import com.example.project.post.exception.PostException;
import com.example.project.post.repository.PostRepository;
import com.example.project.user.entity.UserEntity;
import com.example.project.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.project.post.exception.PostErrorCode.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {

    private final PostRepository postRepository;
    private final PostConverter postConverter;
    private final UserRepository userRepository;

    //게시글 작성
    @Transactional
    public PostResponse createPost(PostRequest dto,Long userId) {

        UserEntity user=userRepository.findById(userId).orElseThrow();
        Post post=postConverter.toPost(dto,user);
        Post saved=postRepository.save(post);

        return PostConverter.toPostResponse(saved);

    }

    @Transactional
    public List<PostResponse> getPost(){
        return postRepository.findAll().stream()
                .map(PostConverter::toPostResponse)
                .collect(Collectors.toList());
    }

    //게시글 수정
    @Transactional
    public PostResponse updatePost(Long postId, PostUpdateRequest dto, Long userId){

        Post post=postRepository.findByIdAndUserId(postId,userId).orElseThrow(()->new PostException(POST_ACCESS_DENIED,"해당 글에 권한이 없습니다."));
        //게시글 내용 업데이트
        post.updateContent(dto.getContent());

        return PostConverter.toPostResponse(post);
    }

    @Transactional
    public void deletePost(Long postId){
        Post post=getPostId(postId);
        postRepository.delete(post);
    }

    private Post getPostId(Long postId) {
        return postRepository.findById(postId).orElseThrow(()->new PostException(POST_ID_NOT_FOUND,"postId"+postId));
    }
}
