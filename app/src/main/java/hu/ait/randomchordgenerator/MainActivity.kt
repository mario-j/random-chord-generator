package hu.ait.randomchordgenerator

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.widget.Spinner
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.min
import kotlin.random.Random
import android.media.MediaPlayer.OnCompletionListener
import android.support.v4.content.ContextCompat
import android.view.View
import kotlin.system.exitProcess
import android.media.AudioAttributes
import android.os.Build


class MainActivity : AppCompatActivity() {

    private lateinit var handler: Handler
    private lateinit var runnableChord2: Runnable
    private lateinit var runnableChord3: Runnable
    private lateinit var runnableChord4: Runnable

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_kayboard -> {
                val intentDetails = Intent(applicationContext, Keyboard::class.java)
                intentDetails.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                ContextCompat.startActivity(applicationContext, intentDetails, null)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_save -> {
                val intentDetails = Intent(applicationContext, SavedChordsRow::class.java)
                intentDetails.putExtra("NAME_ONE", spnNameOne.selectedItem.toString())
                intentDetails.putExtra("QUALITY_ONE", spnQualityOne.selectedItem.toString())
                intentDetails.putExtra("EXTENSION_ONE", spnExtensionOne.selectedItem.toString())

                intentDetails.putExtra("NAME_TWO", spnNameTwo.selectedItem.toString())
                intentDetails.putExtra("QUALITY_TWO", spnQualityTwo.selectedItem.toString())
                intentDetails.putExtra("EXTENSION_TWO", spnExtensionTwo.selectedItem.toString())

                intentDetails.putExtra("NAME_THREE", spnNameThree.selectedItem.toString())
                intentDetails.putExtra("QUALITY_THREE", spnQualityThree.selectedItem.toString())
                intentDetails.putExtra("EXTENSION_THREE", spnExtensionThree.selectedItem.toString())

                intentDetails.putExtra("NAME_FOUR", spnNameFour.selectedItem.toString())
                intentDetails.putExtra("QUALITY_FOUR", spnQualityFour.selectedItem.toString())
                intentDetails.putExtra("EXTENSION_FOUR", spnExtensionFour.selectedItem.toString())
                intentDetails.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                ContextCompat.startActivity(applicationContext, intentDetails, null)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        handler = Handler()

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        val audioManager = getSystemService(Context.AUDIO_SERVICE) as AudioManager
        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, 10, 0)

        btnRandomize.setOnClickListener {
            var randomChordOne = setChordNameOne()
            var randomQualityOne = setQualityOne()
            setExtensionOne()
            setExtensionTwo()
            setExtensionThree()
            setExtensionFour()
            setChords(randomChordOne, randomQualityOne)
        }

        var chordOne: MediaPlayer = MediaPlayer.create(applicationContext, R.raw.cmaj_piano)
        var chordTwo: MediaPlayer = MediaPlayer.create(applicationContext, R.raw.gmaj_piano)
        var chordThree: MediaPlayer = MediaPlayer.create(applicationContext, R.raw.amin_piano)
        var chordFour: MediaPlayer = MediaPlayer.create(applicationContext, R.raw.fmaj_piano)

        btnPlay.setOnClickListener {
            val possibleChordsOne = getPossibleChords(spnQualityOne, spnExtensionOne)
            val possibleChordsTwo = getPossibleChords(spnQualityTwo, spnExtensionTwo)
            val possibleChordsThree = getPossibleChords(spnQualityThree, spnExtensionThree)
            val possibleChordsFour = getPossibleChords(spnQualityFour, spnExtensionFour)

            chordOne = MediaPlayer.create(applicationContext, possibleChordsOne[spnNameOne.selectedItemPosition])
            chordTwo = MediaPlayer.create(applicationContext, possibleChordsTwo[spnNameTwo.selectedItemPosition])
            chordThree = MediaPlayer.create(applicationContext, possibleChordsThree[spnNameThree.selectedItemPosition])
            chordFour = MediaPlayer.create(applicationContext, possibleChordsFour[spnNameFour.selectedItemPosition])
            playChords(chordOne, chordTwo, chordThree, chordFour)
        }
    }

    private fun setQualityOne(): Int {
        var randomQualityOne = 0
        randomQualityOne = Random.nextInt(0, 2)
        spnQualityOne.setSelection(randomQualityOne)
        return randomQualityOne
    }

    private fun setChordNameOne(): Int {
        var randomChordOne = 0
        randomChordOne = Random.nextInt(0, 15)
        spnNameOne.setSelection(randomChordOne)
        return randomChordOne
    }

    private fun setExtensionFour() {
        var randomExtensionFour = 0
        randomExtensionFour = Random.nextInt(0, 4)
        spnExtensionFour.setSelection(randomExtensionFour)
    }

    private fun setExtensionThree() {
        var randomExtensionThree = 0
        randomExtensionThree = Random.nextInt(0, 4)
        spnExtensionThree.setSelection(randomExtensionThree)
    }

    private fun setExtensionTwo() {
        var randomExtensionTwo = 0
        randomExtensionTwo = Random.nextInt(0, 4)
        spnExtensionTwo.setSelection(randomExtensionTwo)
    }

    private fun setExtensionOne() {
        var randomExtensionOne = 0
        randomExtensionOne = Random.nextInt(0, 4)
        spnExtensionOne.setSelection(randomExtensionOne)
    }



    private fun playChords(chordOne: MediaPlayer, chordTwo: MediaPlayer, chordThree: MediaPlayer, chordFour: MediaPlayer) {

        chordOne.start()

        chordOne.setOnCompletionListener {
            chordOne.release()
        }

        runnableChord2 = Runnable{
            chordTwo.start()
        }

        chordTwo.setOnCompletionListener {
            chordTwo.release()
        }

        runnableChord3 = Runnable{
            chordThree.start()
        }

        chordThree.setOnCompletionListener {
            chordThree.release()
        }

        runnableChord4 = Runnable{
            chordFour.start()
        }

        chordFour.setOnCompletionListener {
            chordFour.release()
        }

        handler.postDelayed(runnableChord2, 2000)

        handler.postDelayed(runnableChord3, 4000)

        handler.postDelayed(runnableChord4, 6000)
    }

