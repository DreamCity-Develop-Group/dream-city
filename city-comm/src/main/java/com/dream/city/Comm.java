package com.dream.city;

public class Comm {
    public static void main(String[] args)throws InterruptedException{
        long start = System.currentTimeMillis();

        //First step
        OnlineShoping thread = new OnlineShoping();
        thread.start();
        thread.join();
        //Second Step
        Thread.sleep(2000);
        Shicai shicai = new Shicai();
        System.out.println("第二步：食材到位");
        //third step
        System.out.println("第三步，开始操作");
        cooking(thread.chuju,shicai);
        System.out.println("All Time:"+(System.currentTimeMillis()-start)+"ms");

    }


    static class OnlineShoping extends Thread{
        private Chuju chuju;

        @Override
        public void run() {
            System.out.println("第一步：下单");
            System.out.println("第一步：等待发货");
            try {
                Thread.sleep(5000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            System.out.println("第一步：快递送达");
            chuju = new Chuju();
        }
    }

    static void cooking(Chuju chuju,Shicai shicai){
        System.out.println("chao:"+chuju.getClass()+"["+shicai.getClass()+"]");
    }

    static class Chuju{}

    static class Shicai{}
}
