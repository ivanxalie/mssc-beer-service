package guru.springframework.msscbeerservice.web.controller

import guru.springframework.msscbeerservice.services.BeerService
import guru.springframework.msscbeerservice.web.config.AppConfig.Companion.API_BEER_V1_PATH
import guru.springframework.msscbeerservice.web.config.AppConfig.Companion.MOCK_HOST_PORT
import guru.springframework.msscbeerservice.web.model.BeerDto
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI
import java.util.*
import javax.validation.Valid

@RestController
@RequestMapping(API_BEER_V1_PATH)
class BeerController(private val beerService: BeerService) {

    @GetMapping("/{beerId}")
    fun getBeerById(@PathVariable("beerId") beerId: UUID): ResponseEntity<BeerDto> {
        return ResponseEntity.ok(
            beerService.getById(beerId)
        )
    }

    @PostMapping
    fun saveNewBeer(@Valid @RequestBody beerDto: BeerDto): ResponseEntity<String> {
        val saved = beerService.saveNewBeer(beerDto)
        return ResponseEntity
            .created(URI.create("${MOCK_HOST_PORT}/${API_BEER_V1_PATH}/${saved.id}"))
            .build()
    }

    @PutMapping("/{beerId}")
    fun updateBeerById(
        @PathVariable("beerId") beerId: UUID,
        @Valid @RequestBody beerDto: BeerDto
    ): ResponseEntity<Any> {
        beerService.updateBeerById(beerId, beerDto)
        return ResponseEntity.noContent().build()
    }
}