package guru.springframework.msscbeerservice.repositories

import guru.springframework.msscbeerservice.domain.Beer
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface BeerRepository : JpaRepository<Beer, UUID>