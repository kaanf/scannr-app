package com.example.projectblueprint.domain.model

import java.util.Date

data class RecentFile(
    val id: String,
    val name: String,
    val thumbnailUrl: String? = null,
    val date: Date,
    val size: Long? = null,
    val type: FileType = FileType.PDF
)

enum class FileType {
    PDF, IMAGE, DOCUMENT
}