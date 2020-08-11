/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 8/11/20 1:59 AM
 * Last modified 8/11/20 1:57 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.SearchConfigurations.UI.Utils

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.constraintlayout.widget.ConstraintLayout
import com.abanabsalan.aban.magazine.HomePageConfigurations.Extensions.hidePopupSearches
import com.abanabsalan.aban.magazine.HomePageConfigurations.UI.HomePage
import com.abanabsalan.aban.magazine.R
import com.abanabsalan.aban.magazine.SearchConfigurations.Network.Operations.SearchResultsRetrieval
import com.abanabsalan.aban.magazine.SearchConfigurations.UI.SearchResults
import com.abanabsalan.aban.magazine.Utils.Network.Extensions.JsonRequestResponseInterface
import com.abanabsalan.aban.magazine.Utils.UI.Display.navigationBarHeight
import com.abanabsalan.aban.magazine.databinding.SearchPopupUiViewBinding
import com.google.firebase.analytics.FirebaseAnalytics
import org.json.JSONArray

class SetupSearchViewUI (private val context: HomePage, private val searchPopupUiViewBinding: SearchPopupUiViewBinding) {

    init {

        val searchPopupUiViewParams = searchPopupUiViewBinding.textInputSearchView.layoutParams as ConstraintLayout.LayoutParams
        searchPopupUiViewParams.bottomMargin = searchPopupUiViewParams.bottomMargin + navigationBarHeight(context)
        searchPopupUiViewBinding.textInputSearchView.layoutParams = searchPopupUiViewParams

        searchPopupUiViewBinding.searchView.requestFocus()

        searchPopupUiViewBinding.root.setOnClickListener {

            context.hidePopupSearches()

        }

        searchPopupUiViewBinding.searchViewAction.setOnClickListener {

            if (searchPopupUiViewBinding.searchView.text.isNullOrEmpty()) {



            } else {

                invokeSearchingProcess(searchPopupUiViewBinding.searchView.text.toString())

            }


        }

        searchPopupUiViewBinding.searchView.setOnEditorActionListener { textView, actionId, keyEvent ->

            when (actionId) {
                EditorInfo.IME_ACTION_SEARCH -> {

                    if (textView.text.isNullOrEmpty()) {



                    } else {

                        invokeSearchingProcess(textView.text.toString())

                    }

                }
            }

            true
        }

    }

    fun invokeSearchingProcess(searchQuery: String) {

        searchPopupUiViewBinding.loadingView.visibility = View.VISIBLE
        searchPopupUiViewBinding.loadingView.playAnimation()

        searchPopupUiViewBinding.textInputSearchView.error = ""
        searchPopupUiViewBinding.textInputSearchView.isErrorEnabled = false

        SearchResultsRetrieval(context, "${searchQuery}")
            .start(object : JsonRequestResponseInterface {

                override fun jsonRequestResponseSuccessHandler(rawDataJsonArray: JSONArray) {
                    super.jsonRequestResponseSuccessHandler(rawDataJsonArray)

                    Intent(context, SearchResults::class.java).apply {
                        putExtra(Intent.EXTRA_TEXT, rawDataJsonArray.toString())

                        context.startActivity(this@apply, ActivityOptions.makeCustomAnimation(context, R.anim.fade_in, 0).toBundle())
                    }

                    context.homePageViewBinding.searchPopupInclude.loadingView.pauseAnimation()
                    context.homePageViewBinding.searchPopupInclude.root.visibility = View.INVISIBLE

                }

                override fun jsonRequestResponseFailureHandler(networkError: Int?) {
                    super.jsonRequestResponseFailureHandler(networkError)

                    searchPopupUiViewBinding.textInputSearchView.error = context.getString(R.string.searchError)
                    searchPopupUiViewBinding.textInputSearchView.isErrorEnabled = true

                }

            })

        val firebaseAnalytics = FirebaseAnalytics.getInstance(context)
        firebaseAnalytics.logEvent(this@SetupSearchViewUI.javaClass.simpleName, Bundle().apply {
            putString("SearchQuery", searchQuery)
        })

    }

}