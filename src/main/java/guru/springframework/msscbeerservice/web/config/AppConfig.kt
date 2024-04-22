package guru.springframework.msscbeerservice.web.config

import org.springframework.context.annotation.Configuration

@Configuration
abstract class AppConfig {
    companion object {
        const val MOCK_HOST_PORT = "http://localhost:8080"
        const val API_BEER_V1_PATH = "/api/v1/beer"
    }
}