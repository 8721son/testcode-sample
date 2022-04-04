package com.example.demo;

import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Transactional
@ActiveProfiles("dev")
@AutoConfigureRestDocs
public class BaseTest {

    @Autowired
    public MockMvc mockMvc;

    public final String EVENT_PATH = "/event";

    public String TOKEN = "";

    public void loginSetting () {

        // 통신 테스트
        TOKEN = "aaaa";


    }

}
