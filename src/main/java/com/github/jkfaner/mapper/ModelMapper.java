package com.github.jkfaner.mapper;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.github.jkfaner.common.IBaseObject;
import com.github.jkfaner.ui.constant.UIConstants;
import com.github.jkfaner.mapper.bean.Generation;
import com.github.jkfaner.mapper.bean.Model;
import com.github.jkfaner.mapper.bean.Product;
import com.github.jkfaner.mapper.bean.Series;
import lombok.Getter;
import lombok.Setter;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author: LiamLee
 * @since: 2022-10-03
 **/
@Getter
@Setter
public class ModelMapper implements IBaseObject<ModelMapper> {

    private static ModelMapper modelMapper;
    private List<Product> productList;

    private Map<String,String> checkModelMap= new HashMap();

    @Override
    public ModelMapper getInstance() {
        modelMapper = modelMapper == null ? new ModelMapper() : modelMapper;
        return modelMapper;
    }

    static {
        String modeJsonData = FileUtil.readString(Objects.requireNonNull(UIConstants.class.getResource("/db/model.json")), CharsetUtil.UTF_8);
        JSONObject jsonObject = JSONUtil.parseObj(modeJsonData);
        JSONArray data = (JSONArray) jsonObject.getByPath("data");
        modelMapper = new ModelMapper();
        modelMapper.productList = data.toList(Product.class);

    }

    public ArrayList<Model> getCheckModelList(){
        List<Product> products = productList.stream().filter(e -> e.getValue().equals("iPhone")).collect(Collectors.toList());
        Product product = products.get(0);
        List<Generation> generations = product.getGenerations();
        Generation generation = generations.get(0);
        List<Series> seriesList = generation.getSeries().stream().filter(i -> i.getValue().equals("Pro") || i.getValue().equals("ProMax")).collect(Collectors.toList());

        ArrayList<Model> models = new ArrayList<>();
        for (Series series : seriesList) {
            List<Model> modelList = series.getModels();
            for (Model model : modelList) {
                checkModelMap.put(model.getName(),model.getModel());
            }
            models.addAll(modelList);

        }
        return models;
    }

}
