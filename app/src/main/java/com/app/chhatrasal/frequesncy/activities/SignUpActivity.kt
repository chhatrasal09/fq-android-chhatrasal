package com.app.chhatrasal.frequesncy.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TextInputEditText
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.app.chhatrasal.frequesncy.R
import com.app.chhatrasal.frequesncy.utils.SecureData
import com.google.firebase.auth.*
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {

    private lateinit var userNameTextInputEditText: TextInputEditText
    private lateinit var passwordTextInputEditText: TextInputEditText
    private lateinit var confirmPasswordTextInputEditText: TextInputEditText
    private lateinit var nameTextInputEditText: TextInputEditText
    private lateinit var loginButton: Button
    private lateinit var signUpButton: Button

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        initViews()

        clickEvents()
    }

    private val loginButtonClickListener: View.OnClickListener = View.OnClickListener {
        val intent = Intent(this@SignUpActivity, LoginActivity::class.java)
        startActivity(intent)
    }
    private val signUpButtonClickListener: View.OnClickListener = View.OnClickListener {
        if (!userNameTextInputEditText.text!!.isEmpty() && !passwordTextInputEditText.text!!.isEmpty()) {
            if (passwordTextInputEditText.text!!.length > 6) {
                if (passwordTextInputEditText.text!!.toString() == confirmPasswordTextInputEditText.text!!.toString()) {
                    auth.createUserWithEmailAndPassword(userNameTextInputEditText.text!!.toString(),
                            SecureData.encryptData(passwordTextInputEditText.text!!.toString())).addOnCompleteListener(this@SignUpActivity) { task1 ->
                        if (task1.isSuccessful) {
                            val profileUpdates: UserProfileChangeRequest = UserProfileChangeRequest.Builder()
                                    .setDisplayName(nameTextInputEditText.text!!.toString())
                                    .build()
                            FirebaseAuth.getInstance().currentUser!!.updateProfile(profileUpdates).addOnCompleteListener(this@SignUpActivity) { task2 ->
                                if (task2.isSuccessful) {
                                    val intent = Intent(this@SignUpActivity, HomeActivity::class.java)
                                    Toast.makeText(this@SignUpActivity, "Registration Successful.", Toast.LENGTH_LONG).show()
                                    startActivity(intent)
                                    finish()
                                }
                            }

                        } else {
                            Toast.makeText(this@SignUpActivity, "Registration Failed.", Toast.LENGTH_LONG).show()
                        }
                    }.addOnFailureListener(this@SignUpActivity) { exception ->
                        exception.printStackTrace()
                    }
                } else {
                    Toast.makeText(this@SignUpActivity, "The password doesn't matched", Toast.LENGTH_LONG).show()
                }
            } else {
                Toast.makeText(this@SignUpActivity, "The password length is less than 6", Toast.LENGTH_LONG).show()
            }
        } else {
            var toastMessage = "Please enter "
            if (userNameTextInputEditText.text!!.isEmpty()) {
                toastMessage += "username"
                if (passwordTextInputEditText.text!!.isEmpty()) {
                    toastMessage += "and password"
                }
            }
            if (passwordTextInputEditText.text!!.isEmpty()) {
                toastMessage += "password"
            }
            Toast.makeText(this@SignUpActivity, toastMessage, Toast.LENGTH_LONG).show()
        }
    }

    private fun initViews() {
        userNameTextInputEditText = user_name_input_edit_text
        passwordTextInputEditText = password_input_edit_text
        confirmPasswordTextInputEditText = confirm_password_input_edit_text
        nameTextInputEditText = name_input_edit_text
        loginButton = login_button
        signUpButton = sign_up_button

        auth = FirebaseAuth.getInstance()
        if (auth.currentUser != null) {

        }
    }

    private fun clickEvents() {
        loginButton.setOnClickListener(loginButtonClickListener)
        signUpButton.setOnClickListener(signUpButtonClickListener)
    }

}
