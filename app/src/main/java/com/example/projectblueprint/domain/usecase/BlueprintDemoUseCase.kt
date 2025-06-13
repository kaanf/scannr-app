package com.example.projectblueprint.domain.usecase

import androidx.annotation.VisibleForTesting
import com.example.projectblueprint.domain.repository.BlueprintDemoRepository
import com.example.projectblueprint.framework.base.DataState
import com.example.projectblueprint.framework.usecase.DataStateUseCase
import kotlinx.coroutines.flow.FlowCollector
import javax.inject.Inject

class BlueprintDemoUseCase @Inject constructor(@get: VisibleForTesting(otherwise = VisibleForTesting.PROTECTED) internal val repository: BlueprintDemoRepository) :
    DataStateUseCase<BlueprintDemoUseCase.Params, String>() {
    data class Params(
        val query: String? = null
    )

    override suspend fun FlowCollector<DataState<String>>.execute(params: Params) {
        //
    }
}