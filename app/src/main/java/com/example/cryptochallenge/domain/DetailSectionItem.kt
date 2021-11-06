package com.example.cryptochallenge.domain

/**
 * DTO for a section of cryptocurrency detail
 *
 * @property type Section's Type
 * @property content Section's content
 */
data class DetailSectionItem(val type: SectionType, val content: Any)