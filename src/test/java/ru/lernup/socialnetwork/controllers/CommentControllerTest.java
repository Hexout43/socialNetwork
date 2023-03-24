package ru.lernup.socialnetwork.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.lernup.socialnetwork.Db.Repository.UserRepository;
import ru.lernup.socialnetwork.service.CommentService;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class CommentControllerTest {
 @Mock
    UserRepository userRepository;
 @Mock
    CommentService commentService;
 @InjectMocks
    CommentController commentController;
 @Test
    void createComment(){
     //given

     //when

     //then
 }
 @Test
    void updateComment(){
     //given

     //when

     //then
 }
 @Test
    void deleteComment(){
     //given

     //when

     //then
 }
}