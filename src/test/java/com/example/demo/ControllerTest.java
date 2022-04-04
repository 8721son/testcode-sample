package com.example.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


class ControllerTest extends BaseTest{

    @Autowired
    public EventRepository eventRepository;

    // Event
    // Insert 저장 실패
       // val.. check
       // member not found
       // file file not found

    // Insert 저장

    // Select List
    // Select idx
    // select idx 실패


    @BeforeAll
    public void beforeAllEvent () {

        this.loginSetting();

        eventSave("content1", "title1");
        eventSave("content2", "title2");
    }

    public void eventSave (String content, String title) {
        eventRepository.save(new Event(null, title, content));
    }


    @Test
    @DisplayName("Insert Fail")
    public void insert_fail() throws Exception {

        // 1. not Content
        // 2. not Title

        mockMvc.perform(post(EVENT_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.ALL)
                .header("Authorization", "Bearer " + TOKEN )
        )
        .andDo(print())
        .andExpect(status().isBadRequest())
        ;

    }


    @Test
    @DisplayName("Insert Success")
    public void insert() throws Exception {

        ObjectMapper mapper = new ObjectMapper();

        EventDTO eventDTO = new EventDTO();
        eventDTO.setContent("content");
        eventDTO.setTitle("title");

        mockMvc.perform(post(EVENT_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.ALL)
                        .content(mapper.writeValueAsString(eventDTO))
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("title").value("title"))
                .andExpect(jsonPath("content").value("content"))
                .andDo(document("event-insert-example",
                                responseFields(
                                        fieldWithPath("idx").description("The HTTP error that occurred, e.g. `Bad Request`"),
                                        fieldWithPath("title").description("The path to which the request was made"),
                                        fieldWithPath("content").description("The HTTP status code, e.g. `400`")
                                )
                        )
                )
        ;
    }


    @Test
    @DisplayName("getList")
    public void getList() throws Exception {



        mockMvc.perform(get(EVENT_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.ALL)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].idx").value("1"))
                .andExpect(jsonPath("$[0].title").value("title1"))
                .andExpect(jsonPath("$[0].content").value("content1"))
                .andExpect(jsonPath("$[1].idx").value("2"))
                .andExpect(jsonPath("$[1].title").value("title2"))
                .andExpect(jsonPath("$[1].content").value("content2"))
                ;
        ;
    }









}