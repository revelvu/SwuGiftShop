package com.swu.swugiftshop


import android.app.Activity
import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.MenuItem
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_funding_detailpage.my_toolbar
import kotlinx.android.synthetic.main.activity_make_funding_edit.*
import kotlinx.android.synthetic.main.activity_my_funding.*
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class MakeFundingEditActivity : AppCompatActivity() {
    //firebase
    val firebaseAuth = FirebaseAuth.getInstance()
    val db = FirebaseFirestore.getInstance()

    private var filePath: Uri? = null
    val userID = firebaseAuth.currentUser?.email.toString()
    lateinit var fdNickname: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_make_funding_edit)

        // tool bar back button
        setSupportActionBar(my_toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)

        //글쓰는 유저 닉네임
        val UserProfile =
            db.collection("UserProfile").document(userID)
        UserProfile.get().addOnCompleteListener(OnCompleteListener<DocumentSnapshot> { task ->
            if (task.isSuccessful) {
                val document = task.result
                if (document != null) {
                    fdNickname = task.result!!.data?.get("nickname")?.toString().toString()
                }
            }
        })
        //카테고리
//        val fdCategory=findViewById<Button>(R.id.enterFcategory)
        lateinit var categoryText: String
        enterFcategory.setOnClickListener {
            var optionDialog = AlertDialog.Builder(this)
            val categoryOption = arrayOf("문구류", "의류", "악세사리", "기타")
            //옵션 선택 시 텍스트 변경
            optionDialog.setItems(categoryOption) { dialog, which ->
                when (which) {
                    0 -> {
                        enterFcategory.setText(categoryOption[which])
                        categoryText = enterFcategory.text.toString()
                    }
                    1 -> {
                        enterFcategory.setText(categoryOption[which])
                        categoryText = enterFcategory.text.toString()
                    }
                    2 -> {
                        enterFcategory.setText(categoryOption[which])
                        categoryText = enterFcategory.text.toString()
                    }
                    3 -> {
                        enterFcategory.setText(categoryOption[which])
                        categoryText = enterFcategory.text.toString()
                    }
                }
            }
            val dialog = optionDialog.create()
            optionDialog.show()
        }

        //이미지 선택
        enterFimagechoice.setOnClickListener {
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(Intent.createChooser(intent, "이미지를 선택하세요."), 0)
        }
        //이미지 db에 업로드
        enterFimageupload.setOnClickListener {
            uploadFile();
        }

        //EditText 가져와보자
        val fdName = findViewById<EditText>(R.id.enterFname)
        val fdDate = findViewById<EditText>(R.id.enterFdate)
        val fdMinimum = findViewById<EditText>(R.id.enterFminimum).text
        val fdPrice = findViewById<EditText>(R.id.enterFprice).text
        var fdOption = findViewById<EditText>(R.id.enterFoption)
        //옵션 추가
        val Option = ArrayList<String>()
        addOption.setOnClickListener {
            Option.add(fdOption.text.toString())
            fdOption.setText("")
        }

        storyIntentBtn.setOnClickListener {
            val editorIntent = Intent(this, MakeFundingEditActivity2::class.java)
            //여기다 db생성부 구현하자
            val fundinghash = hashMapOf(
                "펀딩주최자" to fdNickname,
                "물품명" to fdName.text.toString(),
                "마감일" to fdDate.text.toString(),
                "목표인원" to fdMinimum.toString().toInt(),
                "가격" to fdPrice.toString().toInt(),
                "카테고리" to categoryText,
                "서포터" to 0,
                "펀딩률" to 0
            )
            db.collection("UnofficialProduct").document(fdName.text.toString()).set(fundinghash)
            //db에 옵션 추가
            for (i in 0 until Option.size) {
                db.collection("UnofficialProduct").document(fdName.text.toString())
                    .update("옵션$i", Option[i])

            }
            //글쓰는 유저의 UserProfile 컬렉션에 펀딩 올린 물품명 저장하기
            UserProfile.get().addOnCompleteListener(OnCompleteListener<DocumentSnapshot> { task ->
                if (task.isSuccessful) {
                    val document = task.result
                    if (document != null) {
                        UserProfile.update("나의펀딩", "" + fdName.text.toString())
                    }
                }
            })
            editorIntent.putExtra("NAme", fdName.text.toString())
            startActivity(editorIntent)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
        //request코드가 0이고 OK를 선택했고 data에 뭔가가 들어 있다면
        if (requestCode == 0 && resultCode == Activity.RESULT_OK) {
            filePath = data?.data
            Log.d("뭐지이거", "uri:" + java.lang.String.valueOf(filePath))
            try {
                //Uri 파일을 Bitmap으로 만들어서 ImageView에 집어 넣는다.
                val bitmap =
                    MediaStore.Images.Media.getBitmap(contentResolver, filePath)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }


    //upload the file
    private fun uploadFile() {
        //업로드할 파일이 있으면 수행
        if (filePath != null) {
            //업로드 진행 Dialog 보이기
            val progressDialog = ProgressDialog(this)
            progressDialog.setTitle("업로드 중...")
            progressDialog.show()

            //storage
            val storage = FirebaseStorage.getInstance()
            val filename = "$userID.png"
            //storage 주소와 폴더 파일명을 지정
            val storageRef =
                storage.getReferenceFromUrl("gs://swugiftshop.appspot.com")
                    .child("images/$filename")
            storageRef.putFile(filePath!!) //성공시
                .addOnSuccessListener {
                    progressDialog.dismiss() //업로드 진행 Dialog 상자 닫기
                    Toast.makeText(applicationContext, "사진 업로드 완료!", Toast.LENGTH_SHORT)
                        .show()
                } //실패시
                .addOnFailureListener {
                    progressDialog.dismiss()
                    Toast.makeText(applicationContext, "사진 업로드 실패", Toast.LENGTH_SHORT)
                        .show()
                } //진행중
                .addOnProgressListener { taskSnapshot ->
                    val progress =
                        100 * taskSnapshot.bytesTransferred / taskSnapshot.totalByteCount
                            .toDouble()
                    //dialog에 진행률을 퍼센트로 출력해 준다
                    progressDialog.setMessage("Uploaded " + progress.toInt() + "% ...")
                }
        } else {
            Toast.makeText(applicationContext, "파일을 먼저 선택하세요.", Toast.LENGTH_SHORT).show()
        }
    }

    // tool bar back button
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        when (id) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

}
