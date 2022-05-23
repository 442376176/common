package com.zcc.designPattern.factory.simpleFactory;

/**
 * @ProjectName: ORCode
 * @ClassName: com.zcc.designPattern.factory.simpleFactory
 * @author: zcc
 * @date: 2022/3/29 16:53
 * @version:
 * @Describe:
 */
public class Demo {

    class Order{
        public Order() {
            Pizza pizza = null;
            String orderType ;
            do{
                orderType = "greek";
                if (orderType.equals("greek")){
                    pizza = new GreekPizza();
                }else if (orderType.equals("cheese")){
                    pizza = new CheesePizza();
                }else {
                    break;
                }
            }while(true);
        }


    }

    abstract class Pizza{
        public abstract void prepare();
        public  void bake(){

        };
        public  void cut(){

        };
        public  void box(){};

    }

    class CheesePizza extends Pizza{

        @Override
        public void prepare() {
            System.out.println("prepare1");
        }
//
//        @Override
//        public void bake() {
//            System.out.println("bake1");
//        }
//
//        @Override
//        public void cut() {
//            System.out.println("cut1");
//        }
//
//        @Override
//        public void box() {
//            System.out.println("box1");
//        }
    }

    class GreekPizza extends Pizza{

        @Override
        public void prepare() {
            System.out.println("prepare2");
        }
//
//        @Override
//        public void bake() {
//            System.out.println("bake2");
//        }
//
//        @Override
//        public void cut() {
//            System.out.println("cut2");
//        }
//
//        @Override
//        public void box() {
//            System.out.println("box2");
//        }
    }

}
