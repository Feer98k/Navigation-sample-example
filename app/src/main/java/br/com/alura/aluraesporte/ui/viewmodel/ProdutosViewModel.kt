package br.com.alura.aluraesporte.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import br.com.alura.aluraesporte.model.Produto
import br.com.alura.aluraesporte.repository.ProdutoRepository
class ProdutosViewModel(private val repository: ProdutoRepository) : ViewModel() {

    fun buscaTodos(): LiveData<List<Produto>> {
     return   repository.buscaTodos()
    }


}

