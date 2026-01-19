package com.example.handwritingmvp.view

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldTopAppBar(onDeleteClicked: () -> Unit) {
    TopAppBar(
        title = { Text("필기를 작성하세요") },
        actions = {
            // 저장 기능(캡처??)
            TopBarIconButtons(Icons.Rounded.Check) { }
            // 삭제 기능(필기, 필기와 이미지)
            TopBarIconButtons(Icons.Rounded.Delete) { onDeleteClicked }
        }
    )
}

// 상단에 표시되는 아이콘 버튼 양식
@Composable
private fun TopBarIconButtons(icon: ImageVector, onClick: () -> Unit) {
    IconButton(
        onClick = onClick
    ) {
        Icon(imageVector = icon, contentDescription = null)
    }
}