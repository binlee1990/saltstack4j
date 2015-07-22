package saltstack4j.saltstack.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;

public class JsonUtils {
    private JsonUtils() {
    }

    public static String getJsonValueByKey(String json, String key) {
        String appType = "";
        if (isStringJsonFormat(json)) {
            JSONObject jsonObject = JSON.parseObject(json);
            appType = getJsonValueByKey(jsonObject, key);
        }
        return appType;
    }

    private static String getJsonValueByKey(JSONObject jsonObject, String key) {
        if (jsonObject.containsKey(key)) {
            return jsonObject.get(key).toString();
        }

        String secondJson = null;
        for (String secondKey : jsonObject.keySet()) {
            secondJson = jsonObject.get(secondKey).toString();
            if (isStringJsonFormat(secondJson)) {
                JSONObject secondJsonObject = JSON.parseObject(secondJson);
                return getJsonValueByKey(secondJsonObject, key);
            }
        }

        return "";
    }

    public static boolean isStringJsonFormat(String json) {
        boolean isJsonFormat = false;

        try {
            JSON.parseObject(json);
            isJsonFormat = true;
        } catch (Exception e) {
            isJsonFormat = false;
        }
        return isJsonFormat;
    }

    public static List<String> getJsonStringList(String result) {
        List<String> list = Lists.newArrayList();
        try {
            list = new ArrayList<String>();

            int indexOfOpenBrace = result.indexOf('{');
            int indexOfCloseBrace = 0;
            char[] resultChar = result.toCharArray();
            @SuppressWarnings({ "rawtypes", "unchecked" })
            Queue<Character> openBraceQueue = new LinkedBlockingQueue(1024);

            if (null != resultChar && resultChar.length > 0) {
                int length = resultChar.length;
                int i = indexOfOpenBrace + 1;
                for (; i < length; i++) {
                    if (resultChar[i] == '{') {
                        openBraceQueue.offer(resultChar[i]);
                    } else if (resultChar[i] == '}') {
                        if (openBraceQueue.size() > 0) {
                            openBraceQueue.poll();
                        } else {
                            indexOfCloseBrace = i + 1;
                            list.add(result.substring(indexOfOpenBrace, indexOfCloseBrace));
                            if (indexOfCloseBrace + 1 < length) {
                                i = result.indexOf('{', i) + 1;
                                indexOfOpenBrace = i - 1;
                                indexOfCloseBrace = i;
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
        }

        return list;
    }
}