    private fun setChords(randomChordOne: Int, randomQualityOne: Int): Any {
        if (randomChordOne == 0 && randomQualityOne == 0)
            setCMajChords()
        if (randomChordOne == 0 && randomQualityOne == 1)
            setCMinChords()
        if (randomChordOne == 1 && randomQualityOne == 0)
            setCSharpMajChords()
        if (randomChordOne == 1 && randomQualityOne == 1)
            setCSharpMinChords()
        if (randomChordOne == 2 && randomQualityOne == 0)
            setDFlatMajChords()
        if (randomChordOne == 2 && randomQualityOne == 1)
            setDFlatMinChords()
        if (randomChordOne == 3 && randomQualityOne == 0)
            setDMajChords()
        if (randomChordOne == 3 && randomQualityOne == 1)
            setDMinChords()
        if (randomChordOne == 4 && randomQualityOne == 0)
            setDSharpMajChords()
        if (randomChordOne == 4 && randomQualityOne == 1)
            setDSharpMinChords()
        if (randomChordOne == 5 && randomQualityOne == 0)
            setEFlatMajChords()
        if (randomChordOne == 5 && randomQualityOne == 1)
            setEFlatMinChords()
        if (randomChordOne == 6 && randomQualityOne == 0)
            setEMajChords()
        if (randomChordOne == 6 && randomQualityOne == 1)
            setEMinChords()
        if (randomChordOne == 7 && randomQualityOne == 0)
            setFMajChords()
        if (randomChordOne == 7 && randomQualityOne == 1)
            setFMinChords()
        if (randomChordOne == 8 && randomQualityOne == 0)
            setFSharpMajChords()
        if (randomChordOne == 8 && randomQualityOne == 1)
            setFSharpMinChords()
        if (randomChordOne == 9 && randomQualityOne == 0)
            setGFlatMajChords()
        if (randomChordOne == 9 && randomQualityOne == 1)
            setGFlatMinChords()
        if (randomChordOne == 10 && randomQualityOne == 0)
            setGMajChords()
        if (randomChordOne == 10 && randomQualityOne == 1)
            setGMinChords()
        if (randomChordOne == 11 && randomQualityOne == 0)
            setGSharpMajChords()
        if (randomChordOne == 11 && randomQualityOne == 1)
            setGSharpMinChords()
        if (randomChordOne == 12 && randomQualityOne == 0)
            setAFlatMajChords()
        if (randomChordOne == 12 && randomQualityOne == 1)
            setAFlatMinChords()
        if (randomChordOne == 13 && randomQualityOne == 0)
            setAMajChords()
        if (randomChordOne == 13 && randomQualityOne == 1)
            setAMinChords()
        if (randomChordOne == 14 && randomQualityOne == 0)
            setBFlatMajChords()
        if (randomChordOne == 14 && randomQualityOne == 1)
            setBFlatMinChords()
        if (randomChordOne == 15 && randomQualityOne == 0)
            setBMajChords()
        if (randomChordOne == 15 && randomQualityOne == 1)
            setBMinChords()

        return 0
    }

    private fun setBMinChords() {
        val bminChords = listOf(15, 1, 3, 6, 8, 10, 13)
        var selectRandomChordName = Random.nextInt(0, 7)
        setBMinNameAndQuality(selectRandomChordName, bminChords, spnNameTwo, spnQualityTwo)
        selectRandomChordName = Random.nextInt(0, 7)
        setBMinNameAndQuality(selectRandomChordName, bminChords, spnNameThree, spnQualityThree)
        selectRandomChordName = Random.nextInt(0, 7)
        setBMinNameAndQuality(selectRandomChordName, bminChords, spnNameFour, spnQualityFour)
    }

    private fun setBMinNameAndQuality(selectRandomChordName: Int, bminChords: List<Int>, spinnerName: Spinner?, spinnerQuality: Spinner?) {
        spinnerName?.setSelection(bminChords[selectRandomChordName])
        if (selectRandomChordName == 2 || selectRandomChordName == 5 || selectRandomChordName == 6)
            spinnerQuality?.setSelection(0)
        else if (selectRandomChordName == 0 || selectRandomChordName == 3 || selectRandomChordName == 4)
            spinnerQuality?.setSelection(1)
    }

    private fun setBMajChords() {
        val bmajChords = listOf(15, 1, 4, 6, 8, 11, 14)
        var selectRandomChordName = Random.nextInt(0, 7)
        setBMajNameAndQuality(selectRandomChordName, bmajChords, spnNameTwo, spnQualityTwo)
        selectRandomChordName = Random.nextInt(0, 7)
        setBMajNameAndQuality(selectRandomChordName, bmajChords, spnNameThree, spnQualityThree)
        selectRandomChordName = Random.nextInt(0, 7)
        setBMajNameAndQuality(selectRandomChordName, bmajChords, spnNameFour, spnQualityFour)
    }

    private fun setBMajNameAndQuality(selectRandomChordName: Int, bmajChords: List<Int>, spinnerName: Spinner?, spinnerQuality: Spinner?) {
        spinnerName?.setSelection(bmajChords[selectRandomChordName])
        if (selectRandomChordName == 0 || selectRandomChordName == 3 || selectRandomChordName == 4)
            spinnerQuality?.setSelection(0)
        else if (selectRandomChordName == 1 || selectRandomChordName == 2 || selectRandomChordName == 5)
            spinnerQuality?.setSelection(1)
    }

    private fun setBFlatMinChords() {
        val bflatminChords = listOf(14, 0, 2, 5, 7, 9, 12)
        var selectRandomChordName = Random.nextInt(0, 7)
        setBFlatMinNameAndQuality(selectRandomChordName, bflatminChords, spnNameTwo, spnQualityTwo)
        selectRandomChordName = Random.nextInt(0, 7)
        setBFlatMinNameAndQuality(selectRandomChordName, bflatminChords, spnNameThree, spnQualityThree)
        selectRandomChordName = Random.nextInt(0, 7)
        setBFlatMinNameAndQuality(selectRandomChordName, bflatminChords, spnNameFour, spnQualityFour)
    }

    private fun setBFlatMinNameAndQuality(selectRandomChordName: Int, bflatminChords: List<Int>, spinnerName: Spinner?, spinnerQuality: Spinner?) {
        spinnerName?.setSelection(bflatminChords[selectRandomChordName])
        if (selectRandomChordName == 2 || selectRandomChordName == 5 || selectRandomChordName == 6)
            spinnerQuality?.setSelection(0)
        else if (selectRandomChordName == 0 || selectRandomChordName == 3 || selectRandomChordName == 4)
            spinnerQuality?.setSelection(1)
    }

    private fun setBFlatMajChords() {
        val bflatmajChords = listOf(14, 0, 3, 5, 7, 10, 13)
        var selectRandomChordName = Random.nextInt(0, 7)
        setBFlatMajNameAndQuality(selectRandomChordName, bflatmajChords, spnNameTwo, spnQualityTwo)
        selectRandomChordName = Random.nextInt(0, 7)
        setBFlatMajNameAndQuality(selectRandomChordName, bflatmajChords, spnNameThree, spnQualityThree)
        selectRandomChordName = Random.nextInt(0, 7)
        setBFlatMajNameAndQuality(selectRandomChordName, bflatmajChords, spnNameFour, spnQualityFour)
    }

