package sample.qiitaclient.model

/**
 * Created by ekushida on 2017/05/22.
 */

data class Article(val id: String,
                   val title: String,
                   val url: String,
                   var user: User)