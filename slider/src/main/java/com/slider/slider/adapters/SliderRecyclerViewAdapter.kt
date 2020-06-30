package com.slider.slider.adapters

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.slider.slider.event.OnSlideClickListener
import com.slider.slider.viewholder.ImageSlideViewHolder


/**
 * @author S.Shahini
 * @since 12/16/17
 */
class SliderRecyclerViewAdapter(
    private val sliderAdapter: SliderAdapter,
    private var loop: Boolean,
    private val imageViewLayoutParams: ViewGroup.LayoutParams,
    private val itemOnTouchListener: View.OnTouchListener,
    private val positionController: PositionController
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var onSlideClickListener: OnSlideClickListener? = null

    fun setOnSlideClickListener(onSlideClickListener: OnSlideClickListener) {
        this.onSlideClickListener = onSlideClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val lp = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        lp.setMargins(0, 0, 0, 0)


        val imageView = ImageView(parent.context)
        imageView.layoutParams = lp
        imageView.adjustViewBounds = true
        //imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        return ImageSlideViewHolder(imageView)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (!loop) {
            sliderAdapter.onBindImageSlide(position, holder as ImageSlideViewHolder)
        } else {
            when (position) {
                0 -> {
                    sliderAdapter.onBindImageSlide(
                        positionController.lastUserSlidePosition,
                        holder as ImageSlideViewHolder
                    )
                }
                itemCount - 1 -> {
                    sliderAdapter.onBindImageSlide(
                        positionController.firstUserSlidePosition,
                        holder as ImageSlideViewHolder
                    )
                }
                else -> {
                    sliderAdapter.onBindImageSlide(position - 1, holder as ImageSlideViewHolder)
                }
            }
        }




        holder.itemView.setOnClickListener {
            if (onSlideClickListener != null) {

                val list = sliderAdapter.list
                val pos = holder.getAdapterPosition()
                val pos2 = positionController.getUserSlidePosition(pos)
                onSlideClickListener!!.onSlideClick(
                    positionController.getUserSlidePosition(holder.getAdapterPosition()),
                    list[pos2]
                )
            }
        }

        holder.itemView.setOnTouchListener(itemOnTouchListener)
    }

    override fun getItemCount(): Int {
        return sliderAdapter.itemCount + if (loop) 2 else 0
    }

    fun setLoop(loop: Boolean) {
        this.loop = loop
    }


}
