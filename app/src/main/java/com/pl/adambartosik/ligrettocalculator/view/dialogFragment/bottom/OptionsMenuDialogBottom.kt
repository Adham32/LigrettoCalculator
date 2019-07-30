package com.pl.adambartosik.ligrettocalculator.view.dialogFragment.bottom

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.pl.adambartosik.ligrettocalculator.R
import kotlinx.android.synthetic.main.dialog_fragment_bottom_options_menu.*
import kotlinx.android.synthetic.main.dialog_fragment_bottom_options_menu_item.view.*
import org.greenrobot.eventbus.EventBus




class OptionsMenuDialogBottom: BottomSheetDialogFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view =  inflater.inflate(R.layout.dialog_fragment_bottom_options_menu, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        options_rv_dfbom.layoutManager = LinearLayoutManager(this@OptionsMenuDialogBottom.context, RecyclerView.VERTICAL, false)
        options_rv_dfbom.adapter =
            AdapterBottomMenu()
    }

    override fun getTheme(): Int = R.style.BottomSheetDialogTheme
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog = BottomSheetDialog(requireContext(), theme)

    class AdapterBottomMenu: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

        private var dataArray: ArrayList<Option>

        enum class Option(var resID: Int) {
            EDIT(R.string.option_edit), DELETE(R.string.option_delete)
        }

        init {
            dataArray = ArrayList()
            dataArray.add(Option.EDIT)
            dataArray.add(Option.DELETE)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            var view =
                OptionItemViewHolder(
                    LayoutInflater.from(parent.context).inflate(
                        R.layout.dialog_fragment_bottom_options_menu_item,
                        parent,
                        false
                    )
                )
            return view
        }

        override fun getItemCount(): Int {
            return dataArray.size
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            if(holder is OptionItemViewHolder){
                holder.bindView(dataArray[position])
            }
        }

        class OptionItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

            fun bindView(optionEntity: Option) {
                itemView.option_name.text = itemView.context.resources.getString(optionEntity.resID)

                itemView.setOnClickListener {
                    val animation = AnimationUtils.loadAnimation(itemView.context, R.anim.click)
                    animation.setAnimationListener(object : Animation.AnimationListener {
                        override fun onAnimationStart(animation: Animation) {}

                        override fun onAnimationEnd(animation: Animation) {
                            EventBus.getDefault().post(
                                EventOptionSelected(
                                    optionEntity
                                )
                            )
                        }

                        override fun onAnimationRepeat(animation: Animation) {}
                    })
                    itemView.startAnimation(animation)
                }
            }
        }

        class EventOptionSelected(var option: Option)
    }



}