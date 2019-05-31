package com.wazai.utilslibrary.utils

import java.util.regex.Pattern

/**
 * ----------Dragon be here!----------/
 * 　　　┏┓　　　┏┓
 * 　　┏┛┻━━━┛┻┓
 * 　　┃　　　　　　　┃
 * 　　┃　　　━　　　┃
 * 　　┃　┳┛　┗┳　┃
 * 　　┃　　　　　　　┃
 * 　　┃　　　┻　　　┃
 * 　　┃　　　　　　　┃
 * 　　┗━┓　　　┏━┛
 * 　　　　┃　　　┃神兽保佑
 * 　　　　┃　　　┃代码无BUG！
 * 　　　　┃　　　┗━━━┓
 * 　　　　┃　　　　　　　┣┓
 * 　　　　┃　　　　　　　┏┛
 * 　　　　┗┓┓┏━┳┓┏┛
 * 　　　　　┃┫┫　┃┫┫
 * 　　　　　┗┻┛　┗┻┛
 * ━━━━━━神兽出没━━━━━━
 * creater by 陈少华 on 5/30/19 15:45
 **/
object StringUtil {
    /**判断是否是数值类型*/
    fun isNumeric(str: String): Boolean {
        val pattern = Pattern.compile("[0-9]*")
        return if (str.indexOf(".") > 0) {//判断是否有小数点
            if (str.indexOf(".") == str.lastIndexOf(".") && str.split("\\.".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray().size == 2) { //判断是否只有一个小数点
                pattern.matcher(str.replace(".", "")).matches()
            } else {
                false
            }
        } else {
            pattern.matcher(str).matches()
        }
    }
    /**字符串转double*/
    fun ObjectToDouble(o: Any): Double {
        try {
            return java.lang.Double.parseDouble(o.toString())
        } catch (e: Exception) {
            return 0.0
        }

    }
}