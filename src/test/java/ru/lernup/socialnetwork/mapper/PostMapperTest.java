package ru.lernup.socialnetwork.mapper;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.lernup.socialnetwork.Db.Entity.*;
import ru.lernup.socialnetwork.Db.Repository.PersonRepository;
import ru.lernup.socialnetwork.service.GenerateContent;
import ru.lernup.socialnetwork.view.PostView;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)

class PostMapperTest {
    String urlImg = "http://localhost:8189/file/img?name=";
    Post post = new Post();
    PostView postView = new PostView();
    Person person = new Person();
    User user = new User();

    @Mock
    PersonRepository personRepository;
    @Mock
    GenerateContent generateContent;

    @InjectMocks
    PostMapper postMapper;

    void validTest(){

        post.setId(1L);
        post.setHeader("Trees");
        post.setBody("Rest");

        person.setName("Steve");
        person.setId(1L);

        user.setId(1L);
        user.setPerson(person);
        person.setUser(user);
        post.setPerson(person);

        postView.setPerson(1L);
        postView.setBody("Text");
        postView.setHeader("Trees");
        postView.setId(1L);

    }
    @Test
    void mappedToView_WhereCommentAndContentIsNull_ReturnValidView(){
        //given
        validTest();
        Post post1 =new Post();
        post1 = post;
        //when
        var postView = postMapper.mappedToView(post);
        //then

        assertEquals(postView.getId(),post1.getId());
        assertEquals(postView.getHeader(),post1.getHeader());
        assertEquals(postView.getBody(),post1.getBody());
        assertEquals(postView.getPerson(),post1.getPerson().getUser().getId());
        assertNull(postView.getContent());
        assertNull(postView.getComments());


    }
    @Test
    void mappedToView_WhereCommentIsExistButContentIsNull_ReturnValidView(){
        //given
        validTest();
        Post postWhereCommentIsExist = new Post();
        postWhereCommentIsExist = post;
        Comment comment = new Comment();
        comment.setText("123");
        comment.setIdPost(postWhereCommentIsExist);
        comment.setIdAuthor(postWhereCommentIsExist.getPerson());
        postWhereCommentIsExist.setComments(List.of(comment));
        //when
        var postViewWithComments = postMapper.mappedToView(postWhereCommentIsExist);
        //then
        assertArrayEquals(postViewWithComments.getComments().toArray(), postWhereCommentIsExist.getComments()
                .stream().map(Comment::getText).toArray());
    }
    @Test
    void mappedToView_WhereContentIsExistButCommentIsNull_ReturnValidView(){
        //given
        validTest();
        FilePerson filePerson = new FilePerson();
        filePerson.setName("12");
        filePerson.setId(1L);
        Mockito.doReturn(filePerson).when(this.generateContent).generateContent(urlImg+"12");
        Post postWhereContentIsExist = new Post();
        postWhereContentIsExist = post;
        postWhereContentIsExist.setContent(List.of(generateContent.generateContent(urlImg+"12")));
        //when
        var postViewWithContent = postMapper.mappedToView(postWhereContentIsExist);
        //then
        assertArrayEquals(postViewWithContent.getContent().toArray(),postWhereContentIsExist
                .getContent().stream().map(generateContent::generateUrl).toArray());
    }
    @Test
    void mappedFromView_WhereContentAndCommentIsNull_ReturnValidEntity (){
        //given
        validTest();
        Mockito.doReturn(person).when(this.personRepository).getReferenceById(1L);
        PostView postView1 = new PostView();
        postView1 = postView;
        //when
        var post = postMapper.mappedFromView(postView1);
        //then
        assertEquals(post.getBody(),postView1.getBody());
        assertEquals(post.getId(),postView1.getId());
        assertEquals(post.getHeader(),postView1.getHeader());
        assertEquals(post.getPerson().getUser().getId(),postView1.getId());
    }
    @Test
    void mappedFromView_WhereContentIsExist_ReturnValidEntity(){
        //given
        validTest();
        FilePerson filePerson = new FilePerson();
        filePerson.setName("12");
        filePerson.setId(1L);
        Mockito.doReturn(filePerson).when(this.generateContent).generateContent(urlImg+"12");
        PostView postViewWithoutContent = new PostView();
        postViewWithoutContent = postView;
        postViewWithoutContent.setContent(List.of(urlImg+"12"));
        //when
        var post = postMapper.mappedFromView(postViewWithoutContent);
        //then
        assertNotNull(post.getContent());
        assertArrayEquals(postViewWithoutContent.getContent().stream()
                .map(generateContent::generateContent).toArray(), post.getContent().toArray());

    }
    @Test
    void mappedFromView_WhereCommentIsExist_ReturnValidateEntity(){
        //given
        validTest();
        PostView postViewWithComments = new PostView();
        postViewWithComments=postView;
        postViewWithComments.setComments(List.of("Wow","Crazy"));
        //when
        var post = postMapper.mappedFromView(postViewWithComments);
        //then
        assertNotNull(post.getComments());
        assertArrayEquals(post.getComments().stream()
                .map(Comment::getText).toArray(),postViewWithComments.getComments().toArray());
    }


}