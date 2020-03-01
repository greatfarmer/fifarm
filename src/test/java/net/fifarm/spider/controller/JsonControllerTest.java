package net.fifarm.spider.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class JsonControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void getPlayerListByName() throws Exception {
        mockMvc.perform(get("/api/search/son"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(14)));
    }

    @Test
    public void getPlayerById() throws Exception {
        mockMvc.perform(get("/api/player/251858344"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("firstName").exists())
                .andExpect(jsonPath("id", is(equalTo("251858344"))));
    }

    @Test
    public void getPlayerNames() throws Exception {
        mockMvc.perform(get("/api/autocomplete?term=son"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(14)));
    }

}