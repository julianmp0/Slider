# Slider For Grovi

**1-** Create Kotlin class **GlideImageLoadingService**.

    class GlideImageLoadingService : ImageLoadingService {  
  
	    override fun loadImage(url: String?, imageView: ImageView) {  
	        Glide  
	            .with(imageView)  
	            .load(url)  
	            .into(imageView);  
	    }  
  
	    override fun loadImage(resource: Int, imageView: ImageView?) {  
	    }	  
  
	    override fun loadImage(url: String?, placeHolder: Int, errorDrawable: Int, imageView: ImageView?) {  
	    }
    }  

**2-** Init Slider.

    Slider.init(GlideImageLoadingService())

**3-** Add slider into layout

    <com.slider.slider.Slider  
	      android:id="@+id/slider"  
          android:layout_width="match_parent"  
          android:layout_height="wrap_content"       
          app:slider_animateIndicators="true" 
          app:slider_indicatorSize="10dp"  
          app:slider_loopSlides="true" />
         

**4-** Create Model Slider

    
    data class BannerEntity(        
	       var url_image:String? = ""
    )
      
    

**5-** Create Adapter extends **SliderAdapter**

    class MSliderAdapter(val bannerImages: List<BannerEntity>): SliderAdapter() {  
        override fun getItemCount(): Int {  
            return bannerImages.size  
      }  
      
        override fun onBindImageSlide(position: Int, imageSlideViewHolder: ImageSlideViewHolder?) {  
            try {  
                imageSlideViewHolder!!.bindImageSlide(bannerImages[position].url_image)  
            }catch (exc : ArrayIndexOutOfBoundsException){  
                Crashlytics.logException(exc)  
            }  
        }  
      
        override fun getList(): MutableList<String> {  
            return bannerImages
        }  
    }


private fun setupBannerSlider(bannerImages: List<BannerEntity>) {
        dataBinding.bannerSlider1.setInterval(5000)
        dataBinding.bannerSlider1.setAdapter(MSliderAdapter(bannerImages))
        dataBinding.bannerSlider1.setOnSlideClickListener(this)
    }




