package com.java.plus.predict.feature;

import lombok.Data;

@Data
public class FeatureVector {
    private String pid; 
    private float[] feature;
}
