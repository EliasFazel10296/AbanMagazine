/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 9/23/20 4:20 AM
 * Last modified 9/23/20 4:18 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.CategoriesConfigurations.Network.Endpoints

import com.abanabsalan.aban.magazine.Utils.Network.GeneralEndpoints

data class CategoriesEndpointsFactory (
    /**
     * Base Domain Address. In Case You Have Several Wordpress Websites.
     **/
    var baseDomainEndpoint: String = GeneralEndpoints.GeneralEndpointsAddressDotCom,
    /**
     * Exclude Categories - Usually Set Id Number Of Uncategorized Category.
     * Add Them As Comma Separated List.
     **/
    var excludeCategory: String = "199",
    /**
     * Amount Of Categories Per Each Page - Default is 100
     **/
    var amountOfCategoriesToGet: Int = 100,
    /**
     * Sort Collection by Object Attribute
     **/
    var sortByType: String = "count",
    /**
     * Order Sort Attribute Ascending or Descending
     **/
    var sortBy: String = "desc"
)

class CategoriesEndpoints (categoriesEndpointsFactory: CategoriesEndpointsFactory) {

    /**
     * To Get All Parent Categories Check If Each Category Has Json Object With Key Of 'parent=0'
     **/
    val getCategoriesEndpointsAddress: String = "${categoriesEndpointsFactory.baseDomainEndpoint}/wp-json/wp/v2/categories?" +
            "exclude=${categoriesEndpointsFactory.excludeCategory}&per_page=${categoriesEndpointsFactory.amountOfCategoriesToGet}&orderby=${categoriesEndpointsFactory.sortByType}&order=${categoriesEndpointsFactory.sortBy}"

}