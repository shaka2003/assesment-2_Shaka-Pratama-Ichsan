package org.d3if6706213098GaleriHewan.ui.hitung

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import org.d3if6706213098GaleriHewan.R
import org.d3if6706213098GaleriHewan.databinding.FragmentHitungBinding
import org.d3if6706213098GaleriHewan.db.BmiDb
import org.d3if6706213098GaleriHewan.model.HasilBmi
import org.d3if6706213098GaleriHewan.model.KategoriBmi

class HitungFragment : Fragment() {
    private lateinit var binding: FragmentHitungBinding

    val aktifitas = arrayOf("BMR","Sangat Jarang Olahraga", "Jarang Olahraga (1-2 kali seminggu)", "Olahraga Normal (2-3 kali seminggu)", "Sering Olahraga (4-5 kali seminggu)", "Sangat Sering Olahraga (2 kali sehari)")

    private val viewModel: HitungViewModel by lazy {
        val db = BmiDb.getInstance(requireContext())
        val factory = HitungViewModelFactory(db.dao)
        ViewModelProvider(this, factory)[HitungViewModel::class.java]
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentHitungBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.options_menu, menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.menu_histori -> {
                findNavController().navigate(
                    R.id.action_hitungFragment_to_historiFragment
                )
                return true
            }

            R.id.menu_about -> {
                findNavController().navigate(
                    R.id.action_hitungFragment_to_aboutFragment
                )
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.getHasilBmi().observe(requireActivity(), { showResult(it) })
        binding.btnHitung.setOnClickListener { hitungBmi() }
        binding.btnReset.setOnClickListener { reset() }
        binding.saranButton.setOnClickListener { viewModel.mulaiNavigasi() }
        binding.shareButton.setOnClickListener { shareData() }

        viewModel.getNavigasi().observe(viewLifecycleOwner, {
            if (it == null) return@observe
            findNavController().navigate(HitungFragmentDirections
                .actionHitungFragmentToSaranFragment(it))
            viewModel.selesaiNavigasi()
        })
    }

    private fun shareData() {
        val selectedId = binding.radioGroup.checkedRadioButtonId
        val gender = if (selectedId == R.id.priaRadioButton)
            getString(R.string.pria)
        else
            getString(R.string.wanita)
        val message = getString(R.string.bagikan_template,
            binding.beratInp.text,
            binding.tinggiBadanInp.text,
            gender,
            binding.bmiTextView.text,
            binding.kategoriTextView.text
        )
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.setType("text/plain").putExtra(Intent.EXTRA_TEXT, message)
        if (shareIntent.resolveActivity(
                requireActivity().packageManager) != null) {
            startActivity(shareIntent)
        }
    }

    private fun hitungBmi() {
        val berat = binding.beratInp.text.toString()
        if (TextUtils.isEmpty(berat)) {
            Toast.makeText(context, R.string.berat_invalid, Toast.LENGTH_LONG).show()
            return
        }
        val tinggi = binding.tinggiBadanInp.text.toString()
        if (TextUtils.isEmpty(tinggi)) {
            Toast.makeText(context, R.string.tinggi_invalid, Toast.LENGTH_LONG).show()
            return
        }
        val selectedId = binding.radioGroup.checkedRadioButtonId
        if (selectedId == -1) {
            Toast.makeText(context, R.string.gender_invalid, Toast.LENGTH_LONG).show()
            return
        }
        viewModel.hitungBmi(
            berat.toFloat(),
            tinggi.toFloat(),
            selectedId == R.id.priaRadioButton
        )
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
        binding.beratInp.setText("")
        binding.tinggiBadanInp.setText("")
        binding.umurInp.setText("")
        binding.bmiTextView.text = ""
        binding.kategoriTextView.text = ""
        binding.radioGroup.clearCheck()
        binding.buttonGroup.visibility = View.INVISIBLE
    }

    private fun showResult(result: HasilBmi?) {
        if (result == null) return
        binding.bmiTextView.text = getString(R.string.bmi_x, result.bmi)
//        binding.kategoriTextView.text = getString(R.string.kategori_x,
//            getKategoriLabel(result.kategori))
        binding.buttonGroup.visibility = View.VISIBLE
    }
}