package org.example.loadingdevicesoftware;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorsManager {

    private ExecutorsManager() {}

    public static ExecutorService executorForUIUpdate;

    public static ExecutorService executorForCommunication;

}
