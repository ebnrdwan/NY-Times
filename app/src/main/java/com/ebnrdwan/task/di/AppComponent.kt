package com.ebnrdwan.task.di

import android.app.Application
import com.ebnrdwan.task.presentation.main.MainActivity
import dagger.BindsInstance
import dagger.Component

import javax.inject.Singleton

@Singleton
@Component(
    modules = [AppModule::class]
)
interface AppComponent {

      @Component.Factory
     interface Factory {
         fun createWith(@BindsInstance applicationController: Application): AppComponent
     }

     fun inject(mainActivity: MainActivity)
     fun registerArticleComponent(): ArticlesSubcomponent.Factory
}


