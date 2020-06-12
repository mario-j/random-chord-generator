package hu.ait.randomchordgenerator

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.keyboard.*
import kotlinx.android.synthetic.main.saved_chords.*
import kotlinx.android.synthetic.main.saved_chords_row.view.*

class SavedChordsRow : AppCompatActivity() {

    val STATE_USER = "user"
    private var mUser: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.saved_chords)

        btnSave.setOnClickListener {
            createChordEntry()
        }
    }

    private fun createChordEntry() {
        val myChordView = layoutInflater.inflate(R.layout.saved_chords_row, null, false)

        val nameOne = intent.getStringExtra("NAME_ONE")
        val qualityOne = intent.getStringExtra("QUALITY_ONE")
        val extensionOne = intent.getStringExtra("EXTENSION_ONE")

        val nameTwo = intent.getStringExtra("NAME_TWO")
        val qualityTwo = intent.getStringExtra("QUALITY_TWO")
        val extensionTwo = intent.getStringExtra("EXTENSION_TWO")

        val nameThree = intent.getStringExtra("NAME_THREE")
        val qualityThree = intent.getStringExtra("QUALITY_THREE")
        val extensionThree = intent.getStringExtra("EXTENSION_THREE")

        val nameFour = intent.getStringExtra("NAME_FOUR")
        val qualityFour = intent.getStringExtra("QUALITY_FOUR")
        val extensionFour = intent.getStringExtra("EXTENSION_FOUR")

        myChordView.tvSavedNameOne.text = nameOne
        myChordView.tvSavedQualityOne.text = qualityOne
        myChordView.tvSavedExtensionOne.text = extensionOne

        myChordView.tvSavedNameTwo.text = nameTwo
        myChordView.tvSavedQualityTwo.text = qualityTwo
        myChordView.tvSavedExtensionTwo.text = extensionTwo

        myChordView.tvSavedNameThree.text = nameThree
        myChordView.tvSavedQualityThree.text = qualityThree
        myChordView.tvSavedExtensionThree.text = extensionThree

        myChordView.tvSavedNameFour.text = nameFour
        myChordView.tvSavedQualityFour.text = qualityFour
        myChordView.tvSavedExtensionFour.text = extensionFour

        myChordView.btnDelete.setOnClickListener {
            layoutContent.removeView(myChordView)
        }

        layoutContent.addView(myChordView, 0)
    }

}