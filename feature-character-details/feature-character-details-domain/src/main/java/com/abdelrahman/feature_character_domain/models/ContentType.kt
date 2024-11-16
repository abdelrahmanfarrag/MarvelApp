package com.abdelrahman.feature_character_domain.models

enum class ContentType {
    COMICS,
    EVENTS,
    STORIES,
    SERIES
}

fun ContentType.getCharacterDetailsSection(content: ArrayList<CharacterDetailsSectionListItem>): CharacterDetailsSection {
    return when (this) {
        ContentType.COMICS -> CharacterDetailsSection.Comics(data = content)
        ContentType.EVENTS -> CharacterDetailsSection.Events(data = content)
        ContentType.STORIES -> CharacterDetailsSection.Stories(data = content)
        ContentType.SERIES -> CharacterDetailsSection.Series(data = content)
    }
}