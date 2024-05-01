package guru.springframework.msscbeerservice.services

import guru.springframework.msscbeerservice.web.model.BeerDto
import java.util.*

interface BeerService {
    fun getById(beerId: UUID): BeerDto
    fun saveNewBeer(beerDto: BeerDto): BeerDto
    fun updateBeerById(beerId: UUID, beerDto: BeerDto): BeerDto
}