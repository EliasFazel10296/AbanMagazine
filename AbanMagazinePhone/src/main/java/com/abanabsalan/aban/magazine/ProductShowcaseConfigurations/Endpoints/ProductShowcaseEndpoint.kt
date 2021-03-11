/*
 * Copyright © 2021 By Geeks Empire.
 *
 * Created by Elias Fazel on 3/11/21 9:24 AM
 * Last modified 3/11/21 9:23 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.ProductShowcaseConfigurations.Endpoints

class ProductShowcaseEndpoint {

    private object Security {
        const val ConsumerKey = "ck_7ca66b035ebaedd855568e0e51fa4d3c54a2706a"
        const val ConsumerSecret = "cs_5e3d3b4da30f32d124613401da35a6c7997e551b"
    }
    /**
     * Add All Your Products Title, Image, Link To Product In A Wordpress Page Inside
     * -
     * Then Replace This Link.
     **/
    val getAllProductsShowcaseEndpoint = "https://abanabsalan.com/storefront/wp-json/wc/v3/products?" +
            "consumer_key=${Security.ConsumerKey}" +
            "&" +
            "consumer_secret=${Security.ConsumerSecret}"

    fun getProductsSearchEndpoint(productSearchQuery: String): String = "$getAllProductsShowcaseEndpoint&search=$productSearchQuery"

}