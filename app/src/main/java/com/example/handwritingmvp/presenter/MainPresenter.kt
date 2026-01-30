package com.example.handwritingmvp.presenter

import android.net.Uri
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawStyle
import com.example.handwritingmvp.MainContract
import com.example.handwritingmvp.model.DrawingModel
import com.example.handwritingmvp.model.ImageModel

class MainPresenter(
    private var view: MainContract.View,
    private val imageModel: ImageModel,
    private val drawingModel: DrawingModel
) : MainContract.Presenter {
    // 대화상자 표시 요청
    override fun onDeleteClicked() {
        view.showDeleteDialog()
    }

    // 조건 확인해서 model에 데이터 삭제 요청
    override fun onConfirmDelete(withImage: Boolean) {
        if (withImage) {
            // Model에서 이미지와 필기 데이터 모두 null로 변경
            // 사진 uri 제거 요청
            imageModel.deleteUri()
            // 변경된 데이터를 적용해서 화면을 다시 그리도록 요청
            view.showSelectedImage(imageModel.sendUri())
        } else {
            // Model에서 필기 데이터만 null로 변경
        }
        // 모든 작업이 끝나면 대화상자 닫기
        view.hideDeleteDialog()
    }

    // 대화상자 닫기 요청
    override fun closeDeleteDialog() {
        view.hideDeleteDialog()
    }

    // 사진 선택 도구 실행 요청
    override fun onPickedImageClicked() {
        view.openImagePicker()
    }

    // 선택한 사진 uri Model에 저장 요청 및 화면에 표시 요청
    override fun onImagePicked(selectedUri: Uri?) {
        // uri가 빈값이라면
        if (selectedUri == null) {
            // 기존 사진 uri 제거 요청
            imageModel.deleteUri()
        } else {
            // 현재 저장된 uri값 대신 넘어온 uri값을 저장 요청
            imageModel.updateUri(selectedUri)
        }
        // 화면에 표시 요청
        // uri 데이터는 Model에 전달 요청
        view.showSelectedImage(imageModel.sendUri())
    }

    override fun onDragStart(offset: Offset) {
        TODO("Not yet implemented")
    }

    // Model에 필기 저장 요청 및 화면 표시 요청
    override fun saveDrawing(newPath: Pair<Path, DrawStyle>) {
        // Model에 필기 데이터 저장 요청
        drawingModel.updatePath(newPath)

        // 전체 필기 화면에 표시 요청
        view.showDrawing(drawingModel.sendPath())
    }
}