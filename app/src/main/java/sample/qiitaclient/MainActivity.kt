package sample.qiitaclient

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ListView
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import sample.qiitaclient.client.ArticleClient
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


        //リストを選択したとき（ラムダ式）
        listView.setOnItemClickListener { adapterView, view, position, id ->

            val article = listAdapter.articles[position]

            //コンパニオンオブジェクトのメソッドを呼ぶ
            ArticleActivity.intent(this, article).let {
                startActivity(it)
            }
        }

        //APIクライアントの設定
        val gson = GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create()
        var retrofit = Retrofit.Builder()
                .baseUrl("https://qiita")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()

        var articleClient = retrofit.create(ArticleClient::class.java)
    }

    private fun dummyArticle(title: String, userName: String) :  Article =

        Article(id = "",
                title = title,
                url = "http://qiita.com/organizations/st-ventures",
                user = User(id = "", name = userName, profileImageUrl = ""))
}
