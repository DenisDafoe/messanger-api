package com.denisfominykh.messengerapi.user

import com.denisfominykh.messengerapi.region.Region

data class UserData (
    val id: Long,
    val region: Region,
)