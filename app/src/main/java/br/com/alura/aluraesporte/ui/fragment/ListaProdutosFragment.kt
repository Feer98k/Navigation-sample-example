package br.com.alura.aluraesporte.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout.VERTICAL
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import br.com.alura.aluraesporte.R
import br.com.alura.aluraesporte.model.Produto
import br.com.alura.aluraesporte.ui.recyclerview.adapter.ProdutosAdapter
import br.com.alura.aluraesporte.ui.viewmodel.ComponentesVisuais
import br.com.alura.aluraesporte.ui.viewmodel.EstadoViewModel
import br.com.alura.aluraesporte.ui.viewmodel.ProdutosViewModel
import kotlinx.android.synthetic.main.lista_produtos.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class ListaProdutosFragment : BaseFragment() {

    private val viewModel: ProdutosViewModel by viewModel()
        //Pegando uma viewModel compartilhada entre diversos fragments/activities com o sharedViewModel
    private  val estadoViewmodel: EstadoViewModel by sharedViewModel()

    private val adapter: ProdutosAdapter by inject()
    private val controllerNavigation  by lazy {
        findNavController()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        verificaLogin()
        buscaProdutos()
        setHasOptionsMenu(true)
    }

    private fun buscaProdutos() {
        viewModel.buscaTodos().observe(this, Observer { produtosEncontrados ->
            produtosEncontrados?.let {
                adapter.atualiza(it)
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(
            R.layout.lista_produtos,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        estadoViewmodel.temComponentes = ComponentesVisuais(appBar = true, navigation = true)
        configuraRecyclerView()
    }

    private fun configuraRecyclerView() {
        val divisor = DividerItemDecoration(context, VERTICAL)
        lista_produtos_recyclerview.addItemDecoration(divisor)
        adapter.onItemClickListener = {
            //Produto de safe args
            vaiParaTelaDetalhesProduto(it)
        }
        lista_produtos_recyclerview.adapter = adapter
    }

    private fun vaiParaTelaDetalhesProduto(it: Produto) {
        val actionDirection =
            ListaProdutosFragmentDirections
                    //inserindo o safe args
                .actionListaProdutosToDetalhesProdutoFragment(it.id)
        controllerNavigation.navigate(actionDirection)
    }


}
