package com.noman.chitchat.model

data class UserInfo (
    val userInfoList: List<UserInfoList>
        ){
     class UserInfoList{
        var email: String? = null
        var image: String? = null
        var name: String? = null
        var phone: String? = null
        var uid: String? = null

        constructor(){}

        constructor(email:String,image:String,name:String,phone:String,uid:String){
            this.email = email
            this.image = image
            this.name = name
            this.phone = phone
            this.uid = uid
        }
    }

}
