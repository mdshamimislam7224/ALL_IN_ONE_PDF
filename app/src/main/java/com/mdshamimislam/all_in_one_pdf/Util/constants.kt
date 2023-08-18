package com.mdshamimislam.all_in_one_pdf.Util

import android.Manifest
import android.graphics.Color

open class  constants
{
 companion object{

    val DEFAULT_COMPRESSION = "DefaultCompression"
    val SORTING_INDEX = "SORTING_INDEX"
    val IMAGE_EDITOR_KEY = "first"
    val DEFAULT_FONT_SIZE_TEXT = "DefaultFontSize"
    val DEFAULT_FONT_SIZE = 11
    val PREVIEW_IMAGES = "preview_images"
    val DATABASE_NAME = "ImagesToPdfDB.db"
    val DEFAULT_FONT_FAMILY_TEXT = "DefaultFontFamily"
    val DEFAULT_FONT_FAMILY = "TIMES_ROMAN"
    val DEFAULT_FONT_COLOR_TEXT = "DefaultFontColor"
    val DEFAULT_FONT_COLOR = -16777216

    // key for text to pdf (TTP) page color
    val DEFAULT_PAGE_COLOR_TTP = "DefaultPageColorTTP"

    // key for images to pdf (ITP) page color
    val DEFAULT_PAGE_COLOR_ITP = "DefaultPageColorITP"
    val DEFAULT_PAGE_COLOR = Color.WHITE
   open val DEFAULT_THEME_TEXT = "DefaultTheme"
    val DEFAULT_THEME = "System"
    val DEFAULT_IMAGE_BORDER_TEXT = "Image_border_text"
    val RESULT = "result"
    val SAME_FILE = "SameFile"
    val DEFAULT_PAGE_SIZE_TEXT = "DefaultPageSize"
    val DEFAULT_PAGE_SIZE = "A4"
    val CHOICE_REMOVE_IMAGE = "CHOICE_REMOVE_IMAGE"
    val DEFAULT_QUALITY_VALUE = 30
    val DEFAULT_BORDER_WIDTH = 0
    val STORAGE_LOCATION = "storage_location"
    val DEFAULT_IMAGE_SCALE_TYPE_TEXT = "image_scale_type"
    val IMAGE_SCALE_TYPE_STRETCH = "stretch_image"
    val IMAGE_SCALE_TYPE_ASPECT_RATIO = "maintain_aspect_ratio"
    val PG_NUM_STYLE_PAGE_X_OF_N = "pg_num_style_page_x_of_n"
    val PG_NUM_STYLE_X_OF_N = "pg_num_style_x_of_n"
    val PG_NUM_STYLE_X = "pg_num_style_x"
    val MASTER_PWD_STRING = "master_password"

    val IMAGE_TO_PDF_KEY = "Images to PDF"
    val TEXT_TO_PDF_KEY = "Text To PDF"
    val QR_BARCODE_KEY = "QR & Barcodes"
    val VIEW_FILES_KEY = "View Files"
    val HISTORY_KEY = "History"
    val ADD_TEXT_KEY = "Add Text"
    val ADD_PASSWORD_KEY = "Add password"
    val REMOVE_PASSWORD_KEY = "Remove password"
    val ROTATE_PAGES_KEY = "Rotate Pages"
    val ADD_WATERMARK_KEY = "Add Watermark"
    val ADD_IMAGES_KEY = "Add Images"
    val MERGE_PDF_KEY = "Merge PDF"
    val SPLIT_PDF_KEY = "Split PDF"
    val INVERT_PDF_KEY = "Invert Pdf"
    val COMPRESS_PDF_KEY = "Compress PDF"
    val REMOVE_DUPLICATE_PAGES_KEY = "Remove Duplicate Pages"
    val REMOVE_PAGES_KEY = "Remove Pages"
    val REORDER_PAGES_KEY = "Reorder Pages"
    val EXTRACT_TEXT_KEY = "Extract Text"
    val EXTRACT_IMAGES_KEY = "Extract Images"
    val PDF_TO_IMAGES_KEY = "PDF to Images"
    val EXCEL_TO_PDF_KEY = "Excel to PDF"
    val ZIP_TO_PDF_KEY = "ZIP to PDF"

    val BUNDLE_DATA = "bundle_data"
    val REORDER_PAGES = "Reorder pages"
    val REMOVE_PAGES = "Remove pages"
    val COMPRESS_PDF = "Compress PDF"
    val ADD_PWD = "Add password"
    val REMOVE_PWd = "Remove password"
    val ADD_IMAGES = "add_images"
    val PDF_TO_IMAGES = "pdf_to_images"
    val EXTRACT_IMAGES = "extract_images"

    val LAUNCH_COUNT = "launch_count"

    val pdfDirectory = "/PDF Converter/"
    val pdfExtension = ".pdf"
    val appName = "PDF Converter"
    val PATH_SEPERATOR = "/"
    val textExtension = ".txt"
    val excelExtension = ".xls"
    val excelWorkbookExtension = ".xlsx"
    val docExtension = ".doc"
    val docxExtension = ".docx"
    val tempDirectory = "temp"

    val AUTHORITY_APP = "com.swati4star.shareFile"

    val ACTION_SELECT_IMAGES = "android.intent.action.SELECT_IMAGES"
    val ACTION_VIEW_FILES = "android.intent.action.VIEW_FILES"
    val ACTION_TEXT_TO_PDF = "android.intent.action.TEXT_TO_PDF"
    val ACTION_MERGE_PDF = "android.intent.action.MERGE_PDF"
    val OPEN_SELECT_IMAGES = "open_select_images"

    val THEME_WHITE = "White"
    val THEME_BLACK = "Black"
    val THEME_DARK = "Dark"
    val THEME_SYSTEM = "System"

    val IS_WELCOME_ACTIVITY_SHOWN = "is_Welcome_activity_shown"
    val SHOW_WELCOME_ACT = "show_welcome_activity"

    val VERSION_NAME = "VERSION_NAME"

    val PREF_PAGE_STYLE = "pref_page_number_style"
    val PREF_PAGE_STYLE_ID = "pref_page_number_style_rb_id"

   open val REQUEST_CODE_FOR_WRITE_PERMISSION = 4
    val REQUEST_CODE_FOR_READ_PERMISSION = 5


    val WRITE_PERMISSIONS = arrayOf(
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    )
    val READ_PERMISSIONS = arrayOf(
        Manifest.permission.READ_EXTERNAL_STORAGE
    )


    val MODIFY_STORAGE_LOCATION_CODE = 1

    val ROTATE_PAGES = 20
    val ADD_PASSWORD = 21
    val REMOVE_PASSWORD = 22
    val ADD_WATERMARK = 23

    //Preference key name.
    val RECENT_PREF = "Recent"
}
}