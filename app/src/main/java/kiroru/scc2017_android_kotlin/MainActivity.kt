package kiroru.scc2017_android_kotlin

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import com.bumptech.glide.Glide

class MainActivity : AppCompatActivity() {

    class Item(imageUrl:String, val jname:String, val ename:String) {
        val imageUrl = "https://kiroru-inc.jp/share/scc2017/" + imageUrl
    }

    var items = mutableListOf<Item>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        items.add(Item("Japan.png", "日本", "Japan"))
        items.add(Item("China.png", "中国", "China"))
        items.add(Item("USA.png", "アメリカ", "America"))
        items.add(Item("England.png", "イギリス", "England"))
        items.add(Item("France.png", "フランス", "France"))

        val lv = findViewById(R.id.listView) as ListView
        lv.adapter = MyAdapter(this, items)

        lv.setOnItemClickListener { parent, view, position, id ->
            val item = items[position]
            val i = Intent(this, DetailActivity::class.java)
            i.putExtra("imageUrl", item.imageUrl)
            i.putExtra("jname", item.jname)
            i.putExtra("ename", item.ename)
            startActivity(i)
        }
    }

    class MyAdapter(val context: Context, val items:MutableList<Item>) : BaseAdapter() {

        var holder:ViewHolder? = null
        val inflater: LayoutInflater = (context as Activity).layoutInflater

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            var view:View
            if (convertView == null) {
                view = inflater.inflate(R.layout.cell_main, null)
                holder = ViewHolder(
                        view.findViewById(R.id.imageView) as ImageView,
                        view.findViewById(R.id.textView1) as TextView,
                        view.findViewById(R.id.textView1) as TextView)
                view.tag = holder
            } else {
                view = convertView!!
                holder = view.tag as ViewHolder
            }

            val item = items[position]

            Glide.with(context).load(item.imageUrl).into(holder?.iv)
            holder?.tv1?.text = item.jname
            holder?.tv2?.text = item.ename

            return view
        }

        override fun getItem(position: Int): Any {
            return items[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return items.size
        }

        class ViewHolder(val iv: ImageView, val tv1: TextView, val tv2: TextView) {}
    }
}
