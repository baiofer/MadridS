package com.jarzasa.madridshops.repository

import com.jarzasa.madridshops.repository.model.ActivityEntity
import com.jarzasa.madridshops.repository.model.ShopEntity


typealias SuccessClosure = (shops: List<ShopEntity>) -> Unit
typealias SuccessClosureActivity = (activities: List<ActivityEntity>) -> Unit