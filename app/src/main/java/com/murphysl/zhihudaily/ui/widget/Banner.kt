package com.murphysl.zhihudaily.ui.widget

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Color
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import com.murphysl.zhihudaily.R
import com.orhanobut.logger.Logger
import com.squareup.picasso.Picasso
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import jp.wasabeef.picasso.transformations.ColorFilterTransformation
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * Banner
 *
 * 尺寸转化
 * 宽高
 * 代码引用修改
 * 颜色转换问题
 * 单个图片无需指示器
 * 属性 ： 颜色过滤器
 * 一个图片
 *
 * @author: MurphySL
 * @time: 2017/2/14 15:51
 */

class Banner : RelativeLayout {

    private val ORIGINAL_TEXT_SIZE = 22F
    private val ORIGINAL_TEXT_COLOR = Color.WHITE
    private val ORIGINAL_INDIVATOR_MAGIN = 5F
    private val ORIGINAL_TEXT_MAGIN_LEFT = 40F
    private val ORIGINAL_TEXT_MAGIN_RIGHT = 40F
    private val ORIGINAL_AUTO_PLAY = true
    private val ORIGINAL_DELAY_TIME = 8
    private val ORIGINAL_SCROLL_TIME = 6
    private val ORIGINAL_DRAWABLE_UNSELECTED = R.drawable.unselected_radius
    private val ORIGINAL_DRAWABLE_SELECTED = R.drawable.selected_radius

    var textSize : Float = ORIGINAL_TEXT_SIZE
    var textColor : Int = ORIGINAL_TEXT_COLOR
    var indicatorDrawableUnselected: Int = ORIGINAL_DRAWABLE_UNSELECTED
    var indicatorDrawableSelected: Int = ORIGINAL_DRAWABLE_SELECTED
    var autoPlay: Boolean = ORIGINAL_AUTO_PLAY
    var delayTime : Int = ORIGINAL_DELAY_TIME
    var scrollTime : Int = ORIGINAL_SCROLL_TIME
    var indicatorMagin : Float = ORIGINAL_INDIVATOR_MAGIN
    var textMaginLeft : Float = ORIGINAL_TEXT_MAGIN_LEFT
    var textMaginRight : Float = ORIGINAL_TEXT_MAGIN_RIGHT

    private var viewPager : ViewPager
    private var textView : TextView
    private var indicator : LinearLayout

    private var titles : MutableList<String> = ArrayList()
    private var imgs : MutableList<String> = ArrayList()
    private var pics : MutableList<ImageView> = ArrayList()
    private var indicators : MutableList<ImageView> = ArrayList()

    private var currentPage : Int = 1

    interface OnBannerClickListenr{
        fun onClick(i : Int)
    }

    private var onBannerClickListener : OnBannerClickListenr? = null

    fun setOnBannerClickListenr(onBannerClickListener : OnBannerClickListenr){
        this.onBannerClickListener = onBannerClickListener
    }

    constructor(context : Context) : this(context , null)

    constructor(context : Context , attributeSet: AttributeSet?) : this(context , attributeSet , 0)

    constructor(context: Context , attributeSet: AttributeSet? , defStyleAttr: Int) : this(context , attributeSet , defStyleAttr , 0)

    constructor(context: Context , attributeSet: AttributeSet? , defStyleAttr: Int , defStyleRes : Int) :super(context , attributeSet , defStyleAttr , defStyleRes){

        initAttrs(attributeSet)

        viewPager = ViewPager(context)
        val lvp = LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        lvp.addRule(CENTER_IN_PARENT)
        addView(viewPager , lvp)

        indicator = LinearLayout(context)
        indicator.orientation = LinearLayout.HORIZONTAL
        indicator.id = View.generateViewId()
        val ll = LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT , ViewGroup.LayoutParams.WRAP_CONTENT)
        ll.addRule(ALIGN_PARENT_BOTTOM)
        ll.addRule(CENTER_HORIZONTAL)
        ll.bottomMargin = 20
        addView(indicator , ll)

