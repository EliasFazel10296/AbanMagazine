/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 7/26/20 7:07 PM
 * Last modified 7/26/20 6:54 PM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.PostsConfigurations.OfflineDatabase.RoomDatabase

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "PostsOfflineDatabase")
data class PostsDataModel(
        @NonNull @PrimaryKey var PostId: Long,

        @NonNull @ColumnInfo(typeAffinity = ColumnInfo.TEXT, name = "PostAddress") var PostAddress: String,

        @NonNull @ColumnInfo(typeAffinity = ColumnInfo.BLOB, name = "FeaturedImage") var FeaturedImage: ByteArray,
        @NonNull @ColumnInfo(typeAffinity = ColumnInfo.TEXT, name = "PostTitle") var PostTitle: String,
        @NonNull @ColumnInfo(typeAffinity = ColumnInfo.TEXT, name = "PostExcerpt") var PostExcerpt: String,

        /**
         * CSV Of Categories
         **/
        @NonNull @ColumnInfo(typeAffinity = ColumnInfo.TEXT, name = "PostCategories") var PostCategories: String,
        /**
         * CSV Of Tags
         **/
        @NonNull @ColumnInfo(typeAffinity = ColumnInfo.TEXT, name = "PostTags") var PostTags: String,

        /**
         * English - Persian
         **/
        @NonNull @ColumnInfo(typeAffinity = ColumnInfo.INTEGER, name = "PostFavorited") var PostFavorited: Int,
        @NonNull @ColumnInfo(typeAffinity = ColumnInfo.TEXT, name = "PostLanguage") var PostLanguage: String
)