package com.xiaozhuge.springbootldap;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author liyinlong
 * @since 2022/8/22 9:45 上午
 */
public class SemTest {
    
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 5, 8,3, 10, 11, 19,  9,  13, 4,14);
        list.sort(Comparator.comparing(Integer::intValue));

        String convert = convert(list);
        System.out.println(convert);


    }

    public static String convert(List<Integer> list) {
        int state = 0;
        String result = "";
        for (int i = 0; i < list.size(); i++) {
            if (i == list.size() - 1) {
                state = 2;
            }
            if (state == 0) {
                if (list.get(i + 1) == list.get(i) + 1) {
                    result += Integer.toString(list.get(i));
                    result += "-";
                    state = 1;
                } else {
                    result += Integer.toString(list.get(i));
                    result += ",";
                }
            } else if (state == 1) {
                if (list.get(i + 1) != list.get(i) + 1) {
                    result += Integer.toString(list.get(i));
                    result += ",";
                    state = 0;
                } else {
                    result += list.get(i) + "-";
                }
            } else {
                result += Integer.toString(list.get(i));
            }
        }
        String[] str = result.split(",");

        StringBuffer sbf = new StringBuffer();
        for (int stritem = 0; stritem < str.length; stritem++) {
            String[] sp = str[stritem].split("-");
            List<String> tt = Arrays.asList(sp);
            if(tt.size() == 1){
                sbf.append(Arrays.toString(tt.toArray()));
            }else{
                sbf.append("[" + tt.get(0) + "—" + tt.get(tt.size()-1) + "]");
            }
            if(stritem != str.length-1){
                sbf.append(",");
            }
        }
        return sbf.toString();
    }

}
