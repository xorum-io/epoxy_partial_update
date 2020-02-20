package io.xorum.epoxypartialupdate

import android.os.Bundle
import android.view.View
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
            controller.isTranslationDisplayed = !controller.isTranslationDisplayed
            if (controller.isTranslationDisplayed) {
                fab.setImageResource(R.drawable.ic_hide)
            } else {
                fab.setImageResource(R.drawable.ic_reveal)
            }
        }
    }

    private fun fetchData() = MockDataSource().getPhrases().let { controller.phrases = it }

    internal class MyEpoxyController : EpoxyController() {

        var phrases: List<Phrase>? = null
            set(value) {
                field = value
                requestModelBuild()
            }

        var isTranslationDisplayed = false
            set(value) {
                field = value
                requestModelBuild()
            }

        override fun buildModels() {
            phrases?.forEach {
                PhraseEpoxyModel(it, isTranslationDisplayed).addTo(this)
            }
        }
    }

    internal data class PhraseEpoxyModel(
        private val phrase: Phrase,
        private val isTranslationDisplayed: Boolean
    ) : EpoxyModel<View>() {

        init {
            id("PhraseEpoxyModel - $phrase")
        }

        override fun getDefaultLayout() = R.layout.view_item_phrase

        override fun bind(view: View) {
            super.bind(view)

            view.original.text = phrase.original
            view.translation.text = phrase.translation
            updateTranslationVisibility(view)
        }

        override fun bind(view: View, previouslyBoundModel: EpoxyModel<*>) {
            super.bind(view, previouslyBoundModel)

            updateTranslationVisibility(view)
        }

        private fun updateTranslationVisibility(view: View) {
            view.translation.visibility = if (isTranslationDisplayed) {
                View.VISIBLE
            }else  {
                View.INVISIBLE
            }
        }

        override fun unbind(view: View) {
            super.unbind(view)

            view.original.text = ""
            view.translation.text = ""
            view.translation.visibility = View.INVISIBLE
        }
    }
}
