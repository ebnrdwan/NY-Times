package com.ebnrdwan.task.presentation.main.drawer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ebnrdwan.corepresentation.base.BaseViewModel
import com.ebnrdwan.task.data.models.DrawerModel

class DrawerViewModel : BaseViewModel() {
    private var _itemClicked: MutableLiveData<DrawerModel> = MutableLiveData()

    fun setDrawerItemClicked(item: DrawerModel) {
        _itemClicked.value = item
    }

    fun getDrawerItemClicked(): LiveData<DrawerModel> {
        return _itemClicked
    }
}
