package com.gsoft.argentina.probandolivedataroommvvm.ui.fragments

import android.app.AlertDialog
import android.content.DialogInterface
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.view.get
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.liveData
import androidx.recyclerview.widget.RecyclerView
import com.gsoft.argentina.probandolivedataroommvvm.R
import com.gsoft.argentina.probandolivedataroommvvm.core.Resource
import com.gsoft.argentina.probandolivedataroommvvm.data.model.Entidad
import com.gsoft.argentina.probandolivedataroommvvm.databinding.FragmentMainBinding
import com.gsoft.argentina.probandolivedataroommvvm.datasource.dataSource
import com.gsoft.argentina.probandolivedataroommvvm.db.AppDatabase
import com.gsoft.argentina.probandolivedataroommvvm.presentation.EntidadViewModel
import com.gsoft.argentina.probandolivedataroommvvm.presentation.EntidadViewModelFactory
import com.gsoft.argentina.probandolivedataroommvvm.repository.EntidadRepoImpl
import com.gsoft.argentina.probandolivedataroommvvm.ui.adaptadores.Adaptador
import com.gsoft.argentina.probandolivedataroommvvm.utils.SwipeHelper
import java.lang.Exception

class MainFragment : Fragment(R.layout.fragment_main) {

    private lateinit var binding: FragmentMainBinding

    private val viewModel by viewModels<EntidadViewModel> {
        EntidadViewModelFactory(
                EntidadRepoImpl(
                        dataSource(AppDatabase.getDatabase(requireContext()).entidadDao())
                )
        )
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainBinding.bind(view)

        binding.floatingActionButton.setOnClickListener() {
            //Inflate the dialog with custom view
            val mDialogView = LayoutInflater.from(this.requireContext()).inflate(R.layout.dialog_loco, null)
            //AlertDialogBuilder
            val mBuilder = AlertDialog.Builder(this.requireContext())
                    .setView(mDialogView)
            //show dialog
            val yes_button = mDialogView.findViewById<ImageButton>(R.id.b_yes)
            val no_button = mDialogView.findViewById<ImageButton>(R.id.b_no)
            val textInput = mDialogView.findViewById<EditText>(R.id.t_text)
            val mAlertDialog = mBuilder.show()
            //accept button click of custom layout
            yes_button.setOnClickListener {
                //dismiss dialog
                mAlertDialog.dismiss()
                //get text from EditTexts of custom layout
                val name = textInput.text.toString()
                val entidad = Entidad(id = 0, nombre = name)
                viewModel.saveEntidad(entidad)
                mAlertDialog.dismiss()
            }

            no_button.setOnClickListener {
                mAlertDialog.dismiss()
            }
        }


        binding.bDelete.setOnClickListener(){
            viewModel.wipeDB()
        }

        viewModel.allStudents.observe(viewLifecycleOwner, Observer{ students->
            binding.rvLista.adapter = Adaptador(students)
        })




        ///////////////////////////// coso de botones ///////////////////////
      object : SwipeHelper(this.requireContext(), binding.rvLista, false) {

            override fun instantiateUnderlayButton(
                    viewHolder: RecyclerView.ViewHolder?,
                    underlayButtons: MutableList<UnderlayButton>?
            ) {
                // Eliminar Button
                underlayButtons?.add(SwipeHelper.UnderlayButton(
                        "Eliminar",
                        AppCompatResources.getDrawable(
                                requireContext(),
                                R.drawable.ic_no
                        ),
                        Color.parseColor("#FF0000"), Color.parseColor("#ffffff"))
                { pos: Int ->
                    val item = viewModel.allStudents.value?.get(pos)
                    val entidad = item?.let { Entidad(it.id, item.nombre) }
                    if (entidad != null) {
                        try {
                            //Toast.makeText(context, "Queres borrar:   $entidad", Toast.LENGTH_LONG).show()
                            viewModel.deleteEntidad(entidad)
                            binding.rvLista.adapter?.notifyItemRemoved(pos)
                        }catch (e: Exception){
                            println("Iba a crashear chinwewencha")
                        }
                    }
                })

                    //Editar Button
                underlayButtons?.add(SwipeHelper.UnderlayButton(
                        "Editar",
                        AppCompatResources.getDrawable(
                                requireContext(),
                                R.drawable.ic_yes
                        ),
                        Color.parseColor("#127512"), Color.parseColor("#ffffff"),
                        UnderlayButtonClickListener { pos: Int ->

                            var editText = EditText(requireContext())
                            val entidad = viewModel.allStudents.value?.get(pos) as Entidad
                            Toast.makeText(context, "Editar:   $entidad ", Toast.LENGTH_LONG).show()

                            editText.setText(entidad.nombre.toString())

                            val builder = AlertDialog.Builder(requireContext())
                            builder.setTitle("Actualizar")
                            builder.setCancelable(true)
                            builder.setView(editText)

                            builder.setNegativeButton("Cancelar", DialogInterface.OnClickListener{ dialog, which ->

                            })

                            builder.setPositiveButton("Aceptar", DialogInterface.OnClickListener{dialog, which ->
                                val nombre = editText.text.toString()
                                viewModel.updateEntidad(Entidad(entidad.id, nombre))
                            })

                            builder.show()
                           // recyclerAdaptador.notifyItemChanged(pos)
                        }
                ))

            }
        }//BOTONES

    }

}