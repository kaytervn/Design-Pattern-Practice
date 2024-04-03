|                | Design Pattern                                                                      |
| :------------- | :---------------------------------------------------------------------------------- |
| **Creational** | Singleton, Factory, Abstract Factory, Prototype, Builder, Dependency Injection, MVC |
| **Structural** | Adapter, Facade, Composite, Decorator, Bridge, Flyweight, Proxy, DAO                |
| **Behavioral** |                                                                                     |

<h1 align="center">Creational</h1>

<h2>Dependency Injection</h2>

1. Create Maven Project.

2. Config Spring Core to file `pom.xml`.

```xml
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-context</artifactId>
    <version>6.1.4</version>
</dependency>
```

3. From directory `src/main/resources`, create file `application_context.xml`, and config Spring Beans.

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://www.springframework.org/schema/beans
		https://www.springframework.org/schema/beans/spring-beans.xsd">

	<bean id="..." class="...">
		<!-- collaborators and configuration for this bean go here -->
	</bean>

	<bean id="..." class="...">
		<!-- collaborators and configuration for this bean go here -->
	</bean>

	<!-- more bean definitions go here -->

</beans>
```

4. Init Application Context in use:

```
ApplicationContext context = new ClassPathXmlApplicationContext("application_context.xml");
```

<h2>Singleton</h2>
<i><b>Singleton</b> ensure that a class has only one instance, while providing a global access point to this instance.</i>

<br>

**Eager Init:** Khởi tạo một `instance` ngay khi vừa được gọi tới.

**Lazy Init:** Chỉ khởi tạo khi tạo khi gọi phương thức `getInstance()` (Chỉ phù hợp với single-thread). Nếu ở môi trường multi-thread, phải thêm `synchronized` vào phương thức `getInstance()`.

<h2>Factory</h2>
<i><b>Factory</b> provides an interface for creating objects in a superclass, but allows subclasses to alter the type of objects that will be created.</i>

<br>

Factoy Method gồm lớp `interface` chung và các sub class implement interface đó, và lớp `factory` chịu tránh nhiệm khởi tạo các đối tượng sub class dựa theo tham số đầu vào (lớp này là `Singleton` hoặc có các phương thức khai báo ở `public static`).

<h2>Abstract Factory</h2>
<i><b>Abstract Factory</b> creates entire product families without specifying their concrete classes.</i>

<br>

**AbstractFactory:** Khai báo dạng interface hoặc abstract class chứa các phương thức để tạo ra các đối tượng abstract product.

**ConcreteFactory:** Xây dựng, cài đặt các phương thức tạo các đối tượng cụ thể.

**AbstractProduct:** Khai báo dạng interface hoặc abstract class để định nghĩa đối tượng abstract.

**Product:** Cài đặt của các đối tượng cụ thể, cài đặt các phương thức được quy định tại AbstractProduct.

**Client:** là đối tượng sử dụng AbstractFactory và các AbstractProduct.

```java
public interface AbstractFactory {
    Shape createShape(String shapeType);
}

public class CircleFactory implements AbstractFactory {
    @Override
    public Shape createShape(String shapeType) {
        if (shapeType.equals("circle")) {
            return new Circle();
        } else {
            return null;
        }
    }
}

public class RectangleFactory implements AbstractFactory {
    @Override
    public Shape createShape(String shapeType) {
        if (shapeType.equals("rectangle")) {
            return new Rectangle();
        } else {
            return null;
        }
    }
}

public class FactoryProducer {
    public static AbstractFactory getFactory(String factoryType) {
        if (factoryType.equals("circle")) {
            return new CircleFactory();
        } else if (factoryType.equals("rectangle")) {
            return new RectangleFactory();
        } else {
            return null;
        }
    }
}

AbstractFactory factory = FactoryProducer.getFactory("circle");

Shape circle = factory.createShape("circle");
circle.draw();

factory = FactoryProducer.getFactory("rectangle");
Shape rectangle = factory.createShape("rectangle");
rectangle.draw();
```

<h2>Prototype</h2>
<i><b>Prototype</b> allows cloning objects, even complex ones, without coupling to their specific classes.</i>

```java
public class Profile {

    private String name;
    private int age;

    public Profile(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Profile clone() {
        return new Profile(this);
    }
}

Profile originalProfile = new Profile("John Doe", 30);

Profile clonedProfile = originalProfile.clone();

clonedProfile.setName("Jane Doe");
clonedProfile.setAge(25);

System.out.println("Original Profile: " + originalProfile);
System.out.println("Cloned Profile: " + clonedProfile);
```

<h2>Builder</h2>
<i><b>Builder</b> allows constructing complex objects step by step.</i>

```java
class ElectronicProduct {
    private String name;
    private String brand;
    private double price;
    private String description;

    private ElectronicProduct(Builder builder) {
        this.name = builder.name;
        this.brand = builder.brand;
        this.price = builder.price;
        this.description = builder.description;
    }

    static class Builder {
        private String name;
        private String brand;
        private double price;
        private String description;

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setBrand(String brand) {
            this.brand = brand;
            return this;
        }

        public Builder setPrice(double price) {
            this.price = price;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public ElectronicProduct build() {
            return new ElectronicProduct(this);
        }
    }
}

ElectronicProduct.Builder builder = new ElectronicProduct.Builder();

builder.setName("Smartphone")
        .setBrand("Samsung")
        .setPrice(999.99)
        .setDescription("A high-end smartphone with advanced features.");

ElectronicProduct smartphone = builder.build();

System.out.println("Name: " + smartphone.getName());
System.out.println("Brand: " + smartphone.getBrand());
System.out.println("Price: " + smartphone.getPrice());
System.out.println("Description: " + smartphone.getDescription());
```

<h1 align="center">Structural</h1>

<h2>Adapter</h2>
<i><b>Adapter</b> allows objects with incompatible interfaces to collaborate.</i>

```java
public interface OldLibrary {
    void oldMethod();
}

public interface NewLibrary {
    void newMethod();
}

public class OldLibraryAdapter implements NewLibrary {
    private OldLibrary oldLibrary;

