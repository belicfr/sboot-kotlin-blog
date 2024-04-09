package belicfr.exercise.sbkotblog.repositories

import belicfr.exercise.sbkotblog.entities.User
import org.springframework.data.repository.CrudRepository

interface UserRepository: CrudRepository<User, Long> {
    fun findByLogin(login: String): User?
}