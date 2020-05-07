package com.mingwang.nooz

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mingwang.nooz.network.Article
import kotlinx.android.synthetic.main.activity_article.*

class ArticleActivity: AppCompatActivity() {
    private lateinit var article: Article

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article)

        article = intent?.extras?.getParcelable(articleExtra)!!

        article_webview.loadUrl(article.url)
    }

    companion object {
        const val articleExtra = "ARTICLE"

        fun newIntent(article: Article, context: Context): Intent {
            val intent = Intent(context, ArticleActivity::class.java)
            intent.putExtra(articleExtra, article)
            return intent
        }
    }
}