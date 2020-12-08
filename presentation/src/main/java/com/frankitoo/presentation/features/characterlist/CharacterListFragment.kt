package com.frankitoo.presentation.features.characterlist

import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.frankitoo.domain.models.lego.character.Character
import com.frankitoo.presentation.R
import com.frankitoo.presentation.base.BaseFragment
import com.frankitoo.presentation.utils.LoadingAdapter
import com.frankitoo.presentation.utils.PagingGridListAdapter
import com.frankitoo.presentation.utils.getGridDecoration
import kotlinx.android.synthetic.main.fragment_list.recyclerView
import kotlinx.coroutines.flow.collectLatest
import org.koin.android.viewmodel.ext.android.viewModel

class CharacterListFragment : BaseFragment<CharacterListViewModel>() {

    companion object {
        const val SPAN_COUNT = 3
    }

    override val layoutRes: Int = R.layout.fragment_list

    override val viewModel: CharacterListViewModel by viewModel()

    private val characterListAdapter = PagingGridListAdapter<Character>(SPAN_COUNT)

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

        recyclerView.addItemDecoration(getGridDecoration(SPAN_COUNT, requireContext()))
        recyclerView.layoutManager = GridLayoutManager(activity, SPAN_COUNT)
        recyclerView.adapter = characterListAdapter.withLoadStateHeaderAndFooter(
            header = LoadingAdapter { characterListAdapter.retry() },
            footer = LoadingAdapter { characterListAdapter.retry() }
        )

        lifecycleScope.launchWhenStarted {
            viewModel.fetchCharacters().collectLatest { pagingData ->
                characterListAdapter.submitData(pagingData)
            }
        }
    }
}
