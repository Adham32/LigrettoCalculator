package com.pl.adambartosik.ligrettocalculator.view.dialogFragment

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.pl.adambartosik.ligrettocalculator.R
import kotlinx.android.synthetic.main.dialog_fragment_bottom_create_user_name.*
import org.greenrobot.eventbus.EventBus

class CreateUserDialogFragmentBottom : BottomSheetDialogFragment() {

    override fun getTheme(): Int = R.style.BottomSheetDialogTheme
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog = BottomSheetDialog(requireContext(), theme)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view =  inflater.inflate(R.layout.dialog_fragment_bottom_create_user_name, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val animation = AnimationUtils.loadAnimation(context, R.anim.click)
        animation.setAnimationListener(object : Animation.AnimationListener{
            override fun onAnimationEnd(animation: Animation?) {
                EventBus.getDefault().post(
                    EventCreateUser(
                        name_input_tiet_dfbcun.text.toString()
                    )
                )
            }
            override fun onAnimationStart(animation: Animation?) { }
            override fun onAnimationRepeat(animation: Animation?) { }
        })

        create_fl_dfbcun.setOnClickListener {
            create_fl_dfbcun.startAnimation(animation)
        }
    }

    class EventCreateUser(val name: String)

}