package com.example.a1stripedemo.Model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class StripeResponse(
    @field:SerializedName("id") @Expose val id: String?,
    @field:SerializedName("object") @Expose val objectKey: String?,
    @field:SerializedName("associated_objects") @Expose val associatedObjects: List<AssociatedObject>,
    @field:SerializedName("created") @Expose val created: Int,
    @field:SerializedName("expires") @Expose val expires: Int,
    @field:SerializedName("livemode") @Expose val livemode: Boolean,
    @field:SerializedName("secret") @Expose val secret: String?
)

data class AssociatedObject(
    @field:SerializedName("id") @Expose val id: String?,
    @field:SerializedName("type") @Expose val type: String?
)

data class StripeRequest(
    @SerializedName("api_version")
    @Expose
    var apiVersion: String?
)