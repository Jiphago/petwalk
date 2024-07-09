package com.example.petwalk.ui.user

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.petwalk.R
import com.example.petwalk.data.SignUpRequestDto
import com.example.petwalk.data.repository.SignUpRepository
import com.example.petwalk.network.retrofit.ApiService
import com.example.petwalk.network.retrofit.RetrofitClient
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class SignUpActivity : AppCompatActivity() {

    private lateinit var editTextName: EditText
    private lateinit var editTextEmail: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var signUpRepository: SignUpRepository
    private var selectedImageUri: Uri? = null
    private val PICK_IMAGE_REQUEST = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        editTextName = findViewById(R.id.editTextName)
        editTextEmail = findViewById(R.id.editTextEmail)
        editTextPassword = findViewById(R.id.editTextPassword)

        val apiService = RetrofitClient.createService(ApiService::class.java)
        signUpRepository = SignUpRepository(apiService)
    }

    fun onSelectPhotoClick(view: View) {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, PICK_IMAGE_REQUEST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null) {
            selectedImageUri = data.data
        }
    }

    private fun getDefaultImageFile(): File {
        val drawable = resources.getDrawable(R.drawable.icon_profile, null)
        val bitmap = (drawable as BitmapDrawable).bitmap

        val file = File(cacheDir, "default_profile.png")
        try {
            file.createNewFile()
            val bos = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.PNG, 0, bos)
            val bitmapData = bos.toByteArray()
            val fos = FileOutputStream(file)
            fos.write(bitmapData)
            fos.flush()
            fos.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return file
    }

    fun onSignUpClick(view: View) {
        val name = editTextName.text.toString()
        val email = editTextEmail.text.toString()
        val password = editTextPassword.text.toString()

        val signUpRequestDto = SignUpRequestDto(
            nickName = name,
            email = email,
            password = password,
            latitude = 0.0,  // 예제 좌표
            longitude = 0.0  // 예제 좌표
        )

        val uploadPhoto: MultipartBody.Part = if (selectedImageUri != null) {
            val file = File(selectedImageUri!!.path) // 파일 경로를 사용하여 파일 객체 생성
            val requestFile = RequestBody.create("image/*".toMediaTypeOrNull(), file)
            MultipartBody.Part.createFormData("uploadPhoto", file.name, requestFile)
        } else {
            val file = getDefaultImageFile()
            val requestFile = RequestBody.create("image/*".toMediaTypeOrNull(), file)
            MultipartBody.Part.createFormData("uploadPhoto", file.name, requestFile)
        }

        signUpRepository.signUp(
            signUpRequestDto = signUpRequestDto,
            uploadPhoto = uploadPhoto,
            onResponse = { response ->
                runOnUiThread {
                    Toast.makeText(this, response, Toast.LENGTH_SHORT).show()
                    // 회원가입 성공 시 LoginActivity로 돌아가기
                    val intent = Intent(this@SignUpActivity, LoginActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
                    startActivity(intent)
                    finish() // 현재 액티비티를 종료
                }
            },
            onFailure = {
                runOnUiThread {
                    Toast.makeText(this, "회원가입 실패", Toast.LENGTH_SHORT).show()
                }
            }
        )
    }
}
