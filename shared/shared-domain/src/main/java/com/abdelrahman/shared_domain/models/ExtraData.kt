package com.abdelrahman.shared_domain.models

import androidx.annotation.StringRes
import com.abdelrahman.shared_domain.R
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

data class ExtraData(@StringRes val sectionName: Int, val url: String?)