        textView = TextView(context)
        textView.textSize = textSize
        textView.setTextColor(textColor)
        val ltv = LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT , ViewGroup.LayoutParams.WRAP_CONTENT)
        ltv.addRule(ABOVE , indicator.id)
        ltv.bottomMargin = 20
        ltv.leftMargin = textMaginLeft.toInt()
        ltv.rightMargin = textMaginRight.toInt()
        ltv.addRule(CENTER_HORIZONTAL)
        addView(textView , ltv)

    }

    private fun initImageViews() {
        for(i:Int in 0..imgs.size-1){
            var imageView : ImageView = ImageView(context)
            var params : RelativeLayout.LayoutParams = RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT)
            params.addRule(CENTER_IN_PARENT)
            Picasso.with(context)
                    .load(imgs[i])
                    .transform(object : ColorFilterTransformation(R.color.colorFilter){})
                    .fit()
                    .into(imageView)
            pics.add(imageView)

            imageView.setOnClickListener {
                onBannerClickListener?.onClick(getRealPos(i))
            }
        }

        initIndicator()
    }

    private fun getRealPos(i : Int) : Int {
        var realPos = 1
        if(i == 0)
            realPos = imgs.size - 2
        else if(i == imgs.size - 1)
            realPos = 1
        else
            realPos = i
        return realPos
    }

    private fun init(){
        textView.setTextColor(textColor)
        textView.setTextSize(textSize)
    }

    private fun Scroll() {
        Observable.interval(delayTime.toLong() ,scrollTime.toLong() , TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : io.reactivex.Observer<Long>{
                    override fun onSubscribe(d: Disposable?) {

                    }

                    override fun onError(e: Throwable?) {

                    }

                    override fun onComplete() {

                    }

                    override fun onNext(value: Long?) {
                        currentPage = (currentPage + 1)%imgs.size
                        if(currentPage == 0){
                            currentPage = getImgsNum()!! - 2
                        }else if(currentPage == imgs.size -1){
                            currentPage = 1
                        }
                        viewPager.currentItem = currentPage
                    }
                })
    }

    private fun initIndicator() {
        var num : Int = imgs.size -2

        for(i : Int in 0..num-1){
            var imageView : ImageView = ImageView(context)
            imageView.scaleType = ImageView.ScaleType.CENTER_INSIDE
            imageView.setPadding(5,5,5,5)
            if(i == 0)
                imageView.background = resources.getDrawable(R.drawable.selected_radius)
            else
                imageView.background = resources.getDrawable(R.drawable.unselected_radius)

            var lp : LinearLayout.LayoutParams = LinearLayout.LayoutParams(20 , 20)

            lp.bottomMargin = indicatorMagin.toInt()
            lp.leftMargin = indicatorMagin.toInt()
            lp.rightMargin = indicatorMagin.toInt()
            lp.topMargin = indicatorMagin.toInt()

            indicator.addView(imageView , lp)

            indicators.add(imageView)
        }
    }

    private fun initAttrs(attributeSet: AttributeSet?){
        val ta : TypedArray = context.obtainStyledAttributes(attributeSet , R.styleable.Banner)
        textSize = ta.getDimension(R.styleable.Banner_title_size, ORIGINAL_TEXT_SIZE)
        textColor = ta.getColor(R.styleable.Banner_title_color, ORIGINAL_TEXT_COLOR)
        indicatorDrawableUnselected = ta.getResourceId(R.styleable.Banner_indicator_drawable_unselected , ORIGINAL_DRAWABLE_UNSELECTED)
        indicatorDrawableSelected = ta.getResourceId(R.styleable.Banner_indicator_drawable_selected , ORIGINAL_DRAWABLE_SELECTED)
        indicatorMagin = ta.getDimension(R.styleable.Banner_indicator_margin , ORIGINAL_INDIVATOR_MAGIN)
        autoPlay = ta.getBoolean(R.styleable.Banner_auto_play, true)
        delayTime = ta.getInt(R.styleable.Banner_delay_time, ORIGINAL_DELAY_TIME)
        scrollTime = ta.getInt(R.styleable.Banner_scroll_time , ORIGINAL_SCROLL_TIME)
        textMaginLeft = ta.getDimension(R.styleable.Banner_title_margin_left , ORIGINAL_TEXT_MAGIN_LEFT)
        textMaginRight = ta.getDimension(R.styleable.Banner_title_margin_right , ORIGINAL_TEXT_MAGIN_RIGHT)

        ta.recycle()
    }

    fun update(imgs: List<String>,titles: List<String>){
        this.titles.clear()
        this.imgs.clear()
        indicators.clear()
        indicator.removeAllViews()

        this.titles.add(titles[titles.size - 1])
        this.titles.addAll(titles)
        this.titles.add(titles[0])

        this.imgs.add(imgs[imgs.size - 1])
        this.imgs.addAll(imgs)
        this.imgs.add(imgs[0])

        init()

        textView.text = this.titles[currentPage]
        Logger.i("start")
        initImageViews()
        initViewPager()
        if(autoPlay)
            Scroll()

    }

    fun setTitles(titles : List<String>){
        this.titles.add(titles[titles.size - 1])
        this.titles.addAll(titles)
        this.titles.add(titles[0])
    }

    fun setImgsByUrl(imgs : List<String>) {
        this.imgs.add(imgs[imgs.size - 1])
        this.imgs.addAll(imgs)
        this.imgs.add(imgs[0])
    }

    fun getImgsNum() : Int{
        return imgs.size
    }


    private fun initViewPager() {

        viewPager.adapter = object : PagerAdapter(){
            override fun getCount(): Int {
                return pics.size
            }

            override fun isViewFromObject(view: View?, `object`: Any?): Boolean {
                return view == `object`
            }

            override fun instantiateItem(container: ViewGroup?, position: Int): Any {
                container?.addView(pics[position])
                val view = pics[position]

                return view
            }

            override fun destroyItem(container: ViewGroup?, position: Int, `object`: Any?) {
                container?.removeView(`object` as View)
            }

        }

        viewPager.currentItem = currentPage
        viewPager.addOnPageChangeListener(  object : ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {
                if(state == ViewPager.SCROLL_STATE_IDLE)
                    viewPager.setCurrentItem(currentPage , false)//去除动画效果

                textView.text = titles[currentPage]
                for(i : Int in 0..indicators.size-1){
                    indicators[i].background = resources.getDrawable(R.drawable.unselected_radius)
                }
                indicators[currentPage-1].background = resources.getDrawable(R.drawable.selected_radius)
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {
                currentPage = getRealPos(position)
            }
        })

    }


}