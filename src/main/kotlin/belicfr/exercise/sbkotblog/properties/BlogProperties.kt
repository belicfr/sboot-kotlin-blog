package belicfr.exercise.sbkotblog.properties

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("blog")
data class BlogProperties(var title: String,
                          var banner: Banner) {

    data class Banner(val title: String? = null,
                      var content: String)
}
