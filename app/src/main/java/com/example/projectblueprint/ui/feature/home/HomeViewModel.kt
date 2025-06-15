package com.example.projectblueprint.ui.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projectblueprint.R
import com.example.projectblueprint.domain.model.RecentFile
import com.example.projectblueprint.domain.model.ToolItem
import com.example.projectblueprint.ui.theme.TransparentBlue
import com.example.projectblueprint.ui.theme.TransparentBrown
import com.example.projectblueprint.ui.theme.TransparentOrange
import com.example.projectblueprint.ui.theme.TransparentPurple
import com.example.projectblueprint.ui.theme.TransparentRed
import com.example.projectblueprint.ui.theme.TransparentTeal
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {

    private val _recentFiles = MutableStateFlow<List<RecentFile>>(emptyList())
    val recentFiles: StateFlow<List<RecentFile>> = _recentFiles.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    val toolItems = listOf(
        ToolItem(
            id = "scan_code",
            name = "Scan Code",
            icon = R.drawable.ic_scan_code,
            backgroundColor = TransparentOrange
        ),
        ToolItem(
            id = "watermark",
            name = "Watermark",
            icon = R.drawable.ic_watermark,
            backgroundColor = TransparentBrown
        ),
        ToolItem(
            id = "esign_pdf",
            name = "eSign PDF",
            icon = R.drawable.ic_sign_pdf,
            backgroundColor = TransparentRed
        ),
        ToolItem(
            id = "split_pdf",
            name = "Split PDF",
            icon = R.drawable.ic_split_pdf,
            backgroundColor = TransparentPurple
        ),
        ToolItem(
            id = "merge_pdf",
            name = "Merge PDF",
            icon = R.drawable.ic_merge_pdf,
            backgroundColor = TransparentRed
        ),
        ToolItem(
            id = "protect_pdf",
            name = "Protect PDF",
            icon = R.drawable.ic_protect_pdf,
            backgroundColor = TransparentTeal
        ),
        ToolItem(
            id = "compress_pdf",
            name = "Compress PDF",
            icon = R.drawable.ic_compress_pdf,
            backgroundColor = TransparentOrange
        ),
        ToolItem(
            id = "all_tools",
            name = "All Tools",
            icon = R.drawable.ic_all_tools,
            backgroundColor = TransparentBlue
        )
    )

    init {
        loadRecentFiles()
    }

    private fun loadRecentFiles() {
        viewModelScope.launch {
            _isLoading.value = true

            val mockFiles = listOf(
                RecentFile(
                    id = "1",
                    name = "Meeting Notes.pdf",
                    date = Date(),
                    size = 1024 * 245 // 245 KB
                ),
                RecentFile(
                    id = "2",
                    name = "Invoice_2024.pdf",
                    date = Date(System.currentTimeMillis() - 86400000), // Yesterday
                    size = 1024 * 512 // 512 KB
                ),
                RecentFile(
                    id = "3",
                    name = "Project Proposal.pdf",
                    date = Date(System.currentTimeMillis() - 172800000), // 2 days ago
                    size = 1024 * 1024 * 2 // 2 MB
                )
            )
            _recentFiles.value = mockFiles
            _isLoading.value = false
        }
    }

    fun onToolClick(toolId: String) {
        // Handle tool click
    }

    fun onRecentFileClick(fileId: String) {
        // Handle file click
    }

    fun onShareFile(fileId: String) {
        // Handle share
    }

    fun onFileOptions(fileId: String) {
        // Handle file options
    }
}