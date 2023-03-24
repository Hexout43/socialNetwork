package ru.lernup.socialnetwork.controllers;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.lernup.socialnetwork.Db.Repository.PersonRepository;
import ru.lernup.socialnetwork.Db.Repository.PostRepository;
import ru.lernup.socialnetwork.service.PostService;
import ru.lernup.socialnetwork.view.PostView;

import java.util.List;

@RestController
@RequestMapping
public class PostController {
    private final PostService postService;

    private final PersonRepository personRepository;
    private final PostRepository postRepository;

    public PostController(PostService postService,

                          PersonRepository personRepository, PostRepository postRepository) {
        this.postService = postService;

        this.personRepository = personRepository;
        this.postRepository = postRepository;
    }
    @GetMapping("/posts/{id}")
    public List<PostView> getPostsByUser(@PathVariable("id") Long id){

        return postService.getPostsByUser(id);
    }
    @GetMapping("/posts")
    public List<PostView> getAllPosts(){

        return postService.getAllPosts();
    }
    @GetMapping("/post/{id}")
    public PostView getPost(@PathVariable("id")Long id){

        return postService.getPost(id);
    }
    @PostMapping("/post")
    public void  createPost(@RequestBody PostView postView){

        postService.addPost(postView);
    }
    @PostAuthorize("#postRepository.getReferenceById(postView.person).person.user.login==authentication.name")
    @DeleteMapping("/post/{id}")
    public  void  deletePost(@PathVariable("id") Long id,@RequestBody PostView postView){
        postView.setId(id);
        postService.deletePost(id);
    }
    @PutMapping("post/{id}")
    public  void  updatePost(@RequestBody PostView postView,
                             @PathVariable("id") Long id){
        postView.setId(id);
        postService.updatePost(postView);
    }
    @GetMapping("/posts/search")
    public  List<PostView> searchPost(@RequestParam(value = "header",required = false)String header ,
                                      @RequestParam(value = "body",required = false) String body){
        if (header!=null&&body==null){
            return postService.getPostsByHeader(header);
        }
        if (header==null&&body!=null){
            return postService.getPostsByBodyContains(body);
        }
        return null;
    }


}
