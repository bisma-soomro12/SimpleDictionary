package adapters

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.accessibility.AccessibilityNodeInfo
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.simpledictionary.MainActivity2
import com.example.simpledictionary.R

class WordAdapter(private val letterId: String, context: Context) : RecyclerView.Adapter<WordAdapter.WordViewHolder>() {

    private val filterList: List<String>
    init {

    // retrive the list of words from res

        val words=context.resources.getStringArray(R.array.words).toList()
        filterList=words.filter { it.startsWith(letterId,ignoreCase = true) }.shuffled().take(5).sorted()


    }
    class WordViewHolder(val view: View): RecyclerView.ViewHolder(view){
        val button = view.findViewById<Button>(R.id.button_item)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val layout = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.data_item, parent, false)
        layout.accessibilityDelegate = Accessibility

        return WordViewHolder(layout)
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
       val item= filterList[position]
        val context=holder.view.context
        holder.button.text=item
        holder.button.setOnClickListener{
            val queryUrl: Uri = Uri.parse("${MainActivity2.SEARCH_PREFIX}${item}meaning")
            val intent= Intent(Intent.ACTION_VIEW,queryUrl)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int= filterList.size

    object Accessibility : View.AccessibilityDelegate() {
        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        override fun onInitializeAccessibilityNodeInfo(
            host: View?,
            info: AccessibilityNodeInfo?
        ) {
            super.onInitializeAccessibilityNodeInfo(host, info)
            // With `null` as the second argument to [AccessibilityAction], the
            // accessibility service announces "double tap to activate".
            // If a custom string is provided,
            // it announces "double tap to <custom string>".
            val customString = host?.context?.getString(R.string.look_up_word)
            val customClick =
                AccessibilityNodeInfo.AccessibilityAction(
                    AccessibilityNodeInfo.ACTION_CLICK,
                    customString
                )
            info?.addAction(customClick)
        }
    }
}



