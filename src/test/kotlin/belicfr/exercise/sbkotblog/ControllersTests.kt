package belicfr.exercise.sbkotblog

import belicfr.exercise.sbkotblog.entities.Article
import belicfr.exercise.sbkotblog.entities.User
import belicfr.exercise.sbkotblog.repositories.ArticleRepository
import belicfr.exercise.sbkotblog.repositories.UserRepository
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import javax.print.attribute.standard.Media

@WebMvcTest
class ControllersTests(@Autowired val mockMvc: MockMvc) {

    @MockkBean
    lateinit var userRepository: UserRepository

    @MockkBean
    lateinit var articleRepository: ArticleRepository

    @Test
    fun `List articles`() {
        val johnDoe: User = User("johnDoe", "John", "Doe")
        val lorem5Article: Article = Article(
            "Lorem",
            "Lorem",
            "dolor sit amet",
            johnDoe)
        val ipsumArticle: Article = Article(
            "Ipsum",
            "Ipsum",
            "dolor sit amet",
            johnDoe)

        every {
            articleRepository.findAllByOrderByAddedAtDesc()
        } returns listOf(lorem5Article, ipsumArticle)

        mockMvc.perform(
            get("/api/article/").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("\$.[0].author.login").value(johnDoe.login))
            .andExpect(jsonPath("\$.[0].slug").value(lorem5Article.slug))
            .andExpect(jsonPath("\$.[1].author.login").value(johnDoe.login))
            .andExpect(jsonPath("\$.[1].slug").value(ipsumArticle.slug))

    }

    @Test
    fun `List users`() {
        val johnDoe: User = User("johnDoe", "John", "Doe")
        val janeDoe: User = User("janeDoe", "Jane", "Doe")

        every {
            userRepository.findAll()
        } returns listOf(johnDoe, janeDoe)

        mockMvc.perform(
            get("/api/user/").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("\$.[0].login").value(johnDoe.login))
            .andExpect(jsonPath("\$.[1].login").value(janeDoe.login))
    }

}