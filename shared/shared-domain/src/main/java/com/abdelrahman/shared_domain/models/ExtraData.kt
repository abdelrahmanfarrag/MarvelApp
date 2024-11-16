package com.abdelrahman.shared_domain.models

import androidx.annotation.StringRes
import com.abdelrahman.shared_domain.R

sealed class ExtraData(@StringRes val sectionName: Int, val url: String?) {
    data class Details(val detailsUrl: String?) : ExtraData(R.string.details, detailsUrl)
    data class Wiki(val wikiUrl: String?) : ExtraData(R.string.wiki, wikiUrl)
    data class ComicLink(val comicLinkUrl: String?) : ExtraData(R.string.comic_link, comicLinkUrl)
}