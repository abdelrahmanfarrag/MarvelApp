package com.abdelrahman.shared_presentation.models

import com.abdelrahman.shared_presentation.ui.LoadingTypes

data class PagingComponentModel<T>(
    val loadingTypes: LoadingTypes = LoadingTypes.NONE,
    val iPagingComponentInteractions: IPagingComponentInteractions,
    val listItems: ArrayList<T>? = arrayListOf(),

    )