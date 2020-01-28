package com.ebnrdwan.task.di

import android.app.Application
import com.ebnrdwan.task.presentation.main.MainActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AppModule::class, TestDataModule::class]
)
interface TestAppComponent : AppComponent {

    @Component.Factory
    interface Factory {
        fun createWith(@BindsInstance application: Application): TestAppComponent
    }

    override fun inject(mainActivity: MainActivity)
    override fun registerArticleComponent(): ArticlesSubcomponent.Factory
}