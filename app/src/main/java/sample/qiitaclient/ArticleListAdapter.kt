package sample.qiitaclient

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import sample.qiitaclient.model.Article
import sample.qiitaclient.view.ArticleView

/**
 * Created by ekushida on 2017/05/23.
 */

class ArticleListAdapter(private val context: Context) : BaseAdapter(){

    var articles: List<Article> = emptyList()

    override  fun getCount() : Int = articles.size

    override fun getItem(position: Int) : Any? = articles[position]

    override fun getItemId(position: Int) : Long = 0

    override fun getView(position: Int,
                         convertView: View?,
                         parent: ViewGroup?) : View =

            //エルビス演算子 & apply（拡張関数）
            ((convertView as? ArticleView) ?: ArticleView(context)).apply {

                //artileに値を設定したオブジェクトを返す
                setArticle(articles[position])
    }
}