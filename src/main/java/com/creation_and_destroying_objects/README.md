# Creation and Destroying Objects

This package demonstrates various patterns and best practices for object creation and destruction in Java.

## Classes Overview

### 1. TrueSingleton
Demonstrates a thread-safe singleton implementation using a class-based approach.

Key concepts:
- Private constructor to prevent instantiation
- Static instance field for the singleton
- Synchronized `getInstance()` method for thread safety
- Serialization handling with `readResolve()`
- Protection against reflection attacks

Usage:
```java
TrueSingleton instance = TrueSingleton.getInstance();
```

### 2. EnumSingleton
Demonstrates the preferred way to implement a singleton in Java using an enum.

Key concepts:
- Enum-based singleton pattern
- Automatic thread safety
- Built-in serialization handling
- Protection against reflection attacks
- Guaranteed single instance by JVM

Usage:
```java
EnumSingleton instance = EnumSingleton.INSTANCE;
```

### 3. Hierarchical Builder Pattern
Located in the `hierarchical_builder` package, demonstrates how to implement the Builder pattern with class hierarchies.

Key concepts:
- Recursive generics for type-safe builders
- Abstract base class with common building steps
- Concrete subclasses with specific building steps
- Immutable objects
- Fluent interface design

Classes:
- `Pizza`: Abstract base class with common pizza attributes
- `NyPizza`: Concrete implementation for New York style pizza
- `Calzone`: Concrete implementation for Calzone pizza

Usage:
```java
// Creating a New York Pizza
NyPizza nyPizza = new NyPizza.Builder(NyPizza.Size.SMALL)
    .addTopping(Pizza.Topping.SAUSAGE)
    .addTopping(Pizza.Topping.ONION)
    .build();

// Creating a Calzone
Calzone calzone = new Calzone.Builder()
    .addTopping(Pizza.Topping.HAM)
    .addTopping(Pizza.Topping.MUSHROOM)
    .sauceInside()
    .build();
```

## Best Practices Demonstrated

1. **Immutability**
   - Use of final fields
   - Defensive copying where necessary
   - Private constructors

2. **Thread Safety**
   - Synchronized methods
   - Thread-safe initialization
   - Enum-based guarantees

3. **Type Safety**
   - Generic type parameters
   - Recursive type bounds
   - Covariant return types

4. **Encapsulation**
   - Private constructors
   - Private fields
   - Public accessor methods

5. **Design Patterns**
   - Singleton Pattern
   - Builder Pattern
   - Hierarchical Builder Pattern

## When to Use Each Pattern

### Singleton Pattern
Use when:
- You need exactly one instance of a class
- The instance needs to be shared across the application
- You need to control access to a shared resource

### Builder Pattern
Use when:
- You have many constructor parameters
- Some parameters are optional
- You need to enforce invariants during construction
- You want to create immutable objects

### Hierarchical Builder Pattern
Use when:
- You have a class hierarchy
- Each subclass needs its own builder
- You want to share common building steps
- You need type-safe builders 