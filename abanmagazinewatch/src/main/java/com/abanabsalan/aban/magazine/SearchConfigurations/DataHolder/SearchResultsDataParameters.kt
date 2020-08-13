/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 8/12/20 11:59 PM
 * Last modified 8/10/20 5:08 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.SearchConfigurations.DataHolder

object SearchResultJsonStructure {
    const val SearchResultPostId: String = "id"
}

data class SearchResultsDataParameters (var searchResultPostId: String)