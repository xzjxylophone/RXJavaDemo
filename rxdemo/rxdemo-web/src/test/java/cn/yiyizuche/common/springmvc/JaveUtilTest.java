package cn.yiyizuche.common.springmvc;

import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StringUtils;

import java.util.Properties;

/**
 * Created by ceshi on 17/5/19.
 */
public class JaveUtilTest extends  BaseSpringMVCTest {


    @Test
    public void test_PropertiesFile() {
        Properties properties = null;

    }


    @Test
    public void test_StringUtils() {
        String path = "";
        String relativePath = "";

        StringUtils.applyRelativePath(path, relativePath);
    }


}
