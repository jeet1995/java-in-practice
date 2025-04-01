package com.creation_and_destroying_objects;

import java.util.function.Supplier;
import java.util.List;
import java.util.ArrayList;

/**
 * ResourceFactory demonstrates the Supplier pattern for resource creation.
 * This pattern is useful when:
 * 1. You want to encapsulate resource creation logic
 * 2. You need to provide different implementations of resource creation
 * 3. You want to delay resource creation until needed
 * 4. You need to manage resource lifecycle
 * 
 * The factory uses bounded wildcards (Supplier<? extends Resource>) to:
 * 1. Accept suppliers that create any type extending Resource
 * 2. Maintain type safety while providing flexibility
 * 3. Support covariance in the supplier's return type
 * 4. Allow for different resource implementations
 */
public class ResourceFactory {
    private final Supplier<? extends Resource> resourceSupplier;

    /**
     * Constructor that takes a Supplier for resource creation.
     * Uses bounded wildcard to accept any Supplier that produces a Resource or its subclass.
     * The wildcard bound '? extends Resource' means:
     * - The supplier can return Resource or any subclass of Resource
     * - The factory can safely use the returned object as a Resource
     * - Different suppliers can return different Resource subclasses
     * 
     * @param resourceSupplier the supplier that creates the resource
     */
    public ResourceFactory(Supplier<? extends Resource> resourceSupplier) {
        this.resourceSupplier = resourceSupplier;
    }

    /**
     * Creates a new resource using the provided supplier.
     * @return a new resource instance
     */
    public Resource createResource() {
        return this.resourceSupplier.get();
    }

    /**
     * Creates multiple resources using the provided supplier.
     * @param count the number of resources to create
     * @return an array of new resource instances
     */
    public Resource[] createResources(int count) {
        Resource[] resources = new Resource[count];
        for (int i = 0; i < count; i++) {
            resources[i] = this.resourceSupplier.get();
        }
        return resources;
    }

    /**
     * Creates a list of resources using the provided supplier.
     * Demonstrates how the factory can work with collections.
     * @param count the number of resources to create
     * @return a list of new resource instances
     */
    public List<Resource> createResourceList(int count) {
        List<Resource> resources = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            resources.add(this.resourceSupplier.get());
        }
        return resources;
    }
}

/**
 * Base Resource class that could be created by the factory.
 */
class Resource {
    private final String id;
    private final long creationTime;

    public Resource() {
        this.id = java.util.UUID.randomUUID().toString();
        this.creationTime = System.currentTimeMillis();
    }

    public String getId() {
        return this.id;
    }

    public long getCreationTime() {
        return this.creationTime;
    }

    @Override
    public String toString() {
        return "Resource{" +
                "id='" + this.id + '\'' +
                ", creationTime=" + this.creationTime +
                '}';
    }
}

/**
 * Example subclass of Resource demonstrating how the factory can work with different resource types.
 * This class adds additional functionality while maintaining the base Resource contract.
 */
class CustomResource extends Resource {
    private final String customData;

    public CustomResource() {
        super();
        this.customData = "Custom-" + this.getId();
    }

    public String getCustomData() {
        return this.customData;
    }

    @Override
    public String toString() {
        return "CustomResource{" +
                "id='" + this.getId() + '\'' +
                ", creationTime=" + this.getCreationTime() +
                ", customData='" + this.customData + '\'' +
                '}';
    }
}

/**
 * Example usage of ResourceFactory with different resource types and creation strategies.
 */
class ResourceFactoryExample {
    public static void main(String[] args) {
        // Example 1: Using base Resource with method reference
        ResourceFactory baseFactory = new ResourceFactory(Resource::new);
        Resource baseResource = baseFactory.createResource();
        System.out.println("Base Resource: " + baseResource);

        // Example 2: Using CustomResource with constructor reference
        ResourceFactory customFactory = new ResourceFactory(CustomResource::new);
        Resource customResource = customFactory.createResource();
        System.out.println("Custom Resource: " + customResource);

        // Example 3: Using lambda expression
        ResourceFactory lambdaFactory = new ResourceFactory(() -> new Resource());
        Resource lambdaResource = lambdaFactory.createResource();
        System.out.println("Lambda Resource: " + lambdaResource);

        // Example 4: Creating multiple resources
        Resource[] resources = baseFactory.createResources(3);
        System.out.println("Multiple Resources:");
        for (Resource r : resources) {
            System.out.println(r);
        }

        // Example 5: Creating a list of resources
        List<Resource> resourceList = baseFactory.createResourceList(2);
        System.out.println("Resource List:");
        resourceList.forEach(System.out::println);
    }
} 