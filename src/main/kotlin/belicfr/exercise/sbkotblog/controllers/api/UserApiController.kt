package belicfr.exercise.sbkotblog.controllers.api

import belicfr.exercise.sbkotblog.repositories.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/api/user")
class UserApiController(
    private val repository: UserRepository) {

    @GetMapping("", "/")
    fun findAll() = repository.findAll()

    @GetMapping("/{login}")
    fun findOne(@PathVariable login: String)
        = repository.findByLogin(login)
            ?: throw ResponseStatusException(
                HttpStatus.NOT_FOUND, "This user does not exist")
}