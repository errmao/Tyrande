package org.tyrande.common.utils;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.tyrande.common.exception.customize.CustomException;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * 框架类的工具
 *
 * @author Tyrande
 */
@Slf4j
public class FrameworkUtil {

    /**
     * 返回jsonObj参数
     *
     * @param request
     * @return
     */
    public static JSONObject getRequestParamsObj(HttpServletRequest request) {
        JSONObject paramsObj;
        try (InputStream inputStream = request.getInputStream();
             BufferedReader streamReader = new BufferedReader(
                     new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            StringBuilder responseStrBuilder = new StringBuilder();
            String inputStr;
            while ((inputStr = streamReader.readLine()) != null) {
                responseStrBuilder.append(inputStr);
            }
            paramsObj = JSONObject.parseObject(responseStrBuilder.toString());

        } catch (Exception e) {
            throw new CustomException("[从request读取json数据失败]", e);
        }
        return paramsObj;
    }
}