    public OldLibraryAdapter(OldLibrary oldLibrary) {
        this.oldLibrary = oldLibrary;
    }

    @Override
    public void newMethod() {
        oldLibrary.oldMethod();
    }
}

OldLibrary oldLibrary = new OldLibraryImpl();

NewLibrary adapter = new OldLibraryAdapter(oldLibrary);

adapter.newMethod();
```

<h2>Facade</h2>
<i><b>Facade</b> provides a simplified interface to a library, a framework, or any other complex set of classes.</i>

```java
class ComplexSubsystem1 {
    public void operation1() {
        System.out.println("ComplexSubsystem1: Operation 1");
    }
}

class ComplexSubsystem2 {
    public void operation2() {
        System.out.println("ComplexSubsystem2: Operation 2");
    }
}

class ComplexSubsystem3 {
    public void operation3() {
        System.out.println("ComplexSubsystem3: Operation 3");
    }
}

class Facade {
    private ComplexSubsystem1 subsystem1;
    private ComplexSubsystem2 subsystem2;
    private ComplexSubsystem3 subsystem3;

    public Facade() {
        this.subsystem1 = new ComplexSubsystem1();
        this.subsystem2 = new ComplexSubsystem2();
        this.subsystem3 = new ComplexSubsystem3();
    }

    public void operation() {
        System.out.println("Facade: Operation");
        subsystem1.operation1();
        subsystem2.operation2();
        subsystem3.operation3();
    }
}

Facade facade = new Facade();
facade.operation();
```

<h2>Composite</h2>
<i><b>Composite</b> allows composing objects into a tree-like structure and work with the it as if it was a singular object.</i>

```java
public abstract class Component {
    protected String name;

    public Component(String name) {
        this.name = name;
    }

    public abstract void display();
}

public class File extends Component {
    public File(String name) {
        super(name);
    }

    @Override
    public void display() {
        System.out.println("File: " + name);
    }
}

public class Directory extends Component {
    private List<Component> components;

    public Directory(String name) {
        super(name);
        components = new ArrayList<>();
    }

    public void addComponent(Component component) {
        components.add(component);
    }

    public void removeComponent(Component component) {
        components.remove(component);
    }

    @Override
    public void display() {
        System.out.println("Directory: " + name);
        for (Component component : components) {
            component.display();
        }
    }
}

Component file1 = new File("File1.txt");
Component file2 = new File("File2.txt");
Component file3 = new File("File3.txt");

Directory folder = new Directory("Folder");
folder.addComponent(file1);
folder.addComponent(file2);
folder.addComponent(file3);

folder.display();
```

<h2>Decorator</h2>
<i><b>Decorator</b> allows adding new behaviors to objects dynamically by placing them inside special wrapper objects, called decorators.</i>

```java
public interface Coffee {
    double getCost();
    String getDescription();
}

public class SimpleCoffee implements Coffee {
    @Override
    public double getCost() {
        return 1.0;
    }

    @Override
    public String getDescription() {
        return "Simple coffee";
    }
}

public abstract class CoffeeDecorator implements Coffee {
    protected Coffee decoratedCoffee;

    public CoffeeDecorator(Coffee decoratedCoffee) {
        this.decoratedCoffee = decoratedCoffee;
    }

    @Override
    public double getCost() {
        return decoratedCoffee.getCost();
    }

    @Override
    public String getDescription() {
        return decoratedCoffee.getDescription();
    }
}

public class MilkDecorator extends CoffeeDecorator {
    public MilkDecorator(Coffee decoratedCoffee) {
        super(decoratedCoffee);
    }

    @Override
    public double getCost() {
        return super.getCost() + 0.5;
    }

    @Override
    public String getDescription() {
        return super.getDescription() + ", Milk";
    }
}
Coffee coffee = new SimpleCoffee();
System.out.println("Cost: " + coffee.getCost() + ", Description: " + coffee.getDescription());

coffee = new MilkDecorator(coffee);
System.out.println("Cost: " + coffee.getCost() + ", Description: " + coffee.getDescription());
```

<h2>Bridge</h2>
<i><b>Bridge</b> divides business logic or huge class into separate class hierarchies that can be developed independently.</i>

```java
public interface Renderer {
    void renderCircle(float radius);
}

public class VectorRenderer implements Renderer {
    @Override
    public void renderCircle(float radius) {
        System.out.println("Drawing a circle of radius " + radius + " in vector format.");
    }
}

public class RasterRenderer implements Renderer {
    @Override
    public void renderCircle(float radius) {
        System.out.println("Drawing a circle of radius " + radius + " in raster format.");
    }
}

public abstract class Shape {
    protected Renderer renderer;

    public Shape(Renderer renderer) {
        this.renderer = renderer;
    }

    public abstract void draw();
    public abstract void resize(float factor);
}

public class Circle extends Shape {
    private float radius;

    public Circle(Renderer renderer, float radius) {
        super(renderer);
        this.radius = radius;
    }

    @Override
    public void draw() {
        renderer.renderCircle(radius);
    }

    @Override
    public void resize(float factor) {
        radius *= factor;
    }
}

Renderer vectorRenderer = new VectorRenderer();
Renderer rasterRenderer = new RasterRenderer();

Shape vectorCircle = new Circle(vectorRenderer, 5);
Shape rasterCircle = new Circle(rasterRenderer, 10);

vectorCircle.draw();
rasterCircle.draw();
```
