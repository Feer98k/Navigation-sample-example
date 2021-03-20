package br.com.alura.aluraesporte.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import br.com.alura.aluraesporte.R
import br.com.alura.aluraesporte.ui.viewmodel.ComponentesVisuais
import br.com.alura.aluraesporte.ui.viewmodel.EstadoViewModel
import br.com.alura.aluraesporte.ui.viewmodel.LoginViewmodel
import kotlinx.android.synthetic.main.login.*
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.koin.android.viewmodel.ext.android.viewModel


class LoginFragment : Fragment() {
    private val viewmodel: LoginViewmodel by viewModel()
    private  val estadoViewModel: EstadoViewModel by sharedViewModel()
    private val controlador by lazy {
        findNavController()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        estadoViewModel.temComponentes = ComponentesVisuais()


        if (viewmodel.verificar()) {
            listaProdutos()

        }
        login_botao_logar.setOnClickListener {
            viewmodel.logar()
            listaProdutos()
        }
        login_botao_cadastrar.setOnClickListener {
                Log.d("TAG", "onViewCreated: ")
                val direcaoCadastro =
                    LoginFragmentDirections.actionFragmentLoginIdToFramentCadastrar()
                controlador.navigate(direcaoCadastro)
            }
        }



    private fun listaProdutos() {
        val direcao = LoginFragmentDirections.actionLoginToListaProdutos()
        controlador.navigate(direcao)
    }
}