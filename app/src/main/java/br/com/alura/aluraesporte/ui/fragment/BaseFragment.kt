package br.com.alura.aluraesporte.ui.fragment

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import br.com.alura.aluraesporte.R
import br.com.alura.aluraesporte.ui.viewmodel.LoginViewmodel
import org.koin.android.viewmodel.ext.android.viewModel

abstract class BaseFragment : Fragment() {
    private val viewModelLogin: LoginViewmodel by viewModel()
    private val controllerNavigation  by lazy {
        findNavController()
    }
     fun verificaLogin() {
        if (!viewModelLogin.verificar()) {
            vaiParatelaLogin()
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item?.itemId == R.id.menu_sair){
            vaiParatelaLogin()
        }
        return super.onOptionsItemSelected(item)
    }

    fun vaiParatelaLogin() {
        val direcaoLogin =
            ListaProdutosFragmentDirections.actionGlobalLogin()
        viewModelLogin.deslogar()
        controllerNavigation.navigate(direcaoLogin)
    }
}