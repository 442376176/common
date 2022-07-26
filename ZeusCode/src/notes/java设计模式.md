# Java设计模式

模式不是代码，而是某类问题的通用解决方案

其本质提高软件的维护性、通用性，并降低软件的复杂度

## UML类图

1. 虚线+箭头 --->（dependency） 依赖
2. 实线     ——  （association）因果
3. 虚线+三角 ------|> (generalization) 实线
4. 实线+空心菱形    ——<> （aggregation）聚合
5. 实线+实心菱形 ——<> （composite）组合





## 设计模式的目的

编写软件过程中，程序员面临着来自耦合性，内聚性以及可维护性，重用性，灵活性等多方面的挑战，设计模式是为了让程序具有更好的

1）代码复用性 （功能相同代码不用写多次）

2）可读性 （编程规范性，便于其他程序员的阅读和理解）

3）可扩展性 （需要增加新功能时，非常的方便，也称可维护性）

4）可靠性 （增加新功能后，对原来功能没有影响）

5）使程序呈现高内聚，低耦合特性





## 设计模式常用的七大原则

### 1.单一职责原则 SRP 

一个对象应该只包含单一的职责，并且该职责被完整地封装在一个类中

#### 	注意事项和细节

​		1.降低类的复杂度，一个类只负责一个职责；

​		2.提高类的可读性，可维护性；

​		3.降低变更引起的风险；

​		4.通常情况下，我们应当遵守单一职责原则，只有逻辑足够简单，才可以在代码级别违反单一职责原则；只有在类中方法数量足够		少，可以在方法级别保持单一职责原则。

### 2.开闭原则 OCP

软件实体应当对扩展开放，对修改关闭

#### 基本介绍

1.开闭原则是编程中最基础、最重要的设计原则

2.一个软件实体如类，模块和函数应该对扩展开放（对提供方），对修改关闭（对使用方）。用抽象构建框架，用实现扩展细节

3.当软件需要变化时，尽量通过扩展软件实体的行为来实现变化，而不是通过修改已有的代码来实现变化

4.编程中遵循其他原则，以及使用设计模式的目的就是遵循开闭原则



理解demo

```java
package com.zcc.designPattern.OCP;

/**
 * @ProjectName: ORCode
 * @ClassName: com.zcc.designPattern.OCP
 * @author: zcc
 * @date: 2022/3/8 11:05
 * @version:
 * @Describe: 开闭原则
 */
public class OCPDemo {
    public static void main(String[] args) {
        GraphicEditor graphicEditor = new GraphicEditor();
        graphicEditor.drawShape(new Rectangle());
        graphicEditor.drawShape(new Circle());
        graphicEditor.drawShape(new Triangle());
        graphicEditor.drawShape(new OtherGraphic());
    }
}

// 使用方
class GraphicEditor {
    public void drawShape(Shape s) {
//        if (s.m_type == 1){
//            drawRectangle(s);
//        }else if (s.m_type == 2){
//            drawCircle(s);
//        }else if (s.m_type == 3){
//            drawTriangle(s);
//        }
        s.draw();
    }
//    public void drawRectangle(Shape s){
//        System.out.println("矩形");
//    }
//    public void drawCircle(Shape s){
//        System.out.println("圆形");
//    }
//    public void drawTriangle(Shape s){
//        System.out.println("三角形");
//    }

}

abstract class Shape {
    int m_type;

    public abstract void draw();
}

class Rectangle extends Shape {
    Rectangle() {
        super.m_type = 1;
    }

    @Override
    public void draw() {
        System.out.println("矩形");
    }
}

class Circle extends Shape {
    Circle() {
        super.m_type = 2;
    }

    @Override
    public void draw() {
        System.out.println("圆形");
    }
}

class Triangle extends Shape {
    Triangle() {
        super.m_type = 3;
    }

    @Override
    public void draw() {
        System.out.println("三角形");
    }
}

class OtherGraphic extends Shape {
    OtherGraphic() {
        super.m_type = 4;
    }

    @Override
    public void draw() {
        System.out.println("其他图形");
    }
}
```



### 3.里氏代换原则 LSP

#### OO中的继承性的思考和说明

1.继承包含这样一层含义：父类中凡是已经实现好的方法，实际上是在设定规范和契约，虽然它不强制要求所有的子类必须遵循这些契约，但是如果子类对这些已经实现的方法任意修改，就会对整个继承体系造成破坏。

2.继承再给程序设计带来便利的同时也带来了弊端。比如使用继承会给程序带来侵入性，程序的可移植性降低，增加对象间的耦合性，如果一个类被其他的类所继承，则当这个类需要修改时，必须考虑所有的子类，并且父类修改后，所有涉及到的子类的功能都有可能出现故障。

3.问题提出：在编程中，如何正确的使用继承？-->里氏代换原则

