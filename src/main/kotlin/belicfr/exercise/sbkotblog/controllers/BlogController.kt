package belicfr.exercise.sbkotblog.controllers

import belicfr.exercise.sbkotblog.entities.Article
import belicfr.exercise.sbkotblog.entities.User
import belicfr.exercise.sbkotblog.format
import belicfr.exercise.sbkotblog.repositories.ArticleRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.server.ResponseStatusException

@Controller
class BlogController(private val repository: ArticleRepository) {

    @GetMapping("/")
    fun articles(model: Model): String {
        model["title"] = "Blog"
        model["articles"] = repository
            .findAllByOrderByAddedAtDesc()
            .map {
                it.render()
            }

        return "Blog"
    }

    @GetMapping("/article/{slug}")
    fun article(@PathVariable slug: String, model: Model): String {
        val article = repository
            .findBySlug(slug)
            ?.render()
            ?: throw ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "This artice does not exist")

        model["title"] = article.title
        model["article"] = article

        return "Article"
    }

    fun Article.render() = RenderedArticle(
        slug,
        title,
        headline,
        content,
        author,
        addedAt.format())

    data class RenderedArticle(
        val slug: String,
        val title: String,
        val headline: String,
        val content: String,
        val author: User,
        val addedAt: String)

}