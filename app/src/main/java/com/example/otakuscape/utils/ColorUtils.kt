package com.example.otakuscape.utils

import androidx.compose.ui.graphics.Color
import com.example.otakuscape.data.remote.dto.Item

object ColorUtils {
    /**
     * Convert a list of RGB values to a Color
     * @param rgbList List of 3 integers representing RGB values
     * @return Color object or null if invalid input
     */
    fun rgbToColor(rgbList: List<Int>?): Color? {
        if (rgbList == null || rgbList.size != 3) return null
        
        return try {
            Color(rgbList[0], rgbList[1], rgbList[2])
        } catch (e: Exception) {
            null
        }
    }
    
    /**
     * Calculate the brightness of a color (0-255)
     * @param color Color to calculate brightness for
     * @return Brightness value (0-255)
     */
    fun getColorBrightness(color: Color): Float {
        // Convert to perceived brightness using luminance formula
        return (0.299f * color.red + 0.587f * color.green + 0.114f * color.blue) * 255f
    }
    
    /**
     * Determine if a color is light or dark
     * @param color Color to check
     * @return true if light, false if dark
     */
    fun isLightColor(color: Color): Boolean {
        return getColorBrightness(color) > 127.5f
    }
    
    /**
     * Get a list of colors from the palette
     * @param item The wallpaper item
     * @return List of Color objects
     */
    fun getPaletteColors(item: Item): List<Color> {
        val colors = mutableListOf<Color>()
        item.color_palette?.forEach { rgbList ->
            rgbToColor(rgbList)?.let { color ->
                colors.add(color)
            }
        }
        return colors
    }
    
    /**
     * Get the dominant color of an image
     * @param item The wallpaper item
     * @return The dominant color or null
     */
    fun getDominantColor(item: Item): Color? {
        return rgbToColor(item.color_dominant)
    }
}