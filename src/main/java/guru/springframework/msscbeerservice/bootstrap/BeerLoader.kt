package guru.springframework.msscbeerservice.bootstrap

import guru.springframework.msscbeerservice.domain.Beer
import guru.springframework.msscbeerservice.repositories.BeerRepository
import org.springframework.stereotype.Component
import java.math.BigDecimal
import javax.annotation.PostConstruct

@Component
class BeerLoader(private val repository: BeerRepository) {

    @PostConstruct
    fun init() {
        if (repository.count() == 0L) {
            repository.saveAll(
                listOf(
                    brewBeer("Mango Bobs", 337010000001L, 12.95, 12, 200, "IPA"),
                    brewBeer("Galaxy Cat", 337010000002L, 11.95, 12, 200, "PALE_ALE"),
                )
            )
        }
    }

    private fun brewBeer(
        name: String,
        upc: Long,
        price: Double,
        minOnHand: Int,
        quantityToBrew: Int,
        style: String
    ): Beer? = Beer.builder()
        .name(name)
        .upc(upc)
        .price(BigDecimal.valueOf(price))
        .minOnHand(minOnHand)
        .quantityToBrew(quantityToBrew)
        .style(style)
        .build()
}