package com.tjoeun.a191114_01_okhttp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.tjoeun.a191114_01_okhttp.utils.ServerUtil
import kotlinx.android.synthetic.main.activity_sign_up.*
import org.json.JSONObject

class SignUpActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        setupEvents()
        setValues()
    }

    override fun setupEvents() {
        signUpBtn.setOnClickListener {
            var inputId = inputUserIdEdt.text.toString()
            var inputPw = inputUserPwEdt.text.toString()
            var inputName = userNameEdt.text.toString()
            var inputPh = userPhEdt.text.toString()

            ServerUtil.putRequestSignUp(mContext,inputId, inputPw, inputName, inputPh, object : ServerUtil.JsonResponseHandler{
                override fun onResponse(json: JSONObject) {
                    Log.d("서버응답", json.toString())
                }
            } )

        }
    }

    override fun setValues() {

    }

}
