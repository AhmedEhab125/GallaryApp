package com.example.gallaryapp.localData

import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.MediaStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn

class LocalDataSource(var context: Context) : ILocalDataSource {
    override suspend fun getImagePathsFromMediaStore(): Flow<List<Uri>> {
        return flow {
            val imageUris = mutableListOf<Uri>()
            val imageUri: Uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI

            val projection = arrayOf(
                MediaStore.Images.Media._ID,
                MediaStore.Images.Media.DATE_TAKEN
            )

            val sortOrder = MediaStore.Images.Media.DATE_TAKEN + " DESC"

            val query = context.contentResolver.query(imageUri, projection, null, null, sortOrder)

            query?.use { cursor ->
                val idColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID)

                while (cursor.moveToNext()) {
                    val imageId = cursor.getLong(idColumn)
                    val uri = Uri.withAppendedPath(imageUri, imageId.toString())
                    imageUris.add(uri)
                }
            }

            emit(imageUris)
        }
    }

    override suspend fun getVideosPathsFromMediaStore(): Flow<List<String>> {
        val videoPaths = mutableListOf<String>()
        val projection = arrayOf(MediaStore.Video.Thumbnails.DATA)
        val videoUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI

        val cursor: Cursor? =
            context.contentResolver.query(videoUri, projection, null, null, null)
        cursor?.use {
            val columnIndex = it.getColumnIndexOrThrow(MediaStore.Video.Thumbnails.DATA)
            while (it.moveToNext()) {
                val imagePath = it.getString(columnIndex)
                videoPaths.add(imagePath)
            }
        }
        return flowOf(videoPaths)
    }
}