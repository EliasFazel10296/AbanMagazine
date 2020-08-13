/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 8/12/20 11:59 PM
 * Last modified 8/5/20 3:46 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.PostsConfigurations.FavoritedPosts.Extensions

import android.util.Log
import android.widget.Toast
import com.abanabsalan.aban.magazine.PostsConfigurations.FavoritedPosts.Network.Operations.FavoritesPostsRetrieval
import com.abanabsalan.aban.magazine.PostsConfigurations.FavoritedPosts.UI.FavoritesPostsView
import com.abanabsalan.aban.magazine.PostsConfigurations.FavoritedPosts.Utils.FavoriteIt
import com.abanabsalan.aban.magazine.R
import com.abanabsalan.aban.magazine.Utils.Network.Extensions.JsonRequestResponseInterface
import org.json.JSONArray

fun FavoritesPostsView.favoritesPostsNetworkOperations() {

    val favoriteIt: FavoriteIt = FavoriteIt(applicationContext)

    val favoritesPostsRetrieval: FavoritesPostsRetrieval = FavoritesPostsRetrieval(applicationContext)

    val allFavoritedPosts = favoriteIt.getAllFavoritedPosts()

    if (!allFavoritedPosts.isNullOrEmpty()) {

        favoritesPostsRetrieval.start(
            allFavoritedPosts,
            object : JsonRequestResponseInterface {

                override fun jsonRequestResponseSuccessHandler(rawDataJsonArray: JSONArray) {
                    super.jsonRequestResponseSuccessHandler(rawDataJsonArray)

                    favoritesPostsLiveData.prepareRawDataToRenderForAllFavoritedPosts(rawDataJsonArray)

                }

                override fun jsonRequestResponseFailureHandler(jsonError: String?) {
                    Log.d(this@favoritesPostsNetworkOperations.javaClass.simpleName, jsonError.toString())

                }

            })

    } else {

        Toast.makeText(applicationContext, getString(R.string.noMoreContent), Toast.LENGTH_LONG).show()

        this@favoritesPostsNetworkOperations.finish()

    }

}