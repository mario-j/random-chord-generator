package hu.ait.randomchordgenerator

import android.media.MediaPlayer
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MotionEvent
import android.widget.Button
import kotlinx.android.synthetic.main.keyboard.*
import android.annotation.SuppressLint
import android.view.View


class Keyboard : AppCompatActivity() {

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.keyboard)
        getCKey()

        getCSharpKey()

        getDKey()

        getDSharpKey()

        getEKey()

        getFKey()

        getFSharpKey()

        getGKey()

        getGSharpKey()

        getAKey()

        getBFlatKey()

        getBKey()

        getC8Key()
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun getC8Key() {
        val C8 = findViewById(R.id.w8) as Button
        C8.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(arg0: View, arg1: MotionEvent): Boolean {
                val C8key: MediaPlayer? = getInstrumentC8()
                if (arg1.action == MotionEvent.ACTION_DOWN) {
                    C8key?.start()
                }
                if (arg1.action == MotionEvent.ACTION_UP) {
                    C8key?.stop()
                    C8key?.release()
                }
                return false
            }
        })
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun getBKey() {
        val B = findViewById(R.id.w7) as Button
        B.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(arg0: View, arg1: MotionEvent): Boolean {
                val Bkey: MediaPlayer? = getInstrumentB()
                if (arg1.action == MotionEvent.ACTION_DOWN) {
                    Bkey?.start()
                }
                if (arg1.action == MotionEvent.ACTION_UP) {
                    Bkey?.stop()
                    Bkey?.release()
                }
                return false
            }
        })
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun getBFlatKey() {
        val BFlat = findViewById(R.id.b5) as Button
        BFlat.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(arg0: View, arg1: MotionEvent): Boolean {
                val BFlatkey: MediaPlayer? = getInstrumentBFlat()
                if (arg1.action == MotionEvent.ACTION_DOWN) {
                    BFlatkey?.start()
                }
                if (arg1.action == MotionEvent.ACTION_UP) {
                    BFlatkey?.stop()
                    BFlatkey?.release()
                }
                return false
            }
        })
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun getAKey() {
        val A = findViewById(R.id.w6) as Button
        A.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(arg0: View, arg1: MotionEvent): Boolean {
                val Akey: MediaPlayer? = getInstrumentA()
                if (arg1.action == MotionEvent.ACTION_DOWN) {
                    Akey?.start()
                }
                if (arg1.action == MotionEvent.ACTION_UP) {
                    Akey?.stop()
                    Akey?.release()
                }
                return false
            }
        })
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun getGSharpKey() {
        val GSharp = findViewById(R.id.b4) as Button
        GSharp.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(arg0: View, arg1: MotionEvent): Boolean {
                val GSharpkey: MediaPlayer? = getInstrumentGSharp()
                if (arg1.action == MotionEvent.ACTION_DOWN) {
                    GSharpkey?.start()
                }
                if (arg1.action == MotionEvent.ACTION_UP) {
                    GSharpkey?.stop()
                    GSharpkey?.release()
                }
                return false
            }
        })
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun getGKey() {
        val G = findViewById(R.id.w5) as Button
        G.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(arg0: View, arg1: MotionEvent): Boolean {
                val Gkey: MediaPlayer? = getInstrumentG()
                if (arg1.action == MotionEvent.ACTION_DOWN) {
                    Gkey?.start()
                }
                if (arg1.action == MotionEvent.ACTION_UP) {
                    Gkey?.stop()
                    Gkey?.release()
                }
                return false
            }
        })
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun getFSharpKey() {
        val FSharp = findViewById(R.id.b3) as Button
        FSharp.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(arg0: View, arg1: MotionEvent): Boolean {
                val FSharpkey: MediaPlayer? = getInstrumentFSharp()
                if (arg1.action == MotionEvent.ACTION_DOWN) {
                    FSharpkey?.start()
                }
                if (arg1.action == MotionEvent.ACTION_UP) {
                    FSharpkey?.stop()
                    FSharpkey?.release()
                }
                return false
            }
        })
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun getFKey() {
        val F = findViewById(R.id.w4) as Button
        F.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(arg0: View, arg1: MotionEvent): Boolean {
                val Fkey: MediaPlayer? = getInstrumentF()
                if (arg1.action == MotionEvent.ACTION_DOWN) {
                    Fkey?.start()
                }
                if (arg1.action == MotionEvent.ACTION_UP) {
                    Fkey?.stop()
                    Fkey?.release()
                }
                return false
            }
        })
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun getEKey() {
        val E = findViewById(R.id.w3) as Button
        E.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(arg0: View, arg1: MotionEvent): Boolean {
                val Ekey: MediaPlayer? = getInstrumentE()
                if (arg1.action == MotionEvent.ACTION_DOWN) {
                    Ekey?.start()
                }
                if (arg1.action == MotionEvent.ACTION_UP) {
                    Ekey?.stop()
                    Ekey?.release()
                }
                return false
            }
        })
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun getDSharpKey() {
        val DSharp = findViewById(R.id.b2) as Button
        DSharp.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(arg0: View, arg1: MotionEvent): Boolean {
                val DSharpkey: MediaPlayer? = getInstrumentDSharp()
                if (arg1.action == MotionEvent.ACTION_DOWN) {
                    DSharpkey?.start()
                }
                if (arg1.action == MotionEvent.ACTION_UP) {
                    DSharpkey?.stop()
                    DSharpkey?.release()
                }
                return false
            }
        })
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun getDKey() {
        val D = findViewById(R.id.w2) as Button
        D.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(arg0: View, arg1: MotionEvent): Boolean {
                val Dkey: MediaPlayer? = getInstrumentD()
                if (arg1.action == MotionEvent.ACTION_DOWN) {
                    Dkey?.start()
                }
                if (arg1.action == MotionEvent.ACTION_UP) {
                    Dkey?.stop()
                    Dkey?.release()
                }
                return false
            }
        })
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun getCSharpKey() {
        val CSharp = findViewById(R.id.b1) as Button
        CSharp.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(arg0: View, arg1: MotionEvent): Boolean {
                val CSharpkey: MediaPlayer? = getInstrumentCSharp()
                if (arg1.action == MotionEvent.ACTION_DOWN) {
                    CSharpkey?.start()
                }
                if (arg1.action == MotionEvent.ACTION_UP) {
                    CSharpkey?.stop()
                    CSharpkey?.release()
                }
                return false
            }
        })
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun getCKey() {
        val C = findViewById(R.id.w1) as Button
        C.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(arg0: View, arg1: MotionEvent): Boolean {
                val Ckey: MediaPlayer? = getInstrumentC()
                if (arg1.action == MotionEvent.ACTION_DOWN) {
                    Ckey?.start()
                }
                if (arg1.action == MotionEvent.ACTION_UP) {
                    Ckey?.stop()
                    Ckey?.release()
                }

                return false
            }
        })
    }

    private fun getInstrumentC(): MediaPlayer {
        var key: MediaPlayer? = null
        if (spnInstruments.selectedItem.equals("Electric Piano")) {
            key = MediaPlayer.create(applicationContext, R.raw.c_piano)
            return key
        } else if (spnInstruments.selectedItem.equals("Banjo")) {
            key = MediaPlayer.create(applicationContext, R.raw.c_banjo)
            return key
        } else if (spnInstruments.selectedItem.equals("Vibraphone")) {
            key = MediaPlayer.create(applicationContext, R.raw.c_vib)
            return key
        } else
            return MediaPlayer()
    }

    private fun getInstrumentCSharp(): MediaPlayer {
        var key: MediaPlayer? = null
        if (spnInstruments.selectedItem == "Electric Piano") {
            key = MediaPlayer.create(applicationContext, R.raw.csharp_piano)
            return key
        } else if (spnInstruments.selectedItem == "Banjo") {
            key = MediaPlayer.create(applicationContext, R.raw.csharp_banjo)
            return key
        } else if (spnInstruments.selectedItem == "Vibraphone") {
            key = MediaPlayer.create(applicationContext, R.raw.csharp_vib)
            return key
        } else
            return MediaPlayer()
    }

    private fun getInstrumentD(): MediaPlayer {
        var key: MediaPlayer? = null
        if (spnInstruments.selectedItem == "Electric Piano") {
            key = MediaPlayer.create(applicationContext, R.raw.d_piano)
            return key
        } else if (spnInstruments.selectedItem == "Banjo") {
            key = MediaPlayer.create(applicationContext, R.raw.d_banjo)
            return key
        } else if (spnInstruments.selectedItem == "Vibraphone") {
            key = MediaPlayer.create(applicationContext, R.raw.d_vib)
            return key
        } else
            return MediaPlayer()
    }

    private fun getInstrumentDSharp(): MediaPlayer {
        var key: MediaPlayer? = null
        if (spnInstruments.selectedItem == "Electric Piano") {
            key = MediaPlayer.create(applicationContext, R.raw.dsharp_piano)
            return key
        } else if (spnInstruments.selectedItem == "Banjo") {
            key = MediaPlayer.create(applicationContext, R.raw.dsharp_banjo)
            return key
        } else if (spnInstruments.selectedItem == "Vibraphone") {
            key = MediaPlayer.create(applicationContext, R.raw.dsharp_vib)
            return key
        } else
            return MediaPlayer()
    }

    private fun getInstrumentE(): MediaPlayer {
        var key: MediaPlayer? = null
        if (spnInstruments.selectedItem == "Electric Piano") {
            key = MediaPlayer.create(applicationContext, R.raw.e_piano)
            return key
        } else if (spnInstruments.selectedItem == "Banjo") {
            key = MediaPlayer.create(applicationContext, R.raw.e_banjo)
            return key
        } else if (spnInstruments.selectedItem == "Vibraphone") {
            key = MediaPlayer.create(applicationContext, R.raw.e_vib)
            return key
        } else
            return MediaPlayer()
    }

    private fun getInstrumentF(): MediaPlayer {
        var key: MediaPlayer? = null
        if (spnInstruments.selectedItem == "Electric Piano") {
            key = MediaPlayer.create(applicationContext, R.raw.f_piano)
            return key
        } else if (spnInstruments.selectedItem == "Banjo") {
            key = MediaPlayer.create(applicationContext, R.raw.f_banjo)
            return key
        } else if (spnInstruments.selectedItem == "Vibraphone") {
            key = MediaPlayer.create(applicationContext, R.raw.f_vib)
            return key
        } else
            return MediaPlayer()
    }

    private fun getInstrumentFSharp(): MediaPlayer {
        var key: MediaPlayer? = null
        if (spnInstruments.selectedItem == "Electric Piano") {
            key = MediaPlayer.create(applicationContext, R.raw.fsharp_piano)
            return key
        } else if (spnInstruments.selectedItem == "Banjo") {
            key = MediaPlayer.create(applicationContext, R.raw.fsharp_banjo)
            return key
        } else if (spnInstruments.selectedItem == "Vibraphone") {
            key = MediaPlayer.create(applicationContext, R.raw.fsharp_vib)
            return key
        } else
            return MediaPlayer()
    }

    private fun getInstrumentG(): MediaPlayer {
        var key: MediaPlayer? = null
        if (spnInstruments.selectedItem == "Electric Piano") {
            key = MediaPlayer.create(applicationContext, R.raw.g_piano)
            return key
        } else if (spnInstruments.selectedItem == "Banjo") {
            key = MediaPlayer.create(applicationContext, R.raw.g_banjo)
            return key
        } else if (spnInstruments.selectedItem == "Vibraphone") {
            key = MediaPlayer.create(applicationContext, R.raw.g_vib)
            return key
        } else
            return MediaPlayer()
    }

    private fun getInstrumentGSharp(): MediaPlayer {
        var key: MediaPlayer? = null
        if (spnInstruments.selectedItem == "Electric Piano") {
            key = MediaPlayer.create(applicationContext, R.raw.gsharp_piano)
            return key
        } else if (spnInstruments.selectedItem == "Banjo") {
            key = MediaPlayer.create(applicationContext, R.raw.gsharp_banjo)
            return key
        } else if (spnInstruments.selectedItem == "Vibraphone") {
            key = MediaPlayer.create(applicationContext, R.raw.gsharp_vib)
            return key
        } else
            return MediaPlayer()
    }

    private fun getInstrumentA(): MediaPlayer {
        var key: MediaPlayer? = null
        if (spnInstruments.selectedItem == "Electric Piano") {
            key = MediaPlayer.create(applicationContext, R.raw.a_piano)
            return key
        } else if (spnInstruments.selectedItem == "Banjo") {
            key = MediaPlayer.create(applicationContext, R.raw.a_banjo)
            return key
        } else if (spnInstruments.selectedItem == "Vibraphone") {
            key = MediaPlayer.create(applicationContext, R.raw.a_vib)
            return key
        } else
            return MediaPlayer()
    }

    private fun getInstrumentBFlat(): MediaPlayer {
        var key: MediaPlayer? = null
        if (spnInstruments.selectedItem == "Electric Piano") {
            key = MediaPlayer.create(applicationContext, R.raw.bflat_piano)
            return key
        } else if (spnInstruments.selectedItem == "Banjo") {
            key = MediaPlayer.create(applicationContext, R.raw.bflat_banjomp3)
            return key
        } else if (spnInstruments.selectedItem == "Vibraphone") {
            key = MediaPlayer.create(applicationContext, R.raw.bflat_vib)
            return key
        } else
            return MediaPlayer()
    }

    private fun getInstrumentB(): MediaPlayer {
        var key: MediaPlayer? = null
        if (spnInstruments.selectedItem == "Electric Piano") {
            key = MediaPlayer.create(applicationContext, R.raw.b_piano)
            return key
        } else if (spnInstruments.selectedItem == "Banjo") {
            key = MediaPlayer.create(applicationContext, R.raw.b_banjo)
            return key
        } else if (spnInstruments.selectedItem == "Vibraphone") {
            key = MediaPlayer.create(applicationContext, R.raw.b_vib)
            return key
        } else
            return MediaPlayer()
    }

    private fun getInstrumentC8(): MediaPlayer {
        var key: MediaPlayer? = null
        if (spnInstruments.selectedItem == "Electric Piano") {
            key = MediaPlayer.create(applicationContext, R.raw.c8_piano)
            return key
        } else if (spnInstruments.selectedItem == "Banjo") {
            key = MediaPlayer.create(applicationContext, R.raw.c8_banjo)
            return key
        } else if (spnInstruments.selectedItem == "Vibraphone") {
            key = MediaPlayer.create(applicationContext, R.raw.c8_vib)
            return key
        } else
            return MediaPlayer()
    }
}