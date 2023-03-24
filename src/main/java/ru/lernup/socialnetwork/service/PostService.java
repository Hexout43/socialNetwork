package ru.lernup.socialnetwork.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.lernup.socialnetwork.Db.Entity.Post;
import ru.lernup.socialnetwork.Db.Repository.PersonRepository;
import ru.lernup.socialnetwork.Db.Repository.PostRepository;
import ru.lernup.socialnetwork.Db.Repository.UserRepository;
import ru.lernup.socialnetwork.mapper.PostMapper;
import ru.lernup.socialnetwork.view.PostView;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final PostMapper postMapper;
    private final PersonRepository personRepository;

    public PostService(PostRepository postRepository
            , PostMapper postMapper,
                        PersonRepository personRepository) {
        this.postRepository = postRepository;
        this.postMapper = postMapper;
        this.personRepository = personRepository;

    }
    @Transactional
    public void addPost(PostView postView){

        postRepository.save(postMapper.mappedFromView(postView));
    }
    @Transactional
    public void deletePost(Long id){
        Post post = postRepository.getReferenceById(id);

        postRepository.delete(post);
    }
    public List<PostView> getPostsByUser(Long id){
      List<Post> posts =postRepository
              .getPostsByPerson(personRepository.getReferenceById(id));
      return  posts.stream().map(postMapper::mappedToView).collect(Collectors.toList());
    }
    public List<PostView> getAllPosts(){
        return
                postRepository.findAll().stream().map(postMapper::mappedToView)
                        .collect(Collectors.toList());
    }
    @Transactional
    public void updatePost(PostView postView){
    Post post = postRepository.getReferenceById(postView.getId());
    if( postView.getBody()!=null&&!postView.getBody().equals(post.getBody())){
        post.setBody(postView.getBody());
    }
    if(postView.getHeader()!=null&&!postView.getHeader().equals(post.getHeader())){
        post.setHeader(postView.getHeader());
    }
    postRepository.save(post);
    }
    public PostView getPost(Long id){
        return postMapper.mappedToView(postRepository.getReferenceById(id));
    }
    public List<PostView> getPostsByHeader(String header){
        return
                postRepository.getPostsByHeaderContainsIgnoreCase(header).stream()
                        .map(postMapper::mappedToView).collect(Collectors.toList());
    }
    public List<PostView> getPostsByBodyContains(String body){
        return
                postRepository.getPostsByBodyContainsIgnoreCase(body).stream()
                        .map(postMapper::mappedToView).collect(Collectors.toList());
    }
}
