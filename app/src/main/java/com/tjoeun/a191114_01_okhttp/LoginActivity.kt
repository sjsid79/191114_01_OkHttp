package com.tjoeun.a191114_01_okhttp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.tjoeun.a191114_01_okhttp.utils.ServerUtil
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.signUpBtn
import kotlinx.android.synthetic.main.activity_sign_up.*
import org.json.JSONObject

class LoginActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        setupEvents()
        setValues()



    }

    override fun setupEvents() {
        signUpBtn.setOnClickListener {
            var intent = Intent(mContext, SignUpActivity::class.java)
            startActivity(intent)
        }

        loginBtn.setOnClickListener {
            var inputId = userIdEdt.text.toString()
            var inputPw = userPwEdt.text.toString()

            ServerUtil.postRequestLogin(mContext, inputId,inputPw, object : ServerUtil.JsonResponseHandler {
                override fun onResponse(json: JSONObject) {

                    runOnUiThread {
                        Log.d("액티비티에서 보는 응답", json.toString())

                        val code = json.getInt("code")

                        if (code == 400){
                            val message = json.getString("message")
                            Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show()
                        }
                        else if(code == 200){
                            //Toast.makeText(mContext, "로그인 성공", Toast.LENGTH_SHORT).show()
                            val dataObject = json.getJSONObject("data")
                            val userObject = dataObject.getJSONObject("user")
                            val userName = userObject.getString("name")

                            Toast.makeText(mContext, "${userName}님 환영합니다.", Toast.LENGTH_SHORT).show()
                        }

                    }

                }

            })
        }
    }

    override fun setValues() {

    }

}
