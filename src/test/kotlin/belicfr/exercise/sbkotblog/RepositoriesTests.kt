package belicfr.exercise.sbkotblog

import belicfr.exercise.sbkotblog.entities.Article
import belicfr.exercise.sbkotblog.entities.User
import belicfr.exercise.sbkotblog.repositories.ArticleRepository
import belicfr.exercise.sbkotblog.repositories.UserRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.data.repository.findByIdOrNull

@DataJpaTest
class RepositoriesTests @Autowired constructor(
    var entityManager: TestEntityManager,
    var userRepository: UserRepository,
    var articleRepository: ArticleRepository) {

    @Test
    fun `When findByIdOrNull then return Article`() {
        var johnDoe: User = User("johnDoe", "John", "Doe")
        entityManager.persist(johnDoe)

        var article: Article = Article("Lorem", "Lorem", "dolor sit amet", johnDoe)
        entityManager.persist(article)
        entityManager.flush()

        val found = articleRepository.findByIdOrNull(article.id!!)
        assertThat(found).isEqualTo(article)
    }

    @Test
    fun `When findByLogin then return User`() {
        val johnDoe: User = User("johnDoe", "John", "Doe")
        entityManager.persist(johnDoe)
        entityManager.flush()

        val user = userRepository.findByLogin(johnDoe.login)
        assertThat(user).isEqualTo(johnDoe)
    }
}