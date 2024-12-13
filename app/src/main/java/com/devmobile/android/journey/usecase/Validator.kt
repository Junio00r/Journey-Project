package com.devmobile.android.journey.usecase

import java.util.regex.Pattern

object Validator {

    @JvmStatic
    val TEXT: Pattern =
        Pattern.compile("^[a-zA-ZàáâäãåąčćęèéêëėįìíîïłńòóôöõøùúûüųūÿýżźñçšžæÀÁÂÄÃÅĄĆČĐđĖĘÈÉÊËÌÍÎÏĮŁŃÒÓÔÖÕØÙÚÛÜŲŪŸÝŻŹÑßÇŒÆŠŽ∂ð ,.'-]+\$")

    @JvmStatic
    val NUMBER: Pattern = Pattern.compile("^\\d+\$")

    @JvmStatic
    fun isMatch(text: String?, type: Pattern): Boolean {

        if (text.isNullOrEmpty()) return false

        return when (type) {

            TEXT -> {
                Pattern.matches(TEXT.toRegex().toString(), text)
            }

            NUMBER -> {
                Pattern.matches(NUMBER.toRegex().toString(), text)
            }

            else -> {
                Pattern.matches(type.toRegex().toString(), text)
            }
        }
    }
}