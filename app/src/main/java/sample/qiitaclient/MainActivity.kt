package sample.qiitaclient

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import com.trello.rxlifecycle.components.support.RxAppCompatActivity
import com.trello.rxlifecycle.kotlin.bindToLifecycle
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import sample.qiitaclient.client.ArticleClient
import sample.qiitaclient.model.Article
import sample.qiitaclient.model.User

class MainActivity : RxAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val progressBar = findViewById(R.id.progress_bar)

        //ダミー記事をアダプターに登録する
        val listAdapter = ArticleListAdapter(applicationContext)
        listAdapter.articles = listOf(
                dummyArticle("Kotlin入門", "太郎"),
                dummyArticle("Java入門", "太郎")
        )

        //リストビューにアダプターを設定する
        val listView = findViewById(R.id.list_view) as ListView
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
        val retrofit = Retrofit.Builder()
                .baseUrl("https://qiita.com")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()

        val articleClient = retrofit.create(ArticleClient::class.java)

        val queryEditText = findViewById(R.id.query_edit_text) as EditText
        val searchButton = findViewById(R.id.search_button) as Button

        searchButton.setOnClickListener {

            progressBar.visibility = View.VISIBLE

            articleClient.search(queryEditText.text.toString())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doAfterTerminate {
                        progressBar.visibility = View.GONE
                    }
                    .bindToLifecycle(this)
                    .subscribe({
                        queryEditText.text.clear()
                        listAdapter.articles = it
                        listAdapter.notifyDataSetChanged()
                    }, {
                        toast("エラー : $it")
                    })
        }
    }

    private fun dummyArticle(title: String, userName: String) :  Article =

        Article(id = "",
                title = title,
                url = "http://qiita.com/organizations/st-ventures",
                user = User(id = "",
                        name = userName,
                        profileImageUrl = "https://qiita-image-store.s3.amazonaws.com/0/71694/profile-images/1480679524"))
}
