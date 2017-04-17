package com.yeadun;

import com.lion.testutil.util.excel.ExcelWorker;
import com.lion.testutil.util.net.HttpClientFactory;

public class ExcelTest {

    public static void main(String[] args){
        ExcelWorker worker = new ExcelWorker("/Users/very/文档/招财猫测试数据.xlsx");
        boolean res = worker.writeDataIntoCell(
                "Sheet1",
                false,
                true,
                3,
                2,
                "阿士大夫撒打发斯蒂芬"
        );

        if(res){
            System.out.println("写入成功！");
            worker.saveAsFile("/Users/very/文档/招财猫测试数据1.xlsx", true);
        }
        HttpClientFactory._HttpClient client;
    }
}
