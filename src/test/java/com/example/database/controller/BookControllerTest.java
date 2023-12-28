package com.example.database.controller;


import com.example.database.TestDataUtil;
import com.example.database.domain.dto.BookDTO;
import com.example.database.domain.entities.Book;
import com.example.database.service.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class BookControllerTest {

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    private BookService bookService;

    @Autowired
    public BookControllerTest(MockMvc mockMvc, ObjectMapper objectMapper , BookService bookService) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
        this.bookService = bookService;
    }


    @Test
    public void testCreateBookData() throws Exception {
        BookDTO book = TestDataUtil.createTestBookDTO(null);
        mockMvc.perform(
                MockMvcRequestBuilders.put("/books/" + book.getIsbn())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(book))
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.isbn").value(book.getIsbn())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.title").value(book.getTitle())
        );
    }

    @Test
    public void testListBooks() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/books")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testGetBook() throws Exception {
        Book book = TestDataUtil.createTestBook(null);
        bookService.save(book.getIsbn() , book);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/books/123-456")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(book))
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.isbn").isString()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.title").value(book.getTitle())
        );
    }
}
