package com.example.assign5;
/**
 *  MIT License
 *
 *         Copyright (c) [year] [fullname]
 *
 *         Permission is hereby granted, free of charge, to any person obtaining a copy
 *         of this software and associated documentation files (the "Software"), to deal
 *         in the Software without restriction, including without limitation the rights
 *         to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *         copies of the Software, and to permit persons to whom the Software is
 *         furnished to do so, subject to the following conditions:
 *
 *         The above copyright notice and this permission notice shall be included in all
 *         copies or substantial portions of the Software.
 *
 *         THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *         IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *         FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *         AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *         LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *         OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *         SOFTWARE.
 */
/**
 * author: Cynthia Xia Sheng 2020
 */
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * This is the exercise type with both languages
 */

public class ActivityTrackingUtil {
    private static SimpleDateFormat FORMATTER = new SimpleDateFormat("yyyy-MM-dd hh:mm", Locale.getDefault());
    public static SimpleDateFormat getDateFormatter() {
        return FORMATTER;
    }

    public static int getPosition(String type) {
        switch (type) {
            case "Running":
            case "撒鸭子":  return 0;
            case "Walking":
            case "走道": return 1;
            case "Biking":
            case "骑车子": return 2;
            case "Swimming":
            case "游泳": return 3;
            case "Skating":
            case "滑出溜": return 4;
        }
        return 0;
    }
}
