package com.HappyScrolls.service;

import com.HappyScrolls.dto.CommentDTO;
import com.HappyScrolls.entity.Article;
import com.HappyScrolls.entity.Comment;
import com.HappyScrolls.entity.Member;
import com.HappyScrolls.repository.ArticleRepository;
import com.HappyScrolls.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {

    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private CommentRepository commentRepository;

    public CommentDTO.Response commentCreate(Member member, CommentDTO.Request request) {

        Article article = articleRepository.findById(request.getPostId()).get();

        Comment makeComment = request.toEntity();
        makeComment.setArticle(article);
        makeComment.setMember(member);

        commentRepository.save(makeComment);

        return CommentDTO.Response
                .builder()
                .id(makeComment.getId())
                .body(makeComment.getBody())
                .build();
    }

    public List<CommentDTO.Response> commentRetrieve(Long id) {
        Article article = articleRepository.findById(id).get();

        List<Comment> comments = commentRepository.findByArticle(article);

        List<CommentDTO.Response> response = new ArrayList<>();
        for (Comment comment : comments) {
            response.add(CommentDTO.Response
                    .builder()
                    .id(comment.getId())
                    .body(comment.getBody())
                    .build());
        }

        return response;

    }


    public CommentDTO.Response commentEdit(Member member, CommentDTO.Edit request) {
        //유저 검증 로직

        Comment editComment = commentRepository.findById(request.getId()).get();

        editComment.edit(request);

        return CommentDTO.Response
                .builder()
                .id(editComment.getId())
                .body(editComment.getBody())
                .build();
    }

    public void commentDelete(Member member, Long id) {
        //유저 검증 로직

        Comment deleteComment = commentRepository.findById(id).get();

        commentRepository.delete(deleteComment);

    }
}

