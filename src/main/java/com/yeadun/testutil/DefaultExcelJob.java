package com.yeadun.testutil;import com.yeadun.testutil.excel.ExcelTestTask;import com.yeadun.testutil.excel.ExcelWorker;import com.yeadun.testutil.product.ProductWorker;import com.yeadun.testutil.product.ProductWorkerGroup;import com.yeadun.testutil.product.Response;import org.slf4j.Logger;import org.slf4j.LoggerFactory;import java.util.ArrayList;/** * 负责创建ExcelJob，为每个产品测试任务启动一个线程执行ExcelTask。 */public class DefaultExcelJob {    private static final Logger LOG = LoggerFactory.getLogger(DefaultExcelJob.class);    private String excelDirPath;    private String excelName;    private String sheetName;    private String excelPath;    private ExcelWorker<Response> excelWorker;    private DefaultClientFactory factory;    private int taskTotal = 0;    private boolean ifSaveData = false;    public DefaultExcelJob(String excelDirPath, String excelName, String sheetName, DefaultClientFactory factory) {        this.excelDirPath = excelDirPath;        this.excelName = excelName;        this.sheetName = sheetName;        this.factory = factory;        this.excelPath = this.excelDirPath + "/" + this.excelName;        this.excelWorker = new ExcelWorker<>(this.excelPath);    }    public String getExcelPath() {        return this.excelPath;    }    public String getSheetName() {        return sheetName;    }    public boolean isIfSaveData() {        return ifSaveData;    }    public void setIfSaveData(boolean ifSaveData) {        this.ifSaveData = ifSaveData;    }    /**     * 获取ProductMeta中的Product元信息，根据每个ProductMeta的元信息创建ExcelTask任务，     * 启动线程执行ExcelTask任务。     *     * @param group Product的元信息存储对象     */    public void exec(ProductWorkerGroup group) {        // 创建一个线程队列        ArrayList<Thread> queue = new ArrayList<>();        for (ProductWorkerGroup.ProductMeta meta : group.getDataFrame()) {            // 根据Product元信息实例化ProductWorker            ProductWorker productWorker = new ProductWorker(meta.getType(), this.factory.getClientInstance(),                    meta.getMerchCode(), meta.getKey());            // 创建ExcelTestTask对象            ExcelTestTask task = new ExcelTestTask(this.excelWorker, this.sheetName,                    meta.getRowNums(), meta.getColNums(), productWorker);            LOG.info("启动线程: {}, 执行产品: {}({}) 测试。" +                            "[ meta信息 - sheet页: {}, 测试行数: {}, 选择列号: {}, 测试用户: {}, 测试秘钥: {} ]",                    "ExcelTask_" + taskTotal, meta.getType().getProdCode(), meta.getType().getProdName(),                    this.sheetName, meta.getRowNums().length, meta.getColNums(), meta.getMerchCode(), meta.getKey());            taskTotal++;            queue.add(new Thread(task, "ExcelTask_" + taskTotal));        }        // 执行        for(Thread thread: queue){            thread.start();        }        // join        for(Thread thread: queue){            try {                thread.join();            } catch (InterruptedException e) {                LOG.info(e.getMessage(), e);            }        }        this.excelWorker.saveAsFile(this.excelDirPath + "/result_" + this.excelName, true);    }}