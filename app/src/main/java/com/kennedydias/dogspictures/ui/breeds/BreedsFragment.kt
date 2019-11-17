package com.kennedydias.dogspictures.ui.breeds

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.kennedydias.dogspictures.R
import com.kennedydias.dogspictures.databinding.FragmentBreedsBinding
import com.kennedydias.dogspictures.extensions.observe
import com.kennedydias.dogspictures.ui.base.BaseFragment
import com.kennedydias.domain.model.BreedData
import org.koin.androidx.viewmodel.ext.android.viewModel

class BreedsFragment : BaseFragment() {

    private lateinit var binding: FragmentBreedsBinding
    private lateinit var breedsAdapter: BreedsAdapter
    private val viewModel by viewModel<BreedsViewModel>()

    override fun onBackPressed(): Boolean {
        finish()
        return super.onBackPressed()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_breeds, container, false)
        binding.lifecycleOwner = this

        breedsAdapter = BreedsAdapter()

        configureComponents()
        configureObservables()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.init()
    }

    private fun configureComponents() {
        configureList()
    }

    private fun configureList() {
        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.getBreeds(true)
        }
        binding.recyclerView.apply {
            val customLayoutManager = LinearLayoutManager(context)
            val dividerItemDecoration =
                DividerItemDecoration(context, customLayoutManager.orientation)
            addItemDecoration(dividerItemDecoration)

            layoutManager = LinearLayoutManager(context)
            adapter = breedsAdapter
        }
    }

    private fun configureObservables() {
        observe(viewModel.notConnectedOb, ::handleNotConnected)
        observe(viewModel.gettingDataOb, ::handleGettingData)
        observe(viewModel.fatalErrorOb, ::handleFatalError)
        observe(viewModel.breedsOb, ::handleBreeds)
        observe(viewModel.errorOb, ::handleError)
    }

    private fun handleNotConnected(notConnected: Boolean) {
        if (notConnected) {
            showNoConnectionDialog {
                viewModel.init()
            }
        } else {
            hideNotConnectedDialog()
        }
    }

    private fun handleFatalError(message: String) {
        showToast(message)
        finish()
    }

    private fun handleError(message: String) {
        showSnackbar(message)
    }

    private fun handleGettingData(loading: Boolean) {
        binding.swipeRefreshLayout.isRefreshing = loading
    }

    private fun handleBreeds(list: List<BreedData>) {
        breedsAdapter.update(list)
    }

    companion object {
        fun newInstance() = BreedsFragment()
    }

}