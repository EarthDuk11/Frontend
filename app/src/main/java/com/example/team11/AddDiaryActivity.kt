package com.example.team11

import android.content.Intent
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.team11.databinding.ActivityAddDiaryBinding
import com.google.firebase.storage.StorageReference
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class AddDiaryActivity : AppCompatActivity() {
    lateinit var binding : ActivityAddDiaryBinding
    lateinit var filePath: String
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_diary)

        binding= ActivityAddDiaryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE), 1)


        binding.addImage.setOnClickListener{
            val intent = Intent(Intent.ACTION_PICK)
            intent.setDataAndType(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                "image/*"
            )
            requestLauncher.launch(intent)
        }


        binding.btnSave.setOnClickListener{

            if(binding.addTitle.text.isNotEmpty() && binding.addOneLine.text.isNotEmpty() &&
                binding.addContent.text.isNotEmpty()){ //
                //store 에 먼저 데이터를 저장 후 document id 값으로 업로드 파일 이름 지정
                saveStore()
            }else {
                Toast.makeText(this, "데이터가 모두 입력되지 않았습니다.", Toast.LENGTH_SHORT).show()
            }
            finish()
        }
    }
    val requestLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult())
    {
        if(it.resultCode === android.app.Activity.RESULT_OK){
            Glide
                .with(getApplicationContext())
                .load(it.data?.data)
                .apply(RequestOptions().override(250, 200))
                .centerCrop()
                .into(binding.addImage)


            val cursor = contentResolver.query(it.data?.data as Uri,
                arrayOf<String>(MediaStore.Images.Media.DATA), null, null, null);
            cursor?.moveToFirst().let {
                filePath=cursor?.getString(0) as String
                Toast.makeText(this, "사진 string : ${filePath}", Toast.LENGTH_SHORT).show() // /storage/emulated/0/DCMI/파일이름 잃
            }
        }
    }

    fun verifyeHashTag(content: String) : Boolean {
        val isHash = content.contains("#")
        return isHash
    }


    private fun saveStore(){
        //add............................ uid와 id는 음... 어떻게 해야할지 모르겠음.
        val content = binding.addContent.text.toString()
        val data = mapOf(
            "content" to content,
            "date" to dateToString(Date()),
            "email" to MyApplication.email,
            "hash" to verifyeHashTag(content),
//            "img" -> 뒤어 upLoadImg에서 처리
            "smileCount" to 0,
            "surprisedCount" to 0,
            "thumbsUpCount" to 0,
            "oneIntro" to binding.addOneLine.text.toString(),
            "title" to binding.addTitle.text.toString(),
            "uid" to MyApplication.auth.uid,

        )

        MyApplication.db.collection("diaries")
            .add(data)// id 발급을 위해 먼저 저장
            .addOnSuccessListener {
                // 스토리지에 데이터 저장 후 id값으로 스토리지에 이미지 업로드
                uploadImage(it.id) // ????????
                Toast.makeText(this, "diary id??? : ${it}", Toast.LENGTH_SHORT).show() // /storage/emulated/0/DCMI/파일이름 잃
                Log.d("Diary Write", "diary id??? : ${it}")

            }
            .addOnFailureListener{
                Log.d("Diary Write", "data save error", it)
            }
    }
    private fun uploadImage(docId: String){
        //add............................
        val storage = MyApplication.storage
        // 스토리지를 참조하는 StorageReference 생성
        val storageRef: StorageReference = storage.reference
        // 실제 업로드하는 파일을 참조하는 StorageReference 생성
        val imgRef: StorageReference = storageRef.child("images/${docId}.jpg")
        // 파일 업로드
        var fileUri = Uri.fromFile(File(filePath))
        Log.d("kkang", "File URI: $fileUri")

        imgRef.putFile(fileUri)
            .addOnFailureListener { exception ->
                Log.d("kkang", "Failure uploading file: $exception")
            }
            .addOnSuccessListener {
                Toast.makeText(this, "데이터가 저장되었습니다.", Toast.LENGTH_SHORT).show()
                finish()
            }
    }

    fun dateToString(date: Date): String {
        val format = SimpleDateFormat("yyyy-MM-dd hh:mm")
        // DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.KOREAN);
        //  tz = TimeZone.getTimeZone("Asia/Seoul");  // TimeZone에 표준시 설정
        //  dateFormat.setTimeZone(tz);                    //DateFormat에 TimeZone 설정

        return format.format(date)
    }
}