package belicfr.exercise.sbkotblog.repositories

import belicfr.exercise.sbkotblog.entities.Article
import org.springframework.data.repository.CrudRepository

interface ArticleRepository: CrudRepository<Article, Long> {
    fun findBySlug(slug: String): Article?
    fun findAllByOrderByAddedAtDesc(): Iterable<Article>
}