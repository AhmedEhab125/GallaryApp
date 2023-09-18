package com.example.gallaryapp.localData

import android.content.Context
import android.database.Cursor
import android.provider.MediaStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class LocalDataSource(var context: Context) : ILocalDataSource {
    override suspend fun getImagePathsFromMediaStore(): Flow<List<String>> {
        val imagePaths = mutableListOf<String>()
        val projection = arrayOf(MediaStore.Images.Media.DATA)
        val imageUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI

        val cursor: Cursor? =
            context.contentResolver.query(imageUri, projection, null, null, null)
        cursor?.use {
            val columnIndex = it.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
            while (it.moveToNext()) {
                val imagePath = it.getString(columnIndex)
                imagePaths.add(imagePath)
            }
        }
        return flowOf(imagePaths)
    }
}