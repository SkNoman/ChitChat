package com.noman.chitchat.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.noman.chitchat.R
import com.noman.chitchat.model.Message

class MessageAdapter(val context: Context, private val messageList: ArrayList<Message>)
    :RecyclerView.Adapter<ViewHolder>() {

    private val itemReceived = 1
    private val itemSent = 2

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return if (viewType == itemReceived){
            //inflate received
            val view: View = LayoutInflater.from(context).inflate(R.layout.receive,parent,false)
            ReceivedViewHolder(view)
        }else{
            //inflate sent
            val view: View = LayoutInflater.from(context).inflate(R.layout.sent,parent,false)
            SendViewHolder(view)
        }
    }

    override fun getItemViewType(position: Int): Int {
        val currentMessage = messageList[position]
        return if (FirebaseAuth.getInstance().currentUser?.uid.equals(currentMessage.senderId)){
            itemSent
        }else{
            itemReceived
        }
    }

    override fun getItemCount(): Int {
       return messageList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentMessage = messageList[position]
       if (holder.javaClass == SendViewHolder::class.java){
           val viewHolder = holder as SendViewHolder
           holder.sentTxtMessage.text = currentMessage.message
           Glide.with(context).load(currentMessage.senderImage)
               .placeholder(R.drawable.google).into(holder.receiverImage)
       }else{
           val viewHolder = holder as ReceivedViewHolder
           holder.receivedTxtMessage.text = currentMessage.message
           Glide.with(context).load(currentMessage.senderImage)
               .placeholder(R.drawable.google).into(holder.senderImage)
       }
    }

    class SendViewHolder(itemView: View) : ViewHolder(itemView){
        val sentTxtMessage: TextView = itemView.findViewById(R.id.txtSentMessage)
        val receiverImage: ImageView = itemView.findViewById(R.id.ivReceiverImage)
    }

    class  ReceivedViewHolder(itemView: View) : ViewHolder(itemView){
        val receivedTxtMessage: TextView = itemView.findViewById(R.id.txtReceiveMessage)
        val senderImage: ImageView = itemView.findViewById(R.id.ivSenderImage)
    }
}