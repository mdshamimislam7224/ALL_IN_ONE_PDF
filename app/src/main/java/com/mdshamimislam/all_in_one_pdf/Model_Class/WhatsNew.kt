package com.mdshamimislam.all_in_one_pdf.Model_Class

class WhatsNew
{
    constructor(newTitle: String, newContent: String, iconLocation: String)

    private var mTitle: String? = null
    private var mDescription: String? = null
    private var mIcon: String? = null

    fun WhatsNew(title: String?, description: String?, icon: String?) {
        mTitle = title
        mDescription = description
        mIcon = icon
    }

    fun getTitle(): String? {
        return mTitle
    }

    fun setTitle(title: String?) {
        mTitle = title
    }

    fun getDescription(): String? {
        return mDescription
    }

    fun setDescription(description: String?) {
        mDescription = description
    }

    fun getIcon(): String? {
        return mIcon
    }

    fun setIcon(icon: String?) {
        mIcon = icon
    }
}