    private fun setBFlatMajNameAndQuality(selectRandomChordName: Int, bflatmaj: List<Int>, spinnerName: Spinner?, spinnerQuality: Spinner?) {
        spinnerName?.setSelection(bflatmaj[selectRandomChordName])
        if (selectRandomChordName == 0 || selectRandomChordName == 3 || selectRandomChordName == 4)
            spinnerQuality?.setSelection(0)
        else if (selectRandomChordName == 1 || selectRandomChordName == 2 || selectRandomChordName == 5)
            spinnerQuality?.setSelection(1)
    }

    private fun setAMinChords() {
        val aminChords = listOf(13, 15, 0, 3, 6, 7, 10)
        var selectRandomChordName = Random.nextInt(0, 7)
        setAMinNameAndQuality(selectRandomChordName, aminChords, spnNameTwo, spnQualityTwo)
        selectRandomChordName = Random.nextInt(0, 7)
        setAMinNameAndQuality(selectRandomChordName, aminChords, spnNameThree, spnQualityThree)
        selectRandomChordName = Random.nextInt(0, 7)
        setAMinNameAndQuality(selectRandomChordName, aminChords, spnNameFour, spnQualityFour)
    }

    private fun setAMinNameAndQuality(selectRandomChordName: Int, aminChords: List<Int>, spinnerName: Spinner?, spinnerQuality: Spinner?) {
        spinnerName?.setSelection(aminChords[selectRandomChordName])
        if (selectRandomChordName == 2 || selectRandomChordName == 5 || selectRandomChordName == 6)
            spinnerQuality?.setSelection(0)
        else if (selectRandomChordName == 0 || selectRandomChordName == 3 || selectRandomChordName == 4)
            spinnerQuality?.setSelection(1)
    }

    private fun setAMajChords() {
        val amajChords = listOf(13, 15, 1, 3, 6, 8, 11)
        var selectRandomChordName = Random.nextInt(0, 7)
        setAMajNameAndQuality(selectRandomChordName, amajChords, spnNameTwo, spnQualityTwo)
        selectRandomChordName = Random.nextInt(0, 7)
        setAMajNameAndQuality(selectRandomChordName, amajChords, spnNameThree, spnQualityThree)
        selectRandomChordName = Random.nextInt(0, 7)
        setAMajNameAndQuality(selectRandomChordName, amajChords, spnNameFour, spnQualityFour)
    }

    private fun setAMajNameAndQuality(selectRandomChordName: Int, amajChords: List<Int>, spinnerName: Spinner?, spinnerQuality: Spinner?) {
        spinnerName?.setSelection(amajChords[selectRandomChordName])
        if (selectRandomChordName == 0 || selectRandomChordName == 3 || selectRandomChordName == 4)
            spinnerQuality?.setSelection(0)
        else if (selectRandomChordName == 1 || selectRandomChordName == 2 || selectRandomChordName == 5)
            spinnerQuality?.setSelection(1)
    }

    private fun setAFlatMinChords() {
        val aflatminChords = listOf(12, 14, 15, 2, 5, 6, 9)
        var selectRandomChordName = Random.nextInt(0, 7)
        setAFlatMinNameAndQuality(selectRandomChordName, aflatminChords, spnNameTwo, spnQualityTwo)
        selectRandomChordName = Random.nextInt(0, 7)
        setAFlatMinNameAndQuality(selectRandomChordName, aflatminChords, spnNameThree, spnQualityThree)
        selectRandomChordName = Random.nextInt(0, 7)
        setAFlatMinNameAndQuality(selectRandomChordName, aflatminChords, spnNameFour, spnQualityFour)
    }

    private fun setAFlatMinNameAndQuality(selectRandomChordName: Int, aflatminChords: List<Int>, spinnerName: Spinner?, spinnerQuality: Spinner?) {
        spinnerName?.setSelection(aflatminChords[selectRandomChordName])
        if (selectRandomChordName == 2 || selectRandomChordName == 5 || selectRandomChordName == 6)
            spinnerQuality?.setSelection(0)
        else if (selectRandomChordName == 0 || selectRandomChordName == 3 || selectRandomChordName == 4)
            spinnerQuality?.setSelection(1)
    }

    private fun setAFlatMajChords() {
        val aflatmajChords = listOf(12, 14, 0, 2, 5, 7, 10)
        var selectRandomChordName = Random.nextInt(0, 7)
        setAFlatMajNameAndQuality(selectRandomChordName, aflatmajChords, spnNameTwo, spnQualityTwo)
        selectRandomChordName = Random.nextInt(0, 7)
        setAFlatMajNameAndQuality(selectRandomChordName, aflatmajChords, spnNameThree, spnQualityThree)
        selectRandomChordName = Random.nextInt(0, 7)
        setAFlatMajNameAndQuality(selectRandomChordName, aflatmajChords, spnNameFour, spnQualityFour)
    }

    private fun setAFlatMajNameAndQuality(selectRandomChordName: Int, aflatmajChords: List<Int>, spinnerName: Spinner?, spinnerQuality: Spinner?) {
        spinnerName?.setSelection(aflatmajChords[selectRandomChordName])
        if (selectRandomChordName == 0 || selectRandomChordName == 3 || selectRandomChordName == 4)
            spinnerQuality?.setSelection(0)
        else if (selectRandomChordName == 1 || selectRandomChordName == 2 || selectRandomChordName == 5)
            spinnerQuality?.setSelection(1)
    }

    private fun setGSharpMinChords() {
        val gsharpminChords = listOf(11, 14, 15, 1, 4, 6, 8)
        var selectRandomChordName = Random.nextInt(0, 7)
        setGSharpMinNameAndQuality(selectRandomChordName, gsharpminChords, spnNameTwo, spnQualityTwo)
        selectRandomChordName = Random.nextInt(0, 7)
        setGSharpMinNameAndQuality(selectRandomChordName, gsharpminChords, spnNameThree, spnQualityThree)
        selectRandomChordName = Random.nextInt(0, 7)
        setGSharpMinNameAndQuality(selectRandomChordName, gsharpminChords, spnNameFour, spnQualityFour)
    }

    private fun setGSharpMinNameAndQuality(selectRandomChordName: Int, gsharpminChords: List<Int>, spinnerName: Spinner?, spinnerQuality: Spinner?) {
        spinnerName?.setSelection(gsharpminChords[selectRandomChordName])
        if (selectRandomChordName == 2 || selectRandomChordName == 5 || selectRandomChordName == 6)
            spinnerQuality?.setSelection(0)
        else if (selectRandomChordName == 0 || selectRandomChordName == 3 || selectRandomChordName == 4)
            spinnerQuality?.setSelection(1)
    }

