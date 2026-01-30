package com.example.handwritingmvp

import android.net.Uri
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawStyle
import java.util.ArrayDeque

interface MainContract {
    interface View {
        fun showDeleteDialog()
        fun hideDeleteDialog()
        fun openImagePicker()
        fun showSelectedImage(savedUri: Uri?)
        fun showDrawing(savedPaths: ArrayDeque<Pair<Path, DrawStyle>>)
    }

    interface Presenter {
        fun onDeleteClicked()
        fun onConfirmDelete(withImage: Boolean)
        fun closeDeleteDialog()
        fun onPickedImageClicked()
        fun onImagePicked(selectedUri: Uri?)
        fun onDragStart(offset: Offset)
        fun saveDrawing(newPath: Pair<Path, DrawStyle>)
    }
}