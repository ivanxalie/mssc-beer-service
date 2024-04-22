package guru.springframework.msscbeerservice.web.controller

import guru.springframework.msscbeerservice.web.config.AppConfig.Companion.API_BEER_V1_PATH
import guru.springframework.msscbeerservice.web.config.AppConfig.Companion.MOCK_HOST_PORT
import guru.springframework.msscbeerservice.web.model.Beer
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI
import java.util.*

@RestController
@RequestMapping(API_BEER_V1_PATH)
class BeerController {

    @GetMapping("/{beerId}")
    fun getBeerById(@PathVariable("beerId") beerId: UUID): ResponseEntity<Beer> {
        // todo make a real implementation
        return ResponseEntity.ok(
            Beer.builder().id(beerId).build()
        )
    }

    @PostMapping
    fun saveNewBeer(@RequestBody beer: Beer): ResponseEntity<String> {
        // todo make a real implementation
        return ResponseEntity
            .created(URI.create("${MOCK_HOST_PORT}/${API_BEER_V1_PATH}/${UUID.randomUUID()}"))
            .build()
    }

    @PutMapping("/{beerId}")
    fun updateBeerById(@PathVariable("beerId") beerId: UUID, @RequestBody beer: Beer): ResponseEntity<Any> {
        // todo make a real implementation
        return ResponseEntity.noContent().build()
    }
}