package ru.lernup.socialnetwork.mapper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.lernup.socialnetwork.Db.Entity.Comment;
import ru.lernup.socialnetwork.Db.Entity.FilePerson;
import ru.lernup.socialnetwork.Db.Entity.Person;
import ru.lernup.socialnetwork.Db.Entity.Post;
import ru.lernup.socialnetwork.Db.Repository.FileRepository;
import ru.lernup.socialnetwork.Db.Repository.PersonRepository;
import ru.lernup.socialnetwork.service.GenerateContent;
import ru.lernup.socialnetwork.view.PostView;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PostMapper {
    private final PersonRepository personRepository;
    private final GenerateContent generateContent;



    public PostMapper(PersonRepository personRepository,
                      GenerateContent generateContent) {
        this.personRepository = personRepository;
        this.generateContent = generateContent;


    }

    public PostView mappedToView(Post post){
        PostView postView = new PostView();
        postView.setId(post.getId());
        postView.setBody(post.getBody());
        postView.setHeader(post.getHeader());
        postView.setPerson(post.getPerson().getUser().getId());
        if (post.getComments()!=null) {
            postView.setComments(post.getComments().stream().map(Comment::getText)
                    .collect(Collectors.toList()));
        }
        if (post.getContent()!=null) {
            postView.setContent(post.getContent().stream().map(generateContent::generateUrl)
                    .collect(Collectors.toList()));
        }
        return postView;
    }
    public Post mappedFromView(PostView postView){
        Post post = new Post();
        post.setBody(postView.getBody());
        post.setHeader(postView.getHeader());
        post.setId(postView.getId());
        Person person = personRepository.getReferenceById(postView.getPerson());
//        if (person.getPosts()!=null){
//            person.getPosts().add(post);
//        }
//        else person.setPosts(List.of(post));
        post.setPerson(person);
        if(postView.getComments()!=null){
        post.setComments(postView.getComments().stream().map(comment->{
            Comment comment1 = new Comment();
            comment1.setText(comment);
            comment1.setIdPost(post);
            return comment1;
        }).collect(Collectors.toList()));}
        if(postView.getContent()!=null) {

            post.setContent(postView.getContent().stream().map(content->{
                FilePerson filePerson = generateContent.generateContent(content);
                filePerson.setPost(post);
                return filePerson;
            }).collect(Collectors.toList()));
        }
        return post;
    }

}
