package com.sha.food_delivery_app.adapter

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.sha.food_delivery_app.R
import com.squareup.picasso.Picasso
import java.util.*


/**
 * Created by Shahul Hameed Shaik on 11/12/2020.
 */
class PromoViewPagerAdapter(val context: Context, private val promosList: List<String>) : PagerAdapter() {

    // Layout Inflater
    private var layoutInflater: LayoutInflater = LayoutInflater.from(context)

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object` as LinearLayout
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val itemView: View? = layoutInflater.inflate(R.layout.promo, container, false)

        val imageView =
            itemView?.findViewById<View>(R.id.imageViewMain) as ImageView

        Picasso.get().load(promosList[position]).into(imageView)

        Objects.requireNonNull(container).addView(itemView)

        return itemView
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        (container as ViewPager).removeView(`object` as View)
    }

    override fun getCount(): Int {
        return promosList.size
    }
}