/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 8/8/20 8:32 AM
 * Last modified 8/8/20 8:05 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.HomePageConfigurations.DataHolder

import android.text.Html
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.abanabsalan.aban.magazine.CategoriesConfigurations.DataHolder.CategoriesDataParameters
import com.abanabsalan.aban.magazine.CategoriesConfigurations.DataHolder.CategoriesItemData
import com.abanabsalan.aban.magazine.InstagramConfigurations.StoryHighlights.Network.DataHolder.StoryHighlightsItemData
import com.abanabsalan.aban.magazine.PostsConfigurations.DataHolder.PostsDataParameters
import com.abanabsalan.aban.magazine.PostsConfigurations.DataHolder.PostsItemData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async
import org.json.JSONArray
import org.jsoup.Jsoup
import org.jsoup.nodes.Document

class HomePageLiveData : ViewModel() {

    val newestPostsLiveItemData: MutableLiveData<ArrayList<PostsItemData>> by lazy {
        MutableLiveData<ArrayList<PostsItemData>>()
    }

    val categoriesLiveItemData: MutableLiveData<ArrayList<CategoriesItemData>> by lazy {
        MutableLiveData<ArrayList<CategoriesItemData>>()
    }

    val specificCategoryLiveItemData: MutableLiveData<ArrayList<PostsItemData>> by lazy {
        MutableLiveData<ArrayList<PostsItemData>>()
    }

    val instagramStoryHighlightsLiveItemData: MutableLiveData<ArrayList<StoryHighlightsItemData>> by lazy {
        MutableLiveData<ArrayList<StoryHighlightsItemData>>()
    }

