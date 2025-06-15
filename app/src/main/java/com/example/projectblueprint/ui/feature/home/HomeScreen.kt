package com.example.projectblueprint.ui.feature.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.projectblueprint.R
import com.example.projectblueprint.domain.model.RecentFile
import com.example.projectblueprint.domain.model.ToolItem
import com.example.projectblueprint.ui.component.recent_files_card.RecentFileCard
import com.example.projectblueprint.ui.component.tool_card.ToolCard
import com.example.projectblueprint.ui.navigation.BottomNavItem
import com.example.projectblueprint.ui.theme.Grey600
import com.example.projectblueprint.ui.theme.Grey900
import com.example.projectblueprint.ui.theme.ProjectBlueprintTheme
import com.example.projectblueprint.ui.theme.UrbanistTypographyExtended
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onNavigateToTab: (String) -> Unit = {}
) {
    val recentFiles by viewModel.recentFiles.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                item {
                    TopSection()
                }
                
                item {
                    ToolsGrid(
                        tools = viewModel.toolItems,
                        onToolClick = viewModel::onToolClick
                    )
                }
                
                item {
                    RecentFilesSection(
                        files = recentFiles,
                        isLoading = isLoading,
                        onFileClick = viewModel::onRecentFileClick,
                        onShareClick = viewModel::onShareFile,
                        onOptionsClick = viewModel::onFileOptions
                    )
                }
            }
            
            BottomNavigation(
                selectedTab = "home",
                onTabSelected = onNavigateToTab
            )
        }
    }
}

@Composable
private fun TopSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(Color(0xFF2196F3)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_camera),
                    contentDescription = "ProScan",
                    tint = Color.White,
                    modifier = Modifier.size(24.dp)
                )
            }
            
            Text(
                text = "ProScan",
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
        
        IconButton(onClick = { /* Handle search */ }) {
            Icon(
                painter = painterResource(id = R.drawable.ic_search),
                contentDescription = "Search",
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun ToolsGrid(
    tools: List<ToolItem>,
    onToolClick: (String) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(4),
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(tools) { tool ->
            ToolCard(
                tool = tool,
                onClick = { onToolClick(tool.id) }
            )
        }
    }
}

@Composable
private fun RecentFilesSection(
    files: List<RecentFile>,
    isLoading: Boolean,
    onFileClick: (String) -> Unit,
    onShareClick: (String) -> Unit,
    onOptionsClick: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Recent Files",
                style = MaterialTheme.typography.headlineSmall,
                color = Grey900
            )
            
            Icon(
                painter = painterResource(id = R.drawable.ic_right_arrow_blue),
                contentDescription = "View all",
                modifier = Modifier.size(24.dp),
                tint = Color.Unspecified
            )
        }
        
        Spacer(modifier = Modifier.height(12.dp))
        
        if (isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else if (files.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "No recent files",
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        } else {
            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                files.forEach { file ->
                    RecentFileCard(
                        file = file,
                        onClick = { onFileClick(file.id) },
                        onShareClick = { onShareClick(file.id) },
                        onOptionsClick = { onOptionsClick(file.id) }
                    )
                }
            }
        }
    }
}

@Composable
private fun BottomNavigation(
    selectedTab: String,
    onTabSelected: (String) -> Unit
) {
    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.Files,
        BottomNavItem.Premium,
        BottomNavItem.Account
    )
    
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.surface,
        contentColor = MaterialTheme.colorScheme.onSurface
    ) {
        items.forEach { item ->
            NavigationBarItem(
                selected = selectedTab == item.route,
                onClick = { onTabSelected(item.route) },
                icon = {
                    Icon(
                        painter = painterResource(
                            id = if (selectedTab == item.route) {
                                item.selectedIconRes
                            } else {
                                item.unselectedIconRes
                            }
                        ),
                        contentDescription = item.title,
                        modifier = Modifier.size(24.dp),
                        tint = Color.Unspecified
                    )
                },
                label = {
                    Text(
                        text = item.title,
                        style = MaterialTheme.typography.labelMedium
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color(0xFF2196F3),
                    selectedTextColor = Color(0xFF2196F3),
                    unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    indicatorColor = Color(0xFF2196F3).copy(alpha = 0.1f)
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    ProjectBlueprintTheme {
        HomeScreen()
    }
}