#### 定义：

所有引用基类的地方必须能透明地使用其子类的对象

如果对每一个类型为S的对象o1都有类型为T的对象o2，使得以T定义的所有程序P在所有的对象o1都代换成o2时，程序的行为没有发生变化，那么类型T2是类型T1的子类型。换句话说，所有引用基类的地方必须能透明地使用其子类的对象。

在使用继承时，遵循里氏代换原则，在子类中尽量不要重写父类的方法

里氏代换原则告诉我们，继承实际上让两个类耦合性增强了，在适当的情况下，可以通过聚合，组合，依赖来解决问题。







### 4.依赖倒转原则 DIP

1.高层模块不应该依赖低层模块，他们都应该依赖抽象。抽象不应该依赖于细节，细节应该依赖于抽象。

2.抽象不应该依赖细节，细节应该依赖抽象。

3.依赖倒转的中心思想是面向对象

4.依赖倒转原则是基于这样的设计理念：

相对于细节的多变性，抽象的东西要稳定的多。以抽象为基础搭建的架构比以细节为基础的架构要稳定的多。在java中，抽象指的是接口或抽象类，细节就是具体的实现类

5.使用接口或抽象类的目的是制定好规范，而不涉及任何具体的操作，把展现细节的任务交给他们的实现类去完成。

#### 依赖关系传递的三种方式：

1.接口传递

2.构造方法传递

3.setter方式传递

#### 依赖倒转原则的注意事项和细节：

1.低层模块尽量都要有抽象类或接口，或者两者都有，程序的稳定性更好

2.变量的声明类型尽量是抽象类或接口，这样我们的变量引用和实际对象间就存在一个缓冲层，利于程序扩展和优化

3.继承时遵循里氏替换原则

### 5.接口隔离原则 ISP

客户端不应该依赖那些它不需要的接口，即一个类对另一个类的依赖应该建立在最小接口上

![image-20220307161710413](C:\Users\86151\Desktop\zcc\notes\pic\接口隔离原则.png)

类A通过接口Interface1依赖类B，类C通过

接口Interface1依赖类D，如果接口

Interface1对于类A和类C来说不是最小接口，

那么类B和类D必须去实现他们不需要的方

法。

按隔离原则应当这样处理：

将接口Interface1拆分为独立的几个接口，

类A和类C分别与他们需要的接口建立依赖

关系。也就是采用接口隔离原则

![image-20220307161825268](C:\Users\86151\Desktop\zcc\notes\pic\接口隔离原则拆分.png)

1) 类A通过接口Interface1依赖类B，类C通过接口Interface1依赖类D，如果接口

Interface1对于类A和类C来说不是最小接口，那么类B和类D必须去实现他们不

需要的方法

2) 将接口Interface1拆分为独立的几个接口，类A和类C分别与他们需要的接口建立

依赖关系。也就是采用接口隔离原则

3) 接口Interface1中出现的方法，根据实际情况拆分为三个接口

### 6.合成复用原则 CRP

优先使用对象组合，而不是通过继承来达到复用的目的



### 7.迪米特法则 LoD

每一个软件单位对于其他单位都只有最少的知识，而且局限于那些与本单位密切相关的软件单位。

基本介绍：

1.一个对象应该对其他对象保持最少的了解

2.类与类关系越密切，耦合度越大

3.迪米特法则又叫**最少知道原则**，即一个类对自己以来的类知道的越少越好。也就是说，对于被依赖的类不管多复杂，都尽量将逻辑封装在类的内部。除了对外提供的public方法，不对外泄露任何信息。

4.迪米特法则还有个更简单的定义：**只与直接的朋友通信**

5.直接的朋友：每个对象都会与其他对象有耦合关系，只要两个对象之间有耦合关系，我们就说这两个对象之间是朋友关系。耦合的方式很多，依赖，关联，组合，聚合等。其中，我们称出现在成员变量、方法参数、方法返回值中的类为直接的朋友，而出现在局部变量中的类不是直接的朋友。也就是说，陌生的类最好不要以局部变量的形式出现在类的内部。

**注意事项和细节**

1.迪米特法则的核心是降低类之间的耦合

2.由于每个类都减少了不必要的依赖，因此迪米特法则只是要求降低类间（对象间）耦合关系，并不是要求完全没有依赖关系



**依赖关系小结**

1.类中用到了对方

2.如果是类的成员属性

3.如果是方法的返回类型

4.是方法中接受参数的类型

5.方法中使用到

**泛化关系小结**

依赖关系的特例

1.泛化关系实际上是继承关系

2.如果a继承b，那么我们就说A和B存在泛化关系

**实现关系**

![image-20220317154613223](C:\Users\86151\Desktop\zcc\notes\pic\实现关系.png)

**关联关系**

