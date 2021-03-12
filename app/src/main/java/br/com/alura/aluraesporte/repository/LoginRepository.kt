package br.com.alura.aluraesporte.repository

import android.content.SharedPreferences
import androidx.core.content.edit

private const val KEY_LOGADO = "logado"

class LoginRepository(private val preferences: SharedPreferences) {


    fun logar() {
        preferences.edit {
            putBoolean(KEY_LOGADO, true)
        }
    }

    fun verificaLogin(): Boolean = preferences.getBoolean(KEY_LOGADO, false)
    fun deslogar() {
        preferences.edit {
            putBoolean(KEY_LOGADO, false)
        }
    }
}