    private fun setGSharpMajChords() {
        val gsharpmajChords = listOf(11, 14, 0, 1, 4, 7, 10)
        var selectRandomChordName = Random.nextInt(0, 7)
        setGSharpMajNameAndQuality(selectRandomChordName, gsharpmajChords, spnNameTwo, spnQualityTwo)
        selectRandomChordName = Random.nextInt(0, 7)
        setGSharpMajNameAndQuality(selectRandomChordName, gsharpmajChords, spnNameThree, spnQualityThree)
        selectRandomChordName = Random.nextInt(0, 7)
        setGSharpMajNameAndQuality(selectRandomChordName, gsharpmajChords, spnNameFour, spnQualityFour)
    }

    private fun setGSharpMajNameAndQuality(selectRandomChordName: Int, gsharpmajChords: List<Int>, spinnerName: Spinner?, spinnerQuality: Spinner?) {
        spinnerName?.setSelection(gsharpmajChords[selectRandomChordName])
        if (selectRandomChordName == 0 || selectRandomChordName == 3 || selectRandomChordName == 4)
            spinnerQuality?.setSelection(0)
        else if (selectRandomChordName == 1 || selectRandomChordName == 2 || selectRandomChordName == 5)
            spinnerQuality?.setSelection(1)
    }

    private fun setGMinChords() {
        val gminChords = listOf(10, 13, 14, 0, 3, 5, 7)
        var selectRandomChordName = Random.nextInt(0, 7)
        setGMinNameAndQuality(selectRandomChordName, gminChords, spnNameTwo, spnQualityTwo)
        selectRandomChordName = Random.nextInt(0, 7)
        setGMinNameAndQuality(selectRandomChordName, gminChords, spnNameThree, spnQualityThree)
        selectRandomChordName = Random.nextInt(0, 7)
        setGMinNameAndQuality(selectRandomChordName, gminChords, spnNameFour, spnQualityFour)
    }

    private fun setGMinNameAndQuality(selectRandomChordName: Int, gminChords: List<Int>, spinnerName: Spinner?, spinnerQuality: Spinner?) {
        spinnerName?.setSelection(gminChords[selectRandomChordName])
        if (selectRandomChordName == 2 || selectRandomChordName == 5 || selectRandomChordName == 6)
            spinnerQuality?.setSelection(0)
        else if (selectRandomChordName == 0 || selectRandomChordName == 3 || selectRandomChordName == 4)
            spinnerQuality?.setSelection(1)
    }

    private fun setGMajChords() {
        val gmajChords = listOf(10, 13, 15, 0, 3, 6, 8)
        var selectRandomChordName = Random.nextInt(0, 7)
        setGMajNameAndQuality(selectRandomChordName, gmajChords, spnNameTwo, spnQualityTwo)
        selectRandomChordName = Random.nextInt(0, 7)
        setGMajNameAndQuality(selectRandomChordName, gmajChords, spnNameThree, spnQualityThree)
        selectRandomChordName = Random.nextInt(0, 7)
        setGMajNameAndQuality(selectRandomChordName, gmajChords, spnNameFour, spnQualityFour)
    }

    private fun setGMajNameAndQuality(selectRandomChordName: Int, gmajChords: List<Int>, spinnerName: Spinner?, spinnerQuality: Spinner?) {
        spinnerName?.setSelection(gmajChords[selectRandomChordName])
        if (selectRandomChordName == 0 || selectRandomChordName == 3 || selectRandomChordName == 4)
            spinnerQuality?.setSelection(0)
        else if (selectRandomChordName == 1 || selectRandomChordName == 2 || selectRandomChordName == 5)
            spinnerQuality?.setSelection(1)
    }

    private fun setGFlatMinChords() {
        val gflatminChords = listOf(9, 12, 13, 15, 2, 3, 6)
        var selectRandomChordName = Random.nextInt(0, 7)
        setGFlatMinNameAndQuality(selectRandomChordName, gflatminChords, spnNameTwo, spnQualityTwo)
        selectRandomChordName = Random.nextInt(0, 7)
        setGFlatMinNameAndQuality(selectRandomChordName, gflatminChords, spnNameThree, spnQualityThree)
        selectRandomChordName = Random.nextInt(0, 7)
        setGFlatMinNameAndQuality(selectRandomChordName, gflatminChords, spnNameFour, spnQualityFour)
    }

    private fun setGFlatMinNameAndQuality(selectRandomChordName: Int, gflatminChords: List<Int>, spinnerName: Spinner?, spinnerQuality: Spinner?) {
        spinnerName?.setSelection(gflatminChords[selectRandomChordName])
        if (selectRandomChordName == 2 || selectRandomChordName == 5 || selectRandomChordName == 6)
            spinnerQuality?.setSelection(0)
        else if (selectRandomChordName == 0 || selectRandomChordName == 3 || selectRandomChordName == 4)
            spinnerQuality?.setSelection(1)
    }

    private fun setGFlatMajChords() {
        val gflatmajChords = listOf(9, 12, 14, 15, 2, 5, 7)
        var selectRandomChordName = Random.nextInt(0, 7)
        setGFlatMajNameAndQuality(selectRandomChordName, gflatmajChords, spnNameTwo, spnQualityTwo)
        selectRandomChordName = Random.nextInt(0, 7)
        setGFlatMajNameAndQuality(selectRandomChordName, gflatmajChords, spnNameThree, spnQualityThree)
        selectRandomChordName = Random.nextInt(0, 7)
        setGFlatMajNameAndQuality(selectRandomChordName, gflatmajChords, spnNameFour, spnQualityFour)
    }

    private fun setGFlatMajNameAndQuality(selectRandomChordName: Int, gflatmajChords: List<Int>, spinnerName: Spinner?, spinnerQuality: Spinner?) {
        spinnerName?.setSelection(gflatmajChords[selectRandomChordName])
        if (selectRandomChordName == 0 || selectRandomChordName == 3 || selectRandomChordName == 4)
            spinnerQuality?.setSelection(0)
        else if (selectRandomChordName == 1 || selectRandomChordName == 2 || selectRandomChordName == 5)
            spinnerQuality?.setSelection(1)
    }

    private fun setFSharpMinChords() {
        val fsharpminChords = listOf(8, 11, 13, 15, 1, 3, 6)
        var selectRandomChordName = Random.nextInt(0, 7)
        setFSharpMinNameAndQuality(selectRandomChordName, fsharpminChords, spnNameTwo, spnQualityTwo)
        selectRandomChordName = Random.nextInt(0, 7)
        setFSharpMinNameAndQuality(selectRandomChordName, fsharpminChords, spnNameThree, spnQualityThree)
        selectRandomChordName = Random.nextInt(0, 7)
        setFSharpMinNameAndQuality(selectRandomChordName, fsharpminChords, spnNameFour, spnQualityFour)
    }

    private fun setFSharpMinNameAndQuality(selectRandomChordName: Int, fsharpminChords: List<Int>, spinnerName: Spinner?, spinnerQuality: Spinner?) {
        spinnerName?.setSelection(fsharpminChords[selectRandomChordName])
        if (selectRandomChordName == 2 || selectRandomChordName == 5 || selectRandomChordName == 6)
            spinnerQuality?.setSelection(0)
        else if (selectRandomChordName == 0 || selectRandomChordName == 3 || selectRandomChordName == 4)
            spinnerQuality?.setSelection(1)
    }

