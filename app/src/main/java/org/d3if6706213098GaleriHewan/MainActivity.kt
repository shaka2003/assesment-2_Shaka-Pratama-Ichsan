package org.d3if6706213098GaleriHewan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import org.d3if6706213098GaleriHewan.databinding.ActivityMainBinding
import org.d3if6706213098GaleriHewan.model.HasilBmi
import org.d3if6706213098GaleriHewan.model.KategoriBmi

class MainActivity : AppCompatActivity(){
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnHitung.setOnClickListener {
            hitungBmi()
        }
        binding.btnReset.setOnClickListener {
            reset()
        }
    }

    private fun hitungBmi() {
        val berat = binding.beratEditText.text.toString()
        if (TextUtils.isEmpty(berat)) {
            Toast.makeText(this, R.string.berat_invalid, Toast.LENGTH_LONG).show()
            return
        }
        val tinggi = binding.tinggiEditText.text.toString()
        if (TextUtils.isEmpty(tinggi)) {
            Toast.makeText(this, R.string.tinggi_invalid, Toast.LENGTH_LONG).show()
            return
        }
        val selectedId = binding.radioGroup.checkedRadioButtonId
        if (selectedId == -1) {
            Toast.makeText(this, R.string.gender_invalid, Toast.LENGTH_LONG).show()
            return
        }
        val result = hitungBmi(
            berat.toFloat(),
            tinggi.toFloat(),
            selectedId == R.id.priaRadioButton
        )
        showResult(result)
    }
    private fun getKategori(bmi: Float, isMale: Boolean): KategoriBmi {
        val kategori = if (isMale) {
            when {
                bmi < 20.5 -> KategoriBmi.KURUS
                bmi >= 27.0 -> KategoriBmi.GEMUK
                else -> KategoriBmi.IDEAL
            }
        } else {
            when {
                bmi < 18.5 -> KategoriBmi.KURUS
                bmi >= 25.0 -> KategoriBmi.GEMUK
                else -> KategoriBmi.IDEAL
            }
        }
        return kategori
    }

    private fun getKategoriLabel(kategori: KategoriBmi): String {
        val stringRes = when (kategori) {
            KategoriBmi.KURUS -> R.string.kurus
            KategoriBmi.IDEAL -> R.string.ideal
            KategoriBmi.GEMUK -> R.string.gemuk
        }
        return getString(stringRes)
    }

    private fun reset () {
        binding.beratEditText.text.clear()
        binding.tinggiEditText.text.clear()
        binding.bmiTextView.text = ""
        binding.kategoriTextView.text = ""
        binding.radioGroup.clearCheck()
    }

    private fun hitungBmi(berat: Float, tinggi: Float, isMale: Boolean): HasilBmi {
        val tinggiCm = tinggi / 100
        val bmi = berat / (tinggiCm * tinggiCm)
        val kategori = getKategori(bmi, isMale)
        return HasilBmi(bmi, kategori)
    }

    private fun showResult(result: HasilBmi) {
        binding.bmiTextView.text = getString(R.string.bmi_x, result.bmi)
        binding.kategoriTextView.text = getString(R.string.kategori_x,
            getKategoriLabel(result.kategori))
    }
}