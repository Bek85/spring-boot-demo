package com.alex.post;

import com.alex.jsonplaceholder.JsonPlaceHolderService;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/posts")
public class PostController {

  private final PostService postService;
  private final JsonPlaceHolderService jsonPlaceholderService;

  public PostController(PostService postService,
      JsonPlaceHolderService jsonPlaceholderService) {
    this.postService = postService;
    this.jsonPlaceholderService = jsonPlaceholderService;
  }

  @GetMapping
  public List<Post> getAllPosts() {
    return postService.getPosts();
  }

  @GetMapping("{id}")
  public Post getPostById(@PathVariable("id") Integer id) {
    return postService.fetchPostById(id);
  }

  @DeleteMapping("{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deletePostById(@PathVariable("id") Integer id) {
    postService.deletePostById(id);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public void createPost(@RequestBody Post post) {
    postService.createPost(post);
  }

  @PutMapping("{id}")
  public void updatePost(@PathVariable("id") Integer id,
      @RequestBody Post post) {
    postService.updatePost(id, post);
  }

}