    private fun setFSharpMajChords() {
        val fsharpmajChords = listOf(8, 11, 14, 15, 1, 4, 7)
        var selectRandomChordName = Random.nextInt(0, 7)
        setFSharpMajNameAndQuality(selectRandomChordName, fsharpmajChords, spnNameTwo, spnQualityTwo)
        selectRandomChordName = Random.nextInt(0, 7)
        setFSharpMajNameAndQuality(selectRandomChordName, fsharpmajChords, spnNameThree, spnQualityThree)
        selectRandomChordName = Random.nextInt(0, 7)
        setFSharpMajNameAndQuality(selectRandomChordName, fsharpmajChords, spnNameFour, spnQualityFour)
    }

    private fun setFSharpMajNameAndQuality(selectRandomChordName: Int, fsharpmajChords: List<Int>, spinnerName: Spinner?, spinnerQuality: Spinner?) {
        spinnerName?.setSelection(fsharpmajChords[selectRandomChordName])
        if (selectRandomChordName == 0 || selectRandomChordName == 3 || selectRandomChordName == 4)
            spinnerQuality?.setSelection(0)
        else if (selectRandomChordName == 1 || selectRandomChordName == 2 || selectRandomChordName == 5)
            spinnerQuality?.setSelection(1)
    }

    private fun setFMinChords() {
        val fminChords = listOf(7, 10, 12, 14, 0, 2, 5)
        var selectRandomChordName = Random.nextInt(0, 7)
        setFMinNameAndQuality(selectRandomChordName, fminChords, spnNameTwo, spnQualityTwo)
        selectRandomChordName = Random.nextInt(0, 7)
        setFMinNameAndQuality(selectRandomChordName, fminChords, spnNameThree, spnQualityThree)
        selectRandomChordName = Random.nextInt(0, 7)
        setFMinNameAndQuality(selectRandomChordName, fminChords, spnNameFour, spnQualityFour)
    }

    private fun setFMinNameAndQuality(selectRandomChordName: Int, fminChords: List<Int>, spinnerName: Spinner?, spinnerQuality: Spinner?) {
        spinnerName?.setSelection(fminChords[selectRandomChordName])
        if (selectRandomChordName == 2 || selectRandomChordName == 5 || selectRandomChordName == 6)
            spinnerQuality?.setSelection(0)
        else if (selectRandomChordName == 0 || selectRandomChordName == 3 || selectRandomChordName == 4)
            spinnerQuality?.setSelection(1)
    }

    private fun setFMajChords() {
        val fmajChords = listOf(7, 10, 13, 14, 0, 3, 6)
        var selectRandomChordName = Random.nextInt(0, 7)
        setFMajNameAndQuality(selectRandomChordName, fmajChords, spnNameTwo, spnQualityTwo)
        selectRandomChordName = Random.nextInt(0, 7)
        setFMajNameAndQuality(selectRandomChordName, fmajChords, spnNameThree, spnQualityThree)
        selectRandomChordName = Random.nextInt(0, 7)
        setFMajNameAndQuality(selectRandomChordName, fmajChords, spnNameFour, spnQualityFour)
    }

    private fun setFMajNameAndQuality(selectRandomChordName: Int, fmajChords: List<Int>, spinnerName: Spinner?, spinnerQuality: Spinner?) {
        spinnerName?.setSelection(fmajChords[selectRandomChordName])
        if (selectRandomChordName == 0 || selectRandomChordName == 3 || selectRandomChordName == 4)
            spinnerQuality?.setSelection(0)
        else if (selectRandomChordName == 1 || selectRandomChordName == 2 || selectRandomChordName == 5)
            spinnerQuality?.setSelection(1)
    }

    private fun setEMinChords() {
        val eminChords = listOf(6, 8, 10, 13, 15, 0, 3)
        var selectRandomChordName = Random.nextInt(0, 7)
        setEMinNameAndQuality(selectRandomChordName, eminChords, spnNameTwo, spnQualityTwo)
        selectRandomChordName = Random.nextInt(0, 7)
        setEMinNameAndQuality(selectRandomChordName, eminChords, spnNameThree, spnQualityThree)
        selectRandomChordName = Random.nextInt(0, 7)
        setEMinNameAndQuality(selectRandomChordName, eminChords, spnNameFour, spnQualityFour)
    }

    private fun setEMinNameAndQuality(selectRandomChordName: Int, eminChords: List<Int>, spinnerName: Spinner?, spinnerQuality: Spinner?) {
        spinnerName?.setSelection(eminChords[selectRandomChordName])
        if (selectRandomChordName == 2 || selectRandomChordName == 5 || selectRandomChordName == 6)
            spinnerQuality?.setSelection(0)
        else if (selectRandomChordName == 0 || selectRandomChordName == 3 || selectRandomChordName == 4)
            spinnerQuality?.setSelection(1)
    }

    private fun setEMajChords() {
        val emajChords = listOf(6, 8, 11, 13, 15, 1, 4)
        var selectRandomChordName = Random.nextInt(0, 7)
        setEMajNameAndQuality(selectRandomChordName, emajChords, spnNameTwo, spnQualityTwo)
        selectRandomChordName = Random.nextInt(0, 7)
        setEMajNameAndQuality(selectRandomChordName, emajChords, spnNameThree, spnQualityThree)
        selectRandomChordName = Random.nextInt(0, 7)
        setEMajNameAndQuality(selectRandomChordName, emajChords, spnNameFour, spnQualityFour)
    }

    private fun setEMajNameAndQuality(selectRandomChordName: Int, emajChords: List<Int>, spinnerName: Spinner?, spinnerQuality: Spinner?) {
        spinnerName?.setSelection(emajChords[selectRandomChordName])
        if (selectRandomChordName == 0 || selectRandomChordName == 3 || selectRandomChordName == 4)
            spinnerQuality?.setSelection(0)
        else if (selectRandomChordName == 1 || selectRandomChordName == 2 || selectRandomChordName == 5)
            spinnerQuality?.setSelection(1)
    }

    private fun setEFlatMinChords() {
        val eflatminChords = listOf(5, 7, 9, 12, 14, 15, 2)
        var selectRandomChordName = Random.nextInt(0, 7)
        setEFlatMinNameAndQuality(selectRandomChordName, eflatminChords, spnNameTwo, spnQualityTwo)
        selectRandomChordName = Random.nextInt(0, 7)
        setEFlatMinNameAndQuality(selectRandomChordName, eflatminChords, spnNameThree, spnQualityThree)
        selectRandomChordName = Random.nextInt(0, 7)
        setEFlatMinNameAndQuality(selectRandomChordName, eflatminChords, spnNameFour, spnQualityFour)
    }

