package ru.lernup.socialnetwork.mapper;

import org.springframework.stereotype.Component;
import ru.lernup.socialnetwork.Db.Entity.Comment;
import ru.lernup.socialnetwork.Db.Repository.PersonRepository;
import ru.lernup.socialnetwork.Db.Repository.PostRepository;
import ru.lernup.socialnetwork.view.CommentView;

@Component
public class CommentMapper {
    private final PersonRepository personRepository;
    private final PostRepository postRepository;

    public CommentMapper(PersonRepository personRepository,
                         PostRepository postRepository) {
        this.personRepository = personRepository;
        this.postRepository = postRepository;
    }

    public Comment mappedFromView(CommentView commentView){
        Comment comment = new Comment();
        comment.setText(commentView.getText());
        comment.setIdAuthor(personRepository.getReferenceById(commentView.getIdAuthor()));
        comment.setIdPost(postRepository.getReferenceById(commentView.getIdPost()));
        return comment;
    }
    public CommentView mappedToView(Comment comment){
        CommentView commentView = new CommentView();
        commentView.setId(comment.getId());
        commentView.setIdAuthor(comment.getIdAuthor().getId());
        commentView.setText(comment.getText());
        commentView.setIdPost(comment.getIdPost().getId());
        return commentView;
    }
}
