package kiroru.scc2017_android_kotlin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val imageUrl = intent.getStringExtra("imageUrl")
        val jname = intent.getStringExtra("jname")
        val ename = intent.getStringExtra("ename")

        val iv = findViewById(R.id.imageView) as ImageView
        Glide.with(this).load(imageUrl).into(iv)

        val tv1 = findViewById(R.id.textView1) as TextView
        tv1.text = jname

        val tv2 = findViewById(R.id.textView2) as TextView
        tv2.text = ename

        val b = findViewById(R.id.button) as Button
        b.setOnClickListener { _ -> finish() }
    }
}
