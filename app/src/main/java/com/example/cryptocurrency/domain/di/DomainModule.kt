package com.example.cryptocurrency.domain.di

import com.example.cryptocurrency.data.retrofit.CountriesAPI
import com.example.cryptocurrency.domain.interfaces.IImageProcessing
import com.example.cryptocurrency.domain.usecases.GetCountriesFromRemote
import com.example.cryptocurrency.domain.usecases.GetOriginalImage
import com.example.cryptocurrency.domain.usecases.GetProcessedImage
import com.example.cryptocurrency.domain.usecases.UseCases
import com.example.cryptocurrency.utils.ImageProcessing
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class ImageProcessingModule {
    @Binds
    abstract fun getImageProcessing(imageProcessing: ImageProcessing): IImageProcessing
}

@Module
@InstallIn(ActivityComponent::class)
object DomainModule {

    @Provides
    fun getOriginalImageUseCase(imageProcessing: IImageProcessing) : GetOriginalImage = GetOriginalImage(imageProcessing)

    @Provides
    fun getProcessedImageUseCase(imageProcessing: IImageProcessing) : GetProcessedImage = GetProcessedImage(imageProcessing)

    @Provides
    fun getCountriesFromRemote(imageProcessing: CountriesAPI) : GetCountriesFromRemote = GetCountriesFromRemote(imageProcessing)

    @Provides
    fun getUseCases(
        getOriginalImage: GetOriginalImage,
        getProcessedImage: GetProcessedImage,
        getCountriesFromRemote: GetCountriesFromRemote
    ) = UseCases(getOriginalImage, getProcessedImage, getCountriesFromRemote)

}