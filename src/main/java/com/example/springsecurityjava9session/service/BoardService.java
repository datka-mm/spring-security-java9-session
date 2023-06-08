package com.example.springsecurityjava9session.service;

import com.example.springsecurityjava9session.dto.request.BoardRequest;
import com.example.springsecurityjava9session.dto.response.BoardResponse;
import com.example.springsecurityjava9session.dto.response.SimpleResponse;
import com.example.springsecurityjava9session.entity.Board;
import com.example.springsecurityjava9session.entity.User;
import com.example.springsecurityjava9session.repository.BoardRepository;
import com.example.springsecurityjava9session.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    private User getAuthentication() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = userRepository.getUserByEmail(email).orElseThrow(() ->
                new EntityNotFoundException("User not found!"));

        return user;
    }

    public BoardResponse saveBoard(BoardRequest request) {
        User authenticatedUser = getAuthentication();
        Board board = Board.builder()
                .name(request.name())
                .imageLink(request.imageLink())
                .createdAt(ZonedDateTime.now())
                .creator(authenticatedUser)
                .build();

        boardRepository.save(board);
        authenticatedUser.getBoards().add(board);

        return BoardResponse.builder()
                .id(board.getId())
                .name(board.getName())
                .imageLink(board.getImageLink())
                .createdAt(board.getCreatedAt())
                .build();
    }

    public SimpleResponse updateBoard(Long id, BoardRequest request) {
        User authenticatedUser = getAuthentication();
        Board board = boardRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Board with id: " + id + " not found!")
        );

        if (!authenticatedUser.equals(board.getCreator())) {
            throw new BadCredentialsException("You can't update this board!");
        }

        Board updatedBoard = Board.builder()
                .name(request.name())
                .imageLink(request.imageLink())
                .build();
        boardRepository.save(updatedBoard);

        return SimpleResponse.builder()
                .status(HttpStatus.OK)
                .message("Board is updated!")
                .build();
    }

    public List<BoardResponse> getAllCreatorBoard() {
        User authenticatedUser = getAuthentication();
        List<BoardResponse> userBoardResponses = new ArrayList<>();
        List<Board> all = boardRepository.findAll();

        for (Board b : all) {
            if (b.getCreator().equals(authenticatedUser)) {
                BoardResponse response = BoardResponse.builder()
                        .id(b.getId())
                        .name(b.getName())
                        .imageLink(b.getImageLink())
                        .createdAt(b.getCreatedAt())
                        .build();
                userBoardResponses.add(response);
            }
        }

        return userBoardResponses;

    }

}
