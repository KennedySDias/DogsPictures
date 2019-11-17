package com.kennedydias.dogspictures.ui.base

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.kennedydias.dogspictures.R

open class BaseFragment : Fragment(), OnBackPressed {

    private var noConnectionDialogCallback: (() -> Unit)? = null
    private val noConnectionDialog: AlertDialog? by lazy {
        if (context != null) {
            val builder = AlertDialog.Builder(context!!)
            builder.setTitle(getString(R.string.no_connection))
            builder.setMessage(getString(R.string.there_is_no_internet_connection_check_it_and_try_again))
            builder.setPositiveButton(android.R.string.ok) { dialog, _ ->
                dialog.dismiss()
                noConnectionDialogCallback?.invoke()
            }
            builder.create()
        } else null
    }

    private val snackBarLayout: View by lazy {
        (activity?.findViewById<View>(android.R.id.content) as ViewGroup).getChildAt(0)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onBackPressed(): Boolean {
        return false
    }

    protected fun finish() {
        activity?.finish()
    }

    protected fun setSupportActionBar(toolbar: Toolbar) {
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
    }

    protected fun setTitle(titleId: Int) {
        (activity as? AppCompatActivity)?.supportActionBar?.setTitle(titleId)
    }

    protected fun setTitle(title: String) {
        (activity as? AppCompatActivity)?.supportActionBar?.title = title
    }

    protected fun showBackButton() {
        (activity as? AppCompatActivity)?.supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    fun showNoConnectionDialog(callback: (() -> Unit)? = null) {
        noConnectionDialogCallback = callback
        if (noConnectionDialog?.isShowing == false) {
            noConnectionDialog?.show()
        }
    }

    fun hideNotConnectedDialog() {
        if (noConnectionDialog?.isShowing == true) {
            noConnectionDialog?.dismiss()
        }
    }

    fun showSnackbar(message: String) {
        Snackbar.make(snackBarLayout, message, Snackbar.LENGTH_SHORT).apply {
            setAction("Ok") {
                dismiss()
            }
            show()
        }
    }

    fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

}