    private fun setEFlatMinNameAndQuality(selectRandomChordName: Int, eflatminChords: List<Int>, spinnerName: Spinner?, spinnerQuality: Spinner?) {
        spinnerName?.setSelection(eflatminChords[selectRandomChordName])
        if (selectRandomChordName == 2 || selectRandomChordName == 5 || selectRandomChordName == 6)
            spinnerQuality?.setSelection(0)
        else if (selectRandomChordName == 0 || selectRandomChordName == 3 || selectRandomChordName == 4)
            spinnerQuality?.setSelection(1)
    }

    private fun setEFlatMajChords() {
        val eflatmajChords = listOf(5, 7, 10, 12, 14, 0, 3)
        var selectRandomChordName = Random.nextInt(0, 7)
        setEFlatMajNameAndQuality(selectRandomChordName, eflatmajChords, spnNameTwo, spnQualityTwo)
        selectRandomChordName = Random.nextInt(0, 7)
        setEFlatMajNameAndQuality(selectRandomChordName, eflatmajChords, spnNameThree, spnQualityThree)
        selectRandomChordName = Random.nextInt(0, 7)
        setEFlatMajNameAndQuality(selectRandomChordName, eflatmajChords, spnNameFour, spnQualityFour)
    }

    private fun setEFlatMajNameAndQuality(selectRandomChordName: Int, eflatmajChords: List<Int>, spinnerName: Spinner?, spinnerQuality: Spinner?) {
        spinnerName?.setSelection(eflatmajChords[selectRandomChordName])
        if (selectRandomChordName == 0 || selectRandomChordName == 3 || selectRandomChordName == 4)
            spinnerQuality?.setSelection(0)
        else if (selectRandomChordName == 1 || selectRandomChordName == 2 || selectRandomChordName == 5)
            spinnerQuality?.setSelection(1)
    }

    private fun setDSharpMinChords() {
        val dsharpminChords = listOf(4, 7, 8, 11, 14, 15, 1)
        var selectRandomChordName = Random.nextInt(0, 7)
        setDSharpMinNameAndQuality(selectRandomChordName, dsharpminChords, spnNameTwo, spnQualityTwo)
        selectRandomChordName = Random.nextInt(0, 7)
        setDSharpMinNameAndQuality(selectRandomChordName, dsharpminChords, spnNameThree, spnQualityThree)
        selectRandomChordName = Random.nextInt(0, 7)
        setDSharpMinNameAndQuality(selectRandomChordName, dsharpminChords, spnNameFour, spnQualityFour)
    }

    private fun setDSharpMinNameAndQuality(selectRandomChordName: Int, dsharpminChords: List<Int>, spinnerName: Spinner?, spinnerQuality: Spinner?) {
        spinnerName?.setSelection(dsharpminChords[selectRandomChordName])
        if (selectRandomChordName == 2 || selectRandomChordName == 5 || selectRandomChordName == 6)
            spinnerQuality?.setSelection(0)
        else if (selectRandomChordName == 0 || selectRandomChordName == 3 || selectRandomChordName == 4)
            spinnerQuality?.setSelection(1)
    }

    private fun setDSharpMajChords() {
        val dsharpmajChords = listOf(4, 7, 10, 11, 14, 0, 3)
        var selectRandomChordName = Random.nextInt(0, 7)
        setDSharpMajNameAndQuality(selectRandomChordName, dsharpmajChords, spnNameTwo, spnQualityTwo)
        selectRandomChordName = Random.nextInt(0, 7)
        setDSharpMajNameAndQuality(selectRandomChordName, dsharpmajChords, spnNameThree, spnQualityThree)
        selectRandomChordName = Random.nextInt(0, 7)
        setDSharpMajNameAndQuality(selectRandomChordName, dsharpmajChords, spnNameFour, spnQualityFour)
    }

    private fun setDSharpMajNameAndQuality(selectRandomChordName: Int, dsharpmajChords: List<Int>, spinnerName: Spinner?, spinnerQuality: Spinner?) {
        spinnerName?.setSelection(dsharpmajChords[selectRandomChordName])
        if (selectRandomChordName == 0 || selectRandomChordName == 3 || selectRandomChordName == 4)
            spinnerQuality?.setSelection(0)
        else if (selectRandomChordName == 1 || selectRandomChordName == 2 || selectRandomChordName == 5)
            spinnerQuality?.setSelection(1)
    }

    private fun setDMinChords() {
        val dminChords = listOf(3, 6, 7, 10, 13, 14, 0)
        var selectRandomChordName = Random.nextInt(0, 7)
        setDMinNameAndQuality(selectRandomChordName, dminChords, spnNameTwo, spnQualityTwo)
        selectRandomChordName = Random.nextInt(0, 7)
        setDMinNameAndQuality(selectRandomChordName, dminChords, spnNameThree, spnQualityThree)
        selectRandomChordName = Random.nextInt(0, 7)
        setDMinNameAndQuality(selectRandomChordName, dminChords, spnNameFour, spnQualityFour)
    }

    private fun setDMinNameAndQuality(selectRandomChordName: Int, dminChords: List<Int>, spinnerName: Spinner?, spinnerQuality: Spinner?) {
        spinnerName?.setSelection(dminChords[selectRandomChordName])
        if (selectRandomChordName == 2 || selectRandomChordName == 5 || selectRandomChordName == 6)
            spinnerQuality?.setSelection(0)
        else if (selectRandomChordName == 0 || selectRandomChordName == 3 || selectRandomChordName == 4)
            spinnerQuality?.setSelection(1)
    }

    private fun setDMajChords() {
        val dmajChords = listOf(3, 6, 8, 10, 13, 15, 1)
        var selectRandomChordName = Random.nextInt(0, 7)
        setDMajNameAndQuality(selectRandomChordName, dmajChords, spnNameTwo, spnQualityTwo)
        selectRandomChordName = Random.nextInt(0, 7)
        setDMajNameAndQuality(selectRandomChordName, dmajChords, spnNameThree, spnQualityThree)
        selectRandomChordName = Random.nextInt(0, 7)
        setDMajNameAndQuality(selectRandomChordName, dmajChords, spnNameFour, spnQualityFour)
    }

    private fun setDMajNameAndQuality(selectRandomChordName: Int, dmajChords: List<Int>, spinnerName: Spinner?, spinnerQuality: Spinner?) {
        spinnerName?.setSelection(dmajChords[selectRandomChordName])
        if (selectRandomChordName == 0 || selectRandomChordName == 3 || selectRandomChordName == 4)
            spinnerQuality?.setSelection(0)
        else if (selectRandomChordName == 1 || selectRandomChordName == 2 || selectRandomChordName == 5)
            spinnerQuality?.setSelection(1)
    }

