package myapp.features.arch_room

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_room_main.*
import myapp.features.arch_room.db.Word

class RoomMainActivity : AppCompatActivity() {

    //region [private members]

    private lateinit var wordViewModel: WordViewModel

    //endregion

    //region [event handlers]

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recycleView)
        val adapter = WordListAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        wordViewModel = ViewModelProviders.of(this).get(WordViewModel::class.java)
        wordViewModel.allWords.observe(this, Observer { words ->
            words?.let { adapter.setWords(it) }
        })

        fab.setOnClickListener {
            val intent = Intent(this@RoomMainActivity, RoomNewWordActivity::class.java)
            startActivityForResult(intent, newWordActivityRequestCode)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == newWordActivityRequestCode
            && resultCode == Activity.RESULT_OK
        ) {
            data?.let {
                val word = Word(it.getStringExtra(RoomNewWordActivity.EXTRA_REPLY))
                wordViewModel.insert(word)
            }
        } else {
            Toast.makeText(applicationContext, R.string.empty_not_saved, Toast.LENGTH_LONG).show()
        }
    }

    //endregion

    companion object {
        const val newWordActivityRequestCode = 1
    }
}
