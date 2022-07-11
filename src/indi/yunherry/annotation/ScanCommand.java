package indi.yunherry.annotation;

import indi.yunherry.constant.enums.ScanTypeEnum;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author YunHerry
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface ScanCommand {
    public ScanTypeEnum model() default ScanTypeEnum.SCAN_ONLY_ANNOTATION;
}
