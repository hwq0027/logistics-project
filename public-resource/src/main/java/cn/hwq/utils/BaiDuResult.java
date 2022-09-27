package cn.hwq.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.HashMap;

@Data
@AllArgsConstructor
@ToString
public class BaiDuResult implements Serializable {

    private String address;
    private  String name;
    private HashMap<String,String> location;

}
