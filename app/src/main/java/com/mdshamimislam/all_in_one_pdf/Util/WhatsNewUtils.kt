package com.mdshamimislam.all_in_one_pdf.Util

import android.app.Dialog
import android.content.Context
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mdshamimislam.all_in_one_pdf.Adapter.WhatsNewAdapter
import com.mdshamimislam.all_in_one_pdf.Model_Class.WhatsNew
import com.mdshamimislam.all_in_one_pdf.R
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.nio.charset.StandardCharsets

class WhatsNewUtils
{
    private object SingletonHolder {
        val INSTANCE = WhatsNewUtils()
    }

    fun getInstance(): WhatsNewUtils? {
        return SingletonHolder.INSTANCE
    }

    /**
     * Display dialog with whats new
     *
     * @param context - current context
     */
    fun displayDialog(context: Context?) {
        val dialog = Dialog(context!!)
        dialog.setContentView(R.layout.fragment_whats_new)
        val rv: RecyclerView = dialog.findViewById(R.id.whatsNewListView)
        val title = dialog.findViewById<TextView>(R.id.title)
        val continueButton = dialog.findViewById<Button>(R.id.continueButton)
        continueButton.setText(R.string.whatsnew_continue)
        title.setText(R.string.whatsnew_title)
        try {
            val obj = JSONObject(getInstance()?.loadJSONFromAsset(context)!! )
            val whatsNewAdapter = WhatsNewAdapter(context, extractItemsFromJSON(obj)!!)
            val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            rv.layoutManager = layoutManager
            rv.adapter = whatsNewAdapter
            dialog.show()
            continueButton.setOnClickListener { view: View? -> dialog.dismiss() }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }

    /**
     * Load data from json file
     *
     * @param context - current context
     * @return - json
     */
    private fun loadJSONFromAsset(context: Context): String? {
        val json: String
        json = try {
            val `is` = context.assets.open("whatsnew.json")
            val size = `is`.available()
            val buffer = ByteArray(size)
            `is`.read(buffer)
            `is`.close()
            String(buffer, StandardCharsets.UTF_8)
        } catch (ex: IOException) {
            ex.printStackTrace()
            return null
        }
        return json
    }


    /**
     * Extract what's new items by parsing json
     * @param object - json object to be parsed
     * @return list of whatsnew items
     * @throws JSONException - invalid JSON
     */
    @Throws(JSONException::class)
    private fun extractItemsFromJSON(`object`: JSONObject): ArrayList<WhatsNew>? {
        val whatsNewList: ArrayList<WhatsNew>
        val data = `object`.getJSONArray("data")
        whatsNewList = ArrayList<WhatsNew>()
        for (i in 0 until data.length()) {
            val jsonObject = data.getJSONObject(i)
            val newTitle = jsonObject.getString("title")
            val newContent = jsonObject.getString("content")
            val iconLocation = jsonObject.getString("icon")
            val whatsNew = WhatsNew(newTitle, newContent, iconLocation)
            whatsNewList.add(whatsNew)
        }
        return whatsNewList
    }
}