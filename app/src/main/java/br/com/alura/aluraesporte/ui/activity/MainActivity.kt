package br.com.alura.aluraesporte.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import br.com.alura.aluraesporte.R

private const val COMPRA_REALIZADA = "Compra realizada"

class MainActivity : AppCompatActivity() {

    private val controller by lazy{
        findNavController(R.id.produtos_activity_nav_host)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
    }

    override fun onResume() {
        super.onResume()
        setMudancasNAv()
    }

    private fun setMudancasNAv() {
        controller
            .addOnDestinationChangedListener { _, destination, _ ->
                title = destination.label.toString()
                when (destination.id) {
                    //atualiza suport para destinos apos chegar na lista
                    R.id.fragment_lista_id -> supportActionBar?.show()
                    //atualiza suport para destinos do login
                    R.id.fragment_login_id -> supportActionBar?.hide()

                }
            }
    }


}