    private fun setDFlatMinChords() {
        val dflatminChords = listOf(2, 5, 6, 9, 12, 13, 15)
        var selectRandomChordName = Random.nextInt(0, 7)
        setDFlatMinNameAndQuality(selectRandomChordName, dflatminChords, spnNameTwo, spnQualityTwo)
        selectRandomChordName = Random.nextInt(0, 7)
        setDFlatMinNameAndQuality(selectRandomChordName, dflatminChords, spnNameThree, spnQualityThree)
        selectRandomChordName = Random.nextInt(0, 7)
        setDFlatMinNameAndQuality(selectRandomChordName, dflatminChords, spnNameFour, spnQualityFour)
    }

    private fun setDFlatMinNameAndQuality(selectRandomChordName: Int, dflatminChords: List<Int>, spinnerName: Spinner?, spinnerQuality: Spinner?) {
        spinnerName?.setSelection(dflatminChords[selectRandomChordName])
        if (selectRandomChordName == 2 || selectRandomChordName == 5 || selectRandomChordName == 6)
            spinnerQuality?.setSelection(0)
        else if (selectRandomChordName == 0 || selectRandomChordName == 3 || selectRandomChordName == 4)
            spinnerQuality?.setSelection(1)
    }

    private fun setDFlatMajChords() {
        val dflatmajChords = listOf(2, 5, 7, 9, 12, 14, 0)
        var selectRandomChordName = Random.nextInt(0, 7)
        setDFlatMajNameAndQuality(selectRandomChordName, dflatmajChords, spnNameTwo, spnQualityTwo)
        selectRandomChordName = Random.nextInt(0, 7)
        setDFlatMajNameAndQuality(selectRandomChordName, dflatmajChords, spnNameThree, spnQualityThree)
        selectRandomChordName = Random.nextInt(0, 7)
        setDFlatMajNameAndQuality(selectRandomChordName, dflatmajChords, spnNameFour, spnQualityFour)
    }

    private fun setDFlatMajNameAndQuality(selectRandomChordName: Int, dflatmajChords: List<Int>, spinnerName: Spinner?, spinnerQuality: Spinner?) {
        spinnerName?.setSelection(dflatmajChords[selectRandomChordName])
        if (selectRandomChordName == 0 || selectRandomChordName == 3 || selectRandomChordName == 4)
            spinnerQuality?.setSelection(0)
        else if (selectRandomChordName == 1 || selectRandomChordName == 2 || selectRandomChordName == 5)
            spinnerQuality?.setSelection(1)
    }

    private fun setCSharpMinChords() {
        val csharpminChords = listOf(1, 4, 6, 8, 11, 13, 15)
        var selectRandomChordName = Random.nextInt(0, 7)
        setCSharpMinNameAndQuality(selectRandomChordName, csharpminChords, spnNameTwo, spnQualityTwo)
        selectRandomChordName = Random.nextInt(0, 7)
        setCSharpMinNameAndQuality(selectRandomChordName, csharpminChords, spnNameThree, spnQualityThree)
        selectRandomChordName = Random.nextInt(0, 7)
        setCSharpMinNameAndQuality(selectRandomChordName, csharpminChords, spnNameFour, spnQualityFour)
    }

    private fun setCSharpMinNameAndQuality(selectRandomChordName: Int, csharpminChords: List<Int>, spinnerName: Spinner?, spinnerQuality: Spinner?) {
        spinnerName?.setSelection(csharpminChords[selectRandomChordName])
        if (selectRandomChordName == 2 || selectRandomChordName == 5 || selectRandomChordName == 6)
            spinnerQuality?.setSelection(0)
        else if (selectRandomChordName == 0 || selectRandomChordName == 3 || selectRandomChordName == 4)
            spinnerQuality?.setSelection(1)
    }

    private fun setCSharpMajChords() {
        val csharpmajChords = listOf(1, 4, 7, 8, 11, 14, 0)
        var selectRandomChordName = Random.nextInt(0, 7)
        setCSharpMajNameAndQuality(selectRandomChordName, csharpmajChords, spnNameTwo, spnQualityTwo)
        selectRandomChordName = Random.nextInt(0, 7)
        setCSharpMajNameAndQuality(selectRandomChordName, csharpmajChords, spnNameThree, spnQualityThree)
        selectRandomChordName = Random.nextInt(0, 7)
        setCSharpMajNameAndQuality(selectRandomChordName, csharpmajChords, spnNameFour, spnQualityFour)
    }

    private fun setCSharpMajNameAndQuality(selectRandomChordName: Int, csharpmajChords: List<Int>, spinnerName: Spinner?, spinnerQuality: Spinner?) {
        spinnerName?.setSelection(csharpmajChords[selectRandomChordName])
        if (selectRandomChordName == 0 || selectRandomChordName == 3 || selectRandomChordName == 4)
            spinnerQuality?.setSelection(0)
        else if (selectRandomChordName == 1 || selectRandomChordName == 2 || selectRandomChordName == 5)
            spinnerQuality?.setSelection(1)
    }

    private fun setCMinChords() {
        val cminChords = listOf(0, 3, 5, 7, 10, 12, 14)
        var selectRandomChordName = Random.nextInt(0, 7)
        setCMinNameAndQuality(selectRandomChordName, cminChords, spnNameTwo, spnQualityTwo)
        selectRandomChordName = Random.nextInt(0, 7)
        setCMinNameAndQuality(selectRandomChordName, cminChords, spnNameThree, spnQualityThree)
        selectRandomChordName = Random.nextInt(0, 7)
        setCMinNameAndQuality(selectRandomChordName, cminChords, spnNameFour, spnQualityFour)
    }

    private fun setCMinNameAndQuality(selectRandomChordName: Int, cminChords: List<Int>, spinnerName: Spinner?, spinnerQuality: Spinner?) {
        spinnerName?.setSelection(cminChords[selectRandomChordName])
        if (selectRandomChordName == 2 || selectRandomChordName == 5 || selectRandomChordName == 6)
            spinnerQuality?.setSelection(0)
        else if (selectRandomChordName == 0 || selectRandomChordName == 3 || selectRandomChordName == 4)
            spinnerQuality?.setSelection(1)
    }

    private fun setCMajChords() {
        val cmajChords = listOf(0, 3, 6, 7, 10, 13, 15)
        var selectRandomChordName = Random.nextInt(0, 7)
        setCMajNameAndQuality(selectRandomChordName, cmajChords, spnNameTwo, spnQualityTwo)
        selectRandomChordName = Random.nextInt(0, 7)
        setCMajNameAndQuality(selectRandomChordName, cmajChords, spnNameThree, spnQualityThree)
        selectRandomChordName = Random.nextInt(0, 7)
        setCMajNameAndQuality(selectRandomChordName, cmajChords, spnNameFour, spnQualityFour)
    }

