# Creation and Destroying Objects Examples

This package contains examples demonstrating various patterns and best practices for creating and destroying objects in Java. Each example illustrates different aspects of object lifecycle management, from creation to cleanup.

## Examples Overview

### Singleton Pattern
1. `TrueSingleton.java` - Demonstrates the traditional singleton pattern with lazy initialization and thread safety.
2. `EnumSingleton.java` - Shows how to implement a singleton using Java's enum type, which provides serialization safety and thread safety by default.

### Builder Pattern
1. `hierarchical_builder/Pizza.java` - Illustrates the hierarchical builder pattern using recursive generics, allowing for type-safe inheritance in builders.

### Resource Management
1. `ResourceFactory.java` - Shows how to use the Supplier functional interface for flexible resource creation and management.
2. `CleanerWithAutocloseableExample.java` - Demonstrates modern resource cleanup using both Cleaner and AutoCloseable:
   - Combines try-with-resources for explicit cleanup
   - Uses Cleaner as a safety net for garbage collection
   - Shows how to properly structure cleanup code
   - Illustrates best practices for resource management

### Memory Management
1. `WeakHashMapExample.java` - Demonstrates the use of WeakHashMap and string interning:
   - Shows how WeakHashMap allows entries to be garbage collected
   - Illustrates the difference between interned and regular strings
   - Explains memory leak prevention through weak references

### Memory Leak Prevention
1. `LeakyStack.java` - Shows how to prevent memory leaks in a stack implementation:
   - Demonstrates the importance of nullifying references
   - Illustrates common memory leak scenarios
   - Shows proper cleanup of array-based data structures

### Finalizer Safety
1. `FinalizerAttack.java` - Demonstrates security vulnerabilities with finalizers
2. `FinalizerGuardian.java` - Shows how to protect against finalizer attacks:
   - Implements the finalizer guardian pattern
   - Ensures proper cleanup even with malicious subclasses
   - Illustrates safe resource management patterns

## Best Practices Demonstrated

1. **Object Creation**
   - Use builders for objects with many parameters
   - Consider static factory methods over constructors
   - Use dependency injection for flexibility
   - Implement proper singleton patterns

2. **Resource Management**
   - Prefer try-with-resources over try-finally
   - Implement AutoCloseable for cleanup
   - Use Cleaner as a safety net
   - Avoid finalizers when possible

3. **Memory Management**
   - Clean up references in collections
   - Use weak references when appropriate
   - Be aware of memory leak scenarios
   - Implement proper cleanup methods

4. **Security**
   - Protect against finalizer attacks
   - Implement proper access controls
   - Consider serialization safety
   - Use immutable objects when possible

## Usage

Each example class contains a `main` method that demonstrates its functionality. Run the examples individually to see different patterns and practices in action. 