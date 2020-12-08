package com.frankitoo.presentation.features.vehicledetails

import android.view.View
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory
import com.frankitoo.domain.models.lego.vehicle.Vehicle
import com.frankitoo.presentation.R
import com.frankitoo.presentation.base.BaseFragment
import com.frankitoo.presentation.utils.fadeOut
import com.frankitoo.presentation.utils.getCircularProgressDrawable
import com.frankitoo.presentation.utils.startRotatedAnimation
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.fragment_vehicle_details.attackLayout
import kotlinx.android.synthetic.main.fragment_vehicle_details.defenseLayout
import kotlinx.android.synthetic.main.fragment_vehicle_details.imageView
import kotlinx.android.synthetic.main.fragment_vehicle_details.loaderImage
import kotlinx.android.synthetic.main.fragment_vehicle_details.loaderLayout
import kotlinx.android.synthetic.main.fragment_vehicle_details.specialCard
import kotlinx.android.synthetic.main.fragment_vehicle_details.speedLayout
import kotlinx.android.synthetic.main.fragment_vehicle_details.tvAttackValue
import kotlinx.android.synthetic.main.fragment_vehicle_details.tvCapacity
import kotlinx.android.synthetic.main.fragment_vehicle_details.tvCapacityValue
import kotlinx.android.synthetic.main.fragment_vehicle_details.tvDefenseValue
import kotlinx.android.synthetic.main.fragment_vehicle_details.tvName
import kotlinx.android.synthetic.main.fragment_vehicle_details.tvSpecial
import kotlinx.android.synthetic.main.fragment_vehicle_details.tvSpeedValue
import org.koin.android.viewmodel.ext.android.viewModel

class VehicleDetailsFragment : BaseFragment<VehicleDetailsViewModel>() {

    override val layoutRes: Int = R.layout.fragment_vehicle_details

    override val viewModel: VehicleDetailsViewModel by viewModel()

    override fun setupViews() {
        onLoading(true)
        viewModel.vehicle.observe(this) {
            onVehicle(it)
            onLoading(false)
        }
    }

    private fun onLoading(isLoading: Boolean) {
        loaderImage.startRotatedAnimation(requireContext())
        if (isLoading) {
            loaderLayout.visibility = View.VISIBLE
            loaderLayout.alpha = 1.0f
        } else {
            loaderLayout.fadeOut()
        }
    }

    private fun onVehicle(vehicle: Vehicle) {
        val storageRef = FirebaseStorage.getInstance().reference
        vehicle.imageUrl?.let {
            storageRef.child(it).downloadUrl.addOnSuccessListener { downloadUrl ->
                val factory = DrawableCrossFadeFactory.Builder().setCrossFadeEnabled(true).build()
                Glide.with(requireContext())
                    .load(downloadUrl)
                    .fitCenter()
                    .placeholder(getCircularProgressDrawable(requireContext()))
                    .transition(DrawableTransitionOptions.withCrossFade(factory))
                    .into(imageView)
            }
        }

        setupStats(vehicle)
        setupSpecial(vehicle)
        setupCapacity(vehicle)
    }

    private fun setupStats(vehicle: Vehicle) {
        tvName.text = vehicle.name
        attackLayout.isVisible = !vehicle.attack.isNullOrEmpty()
        if (vehicle.attack != null) {
            tvAttackValue.text = vehicle.attack.toString()
        }
        defenseLayout.isVisible = !vehicle.defense.isNullOrEmpty()
        if (vehicle.defense != null) {
            tvDefenseValue.text = vehicle.defense.toString()
        }
        speedLayout.isVisible = !vehicle.speed.isNullOrEmpty()
        if (vehicle.speed != null) {
            tvSpeedValue.text = vehicle.speed.toString()
        }
    }

    private fun setupSpecial(vehicle: Vehicle) {
        specialCard.isVisible = !vehicle.special.isNullOrEmpty()
        if (vehicle.special != null) {
            tvSpecial.text = vehicle.special
        }
    }

    private fun setupCapacity(vehicle: Vehicle) {
        tvCapacity.isVisible = !vehicle.capacity.isNullOrEmpty()
        tvCapacityValue.isVisible = !vehicle.capacity.isNullOrEmpty()
        if (vehicle.capacity != null) {
            tvCapacityValue.text = vehicle.capacity
        }
    }
}


