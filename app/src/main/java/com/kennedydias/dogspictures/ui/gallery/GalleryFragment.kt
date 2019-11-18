package com.kennedydias.dogspictures.ui.gallery

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.recyclerview.widget.GridLayoutManager
import com.kennedydias.dogspictures.R
import com.kennedydias.dogspictures.databinding.FragmentGalleryBinding
import com.kennedydias.dogspictures.extensions.hideWithAlphaAnimation
import com.kennedydias.dogspictures.extensions.observe
import com.kennedydias.dogspictures.extensions.showWithAlphaAnimation
import com.kennedydias.dogspictures.ui.base.BaseFragment
import com.kennedydias.dogspictures.ui.fullscreen.FullScreenPictureActivity
import com.kennedydias.domain.model.BreedData
import org.koin.androidx.viewmodel.ext.android.viewModel

class GalleryFragment : BaseFragment() {

    private lateinit var binding: FragmentGalleryBinding
    private lateinit var galleryAdapter: GalleryAdapter
    private val viewModel by viewModel<GalleryViewModel>()

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
            DataBindingUtil.inflate(inflater, R.layout.fragment_gallery, container, false)
        binding.lifecycleOwner = this

        galleryAdapter = GalleryAdapter(viewModel)

        val view = binding.root

        configureComponents()
        configureObservables()

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.init(arguments)
    }

    private fun configureComponents() {
        configureToolbar()
        configureRecyclerView()
    }

    private fun configureObservables() {
        observe(viewModel.picturesOb, ::handlePictures)
        observe(viewModel.notConnectedOb, ::handleNotConnected)
        observe(viewModel.gettingDataOb, ::handleGettingData)
        observe(viewModel.fatalErrorOb, ::handleFatalError)
        observe(viewModel.seeMoreOb, ::handleSeeMore)
        observe(viewModel.errorOb, ::handleError)
    }

    private fun handlePictures(liveData: LiveData<PagedList<String>>) {
        liveData.observe(this, Observer {
            galleryAdapter.submitList(it)
        })
    }

    private fun handleNotConnected(notConnected: Boolean) {
        if (notConnected) {
            showNoConnectionDialog {
                viewModel.init(arguments)
            }
        } else {
            hideNotConnectedDialog()
        }
    }

    private fun handleGettingData(loading: Boolean) {
        if (loading) {
            binding.recyclerView.hideWithAlphaAnimation()
            binding.progressBar.showWithAlphaAnimation()
        } else {
            binding.progressBar.hideWithAlphaAnimation()
            binding.recyclerView.showWithAlphaAnimation()
        }
    }

    private fun handleFatalError(message: String) {
        showToast(message)
        finish()
    }

    private fun handleSeeMore(url: String) {
        val newIntent = Intent(context, FullScreenPictureActivity::class.java)
        newIntent.putExtra(FullScreenPictureActivity.PARAMETER_URL_IMAGE, url)
        startActivity(newIntent)
    }

    private fun handleError(message: String) {
        showSnackbar(message)
    }

    private fun configureToolbar() {
        binding.toolbar.title =
            arguments?.getParcelable<BreedData>(PARAMETER_BREED)?.name ?: getString(
                R.string.gallery
            )
    }

    private fun configureRecyclerView() {
        binding.recyclerView.apply {
            layoutManager = GridLayoutManager(context, 3, GridLayoutManager.VERTICAL, false)
            adapter = galleryAdapter
        }
    }

    companion object {
        const val PARAMETER_BREED = "PARAMETER_BREED"

        fun newInstance(breed: BreedData?): GalleryFragment {
            val args = Bundle()
            args.putParcelable(PARAMETER_BREED, breed)

            val fragment = GalleryFragment()
            fragment.arguments = args

            return fragment
        }

    }

}