    private fun setCMajNameAndQuality(selectRandomChordName: Int, cmajChords: List<Int>, spinnerName: Spinner?, spinnerQuality: Spinner?) {
        spinnerName?.setSelection(cmajChords[selectRandomChordName])
        if (selectRandomChordName == 0 || selectRandomChordName == 3 || selectRandomChordName == 4)
            spinnerQuality?.setSelection(0)
        else if (selectRandomChordName == 1 || selectRandomChordName == 2 || selectRandomChordName == 5)
            spinnerQuality?.setSelection(1)
    }

    private fun getPossibleChords(spinnerQuality: Spinner?, spinnerExtension: Spinner?): List<Int> {
        if (spinnerQuality?.selectedItem.toString().equals("Major")) {
            return majChordsPiano(spinnerExtension)
        } else {
            return minChordsPiano(spinnerExtension)
        }
    }

    private fun minChordsPiano(spinnerExtension: Spinner?): List<Int> {
        val minPiano: List<Int>
        if (spinnerExtension?.selectedItem!!.equals("None")) {
            minPiano = listOf(
                R.raw.cmin_piano, R.raw.csharpmin_piano, R.raw.csharpmin_piano, R.raw.dmin_piano, R.raw.dsharpmin_piano,
                R.raw.dsharpmin_piano, R.raw.emin_piano, R.raw.fmin_piano, R.raw.fsharpmin_piano, R.raw.fsharpmin_piano,
                R.raw.gmin_piano, R.raw.gsharpmin_piano, R.raw.gsharpmin_piano, R.raw.amin_piano, R.raw.bflatmin_piano, R.raw.bmin_piano
            )
            return minPiano
        } else if (spinnerExtension.selectedItem!!.equals("7")) {
            minPiano = listOf(
                R.raw.cminseven_piano, R.raw.csharpminseven_piano, R.raw.csharpminseven_piano, R.raw.dminseven_piano, R.raw.dsharpminseven_piano,
                R.raw.dsharpminseven_piano, R.raw.eminseven_piano, R.raw.fminseven_piano, R.raw.fsharpminseven_piano, R.raw.fsharpminseven_piano,
                R.raw.gminseven_piano, R.raw.gsharpminseven_piano, R.raw.gsharpminseven_piano, R.raw.aminseven_piano, R.raw.bflatminseven_piano, R.raw.bminseven_piano
            )
            return minPiano
        } else if (spinnerExtension?.selectedItem!!.equals("9")) {
            minPiano = listOf(
                R.raw.cminnine_piano, R.raw.csharpminnine_piano, R.raw.csharpminnine_piano, R.raw.dminnine_piano, R.raw.dsharpminnine_piano,
                R.raw.dsharpminnine_piano, R.raw.eminnine_piano, R.raw.fminnine_piano, R.raw.fsharpminnine_piano, R.raw.fsharpminnine_piano,
                R.raw.gminnine_piano, R.raw.gsharpminnine_piano, R.raw.gsharpminnine_piano, R.raw.aminnine_piano, R.raw.bflatminnine_piano, R.raw.bminnine_piano
            )
            return minPiano
        } else {
            minPiano = listOf(
                R.raw.cmineleven_piano, R.raw.csharpmineleven_piano, R.raw.csharpmineleven_piano, R.raw.dmineleven_piano, R.raw.dsharpmineleventh_piano,
                R.raw.dsharpmineleventh_piano, R.raw.emineleven_piano, R.raw.fmineleven_piano, R.raw.fsharpmineleven_piano, R.raw.fsharpmineleven_piano,
                R.raw.gmineleven_piano, R.raw.gsharpmineleven_piano, R.raw.gsharpmineleven_piano, R.raw.amineleven_piano, R.raw.bflatmineleven_piano, R.raw.bmineleven_piano
            )
            return minPiano
        }
    }

    private fun majChordsPiano(spinnerExtension: Spinner?): List<Int> {
        val majPiano: List<Int>
        if (spinnerExtension?.selectedItem!!.equals("None")) {
            majPiano = listOf(
                R.raw.cmaj_piano, R.raw.csharpmaj_piano, R.raw.csharpmaj_piano, R.raw.dmaj_piano, R.raw.dsharpmaj_piano,
                R.raw.dsharpmaj_piano, R.raw.emaj_piano, R.raw.fmaj_piano, R.raw.fsharpmaj_piano, R.raw.fsharpmaj_piano,
                R.raw.gmaj_piano, R.raw.gsharpmaj_piano, R.raw.gsharpmaj_piano, R.raw.amaj_piano, R.raw.bflatmaj_piano, R.raw.bmaj_piano
            )
            return majPiano
        } else if (spinnerExtension?.selectedItem!!.equals("7")) {
             majPiano = listOf(
                R.raw.cmajseven_piano, R.raw.csharpmajseven_piano, R.raw.csharpmajseven_piano, R.raw.dmajseven_piano, R.raw.dsharpmajseven_pian, R.raw.dsharpmajseven_pian,
                R.raw.emajseven_piano, R.raw.fmajseven_piano, R.raw.fsharpmajseven_piano, R.raw.fsharpmajseven_piano, R.raw.gmajseven_piano, R.raw.gsharpmajseven_piano,
                R.raw.gsharpmajseven_piano, R.raw.amajseven_piano, R.raw.bflatmajseven_piano, R.raw.bmajseven_piano
            )
            return majPiano
        } else if (spinnerExtension?.selectedItem!!.equals("9")) {
            majPiano = listOf(
                R.raw.cmajnine_piano, R.raw.csharpmajnine_piano, R.raw.csharpmajnine_piano, R.raw.dmajnine_piano, R.raw.dsharpmajnine_piano, R.raw.dsharpmajnine_piano,
                R.raw.emajnine_piano, R.raw.fmajnine_piano, R.raw.fsharpmajnine_piano, R.raw.fsharpmajnine_piano, R.raw.gmajnine_piano, R.raw.gsharpmajnine_piano,
                R.raw.gsharpmajnine_piano, R.raw.amajnine_piano, R.raw.bflatmajnine_piano, R.raw.bmajnine_piano
            )
            return majPiano
        } else {
            majPiano = listOf(
                R.raw.cmajeleven_piano, R.raw.csharpeleven_piano, R.raw.csharpeleven_piano, R.raw.dmajeleven_piano, R.raw.dsharpmajeleven_piano,
                R.raw.dsharpmajeleven_piano, R.raw.emajeleven_piano, R.raw.fmajeleven_piano, R.raw.fsharpmajeleven_piano, R.raw.fsharpmajeleven_piano,
                R.raw.gmajeleven_piano, R.raw.gsharpmajeleven_piano, R.raw.gsharpmajeleven_piano, R.raw.amajeleven_piano, R.raw.bflatmajeleven_piano, R.raw.bmajeleven_piano
            )
            return majPiano
        }
    }
}
