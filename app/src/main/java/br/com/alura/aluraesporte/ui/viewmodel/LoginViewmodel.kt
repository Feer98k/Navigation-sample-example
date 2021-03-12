package br.com.alura.aluraesporte.ui.viewmodel

import androidx.lifecycle.ViewModel
import br.com.alura.aluraesporte.repository.LoginRepository

class LoginViewmodel(private val repositorio : LoginRepository) : ViewModel() {

    fun logar(){
        repositorio.logar()
    }
    fun verificar() : Boolean {
        return repositorio.verificaLogin()
    }

    fun deslogar() {
        repositorio.deslogar()
    }

}
