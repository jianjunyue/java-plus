package com.java.plus.algo.feature;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.java.plus.algo.feature.core.FeatureConfig;

/**
 * @author shihu
 */
public class TestFeatureConfig {
    public static void main(String args[]) {
        FeatureConfig featureConfig = FeatureTransformUtils.loadFeatureConfig(6);
        Map<String,Object> map = new HashMap<>();
        map.put("appid","android_app");
        map.put("tab","美车节");
        map.put("from_id","详情");
        map.put("prdtype","unclassified");
        map.put("prev_click_days",1);
        map.put("expose_uv_avg3",2);
        map.put("click_uv_avg3",3);
        map.put("ctr_std_avg3",4);
        map.put("prd_price",5);
        map.put("mkt_price",5);
        map.put("dim_gender",6);
        map.put("regist_delta_days",8);
        map.put("last_login_time_delta",8);
        map.put("last_user_grade_update_time_delta",8);
        map.put("min_growth_value",8);
        map.put("max_growth_value",8);
        map.put("available_integral",8);
        map.put("is_hucard",8);
        map.put("is_blackcard",8);
        map.put("is_tire_old_cust",8);
        map.put("is_baoyang_old_cust",8);
        map.put("is_meirong_old_cust",8);
        map.put("is_carproduct_old_cust",8);
        map.put("ancestor_order_num",8);
        map.put("last_ancestor_order_delta",8);
        map.put("per_month_real_pay_amt",8);
        map.put("vehicle_cnt",8);
        map.put("dim_car_year",8);
        map.put("dim_vehicle_brand","Z - 知豆");
        map.put("tv_max_price",8);
        map.put("tv_avg_price",8);
        map.put("tv_min_price",8);
        map.put("buy_year",8);
        map.put("mileage_daily",8);
        map.put("car_regist_delta",8);
        map.put("dim_device_manufacturer","coolpad");
        map.put("valid_comment_nums",8);
        map.put("commentr_avg",8);


        double[] resultArray =featureConfig.transformToArray(map);

//        System.out.println(resultArray.length);
        System.out.println("resultArray:"+JSON.toJSONString( resultArray));

        long start = System.currentTimeMillis();
        for (int i = 0; i < 500;i++) {
            featureConfig.transformToArray(map);
        }

        long end = System.currentTimeMillis();
        System.out.println("start "+start);
        System.out.println("end "+end);
        System.out.println("end-start "+(end-start));
    }
}
