package guru.springframework.msscbeerservice.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import guru.springframework.msscbeerservice.web.model.BeerDto;
import guru.springframework.msscbeerservice.web.model.BeerStyle;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import java.math.BigDecimal;
import java.util.UUID;

import static guru.springframework.msscbeerservice.web.config.AppConfig.API_BEER_V1_PATH;
import static java.util.UUID.randomUUID;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BeerController.class)
public class BeerDtoControllerTest {
    @Autowired
    private MockMvc mockMvc;
    private BeerDto validBeerDto;

    @Autowired
    private ObjectMapper mapper;

    @BeforeEach
    void init() {
        validBeerDto = BeerDto.builder()
                .beerName("Beer")
                .beerStyle(BeerStyle.ALE)
                .upc(1234567890L)
                .price(BigDecimal.valueOf(100))
                .build();
    }

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
        BeerDto beerDto = validBeerDto;
        String json = mapper.writeValueAsString(beerDto);

        performAndExpect(
                post(API_BEER_V1_PATH).contentType(APPLICATION_JSON).content(json),
                status().isCreated()
        );
    }

    @Test
    @SneakyThrows
    void updateBeerById() {
        BeerDto beerDto = validBeerDto;
        String json = mapper.writeValueAsString(beerDto);

        performAndExpect(
                put(API_BEER_V1_PATH + "/" + UUID.randomUUID()).contentType(APPLICATION_JSON).content(json),
                status().isNoContent()
        );
    }
}