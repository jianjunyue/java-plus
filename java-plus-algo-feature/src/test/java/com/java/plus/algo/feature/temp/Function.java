package com.java.plus.algo.feature.temp;

import java.io.Serializable;

public class Function implements Serializable {
	
    @ConfigField(allowNull = false, document = "连接方式,str_format：java语法自定义写入格式,concat：使用自定义分隔符连接,default：使用:连接")
    private String name;

    @ConfigField(allowNull = false, document = "例：str_format: %s_%s_%s； concat: #；default: .... ")
    private String with;

    public Function(String name, String with) {
        this.name = name;
        this.with = with;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWith() {
        return with;
    }

    public void setWith(String with) {
        this.with = with;
    }
}
