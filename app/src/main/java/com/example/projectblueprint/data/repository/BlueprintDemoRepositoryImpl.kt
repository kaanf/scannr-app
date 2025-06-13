package com.example.projectblueprint.data.repository

import android.content.Context
import com.example.projectblueprint.domain.repository.BlueprintDemoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class BlueprintDemoRepositoryImpl @Inject constructor(context: Context): BlueprintDemoRepository {
    override fun getData(): Flow<List<String>> = flow {  }
}