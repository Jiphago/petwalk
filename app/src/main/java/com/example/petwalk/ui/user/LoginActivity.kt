package com.example.petwalk.ui.user

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.petwalk.R
import com.example.petwalk.data.repository.LoginRepository
import com.example.petwalk.network.request.LoginRequest
import com.example.petwalk.network.response.LoginResponse
import com.example.petwalk.network.retrofit.ApiService
import com.example.petwalk.network.retrofit.RetrofitClient

class LoginActivity : AppCompatActivity() {

    private lateinit var editTextEmail: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var buttonLogin: Button
    private lateinit var buttonSignUp: Button
    private lateinit var loginRepository: LoginRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        editTextEmail = findViewById(R.id.editTextEmail)
        editTextPassword = findViewById(R.id.editTextPassword)
        buttonLogin = findViewById(R.id.buttonLogin)
        buttonSignUp = findViewById(R.id.buttonSignUp)

        val apiService = RetrofitClient.createService(ApiService::class.java)
        loginRepository = LoginRepository(apiService)

        buttonSignUp.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        buttonLogin.setOnClickListener {
            val email = editTextEmail.text.toString().trim()
            val password = editTextPassword.text.toString().trim()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Email과 Password를 입력해주세요.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val loginRequest = LoginRequest(email, password)
            loginRepository.login(loginRequest,
                onResponse = {
                    runOnUiThread {
                        Toast.makeText(this, "로그인 성공!", Toast.LENGTH_SHORT).show()
                        // 로그인 성공 시 토큰을 저장하거나 MainActivity로 이동
                        // 예: SharedPreferences에 저장
                        // SharedPreferencesManager.getInstance().saveAuthToken(response.authorization)

                        val intent = Intent(this, MainActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
                        startActivity(intent)
                        finish()
                    }
                },
                onFailure = {
                    runOnUiThread {
                        Toast.makeText(this, "로그인 실패", Toast.LENGTH_SHORT).show()
                    }
                }
            )
        }
    }
}
