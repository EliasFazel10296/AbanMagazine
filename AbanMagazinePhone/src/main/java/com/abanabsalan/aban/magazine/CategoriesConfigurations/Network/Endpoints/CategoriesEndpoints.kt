/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 7/1/20 3:54 PM
 * Last modified 7/1/20 3:54 PM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.CategoriesConfigurations.Network.Endpoints

import com.abanabsalan.aban.magazine.Utils.Network.GeneralEndpoints

data class CategoriesEndpointsFactory (
    /**
     * Exclude A Category - Usually Set Id Number Of Uncategorized Category
     **/
    var excludeCategory: Int = 199,
    /**
     * Amount Of Categories Per Each Page - Default is 100
     **/
    var amountOfCategoriesToGet: Int = 100,
    /**
     * Sort collection by object attribute
     **/
    var sortByType: String = "count",

    var IdOfCategoryToGetPosts: Int
)

class CategoriesEndpoints (subCategoriesEndpointsFactory: CategoriesEndpointsFactory = CategoriesEndpointsFactory(IdOfCategoryToGetPosts = 2016)) {

    /**
     * To Get All Parent Categories Check If Each Category Has Json Object With Key Of 'parent=0'
     **/
    val getCategoriesEndpointsAddress: String = "${GeneralEndpoints.GeneralEndpointsAddress}/wp-json/wp/v2/categories?" +
            "exclude=${subCategoriesEndpointsFactory.excludeCategory}&per_page=${subCategoriesEndpointsFactory.amountOfCategoriesToGet}&orderby=${subCategoriesEndpointsFactory.sortByType}"


    /**
     * Always Change This To Id Category You Want.
     * Get All Posts Of A Specific Category
     **/
    val getSpecificCategoryPostsEndpointAddress: String = "${GeneralEndpoints.GeneralEndpointsAddress}/wp-json/wp/v2/posts?" +
            "categories=${subCategoriesEndpointsFactory.IdOfCategoryToGetPosts}"
}