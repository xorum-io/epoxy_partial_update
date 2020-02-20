package io.xorum.epoxypartialupdate

import android.os.Bundle
import android.view.View
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.airbnb.epoxy.EpoxyController
import com.airbnb.epoxy.EpoxyModel
import io.xorum.epoxypartialupdate.data.MockDataSource
import io.xorum.epoxypartialupdate.model.Phrase

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.view_item_phrase.view.*

class MainActivity : AppCompatActivity() {

    private val controller = MyEpoxyController()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        initViews()
        fetchData()
    }

    private fun initViews() {
        recyclerView.setController(controller)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    private fun fetchData() = MockDataSource().getPhrases().let { controller.phrases = it }

    internal class MyEpoxyController : EpoxyController() {

        var phrases: List<Phrase>? = null
            set(value) {
                field = value
                requestModelBuild()
            }

        override fun buildModels() {
            phrases?.forEach {
                PhraseEpoxyModel(it).addTo(this)
            }
        }
    }

    internal class PhraseEpoxyModel(
        private val phrase: Phrase
    ) : EpoxyModel<View>() {

        init {
            id("PhraseEpoxyModel - $phrase")
        }

        override fun getDefaultLayout() = R.layout.view_item_phrase

        override fun bind(view: View) {
            super.bind(view)

            view.original.text = phrase.original
            view.translation.text = phrase.translation
        }

        override fun unbind(view: View) {
            super.unbind(view)

            view.original.text = ""
            view.translation.text = ""
        }
    }
}
