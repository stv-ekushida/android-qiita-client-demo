package sample.qiitaclient.view

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import sample.qiitaclient.R
import sample.qiitaclient.model.Article

/**
 * Created by ekushida on 2017/05/22.
 */

class ArticleView : FrameLayout {

    //セカンダリコンストラクタ
    constructor(context: Context?) : super(context)

    constructor(context: Context?,
                attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context?,
                attrs: AttributeSet?,
                defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    constructor(content: Context?,
                attrs: AttributeSet?,
                defStyleAttr: Int,
                defStyleRes: Int) : super(content, attrs, defStyleAttr, defStyleRes)


    var profileImageView: ImageView? = null
    var titleTextView: TextView? = null
    var userNameTextView: TextView? = null



    //初期化は、initブロックに書く
    init {
        LayoutInflater.from(context).inflate(R.layout.view_article, this)

        profileImageView = findViewById(R.id.profile_image_view) as ImageView
        titleTextView = findViewById(R.id.title_text_view) as TextView
        userNameTextView = findViewById(R.id.user_name_text_view) as TextView
    }

    fun setArticle(article: Article) {
        titleTextView?.text = article.title
        userNameTextView?.text = article.user.name
        profileImageView?.setBackgroundColor(Color.RED)

        Glide.with(context).load(article.user.profileImageUrl).into(profileImageView)
    }
}