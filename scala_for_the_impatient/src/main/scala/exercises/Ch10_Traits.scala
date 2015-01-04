package exercises

/**
 * Created by aguestuser on 1/3/15.
 */
class Ch10_Traits {

// SKIPPING THESE, COMING BACK LATER, I PROMISE!!!

//  1. The java.awt.Rectangle class has useful methods translate and grow that are unfortunately absent from classes such as java.awt.geom.Ellipse2D. In Scala, you can fix this problem. Define a trait RectangleLike with concrete methods translate and grow. Provide any abstract methods that you need for the implementation, so that you can mix in the trait like this:
//  Click here to view code image
//  val egg = new java.awt.geom.Ellipse2D.Double(5, 10, 20, 30) with RectangleLike
//  egg.translate(10, -10)
//  egg.grow(10, 20)
//  2. Define a class OrderedPoint by mixing scala.math.Ordered[Point] into java.awt.Point. Use lexicographic ordering, i.e. (x, y) < (x’, y’) if x < x’ or x = x’ and y < y’.
//  3. Look at the BitSet class, and make a diagram of all its superclasses and traits. Ignore the type parameters (everything inside the [...]). Then give the linearization of the traits.
//    ￼￼￼
//  4. Provide a CryptoLogger trait that encrypts the log messages with the Caesar cipher. The key should be 3 by default, but it should be overridable by the user. Provide usage examples with the default key and a key of –3.
//  5. The JavaBeans specification has the notion of a property change listener, a standardized way for beans to communicate changes in their properties. The PropertyChangeSupport class is provided as a convenience superclass for any bean that wishes to support property change listeners. Unfortunately, a class that already has another superclass—such as JComponent—must reimplement the methods. Reimplement PropertyChangeSupport as a trait, and mix it into the java.awt.Point class.
//  6. In the Java AWT library, we have a class Container, a subclass of Component that collects multiple components. For example, a Button is a Component, but a Panel is a Container. That’s the composite pattern at work. Swing has JComponent and JContainer, but if you look closely, you will notice something strange. JComponent extends Container, even though it makes no sense to add other components to, say, a JButton. The Swing designers would have ideally preferred the design in Figure 10–4.
//  Figure 10–4. A better design for Swing containers
//    But that’s not possible in Java. Explain why not. How could the design be executed in Scala with traits?
//    7. There are dozens of Scala trait tutorials with silly examples of barking dogs or philosophizing frogs. Reading through contrived hierarchies can be tedious and not very helpful, but designing your own is very illuminating. Make your own silly trait hierarchy example that demonstrates layered traits, concrete and abstract methods, and concrete and abstract fields.
//  8. In the java.io library, you add buffering to an input stream with a BufferedInputStream decorator. Reimplement buffering as a trait. For simplicity, override the read method.
//  9. Using the logger traits from this chapter, add logging to the solution of the preceding problem that demonstrates buffering.
//  10. Implement a class IterableInputStream that extends java.io.InputStream with the trait Iterable[Byte].
}
