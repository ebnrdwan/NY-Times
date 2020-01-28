package com.ebnrdwan.core.di

import javax.inject.Qualifier
import javax.inject.Scope

@MustBeDocumented
@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class FragmentScope


@MustBeDocumented
@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class MainArticlesSource


@Qualifier
@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
annotation class BaseUrl