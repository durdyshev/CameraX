package com.justme.camerax.presentation

import android.graphics.Bitmap
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.label.ImageLabeling
import com.google.mlkit.vision.label.defaults.ImageLabelerOptions

object ImageLabelingHelper {
    private val imageLabeler by lazy {
        ImageLabeling.getClient(
            ImageLabelerOptions.Builder()
                .setConfidenceThreshold(0.20f)
                .build()
        )
    }

    private val preferredLabels = mapOf(
        "tv" to "Television",
        "television" to "Television",
        "monitor" to "Monitor",
        "computer monitor" to "Monitor",
        "screen" to "Screen",
        "lcd" to "Television",
        "display" to "Display",
        "electronics" to "Electronics",
        "appliance" to "Appliance",
        "laptop" to "Laptop",
        "desktop computer" to "Computer",
        "cell phone" to "Phone",
        "mobile phone" to "Phone"
    )

    fun recognize(bitmap: Bitmap, onSuccess: (String) -> Unit, onError: (Exception) -> Unit) {
        val image = InputImage.fromBitmap(bitmap, 0)

        imageLabeler.process(image)
            .addOnSuccessListener { labels ->
                val normalizedLabel = labels
                    .map { it.text.trim().lowercase() }
                    .firstOrNull { preferredLabels.containsKey(it) }
                    ?.let { preferredLabels[it] }

                val fallbackLabel = labels
                    .filter { it.confidence >= 0.20f }
                    .sortedByDescending { it.confidence }
                    .map { it.text.trim() }
                    .firstOrNull()
                    ?.ifBlank { null }

                val finalLabel = normalizedLabel
                    ?: fallbackLabel
                    ?: "Object not recognized"

                onSuccess(finalLabel)
            }
            .addOnFailureListener { exception ->
                onError(exception)
            }
    }
}