    val controlLoadingView: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }

    /**
     * True To Force Reset Theme
     **/
    val toggleTheme: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }

    fun prepareRawDataToRenderForNewestPosts(postsJsonArray: JSONArray) = CoroutineScope(SupervisorJob() + Dispatchers.IO).async {

        controlLoadingView.postValue(true)

        val newestPostsItemData: ArrayList<PostsItemData> = ArrayList<PostsItemData>()

        for (i in 0 until postsJsonArray.length()) {
            val postJsonObject = postsJsonArray.getJSONObject(i)
            Log.d("${this@HomePageLiveData.javaClass.simpleName} PrepareRawDataToRenderForNewestPosts", postJsonObject.getString(PostsDataParameters.JsonDataStructure.PostId))

            newestPostsItemData.add(PostsItemData(
                postLink = postJsonObject.getString(PostsDataParameters.JsonDataStructure.PostLink),
                postId = postJsonObject.getString(PostsDataParameters.JsonDataStructure.PostId),
                postFeaturedImage = postJsonObject.getString(PostsDataParameters.JsonDataStructure.FeauturedImage),
                postTitle = postJsonObject.getJSONObject(PostsDataParameters.JsonDataStructure.PostTitle).getString(PostsDataParameters.JsonDataStructure.Rendered),
                postContent = postJsonObject.getJSONObject(PostsDataParameters.JsonDataStructure.PostContent).getString(PostsDataParameters.JsonDataStructure.Rendered),
                postExcerpt = postJsonObject.getJSONObject(PostsDataParameters.JsonDataStructure.PostExcerpt).getString(PostsDataParameters.JsonDataStructure.Rendered),
                postPublishDate = postJsonObject.getString(PostsDataParameters.JsonDataStructure.PostDate),
                postCategories = postJsonObject.getJSONArray(PostsDataParameters.JsonDataStructure.PostCategories).join(","),
                postTags = postJsonObject.getJSONArray(PostsDataParameters.JsonDataStructure.PostTags).join(","),
                relatedPostsContent = postJsonObject.getJSONArray(PostsDataParameters.JsonDataStructure.RelatedPosts).toString()
            ))

        }

        newestPostsLiveItemData.postValue(newestPostsItemData)

    }

    fun prepareRawDataToRenderForCategories(categoriesJsonArray: JSONArray) = CoroutineScope(SupervisorJob() + Dispatchers.IO).async {

        controlLoadingView.postValue(true)

        val categoriesItemData: ArrayList<CategoriesItemData> = ArrayList<CategoriesItemData>()

        for (i in 0 until categoriesJsonArray.length()) {
            val categoryJsonObject = categoriesJsonArray.getJSONObject(i)
            Log.d("${this@HomePageLiveData.javaClass.simpleName} PrepareRawDataToRenderForCategories", categoryJsonObject.getString(CategoriesDataParameters.JsonDataStructure.CategoryId))

            if (categoryJsonObject.getInt(CategoriesDataParameters.JsonDataStructure.CategoryParentId) == 0) {

                categoriesItemData.add(CategoriesItemData(
                    categoryLink = categoryJsonObject.getString(CategoriesDataParameters.JsonDataStructure.CategoryLink),
                    categoryId = categoryJsonObject.getString(CategoriesDataParameters.JsonDataStructure.CategoryId),
                    categoryName = categoryJsonObject.getString(CategoriesDataParameters.JsonDataStructure.CategoryName),
                    categoryDescription = categoryJsonObject.getString(CategoriesDataParameters.JsonDataStructure.CategoryDescription)
                ))

            }

        }

        categoriesLiveItemData.postValue(categoriesItemData)

    }

    fun prepareRawDataToRenderForSpecificPosts(featuredPostsJsonArray: JSONArray) = CoroutineScope(SupervisorJob() + Dispatchers.IO).async {

        controlLoadingView.postValue(true)

        val specificCategoryPostsItemData: ArrayList<PostsItemData> = ArrayList<PostsItemData>()

        for (i in 0 until featuredPostsJsonArray.length()) {
            val postJsonObject = featuredPostsJsonArray.getJSONObject(i)
            Log.d("${this@HomePageLiveData.javaClass.simpleName} PrepareRawDataToRenderForSpecificPosts", postJsonObject.getString(PostsDataParameters.JsonDataStructure.PostId))

            specificCategoryPostsItemData.add(PostsItemData(
                postLink = postJsonObject.getString(PostsDataParameters.JsonDataStructure.PostLink),
                postId = postJsonObject.getString(PostsDataParameters.JsonDataStructure.PostId),
                postFeaturedImage = postJsonObject.getString(PostsDataParameters.JsonDataStructure.FeauturedImage),
                postTitle = postJsonObject.getJSONObject(PostsDataParameters.JsonDataStructure.PostTitle).getString(PostsDataParameters.JsonDataStructure.Rendered),
                postContent = postJsonObject.getJSONObject(PostsDataParameters.JsonDataStructure.PostContent).getString(PostsDataParameters.JsonDataStructure.Rendered),
                postExcerpt = postJsonObject.getJSONObject(PostsDataParameters.JsonDataStructure.PostExcerpt).getString(PostsDataParameters.JsonDataStructure.Rendered),
                postPublishDate = postJsonObject.getString(PostsDataParameters.JsonDataStructure.PostDate),
                postCategories = postJsonObject.getJSONArray(PostsDataParameters.JsonDataStructure.PostCategories).join(","),
                postTags = postJsonObject.getJSONArray(PostsDataParameters.JsonDataStructure.PostTags).join(","),
                relatedPostsContent = postJsonObject.getJSONArray(PostsDataParameters.JsonDataStructure.RelatedPosts).toString()
            ))

        }

        specificCategoryLiveItemData.postValue(specificCategoryPostsItemData)

    }

    fun prepareRawDataToRenderForInstagramStoryHighlights(rawInstagramStoryHighlights: String) {

        val storyHighlightsItemData: ArrayList<StoryHighlightsItemData> = ArrayList<StoryHighlightsItemData>()

        val storyHighlightsContent: Document = Jsoup.parse(rawInstagramStoryHighlights)

        val allHtmlElement = storyHighlightsContent.allElements

        allHtmlElement.forEachIndexed { index, element ->
            if (element.`is`("a")) {
                Log.d(this@HomePageLiveData.javaClass.simpleName, "Link ${element}")

                val linkContent: Document = Jsoup.parse(element.toString())

                storyHighlightsItemData.add(
                    StoryHighlightsItemData(
                        linkToStoryHighlight = "${linkContent.select("a").first().attr("abs:href")}",
                        storyHighlightsName = Html.fromHtml("${element}").toString(),
                        storyHighlightsCoverImage = ""
                    ))

            }
        }

        instagramStoryHighlightsLiveItemData.postValue(storyHighlightsItemData)

    }

}