package com.example.projectblueprint.domain.repository

import kotlinx.coroutines.flow.Flow

interface BlueprintDemoRepository {
    fun getData(): Flow<List<String>>
}