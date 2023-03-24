package ru.lernup.socialnetwork.mapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.lernup.socialnetwork.Db.Entity.Comment;
import ru.lernup.socialnetwork.Db.Entity.Person;
import ru.lernup.socialnetwork.Db.Entity.Post;
import ru.lernup.socialnetwork.Db.Repository.PersonRepository;
import ru.lernup.socialnetwork.Db.Repository.PostRepository;
import ru.lernup.socialnetwork.view.CommentView;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class CommentMapperTest {
    Person person ;
    Post post;
    @Mock
    PersonRepository personRepository;
    @Mock
    PostRepository postRepository;
    @InjectMocks
    CommentMapper commentMapper;
    @Test
    void mappedToView_ReturnValidView(){
        //given
        generatePerson();
        generatePost();
        Comment comment = new Comment();
        comment.setIdAuthor(person);
        comment.setText("WOOOOW");
        comment.setId(1L);
        comment.setIdPost(post);
        //when
        var commentView = commentMapper.mappedToView(comment);
        //then
        assertEquals(commentView.getText(),comment.getText());
        assertEquals(commentView.getId(),comment.getId());
        assertEquals(commentView.getIdPost(),comment.getIdPost().getId());
        assertEquals(commentView.getIdAuthor(),comment.getIdAuthor().getId());
    }
    void generatePost(){
        post = new Post();
        post.setPerson(person);
        post.setId(1L);
    }
    void generatePerson(){
        person = new Person();
        person.setId(1L);
    }
    @Test
    void mappedFromView_returnValidEntity(){
        //given
        generatePost();
        generatePerson();
        Mockito.doReturn(post).when(this.postRepository).getReferenceById(1L);
        Mockito.doReturn(person).when(this.personRepository).getReferenceById(1L);
        CommentView commentView = new CommentView();
        commentView.setId(1L);
        commentView.setText("Woooow");
        commentView.setIdPost(1L);
        commentView.setIdAuthor(1L);
        //when
        var comment = commentMapper.mappedFromView(commentView);
        //then

    }

}