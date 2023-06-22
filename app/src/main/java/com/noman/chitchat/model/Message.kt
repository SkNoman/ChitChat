package com.noman.chitchat.model

class Message {
    var message: String? = null
    var senderId: String? = null
    var senderImage: String? = null

    constructor(){}

    constructor(message: String?, senderId: String?,senderImage:String?){
        this.message = message
        this.senderId = senderId
        this.senderImage = senderImage
    }
}