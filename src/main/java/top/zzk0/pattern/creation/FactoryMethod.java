package top.zzk0.pattern.creation;

/*
https://refactoring.guru/design-patterns/factory-method

工厂方法: Factory Method is a creational design pattern that provides an interface for creating
objects in a superclass, but allows subclasses to alter the type of objects that will be created.

组成部分:
两个抽象的成份之间耦合, Factory 和 Product 之间耦合, Factory 的工厂方法会生产一个 Product
具体的实现之间是耦合的, 看后面的举例
*/

public class FactoryMethod {

    public static void main(String[] args) {
        // Change here to decide the type of Logistics
        AbstractLogistics logistics = new SeaLogistics();

        // Reusable part
        Transport transport = logistics.createTransport();
        transport.deliver();
    }

}

// ------------------------------- Abstract Part -------------------------------

interface Transport {
    void deliver();
}

abstract class AbstractLogistics {
    abstract public Transport createTransport();
    void negotiatePrice() {
        System.out.println("Negotiate Price");
    }
}

// ------------------------------- Implementation Part -------------------------------

class RoadLogistics extends AbstractLogistics {
    @Override
    public Transport createTransport() {
        return new Car();
    }
}

class SeaLogistics extends AbstractLogistics {
    @Override
    public Transport createTransport() {
        return new Ship();
    }
}

class Car implements Transport {
    @Override
    public void deliver() {
        System.out.println("Car deliver");
    }
}

class Ship implements Transport {
    @Override
    public void deliver() {
        System.out.println("Ship deliver");
    }
}
