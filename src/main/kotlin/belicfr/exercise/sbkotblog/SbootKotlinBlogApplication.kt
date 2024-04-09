package belicfr.exercise.sbkotblog

import belicfr.exercise.sbkotblog.properties.BlogProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties(BlogProperties::class)
class SbootKotlinBlogApplication

fun main(args: Array<String>) {
	runApplication<SbootKotlinBlogApplication>(*args)
}
