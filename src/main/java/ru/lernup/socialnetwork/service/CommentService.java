package ru.lernup.socialnetwork.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.lernup.socialnetwork.Db.Entity.Comment;
import ru.lernup.socialnetwork.Db.Repository.CommentRepository;
import ru.lernup.socialnetwork.Db.Repository.PostRepository;
import ru.lernup.socialnetwork.mapper.CommentMapper;
import ru.lernup.socialnetwork.view.CommentView;

@Service
public class CommentService {
    private final CommentMapper commentMapper;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    public CommentService(CommentMapper commentMapper,
                          PostRepository postRepository,
                          CommentRepository commentRepository) {
        this.commentMapper = commentMapper;
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }
    @Transactional
    public void createComment(CommentView commentView){
        commentRepository.save(commentMapper.mappedFromView(commentView));
    }
    @Transactional
    public void deleteComment(CommentView commentView){
        commentRepository.delete(commentRepository.getReferenceById(commentView.getId()));
    }
    @Transactional
    public  void  updateComment(CommentView commentView){
        Comment comment = commentRepository.getReferenceById(commentView.getId());
        if(commentView.getText()!=null&&!comment.getText().equals(commentView.getText())){
            comment.setText(commentView.getText());
        }
        commentRepository.save(comment);

    }
}
