package com.pl.adambartosik.ligrettocalculator.viewmodel

import androidx.fragment.app.DialogFragment

interface onDialogAnswerResultListener {

    fun neutralResult(dialog: DialogFragment)
    fun positiveResult(dialog: DialogFragment)
    fun negativeResult(dialog: DialogFragment)
}