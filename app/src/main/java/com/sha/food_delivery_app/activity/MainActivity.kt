package com.sha.food_delivery_app.activity

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.tabs.TabLayout
import com.sha.food_delivery_app.FoodDeliveryApplication
import com.sha.food_delivery_app.R
import com.sha.food_delivery_app.adapter.PromoViewPagerAdapter
import com.sha.food_delivery_app.view.FoodItemsFragment
import com.sha.food_delivery_app.customview.ShaFloatingActionButton
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.Executors


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))
        findViewById<CollapsingToolbarLayout>(R.id.toolbar_layout).title = title
        val fab = findViewById<ShaFloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            //Todo: open Cart page
        }

        val application = application as FoodDeliveryApplication
        application.mainDataRepository.getStarupData().subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe { mainData ->
            if(mainData != null) {
                updateViewpager(mainData.promotionsList)
                val foodItemsFragment= FoodItemsFragment.newInstance(mainData.availableFoodItemsList[0], object : FoodItemsFragment.Listener {
                    override fun addedToCart() {
                        fab.count = fab.count + 1
                    }
                })
                val transaction = supportFragmentManager.beginTransaction()
                transaction.replace(R.id.fragment_container, foodItemsFragment)
                transaction.commit()
            }
        }
    }

    private fun updateViewpager(promotions: List<String>?) {
        promotions?.let { promotions ->
            val viewPager = findViewById<ViewPager>(R.id.promoViewPager)
            val adapter = PromoViewPagerAdapter(
                this,
                promotions
            );
            val tabLayout = findViewById<TabLayout>(R.id.tabLayout)

            viewPager.adapter = adapter
            viewPager.autoScroll(3000)
            tabLayout.setupWithViewPager(viewPager, true)
        }
    }

    private fun ViewPager.autoScroll(interval: Long) {
        var scrollPosition = 0
        val handler = Handler(Looper.getMainLooper())
        val executor = Executors.newSingleThreadExecutor()

        val runnable = object : Runnable {
            override fun run() {
                val count = adapter?.count ?: 0
                setCurrentItem(scrollPosition++ % count, true)

                handler.postDelayed(this, interval)
            }
        }

        executor.execute(runnable)

        addOnPageChangeListener(object: ViewPager.OnPageChangeListener {
            override fun onPageSelected(position: Int) {
                scrollPosition = position + 1
            }

            override fun onPageScrollStateChanged(state: Int) {}
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {}
        })
    }
}
