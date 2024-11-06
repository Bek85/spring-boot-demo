package com.alex.post;

import com.alex.jsonplaceholder.JsonPlaceHolderService;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Qualifier;

@RestController
@RequestMapping("/posts")
public class PostController {

  private final PostService postService;
  private final JsonPlaceHolderService jsonPlaceholderService;

  public PostController(PostService postService,
      @Qualifier("web-client") JsonPlaceHolderService jsonPlaceholderService) {
    this.postService = postService;
    this.jsonPlaceholderService = jsonPlaceholderService;
  }

  @GetMapping
  public List<Post> getAllPosts() {
    return jsonPlaceholderService.getAllPosts();
  }

  @GetMapping("{id}")
  public Post getPostById(@PathVariable("id") Integer id) {
    return jsonPlaceholderService.getPostById(id);
  }

  @DeleteMapping("{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deletePostById(@PathVariable("id") Integer id) {
    jsonPlaceholderService.deletePostById(id);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public void createPost(@RequestBody Post post) {
    jsonPlaceholderService.createPost(post);
  }

  @PutMapping("{id}")
  public void updatePost(@PathVariable("id") Integer id,
      @RequestBody Post post) {
    jsonPlaceholderService.updatePost(id, post);
  }

}
