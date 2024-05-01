package guru.springframework.msscbeerservice.services

import guru.springframework.msscbeerservice.repositories.BeerRepository
import guru.springframework.msscbeerservice.web.mappers.BeerMapper
import guru.springframework.msscbeerservice.web.model.BeerDto
import org.springframework.stereotype.Service
import java.util.*

@Service
class BeerServiceImpl(private val beerRepository: BeerRepository, private val beerMapper: BeerMapper) : BeerService {
    override fun getById(beerId: UUID): BeerDto = beerMapper
        .beerToBeerDto(beerRepository.findById(beerId)
            .orElseThrow { BeerNotFoundException(beerId) })

    override fun saveNewBeer(beerDto: BeerDto): BeerDto = beerMapper
        .beerToBeerDto(
            beerRepository
                .save(beerMapper.beerDtoToBeer(beerDto))
        )

    override fun updateBeerById(beerId: UUID, beerDto: BeerDto): BeerDto {
        val beer = beerRepository.findById(beerId).orElseThrow { BeerNotFoundException(beerId) }

        beer.name = beerDto.beerName
        beer.style = beerDto.beerStyle.name
        beer.price = beerDto.price
        beer.upc = beerDto.upc

        return beerMapper.beerToBeerDto(beerRepository.save(beer))
    }
}