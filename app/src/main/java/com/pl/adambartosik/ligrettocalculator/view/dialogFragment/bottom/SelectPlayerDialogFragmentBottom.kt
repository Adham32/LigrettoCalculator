package com.pl.adambartosik.ligrettocalculator.view.dialogFragment.bottom

import android.app.Dialog
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.FrameLayout
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.pl.adambartosik.ligrettocalculator.R
import com.pl.adambartosik.ligrettocalculator.viewmodel.PlayerViewModel
import com.pl.adambartosik.ligrettocalculator.viewmodel.adapter.AdapterOfPlayersToSelect
import kotlinx.android.synthetic.main.dialog_fragment_bottom_select_player.*

class SelectPlayerDialogFragmentBottom : BottomSheetDialogFragment() {

    private lateinit var adapter: AdapterOfPlayersToSelect
    private lateinit var mPlayerViewModel: PlayerViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view =  inflater.inflate(R.layout.dialog_fragment_bottom_select_player, container, false)
        mPlayerViewModel = ViewModelProviders.of(this.activity!!).get(PlayerViewModel::class.java)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = AdapterOfPlayersToSelect()
        select_player_rv_dfbsp.layoutManager = LinearLayoutManager(this@SelectPlayerDialogFragmentBottom.context, RecyclerView.VERTICAL, false)
        select_player_rv_dfbsp.adapter = adapter
        mPlayerViewModel.getAllPlayers().observe(this, Observer {
            adapter.setData(it)
        })
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NORMAL, R.style.BottomSheetDialogTheme)
    }


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        dialog.setOnShowListener {
            Handler().post {
                val bottomSheet = (dialog as? BottomSheetDialog)?.findViewById<View>(R.id.design_bottom_sheet) as? FrameLayout
                bottomSheet?.let {
                    BottomSheetBehavior.from(it).state = BottomSheetBehavior.STATE_EXPANDED
                }
            }
        }
        return dialog
    }
}