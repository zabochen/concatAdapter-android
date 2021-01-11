package ua.ck.zabochen.android.concatadapter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import ua.ck.zabochen.android.concatadapter.adapter.EmptyAdapter
import ua.ck.zabochen.android.concatadapter.adapter.HeaderAdapter
import ua.ck.zabochen.android.concatadapter.adapter.RecentlyAdapter
import ua.ck.zabochen.android.concatadapter.adapter.SubjectAdapter
import ua.ck.zabochen.android.concatadapter.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    private val recentlyAdapter by lazy { RecentlyAdapter() }
    private val headerAdapter by lazy { HeaderAdapter() }
    private val subjectAdapter by lazy { SubjectAdapter() }
    private val emptyAdapter by lazy { EmptyAdapter() }

    private val subjects = listOf(
        "Subject_1", "Subject_2", "Subject_3", "Subject_4",
        "Subject_5", "Subject_6", "Subject_7", "Subject_8",
    )

    private var recentlyIsShown = false
    private var headerIsShown = false
    private var emptyIsShown = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding()
        initUi()
    }

    private fun initBinding() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun initUi() {
        setAdapter()
        setButtons()
    }

    private fun setAdapter() = with(binding) {
        val concatAdapter = ConcatAdapter(
            recentlyAdapter,
            headerAdapter,
            subjectAdapter,
            emptyAdapter
        )
        rvMain.apply {
            adapter = concatAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
            setHasFixedSize(true)
        }

        subjectAdapter.submitList(subjects)
    }

    private fun setButtons() = with(binding) {

        btnAddRecently.setOnClickListener {
            if (!emptyAdapter.currentList.isNullOrEmpty()) emptyAdapter.submitList(emptyList())
            if (recentlyIsShown) recentlyAdapter.submitList(emptyList())
            else recentlyAdapter.submitList(listOf("Recently Subject"))
            recentlyIsShown = !recentlyIsShown
        }

        btnAddHeader.setOnClickListener {
            if (!emptyAdapter.currentList.isNullOrEmpty()) emptyAdapter.submitList(emptyList())
            if (headerIsShown) headerAdapter.submitList(emptyList())
            else headerAdapter.submitList(listOf("Subject Header"))
            headerIsShown = !headerIsShown
        }

        btnAddSubject.setOnClickListener {
            if (!emptyAdapter.currentList.isNullOrEmpty()) emptyAdapter.submitList(emptyList())
            subjectAdapter.submitList(subjects.shuffled())
        }

        btnAddEmpty.setOnClickListener {
            recentlyAdapter.submitList(emptyList())
            headerAdapter.submitList(emptyList())
            subjectAdapter.submitList(emptyList())
            emptyAdapter.submitList(listOf("Empty state"))
        }
    }
}