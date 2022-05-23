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