![image-20220317154643852](C:\Users\86151\Desktop\zcc\notes\pic\关联关系.png)

**聚合关系**

表示整体和部分之间的关系，两者可以分开。聚合关系是关联关系的特例，所以他具有关联的导航性与多重性

![image-20220317154829228](C:\Users\86151\Desktop\zcc\notes\pic\聚合关系.png)

**组合关系**

也是整体和部分的关系，但是整体和部分不可以分开

![image-20220317155044717](C:\Users\86151\Desktop\zcc\notes\pic\组合关系.png)

## 设计模式类型

1.创建型模式：

​	单例模式、抽象工厂模式、原型模式、建造者模式、工厂模式

2.结构型模式：

​	适配器模式、桥接模式、装饰模式、组合模式、外观模式、享元模式、代理模式

3.行为型模式：

​	模板方法模式、命令模式、访问者模式、迭代器模式、观察者模式、中介者模式、备忘录模式、解释器模式（Interpreter模式）、状态模式、策略模式、职责（责任）链模式



### 1.单例模式

所谓类的单例设计模式，就是采取一定的方法保证在整个系统软件中，对某个类只能存在一个对象shilling，并且该类只提供一个取得其对象实例的方法（静态方法）。

####  **单例模式的八种方式**

#### 	**1.饿汉式（静态常量）**

```java
package com.zcc.designPattern.singletonDemo;

/**
 * @ProjectName: ORCode
 * @ClassName: com.zcc.designPattern.singleDemo
 * @author: zcc
 * @date: 2022/3/17 16:28
 * @version:

 */
public class Demo1 {
    public static void main(String[] args) {
        Singleton instance = Singleton.getInstance();
        Singleton instance1 = Singleton.getInstance();
        System.out.println(instance.hashCode());
        System.out.println(instance1.hashCode());
    }
}

/**
 *  * @Describe: 静态常量
 *  * 1.构造器私有化
 *  * 2.类的内部对象创建
 *  * 3.向外暴露一个静态的公共方法
 *
 *  优缺点：
 *  写法简单，在类装载的时候就完成实例化。避免线程同步问题
 *  在类装载的时候就完成实例化，没有达到Lazy Loading的效果。如果从始至终从未使用过这个实例就会造成内存的浪费
 *  这种模式可用，可能会造成内存浪费
 */
class Singleton{
    private Singleton() {
    }
    private final static Singleton instance = new Singleton();

    public static Singleton getInstance(){
        return instance;
    }
}
```

#### 	**2.饿汉式（静态代码块）**

```java
package com.zcc.designPattern.singletonDemo;

/**
 * @ProjectName: ORCode
 * @ClassName: com.zcc.designPattern.singletonDemo
 * @author: zcc
 * @date: 2022/3/17 16:41
 * @version:
 * @Describe: 静态代码块
 */
public class Demo2 {
    public static void main(String[] args) {
        Singleton1 instance = Singleton1.getInstance();
        Singleton1 instance1 = Singleton1.getInstance();
        System.out.println(instance.hashCode());
        System.out.println(instance1.hashCode());
    }
}

class Singleton1 {

    private Singleton1() {
    }

    private static Singleton1 singleton1;

    static {
        singleton1 = new Singleton1();
    }

    public static Singleton1 getInstance() {
        return singleton1;
    }
}
```

#### 	3.懒汉式（线程不安全）

```java
package com.zcc.designPattern.singletonDemo;

/**
 * @ProjectName: ORCode
 * @ClassName: com.zcc.designPattern.singletonDemo
 * @author: zcc
 * @date: 2022/3/17 16:52
 * @version:
 * @Describe: 懒汉式(线程不安全)
 */
public class Demo3 {
    public static void main(String[] args) {
        Singleton2 instance = Singleton2.getInstance();
        Singleton2 instance1 = Singleton2.getInstance();
        System.out.println(instance.hashCode());
        System.out.println(instance1.hashCode());
    }
}

class Singleton2 {
    private static Singleton2 singleton2;
    private Singleton2(){}
    public static Singleton2 getInstance(){
        if (singleton2==null){ // 多线程有问题
            singleton2 = new Singleton2();
        }
        return singleton2;
    }
}
```

#### 	4.懒汉式（线程安全，同步方法）

```java
package com.zcc.designPattern.singletonDemo;

/**
 * @ProjectName: ORCode
 * @ClassName: com.zcc.designPattern.singletonDemo
 * @author: zcc
 * @date: 2022/3/17 16:52
 * @version:
 * @Describe: 懒汉式(线程安全 同步方法) 不推荐
 */
public class Demo4 {
    public static void main(String[] args) {
        Singleton3 instance = Singleton3.getInstance();
        Singleton3 instance1 = Singleton3.getInstance();
        System.out.println(instance.hashCode());
        System.out.println(instance1.hashCode());
    }
}

class Singleton3 {
    private static Singleton3 singleton3;
    private Singleton3(){}
    public static synchronized Singleton3 getInstance(){ // 速度慢 效率过低
        if (singleton3==null){
            singleton3 = new Singleton3();
        }
        return singleton3;
    }
}
```

