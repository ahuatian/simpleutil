package com.wazai.utilslibrary.utils

import java.lang.reflect.InvocationTargetException
import java.lang.reflect.Method
import java.util.*

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
 * creater by 陈少华 on 5/27/19 15:46
 */
object SortUtil {

    fun sort( list:List<Any>, properName: String?// 根据此关键字属性排序
              ,  isAsc: Boolean// 为true的时候是正序，为false的时候是倒序
    ){
        Collections.sort(list,AnyProperComparator(properName,isAsc) as Comparator<in Any>)
    }

    // 按任意属性进行排序
    class AnyProperComparator(var properName: String?// 根据此关键字属性排序
                              , var isFlag: Boolean// 为true的时候是正序，为false的时候是倒序
    ) : Comparator<Any> {

        /**
         * 实现Comparator的对比方法
         *
         * @param r1
         * @param r2
         */
        override fun compare(r1: Any, r2: Any): Int {
            val c = r1.javaClass
            var result = 0.0
            try {
                val field = c.getDeclaredField(properName)
                val classType = field.type.simpleName
                var method: Method? = null
                // 这里仅根据方法的返回值类型的名称来判定，比较方便
                if ("String" == classType) {
                    method = c.getMethod("get" + properName!!.substring(0, 1).toUpperCase() + properName!!.substring(1), *arrayOf())
                    if (StringUtil.isNumeric((method!!.invoke(r1) as String))&&StringUtil.isNumeric((method!!.invoke(r2) as String))){
                        if (isFlag) {
                            result = StringUtil.ObjectToDouble(method!!.invoke(r1)) - StringUtil.ObjectToDouble(method.invoke(r2))
                        } else {
                            result = StringUtil.ObjectToDouble(method!!.invoke(r2)) - StringUtil.ObjectToDouble(method.invoke(r1))
                        }
                    } else if(!StringUtil.isNumeric((method!!.invoke(r1) as String))&&StringUtil.isNumeric((method!!.invoke(r2) as String))){
                        return 1//比较 的 数据中 第一个不为数值，确保数值在前面
                    } else if(StringUtil.isNumeric((method!!.invoke(r1) as String))&&!StringUtil.isNumeric((method!!.invoke(r2) as String))){
                        return -1//比较 的 数据中 第二个不为数值，确保数值在前面
                    }else {
                        if (isFlag) {
                            result = (method!!.invoke(r1) as String).compareTo(method.invoke(r2) as String).toDouble()
                        } else {
                            result = (method!!.invoke(r2) as String).compareTo(method.invoke(r1) as String).toDouble()
                        }
                    }

                } else if ("Integer" == classType || "int" == classType) {
                    method = c.getMethod("get" + properName!!.substring(0, 1).toUpperCase() + properName!!.substring(1), *arrayOf())
                    if (isFlag) {
                        result = (method!!.invoke(r1) as Int - method.invoke(r2) as Int).toDouble()
                    } else {
                        result = (method!!.invoke(r2) as Int - method.invoke(r1) as Int).toDouble()
                    }
                } else if ("Double" == classType || "double" == classType) {
                    method = c.getMethod("get" + properName!!.substring(0, 1).toUpperCase() + properName!!.substring(1), *arrayOf())
                    if (isFlag) {
                        result = method!!.invoke(r1) as Double - method.invoke(r2) as Double
                    } else {
                        result = method!!.invoke(r2) as Double - method.invoke(r1) as Double
                    }
                } else if ("Float" == classType || "float" == classType) {
                    method = c.getMethod("get" + properName!!.substring(0, 1).toUpperCase() + properName!!.substring(1), *arrayOf())
                    if (isFlag) {
                        result = (method!!.invoke(r1) as Float - method.invoke(r2) as Float).toDouble()
                    } else {
                        result = (method!!.invoke(r2) as Float - method.invoke(r1) as Float).toDouble()
                    }
                } else {
                    println("属性排序只支持数据类型和String类型，其它类型暂不支持。")
                    result = -100.0
                }
            } catch (e: SecurityException) {
                e.printStackTrace()
            } catch (e: NoSuchFieldException) {
                e.printStackTrace()
            } catch (e: NoSuchMethodException) {
                e.printStackTrace()
            } catch (e: IllegalArgumentException) {
                e.printStackTrace()
            } catch (e: IllegalAccessException) {
                e.printStackTrace()
            } catch (e: InvocationTargetException) {
                e.printStackTrace()
            }

            // 确定返回值
            if (result > 0) {
                return 1
            } else if (result < 0) {
                return -1
            }
            return 0
        }

    }

}
