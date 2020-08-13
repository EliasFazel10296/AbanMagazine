/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 8/12/20 11:59 PM
 * Last modified 8/5/20 3:46 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.PostsConfigurations.FavoritedPosts.Network.Endpoints

class FavoritedPostsEndpoints {

    var favoritedPostBaseLink: StringBuilder = StringBuilder("https://abanabsalan.com/wp-json/wp/v2/posts?")

    fun getFavoritedPostsEndpoints(listOfFavoritedPostIds: MutableMap<String, *>) : String {

        listOfFavoritedPostIds.forEach { key, value ->

            favoritedPostBaseLink.append("&include[]=${key}")

        }

        return favoritedPostBaseLink.toString()
    }

}