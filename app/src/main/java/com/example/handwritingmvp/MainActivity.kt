package com.example.handwritingmvp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.handwritingmvp.model.ImageModel
import com.example.handwritingmvp.presenter.MainPresenter
import com.example.handwritingmvp.ui.theme.HandwritingMVPTheme
import com.example.handwritingmvp.view.DeleteImageAndDrawingDialog
import com.example.handwritingmvp.view.MainScreenLayout

class MainActivity : ComponentActivity(), MainContract.View {
    // View에서 하나의 MainPresenter를 사용해 기능 구현
    private lateinit var presenter: MainContract.Presenter

    // 사진 선택 도구 선언 변수
    private lateinit var pickMedia: ActivityResultLauncher<PickVisualMediaRequest>

    // 삭제 대화상자가 화면에 표시되어 있는지에 대한 변수
    private var showDeleteDialog by mutableStateOf(false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 사진 데이터(Model)
        val imageModel = ImageModel()

        // 현재 Presenter를 Presenter 함수를 구현한 클래스로 초기화
        presenter = MainPresenter(this, imageModel)

        // 사진선택도구 단일 사진
        pickMedia = registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
                uri.let {
                    presenter.onImagePicked(uri)
                }
            }

        enableEdgeToEdge()
        setContent {
            HandwritingMVPTheme {
                MainScreenLayout(
                    onDeleteClicked = { presenter.onDeleteClicked() },
                    onPickedImageClicked = { presenter.onPickedImageClicked() }) {
                    if (showDeleteDialog) {
                        DeleteImageAndDrawingDialog(
                            // 이미지와 필기 모두 삭제
                            onConfirmWithImage = { presenter.onConfirmDelete(true) },
                            // 이미지만 삭제
                            onConfirmWithoutImage = { presenter.onConfirmDelete(false) },
                            // 취소 요청을 취소했을 때, Presenter에 상태 전달(대화상자 닫기)
                            onDismiss = { presenter.closeDeleteDialog() }
                        )
                    }
                }
            }
        }
    }

    // 삭제 여부를 확인하는 대화상자 표시
    override fun showDeleteDialog() {
        showDeleteDialog = true
    }

    // 삭제 여부를 확인하는 대화상자 닫기
    override fun hideDeleteDialog() {
        showDeleteDialog = false
    }

    // 사진 선택 도구 실행
    override fun openImagePicker() {
        pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
    }
}