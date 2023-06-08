package com.example.springsecurityjava9session.api;

import com.example.springsecurityjava9session.dto.request.BoardRequest;
import com.example.springsecurityjava9session.dto.response.BoardResponse;
import com.example.springsecurityjava9session.dto.response.SimpleResponse;
import com.example.springsecurityjava9session.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/boards")
public class BoardApi {

    private final BoardService boardService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/save-board")
    BoardResponse saveBoard(@RequestBody BoardRequest request) {
        return boardService.saveBoard(request);
    }

    @PutMapping("/update-board/{id}")
    SimpleResponse updateBoard(@PathVariable Long id,
                               @RequestBody BoardRequest request) {
        return boardService.updateBoard(id, request);
    }

    @GetMapping
    List<BoardResponse> getAll() {
        return boardService.getAllCreatorBoard();
    }
}
