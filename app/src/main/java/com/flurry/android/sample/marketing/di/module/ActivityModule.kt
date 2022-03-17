package com.flurry.android.sample.marketing.di.module

import com.flurry.android.sample.marketing.MainActivity
import com.flurry.android.sample.marketing.di.scope.ActivityScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {
    @ContributesAndroidInjector
    @ActivityScope
    abstract fun provideMainActivity(): MainActivity
}