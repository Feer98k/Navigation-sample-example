package br.com.alura.aluraesporte.ui.activity

import android.os.Bundle
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import br.com.alura.aluraesporte.R
import br.com.alura.aluraesporte.ui.viewmodel.EstadoViewModel
import kotlinx.android.synthetic.main.main_activity.*
import org.koin.android.viewmodel.ext.android.viewModel


private const val COMPRA_REALIZADA = "Compra realizada"

class MainActivity : AppCompatActivity() {
    private val viewModel: EstadoViewModel by viewModel()

    private val controller by lazy {
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
                viewModel.componentes.observe(this, Observer {
                    it?.let { componente ->
                        if (componente.appBar) {
                            supportActionBar?.show();
                        } else {
                            supportActionBar?.hide()
                        }
                        if (componente.navigation) {
                            main_activity_bottom_navigation.visibility = VISIBLE
                        } else {
                            main_activity_bottom_navigation.visibility = INVISIBLE

                        }

                    }
                })
            }
        main_activity_bottom_navigation.setupWithNavController(controller)
    }


}
