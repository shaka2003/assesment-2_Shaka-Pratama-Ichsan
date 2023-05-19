package org.d3if6706213098GaleriHewan.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import org.d3if6706213098GaleriHewan.R
import org.d3if6706213098GaleriHewan.databinding.FragmentSaranBinding
import org.d3if6706213098GaleriHewan.model.KategoriBmi

class SaranFragment : Fragment() {
    private lateinit var binding: FragmentSaranBinding
    private val args: SaranFragmentArgs by navArgs()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = FragmentSaranBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    private fun updateUI(kategori: KategoriBmi) {
        val actionBar = (requireActivity() as AppCompatActivity).supportActionBar
        when (kategori) {
            KategoriBmi.KURUS -> {
                actionBar?.title = getString(R.string.judul_kalori)
                binding.imageView.setImageResource(R.drawable.kalori)
                binding.textView.text = getString(R.string.saran_kalori)
            }
            KategoriBmi.IDEAL -> {
                actionBar?.title = getString(R.string.judul_kalori)
                binding.imageView.setImageResource(R.drawable.kalori)
                binding.textView.text = getString(R.string.saran_kalori)
            }
            KategoriBmi.GEMUK -> {
                actionBar?.title = getString(R.string.judul_kalori)
                binding.imageView.setImageResource(R.drawable.kalori)
                binding.textView.text = getString(R.string.saran_kalori)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        updateUI(args.kategori)
    }

}