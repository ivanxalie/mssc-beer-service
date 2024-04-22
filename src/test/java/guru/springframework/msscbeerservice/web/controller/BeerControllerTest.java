package guru.springframework.msscbeerservice.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import guru.springframework.msscbeerservice.web.model.Beer;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.UUID;

import static guru.springframework.msscbeerservice.web.config.AppConfig.API_BEER_V1_PATH;
import static java.util.UUID.randomUUID;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BeerController.class)
public class BeerControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Test
    @SneakyThrows
    void getBeerById() {
        performAndExpect(
                get(API_BEER_V1_PATH + "/" + randomUUID()).accept(APPLICATION_JSON),
                status().isOk());
    }

    @SneakyThrows
    private void performAndExpect(RequestBuilder builder, ResultMatcher matcher) {
        mockMvc.perform(builder).andExpect(matcher);
    }

    @Test
    @SneakyThrows
    void saveNewBeer() {
        Beer beer = Beer.builder().build();
        String json = mapper.writeValueAsString(beer);

        performAndExpect(
                post(API_BEER_V1_PATH).contentType(APPLICATION_JSON).content(json),
                status().isCreated()
        );
    }

    @Test
    @SneakyThrows
    void updateBeerById() {
        Beer beer = Beer.builder().build();
        String json = mapper.writeValueAsString(beer);

        performAndExpect(
                put(API_BEER_V1_PATH + "/" + UUID.randomUUID()).contentType(APPLICATION_JSON).content(json),
                status().isNoContent()
        );
    }
}