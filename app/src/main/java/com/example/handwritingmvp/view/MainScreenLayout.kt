package com.example.handwritingmvp.view

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun MainScreenLayout(onShowDeleteDialog: () -> Unit, content: @Composable (PaddingValues) -> Unit) {
    // 사진선택도구 단일 사진
//    val pickMedia =
//        rememberLancherForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
//            uri.let {
//                uri = uri
//            }
//        }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        // 저장, 휴지통 기능이 적용된 버튼을 포함한 상단바
        topBar = { ScaffoldTopAppBar(onDeleteClicked = { onShowDeleteDialog() }) },
        // 이미지 불러오기
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    // 사진선택도구를 불러오는 기능
                }
            ) {
                Icon(imageVector = Icons.Rounded.Edit, contentDescription = null)
            }
        }
    ) { innerPadding ->
        content(innerPadding)
    }
}