#### 	5.懒汉式（线程不安全，同步代码块）

```java
package com.zcc.designPattern.singletonDemo;

/**
 * @ProjectName: ORCode
 * @ClassName: com.zcc.designPattern.singletonDemo
 * @author: zcc
 * @date: 2022/3/17 16:52
 * @version:
 * @Describe: 懒汉式(线程不安全 同步代码块) 不能使用！！！
 */
public class Demo5 {
    public static void main(String[] args) {
        Singleton4 instance = Singleton4.getInstance();
        Singleton4 instance1 = Singleton4.getInstance();
        System.out.println(instance.hashCode());
        System.out.println(instance1.hashCode());
    }
}

class Singleton4 {
    private static Singleton4 singleton4;

    private Singleton4() {
    }


    public static Singleton4 getInstance() { // 速度慢 效率过低

        if (singleton4 == null) {
            synchronized (Singleton4.class) {
                singleton4 = new Singleton4();
            }

        }

        return singleton4;
    }
}
```

#### 	**6.双重检查**

```java
package com.zcc.designPattern.singletonDemo;

/**
 * @ProjectName: ORCode
 * @ClassName: com.zcc.designPattern.singletonDemo
 * @author: zcc
 * @date: 2022/3/17 17:11
 * @version:
 * @Describe:双重检查 比较推荐
 * 线程安全；延迟加载(懒加载)；效率较高
 */
public class Demo6 {
    public static void main(String[] args) {
        Singleton5 instance = Singleton5.getInstance();
        Singleton5 instance1 = Singleton5.getInstance();
        System.out.println(instance.hashCode());
        System.out.println(instance1.hashCode());
    }
}

class Singleton5 {
    private static volatile Singleton5 singleton5;

    private Singleton5() {
    }


    public static Singleton5 getInstance() { //

        if (singleton5 == null) {
            synchronized (Singleton5.class) {
                if (singleton5 == null) {
                    singleton5 = new Singleton5();
                }
            }
        }
        return singleton5;
    }
}
```

#### 	**7.静态内部类**

```java
package com.zcc.designPattern.singletonDemo;

/**
 * @ProjectName: ORCode
 * @ClassName: com.zcc.designPattern.singletonDemo
 * @author: zcc
 * @date: 2022/3/17 17:11
 * @version:
 * @Describe:静态内部类 推荐使用
 * （JVM类装载是线程安全）
 * 外层装载时，静态内部类不会装载
 * 调用方法getInstance时，静态内部类会去装载，只装载一次
 * 线程安全；
 * 延迟加载(懒加载)；
 * 效率较高
 */
public class Demo7 {
    public static void main(String[] args) {
        Singleton6 instance = Singleton6.getInstance();
        Singleton6 instance1 = Singleton6.getInstance();
        System.out.println(instance.hashCode());
        System.out.println(instance1.hashCode());
    }
}

class Singleton6 {
    private static volatile Singleton6 singleton6;

    private Singleton6() {
    }

    private static class SingletonInstance {
        private static final Singleton6 INSTANCE = new Singleton6();
    }

    public static Singleton6 getInstance() {

        return SingletonInstance.INSTANCE;
    }
}
```

#### 	**8.枚举**

```java
package com.zcc.designPattern.singletonDemo;

/**
 * @ProjectName: ORCode
 * @ClassName: com.zcc.designPattern.singletonDemo
 * @author: zcc
 * @date: 2022/3/17 17:26
 * @version:
 * @Describe:枚举类 推荐使用
 * 1.线程安全
 * 2.防止反序列化重新创建对象
 */
public class Demo8 {
    public static void main(String[] args) {
        Singleton7 instance = Singleton7.INSTANCE;
        Singleton7 instance1 = Singleton7.INSTANCE;
        System.out.println(instance.hashCode());
        System.out.println(instance);
        instance.method();
    }

}
enum Singleton7{
    INSTANCE;
    public void method(){
        System.out.println("ok");
    }
}
```

#### 单例模式的注意事项和细节说明

1.单例模式保证了 系统内存中该类只存在一个对象，节省了系统资源，对于一些需要频繁创建销毁的对象，使用单例模式可以提高系统性能。

2.想实例化一个单例类的时候，必须要记住使用相应的获取对象的方法，而不是使用new

3.单例模式使用的场景：

需要频繁的创建和销毁对象、创建对象时耗时过多或耗费资源过多（重量级对象），但又经常用到的对象、工具类独享、频繁访问数据库或文件的对象（数据源、session工厂等）

### 2.简单工厂模式

