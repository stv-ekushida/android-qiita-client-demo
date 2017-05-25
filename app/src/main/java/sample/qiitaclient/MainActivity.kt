package sample.qiitaclient

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ListView
import sample.qiitaclient.model.Article
import sample.qiitaclient.model.User

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //ダミー記事をアダプターに登録する
        val listAdapter = ArticleListAdapter(applicationContext)
        listAdapter.articles = listOf(
                dummyArticle("Kotlin入門", "太郎"),
                dummyArticle("Java入門", "太郎")
        )

        //リストビューにアダプターを設定する
        var listView = findViewById(R.id.list_view) as ListView
        listView.adapter = listAdapter
    }

    private fun dummyArticle(title: String, userName: String) :  Article =

        Article(id = "",
                title = title,
                url = "http://www",
                user = User(id = "", name = userName, profileImageUrl = ""))
}
