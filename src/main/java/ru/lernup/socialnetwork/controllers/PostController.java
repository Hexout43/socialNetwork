package ru.lernup.socialnetwork.controllers;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.lernup.socialnetwork.service.PostService;
import ru.lernup.socialnetwork.service.UserService;
import ru.lernup.socialnetwork.view.PostView;

import java.util.List;

@RestController
@RequestMapping
public class PostController {
    private final PostService postService;
    private final UserService userService;



    public PostController(PostService postService, UserService userService) {
        this.postService = postService;


        this.userService = userService;
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
    @PreAuthorize("#login == authentication.name")
    public void  createPost(@RequestParam("login")String login
            ,@RequestBody PostView postView){

        postService.addPost(postView);
    }
    @PreAuthorize("#login==authentication.name")
    @DeleteMapping("/post/{id}")
    public  void  deletePost(@RequestParam("login")String login,
                             @PathVariable("id")Long id,@RequestBody PostView postView){
        Long idAuthor = userService.getUserByLogin(login).getPerson().getId();
        if (postView.getPerson().equals(idAuthor)){
        postView.setId(id);
        postService.deletePost(id);}
    }
    @PutMapping("/post/{id}")
    @PreAuthorize("#login==authentication.name")
    public  void  updatePost(@RequestBody PostView postView,
                             @PathVariable("id")Long id,
                             @RequestParam("login")String login){
        Long idAuthor = userService.getUserByLogin(login).getPerson().getId();
        if (postView.getPerson().equals(idAuthor)){
        postView.setId(id);
        postService.updatePost(postView);
        }
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
