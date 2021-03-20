package br.com.alura.aluraesporte.ui.fragment

import android.os.Bundle
import android.view.*
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import br.com.alura.aluraesporte.R
import br.com.alura.aluraesporte.extensions.formatParaMoedaBrasileira
import br.com.alura.aluraesporte.ui.viewmodel.ComponentesVisuais
import br.com.alura.aluraesporte.ui.viewmodel.DetalhesProdutoViewModel
import br.com.alura.aluraesporte.ui.viewmodel.EstadoViewModel
import kotlinx.android.synthetic.main.detalhes_produto.*
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class DetalhesProdutoFragment : BaseFragment() {

    private val arguments by navArgs<DetalhesProdutoFragmentArgs>()
    private val produtoId by lazy {
        arguments.produtoID
    }
    private val viewModel: DetalhesProdutoViewModel by viewModel { parametersOf(produtoId) }
    private val estadoViewModel: EstadoViewModel by sharedViewModel()
    private val controllerNavigation by lazy {
        findNavController()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.detalhes_produto,
            container,
            false
        )

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        estadoViewModel.temComponentes = ComponentesVisuais(appBar = true,navigation = false)
        buscaProduto()
        configuraBotaoComprar()


    }

    private fun configuraBotaoComprar() {
        detalhes_produto_botao_comprar.setOnClickListener {
            viewModel.produtoEncontrado.value?.let {produto ->
                val detalhesProdutoFragmentDirections =
                    DetalhesProdutoFragmentDirections
                    .actionDetalhesProdutoFragmentToPagamentoFragment(produto.id)
                controllerNavigation.navigate(detalhesProdutoFragmentDirections)
            }

        }
    }

    private fun buscaProduto() {
        viewModel.produtoEncontrado.observe(viewLifecycleOwner, Observer {
            it?.let { produto ->
                detalhes_produto_nome.text = produto.nome
                detalhes_produto_preco.text = produto.preco.formatParaMoedaBrasileira()
            }
        })
    }

}