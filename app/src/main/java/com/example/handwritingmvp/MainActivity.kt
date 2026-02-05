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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawStyle
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.lifecycle.MutableLiveData
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
    // 표시되고 있는 필기
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

        // point 위치 추적을 위한 State
        var point by mutableStateOf(Offset.Zero)
        // 새로 그려지는 path 표시하기 위한 points State
        val points = mutableListOf<Offset>()
        // 새로 그려지고 있는 중인 획 State
        val pathInProgress = MutableLiveData(Path())


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
                        onDragStart = { offset ->
                            point = offset
                            points.add(point)
                        },
                        // 드래그하는 동안 실행될 동작 presenter에게 요청
                        onDrag = { dragAmount ->
                            point += dragAmount
                            points.add(point)
                            // onDrag가 호출될 때마다 바로 그려지는 획 표시
                            pathInProgress.value = Path()
                            points.forEachIndexed { index, point ->
                                // 만약 드래그를 처음 시작한다면(처음 클릭한다면)
                                if (index == 0) {
                                    // 획의 시작 지점을 해당 좌표로 옮기기
                                    pathInProgress.value!!.moveTo(point.x, point.y)
                                } else {
                                    // 만약 드래그가 이미 진행되고 있었다면 직선 추가
                                    pathInProgress.value!!.lineTo(point.x, point.y)
                                }
                            }
                        },
                        // 화면에서 손을 떼면 실행할 동작 presenter에게 요청
                        onDragEnd = {
                            saveDrawing(Pair(pathInProgress.value!!, Stroke(1f)))
                            points.clear()
                            pathInProgress.value = Path()
                        },
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

    // 작성이 끝난 필기를 Model에 저장
    override fun saveDrawing(newPath: Pair<Path, DrawStyle>) {
        presenter.saveDrawing(newPath)
    }

    // 작성이 끝난 필기를 화면에 표시
    override fun showDrawing(savedPaths: ArrayDeque<Pair<Path, DrawStyle>>) {
        allPath = savedPaths.toList().reversed()
    }
}