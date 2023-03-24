package ru.lernup.socialnetwork.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.lernup.socialnetwork.Db.Repository.PersonRepository;
import ru.lernup.socialnetwork.service.CommentService;
import ru.lernup.socialnetwork.service.UserService;
import ru.lernup.socialnetwork.view.CommentView;

@RestController
@RequestMapping("/post")
public class CommentController {
    private final CommentService commentService;
    private final UserService userService;


    public CommentController(CommentService commentService,
                             UserService userService) {
        this.commentService = commentService;
        this.userService = userService;

    }

    @PostMapping("/{id}/{login}")
    @PreAuthorize("#login==authentication.name")
    public  void createComment(@RequestBody CommentView commentView,
                               @PathVariable("id") Long id,
                               @PathVariable("login")String login) {
        commentView.setIdPost(id);
        commentView.setIdAuthor(userService.getUserByLogin(login).getPerson().getId());
    commentService.createComment(commentView);
    }
    @PutMapping("/{id}/{login}")
    @PreAuthorize("#login==authentication.name")
    public  void  updateComment(@RequestBody CommentView commentView,
                                @PathVariable("id") Long id,
                                @PathVariable("login")String login) {
        commentView.setIdPost(id);
        commentView.setIdAuthor(userService.getUserByLogin(login).getPerson().getId());
        commentService.updateComment(commentView);
    }
    @DeleteMapping("/{id}/{login}")
    @PreAuthorize("#idAuthor==authentication.name")
    public void  deleteComment(@RequestBody CommentView commentView,
                               @PathVariable("id") Long id,
                               @PathVariable("login")String idAuthor){
        commentView.setIdPost(id);
        commentView.setIdAuthor(userService.getUserByLogin(idAuthor).getPerson().getId());
        commentService.deleteComment(commentView);
    }
}
