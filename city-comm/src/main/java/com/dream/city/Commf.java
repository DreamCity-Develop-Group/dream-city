package com.dream.city;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class Commf {

    public static void main(String[]args) throws InterruptedException, ExecutionException {
        long start = System.currentTimeMillis();

        //First shop for chuju
        Callable<Comm.Chuju> shoping = new Callable<Comm.Chuju>() {
            @Override
            public Comm.Chuju call() throws Exception {
                System.out.println("第一步：下单");
                System.out.println("第一步：等待发货");
                Thread.sleep(5000);
                System.out.println("第一步：快递到达");
                return new Comm.Chuju();
            }
        };

        FutureTask<Comm.Chuju> task = new FutureTask<>(shoping);

        new Thread(task).start();

        //Second shop for shicai
        Thread.sleep(2000);
        Comm.Shicai shicai = new Comm.Shicai();
        System.out.println("第二步：食材到位");

        //Third cooking step
        if (!task.isDone()){
            System.out.println("第三步：厨具还没有到位，要么等，要么取消");
            //task.cancel(true);
        }
        Comm.Chuju chuju = task.get();
        System.out.println("厨具到位，开始展现厨艺");
        cooking(chuju,shicai);

    }

    static void cooking(Comm.Chuju chuju, Comm.Shicai shicai){
        System.out.println("chao:"+chuju.getClass()+"["+shicai.getClass()+"]");
    }
}
