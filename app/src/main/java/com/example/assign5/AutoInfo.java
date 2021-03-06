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

/**
 * the basic information of automobile for user
 */


public class AutoInfo {
        private String id;
        private String year;
        private String month;
        private String day;

        private String price;
        private String liters;
        private String kilo;

        public AutoInfo(String id, String year, String month, String day, String price, String liters, String kilo) {
            this.id = id;
            this.year = year;
            this.month = month;
            this.day = day;
            this.price = price;
            this.liters = liters;
            this.kilo = kilo;
        }

        public String getId() {
            return id;
        }

        public String getYear() {
            return year;
        }
        public String getMonth() {
            return month;
        }
        public String getDay() {
            return day;
        }

        public String getPrice() {
            return price;
        }

        public String getLiters() {
            return liters;
        }

        public String getKilo() {
            return kilo;
        }
}
