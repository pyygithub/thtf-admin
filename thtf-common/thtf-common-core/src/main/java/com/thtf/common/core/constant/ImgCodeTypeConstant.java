package com.thtf.common.core.constant;

import lombok.Data;

/**
 * ---------------------------
 * 图片验证码类型 (https://gitee.com/whvse/EasyCaptcha)
 * ---------------------------
 * 作者：  pyy
 * 时间：  2020/1/13 15:39
 * 版本：  v1.0
 * ---------------------------
 */
public interface ImgCodeTypeConstant {
    String PNG = "1"; // 字母+数字PNG类型
    String GIF = "2"; // 字母+数字GIF类型
    String CHINESE= "3"; // 中文类型
    String CHINESE_GIF= "4"; // 中文gif类型
    String ARITHMETIC= "5"; // 算术类型
}
