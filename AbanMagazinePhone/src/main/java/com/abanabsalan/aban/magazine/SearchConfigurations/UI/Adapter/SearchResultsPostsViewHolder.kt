/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 8/10/20 6:09 AM
 * Last modified 8/10/20 5:33 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.SearchConfigurations.UI.Adapter

import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.all_search_results_posts_item.view.*
import net.geekstools.imageview.customshapes.ShapesImage

class SearchResultsPostsViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    val rootViewItem: ConstraintLayout = view.rootViewItem
    val postFeatureImageView: ShapesImage = view.postFeatureImageView
    val postTitleView: TextView = view.postTitleView
    val postExcerptView: TextView = view.postExcerptView
    val shareIcon: AppCompatImageView = view.shareIcon
}