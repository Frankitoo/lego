package com.frankitoo.presentation.features.characterlist

import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.frankitoo.presentation.R
import com.frankitoo.presentation.base.BaseFragment
import com.frankitoo.presentation.utils.GridSpacingItemDecoration
import kotlinx.android.synthetic.main.fragment_character_list.rvCharacters
import kotlinx.coroutines.flow.collectLatest
import org.koin.android.viewmodel.ext.android.viewModel

class CharacterListFragment : BaseFragment<CharacterListViewModel>() {

    companion object {
        const val SPAN_COUNT = 3
    }

    override val layoutRes: Int = R.layout.fragment_character_list

    override val viewModel: CharacterListViewModel by viewModel()

    private val characterListAdapter = CharacterListAdapter()

    private val gridDecoration: RecyclerView.ItemDecoration by lazy {
        GridSpacingItemDecoration(
            SPAN_COUNT, resources.getDimension(R.dimen.margin_grid).toInt(), false
        )
    }

    override fun setupViews() {
        initAdapter()
    }

    private fun initAdapter() {
        characterListAdapter.onClickListener =
            { item ->
                lifecycleScope.launchWhenCreated {
                    item.id?.let {
                        findNavController().navigate(CharacterListFragmentDirections.toCharacterDetails(it))
                    }
                }
            }

        rvCharacters.addItemDecoration(gridDecoration)
        rvCharacters.layoutManager = GridLayoutManager(activity, SPAN_COUNT)
        rvCharacters.adapter = characterListAdapter.withLoadStateHeaderAndFooter(
            header = CharacterLoadingAdapter { characterListAdapter.retry() },
            footer = CharacterLoadingAdapter { characterListAdapter.retry() }
        )

        lifecycleScope.launchWhenStarted {
            viewModel.fetchCharacters().collectLatest { pagingData ->
                characterListAdapter.submitData(pagingData)
            }
        }
    }
}
