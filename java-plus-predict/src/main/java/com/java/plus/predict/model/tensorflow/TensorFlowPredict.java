package com.java.plus.predict.model.tensorflow;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Random;
 

public class TensorFlowPredict {

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("D:\\data\\webroot\\zhuque\\save_model_bundle.tar.gz");
        System.out.println(file.isFile());
        System.out.println(file.exists());
        TensorflowModel tensorflowModel = new TensorflowModel(new FileInputStream(file));
        double[][] mat = new double[100][331];
        System.out.println(Arrays.toString(mat));
        double[] output = tensorflowModel.output(mat);
        for (int i = 0; i < output.length; i++) {
            System.out.println(output[i]);
        }
        double[] future = new double[100 * 331];
        for (int i = 0; i < future.length; i++) {
            future[i] = new Random().nextDouble();
        }
        double[] predict  = tensorflowModel.predict(future,331);
        for (int i = 0; i < predict.length; i++) {
            System.out.println(predict[i]);
        }
    }
}
