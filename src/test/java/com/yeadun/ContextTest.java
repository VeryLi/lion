package com.yeadun;import com.yeadun.testutil.TestContext;import org.slf4j.Logger;import org.slf4j.LoggerFactory;public class ContextTest {    private static final Logger LOG = LoggerFactory.getLogger(ContextTest.class);    private static final String excelPath = "/Users/very/文档";    private static final String excelName = "联动四要素测试数据.xlsx";    private static final String sheetName = "Sheet1";    public static void main(String[] args) throws Exception{        TestContext context = new TestContext();        context.setBuilder("ApiBuidler")                .setKey("product")                .setUrl("product")                .setExcel(excelPath, excelName)                .setSheet(sheetName);        context.addProductWorker("P100028", new int[]{1, 2, 3, 4}, new int[]{2, 3, 4, 15, 55, 23, 12, 41})                .addProductWorker("P100029", new int[]{1, 2, 3, 4}, new int[]{2, 3, 4, 15, 55, 23, 12, 41})                .addProductWorker("P100007", new int[]{1, 2, 3, 4}, new int[]{2, 3, 4, 15, 55, 23, 12, 41})                .addProductWorker("P100006", new int[]{1, 2, 3}, new int[]{2, 3, 4, 15, 55, 23, 12, 41})                .addProductWorker("P100005", new int[]{2, 3, 4}, new int[]{2, 3, 4, 15, 55, 23, 12, 41});        context.start();    }}