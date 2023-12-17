package com.shall_we.myAlbum

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.shall_we.R
import java.io.File





class MyAlbumAdapter(private val context: Context) : RecyclerView.Adapter<MyAlbumAdapter.ViewHolder>() {
    lateinit var datas : MyAlbumPhotoData

    private var itemClickListener: OnItemClickListener? = null

    // 클릭 리스너 인터페이스 정의
    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        itemClickListener = listener
    }
//    fun setImageList(list: ArrayList<MyAlbumPhotoData>) {
//        datas.addAll(list)
//    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_album, parent, false)
//        val binding = ItemAlbumBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//        val gridLayoutManager = GridLayoutManager(parent.context, 2)
//        binding.root.layoutManager = gridLayoutManager
//        setImageList()
        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val data = datas.imgUrl?.get(position)

        if (position == 0) {
            // 맨 처음 아이템일 때 정해진 이미지 로드
            Glide.with(context)
                .load(R.drawable.add_photo)
                .apply(RequestOptions.placeholderOf(R.drawable.baking))
                .into(holder.ivAlbum)
        } else if (position < datas.imgUrl?.size ?: 0) {
            // 나머지 아이템은 실제 데이터의 이미지 로드
            Glide.with(context)
                .load(data)
                .apply(RequestOptions.placeholderOf(R.drawable.baking))
                .into(holder.ivAlbum)
        }

//        holder.bind(datas)
        holder.itemView.setOnClickListener {
            picDialog(it, position, datas)

        }
    }

    private fun picDialog(view: View, position: Int, data: MyAlbumPhotoData) {
        // 사진 추가
        if (position == 0) {
//            Toast.makeText(view.context, "사진 추가 기능", Toast.LENGTH_SHORT).show()
            itemClickListener?.onItemClick(position)

            //openGalleryForImageSelection()
        }

        // 사진 뷰어
        else {
            val myLayout = LayoutInflater.from(context).inflate(R.layout.dialog_photoviewer, null)
            val imageView = myLayout.findViewById<ImageView>(R.id.iv_photo)

            Glide.with(context).load(data.imgUrl?.get(position)).into(imageView)

            val build = AlertDialog.Builder(view.context).apply {
                setView(myLayout)
            }
            val dialog = build.create()
            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

            dialog.show()

            myLayout.findViewById<Button>(R.id.btn_delete).setOnClickListener {
                cuDialog(it, position, position)

            }

            myLayout.findViewById<Button>(R.id.btn_close).setOnClickListener {
                dialog.dismiss()
            }
        }
    }

    fun cuDialog(view: View, position: Int, idx: Int) {
        val myLayout = LayoutInflater.from(context).inflate(R.layout.dialog_delete_photo, null)
        val build = AlertDialog.Builder(view.context).apply {
            setView(myLayout)
        }
        val dialog = build.create()
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        dialog.show()

        myLayout.findViewById<Button>(R.id.btn_cancel_reservation).setOnClickListener {
            //retrofitdelResApiCall(idx)
            datas.imgUrl?.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, itemCount)
            Toast.makeText(context, "삭제되었습니다.", Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }
        myLayout.findViewById<Button>(R.id.btn_cancel).setOnClickListener {
            dialog.dismiss()
        }
    }


    override fun getItemCount(): Int {
        return datas.imgUrl?.size ?: 0
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ivAlbum: ImageView = itemView.findViewById(R.id.iv_album)
        init {
            // itemView를 클릭했을 때 해당 항목의 ProductData를 클릭 리스너를 통해 전달
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    itemClickListener?.onItemClick(position)
                }
            }
        }
    }
}