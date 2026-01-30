package com.example.handwritingmvp

import android.net.Uri
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
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawStyle
import com.example.handwritingmvp.model.DrawingModel
import com.example.handwritingmvp.model.ImageModel
import com.example.handwritingmvp.presenter.MainPresenter
import com.example.handwritingmvp.ui.theme.HandwritingMVPTheme
import com.example.handwritingmvp.view.CanvasLayout
import com.example.handwritingmvp.view.DeleteImageAndDrawingDialog
import com.example.handwritingmvp.view.MainScreenLayout
import com.example.handwritingmvp.view.NoteLayout
import java.util.ArrayDeque

class MainActivity : ComponentActivity(), MainContract.View {
    // View에서 하나의 MainPresenter를 사용해 기능 구현
    private lateinit var presenter: MainContract.Presenter

    // 사진 선택 도구 선언 변수
    private lateinit var pickMedia: ActivityResultLauncher<PickVisualMediaRequest>

    // 삭제 대화상자가 화면에 표시되어 있는지에 대한 변수
    private var showDeleteDialog by mutableStateOf(false)

    // 표시되고 있는 사진
    private var displayedUri by mutableStateOf<Uri?>(null)

    private var allPath by mutableStateOf(emptyList<Pair<Path, DrawStyle>>())
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 사진 데이터(Model)
        val imageModel = ImageModel()
        // 필기 데이터(Model)
        val drawingModel = DrawingModel()

        // 현재 Presenter를 Presenter 함수를 구현한 클래스로 초기화
        presenter = MainPresenter(this, imageModel, drawingModel)

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
                    // presenter에 삭제 버튼 클릭 상태 전달
                    onDeleteClicked = { presenter.onDeleteClicked() },
                    // presenter에 사진 선택 도구 실행 버튼 클릭 상태 전달
                    onPickedImageClicked = { presenter.onPickedImageClicked() }
                ) {
                    // 사진 표시용(coli)
                    NoteLayout(displayedUri)
                    // 필기용
                    CanvasLayout(
                        // 화면을 터치한 순간 실행할 동작 presenter에게 요청
                        onDragStart = {},
                        // 드래그하는 동안 실행될 동작 presenter에게 요청
                        onDrag = {},
                        // 화면에서 손을 떼면 실행할 동작 presenter에게 요청
                        saveDrawing = {},
                        // 화면에 보여줄 전체 필기
                        allPath = allPath
                    )

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

    // 사진 화면에 표시
    override fun showSelectedImage(savedUri: Uri?) {
        displayedUri = savedUri
    }

    // 작성이 끝난 필기를 화면에 표시
    override fun showDrawing(savedPaths: ArrayDeque<Pair<Path, DrawStyle>>) {
        allPath = savedPaths.toList().reversed()
    }
}