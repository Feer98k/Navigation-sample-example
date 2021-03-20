package br.com.alura.aluraesporte.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import br.com.alura.aluraesporte.R
import br.com.alura.aluraesporte.ui.viewmodel.ComponentesVisuais
import br.com.alura.aluraesporte.ui.viewmodel.EstadoViewModel
import kotlinx.android.synthetic.main.cadastro_usuario.*
import org.koin.android.viewmodel.ext.android.sharedViewModel

class CadastroUsuario  : Fragment(){
    private val controller by lazy{
        findNavController()
    }
    private val estadoViewModel:EstadoViewModel by sharedViewModel()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.cadastro_usuario,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        estadoViewModel.temComponentes = ComponentesVisuais()
        cadastro_usuario_botao_cadastrar?.setOnClickListener {
            controller.popBackStack()
        }
    }
}