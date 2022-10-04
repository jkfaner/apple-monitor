package com.github.jkfaner.util;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @version 1.0.0
 * @author: LiamLee
 * @since: 2022-09-27
 **/
public class JsonPathFinder {

    private final Object jsonObject;

    static class Entity<K, V> {
        private K key;
        private V value;

        public Entity(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }
    }

    public JsonPathFinder(String jsonStr) {
        if (StringUtils.startsWith(jsonStr,"{")){
            this.jsonObject = JSONUtil.parseObj(jsonStr);
        }else{
            this.jsonObject = JSONUtil.parseArray(jsonStr);
        }
    }

    /**
     * 递归节点
     *
     * @param data       被迭代的json对象
     * @param roadStep   步骤：默认为空列表
     * @param target     提取标签
     * @param resultPath 结果路径
     */
    private <T, K, V> void iterNode(T data, List<K> roadStep, String target, List<List<K>> resultPath) {
        List<Entity<K, V>> jsonObjs = new ArrayList<>();
        if (data instanceof JSONObject) {
            JSONObject jsonObject = (JSONObject) data;
            for (Map.Entry<String, Object> entry : jsonObject) {
                jsonObjs.add(new Entity(entry.getKey(), entry.getValue()));
            }
        } else if (data instanceof JSONArray) {
            JSONArray jsonArray = (JSONArray) data;
            // 排除["value"]异常
            if (StringUtils.startsWith(jsonArray.toString(), "[{")) {
                List<JSONObject> jsonObjects = jsonArray.toList(JSONObject.class);
                for (int i = 0; i < jsonObjects.size(); i++) {
                    jsonObjs.add(new Entity(i, jsonObjects.get(i)));
                }
            }
        }
        for (Entity<K, V> jsonObj : jsonObjs) {
            K key = jsonObj.getKey();
            V value = jsonObj.getValue();
            List<K> currentPath = new ArrayList<>(roadStep);
            currentPath.add(key);
            if (key.equals(target)) {
                resultPath.add(currentPath);
                break;
            }
            if (value instanceof JSONObject || value instanceof JSONArray) {
                iterNode(value, currentPath, target, resultPath);
            }
        }
    }

    /**
     * 获取路径
     *
     * @param target 提取标签
     * @return 结果路径
     */
    public <K> List<List<K>> getPath(String target) {
        List<List<K>> resultPath = new ArrayList<>();
        iterNode(jsonObject, new ArrayList<>(), target, resultPath);
        return resultPath;
    }

    /**
     * 获取值的数量
     * @param target 提取标签
     * @return 数量
     */
    public int getValueNum(String target){
        return this.getPath(target).size();
    }

    /**
     * 获取第index个值
     * @param target 提取标签
     * @param index 索引
     * @return 结果
     */
    public <K> Object getValue(String target,Integer index){
        List<List<K>> path = getPath(target);
        List<K> valuePath = path.get(index);
        String join = StringUtils.join(valuePath, ".");
        JSONObject jsonObject = (JSONObject) this.jsonObject;
        return jsonObject.getByPath(join);
    }

    /**
     * 获取第一个值
     * @param target 提取标签
     * @return 结果
     */
    public Object getFirst(String target) {
        return this.getValue(target,0);
    }

    /**
     * 获取所有
     * @param target 提取标签
     * @param <K>
     * @return 结果
     */
    public <K> List<Object> getAll(String target){
        List<Object> list = new ArrayList<>();
        List<List<K>> paths = getPath(target);
        for (List<K> path : paths) {
            String join = StringUtils.join(path, ".");
            JSONObject jsonObject = (JSONObject) this.jsonObject;
            list.add(jsonObject.getByPath(join));
        }
        return list;
    }

    /**
     * 检查节点标签是否存在
     * @param target 标签
     * @return true存在 false不存在
     */
    public boolean checkKeyExist(String target){
        return this.getPath(target).size() != 0;
    }
}
