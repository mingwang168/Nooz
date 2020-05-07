package com.mingwang.nooz.network

import android.util.Log
import com.mingwang.nooz.BuildConfig
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

class NewsAPI(){
    // Moshi to help parse our JSON response into kotlin class objects
    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    // Retrofit as our HTTP Client to interact with the Api
    private val retrofit: Retrofit? = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl("https://api.nytimes.com/svc/")
        .build()

    // Retrofit requires us to add an interface (see below)
// within which we add method declarations to outline
// the endpoints that we want to access
    private var api: API = retrofit!!.create<API>(API::class.java)
    // This is our coroutine (by adding GlobalScope launch Main)
    suspend fun getPopularArticles() : List<Article>? {
        return try{
            val response = api.getPopularArticlesAsync(BuildConfig.API_KEY)
            response.results
        } catch (e:Exception){
            Log.e(e.toString(),e.localizedMessage)
            null
        }
        // This implements the interface asynchronously
        // without blocking the main thread and potentially freezing our app
        val firstResult: Article = api.getPopularArticlesAsync(BuildConfig.API_KEY).results.first()
    }
}

// Retrofit requires us to use an interface, this is where we define our endpoints
interface API {
    @GET("mostpopular/v2/viewed/1.json")
    suspend fun getPopularArticlesAsync(@Query("api-key") apiKey: String): ArticleResponse
}