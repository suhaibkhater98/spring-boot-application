package com.example.database.controller;


import com.example.database.TestDataUtil;
import com.example.database.domain.entities.Author;
import com.example.database.service.AuthorService;
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
public class AuthorControllerTest {

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    private AuthorService authorService;

    @Autowired
    public AuthorControllerTest(MockMvc mockMvc , ObjectMapper objectMapper , AuthorService authorService) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
        this.authorService = authorService;
    }

    @Test
    public void testCreateAuthor() throws Exception {
        Author author = TestDataUtil.createTestAuthor();
        author.setId(null);
        mockMvc.perform(
                MockMvcRequestBuilders.post("/authors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(author))
        ).andExpect(
                MockMvcResultMatchers.status().isCreated()
        );
    }

    @Test
    public void testCreateAuthorContent() throws Exception {
        Author author = TestDataUtil.createTestAuthor();
        author.setId(null);
        mockMvc.perform(
                MockMvcRequestBuilders.post("/authors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(author))
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.id").isNumber()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.name").value("Suhaib Khater")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.age").value(25)
        );
    }

    @Test
    public void testListAuthors() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/authors")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testGetAuthor() throws Exception {
        Author author = TestDataUtil.createTestAuthor();
        authorService.save(author);
        mockMvc.perform(
                MockMvcRequestBuilders.get("/authors/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(author))
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.id").isNumber()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.name").value(author.getName())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.age").value(author.getAge())
        );
    }

    @Test
    public void testUpdateAuthorNotFound() throws Exception {
        Author author = TestDataUtil.createTestAuthor();
        mockMvc.perform(
                MockMvcRequestBuilders.put("/authors/23")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(author))
        ).andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testUpdateAuthorExist() throws Exception {
        Author author = TestDataUtil.createTestAuthor();
        Author savedData = authorService.save(author);
        mockMvc.perform(
                MockMvcRequestBuilders.put("/authors/" + savedData.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(author))
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }


    @Test
    public void testUpdateAuthorDataAfterUpdate() throws Exception {
        Author author = TestDataUtil.createTestAuthor();
        authorService.save(author);
        author.setName("updated name");
        author.setAge(90);
        mockMvc.perform(
                MockMvcRequestBuilders.put("/authors/" + author.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(author))
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.id").value(author.getId())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.name").value(author.getName())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.age").value(author.getAge())
        );
    }
}
