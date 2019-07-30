package com.pl.adambartosik.ligrettocalculator.view.dialogFragment.bottom

import android.app.Dialog
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.FrameLayout
import androidx.fragment.app.DialogFragment
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.pl.adambartosik.ligrettocalculator.R
import com.pl.adambartosik.ligrettocalculator.model.tables.Player
import kotlinx.android.synthetic.main.dialog_fragment_bottom_create_user_name.*
import org.greenrobot.eventbus.EventBus

class CreateUserDialogFragmentBottom : BottomSheetDialogFragment() {

    private lateinit var dataList: List<Player>
    private var isErrorState = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view =  inflater.inflate(R.layout.dialog_fragment_bottom_create_user_name, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val animation = AnimationUtils.loadAnimation(context, R.anim.click)
        animation.setAnimationListener(object : Animation.AnimationListener{
            override fun onAnimationEnd(animation: Animation?) {
                if(!isErrorState){
                    EventBus.getDefault().post(
                        EventCreateUser(
                            name_input_tiet_dfbcun.text.toString()
                        )
                    )
                }
            }
            override fun onAnimationStart(animation: Animation?) { }
            override fun onAnimationRepeat(animation: Animation?) { }
        })

        button_create_new_player_btn_dfbcun.setOnClickListener {
            button_create_new_player_btn_dfbcun.startAnimation(animation)
        }

        name_input_tiet_dfbcun.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                dataList.forEach {
                    if(it.name == s.toString()){
                        showError()
                    }else{
                        cleanError()
                    }
                }
            }

        })
    }

    private fun showError(){
        isErrorState = true
        name_input_tiet_dfbcun.error = resources.getString(R.string.error_display_player_name_taken)
    }

    private fun cleanError(){
        if(isErrorState){
            name_input_tiet_dfbcun.error = null
            isErrorState = false
        }
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

    fun setList(dataList: List<Player>) {
        this.dataList = dataList
    }

    class EventCreateUser(val name: String)

}