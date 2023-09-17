package com.xiaozhuge.springbootldap;

import io.minio.BucketExistsArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.errors.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * @author liyinlong
 * @since 2023/4/10 2:40 下午
 */
public class MinioDemo {

    private static MinioClient client;

    public static void main(String[] args0) {
        try {
            String url = "http://10.10.101.21:32901";
            //构造方法 minio客户端
            MinioClient client = MinioClient.builder()
                    .endpoint(url)
                    .credentials("minio", "minio123")
                    .build();
            client.setTimeout(3000, 1000, 1000);

            File file = new File("/Users/liyinlong/jiaofu/zeus126.tar");
            FileInputStream in = new FileInputStream(file);

            String fileName = "test.tgz";       //对文件名进行编码
            PutObjectArgs args = PutObjectArgs.builder()
                    .bucket("test")   //储存桶名称
                    .object(fileName)                      //文件路径加文件名
                    .stream(in, file.length(), -1)
                    .contentType("application/x-tar")
                    .build();
            client.putObject(args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

