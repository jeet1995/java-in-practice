package com.creation_and_destroying_objects;

/**
 * Demonstrates the Finalizer Guardian pattern in detail.
 * This pattern is used to ensure proper cleanup of resources even when
 * a malicious subclass tries to resurrect an object during finalization.
 */
public class FinalizerGuardian {
    public static void main(String[] args) {
        try {
            // Create a resource with guardian
            ResourceWithGuardian resource = new ResourceWithGuardian();
            System.out.println("Resource created and initialized");
            
            // Use the resource
            resource.doSomething();
            
            // Attempt to destroy the resource
            resource.destroy();
            System.out.println("Resource marked for destruction");
            
            // Force garbage collection
            System.gc();
            
            // Wait for finalizer to run
            Thread.sleep(1000);
            
            // Check if resource was properly destroyed
            System.out.println("Resource state after GC: " + resource.isDestroyed());
            
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

/**
 * A resource class that uses the Finalizer Guardian pattern.
 * The guardian ensures proper cleanup even if a malicious subclass
 * tries to resurrect the object during finalization.
 */
class ResourceWithGuardian {
    // The guardian object - its finalizer runs before the outer class's finalizer
    private final Object guardian = new Object() {
        @Override
        protected void finalize() throws Throwable {
            // This finalizer runs before the outer class's finalizer
            // It can ensure proper cleanup of the outer class's resources
            if (!ResourceWithGuardian.this.destroyed) {
                System.out.println("Guardian finalizer running - ensuring cleanup");
                ResourceWithGuardian.this.destroy();
            }
        }
    };

    private boolean destroyed = false;
    private String resourceData;

    public ResourceWithGuardian() {
        this.resourceData = "Initialized";
        System.out.println("ResourceWithGuardian constructor running");
    }

    public void doSomething() {
        if (this.destroyed) {
            throw new IllegalStateException("Resource has been destroyed");
        }
        System.out.println("Resource is doing something with: " + this.resourceData);
    }

    public void destroy() {
        if (!this.destroyed) {
            this.destroyed = true;
            this.resourceData = null;
            System.out.println("Resource destroyed");
        }
    }

    public boolean isDestroyed() {
        return this.destroyed;
    }

    @Override
    protected void finalize() throws Throwable {
        // This finalizer runs after the guardian's finalizer
        // Even if a malicious subclass tries to resurrect the object,
        // the guardian has already ensured proper cleanup
        if (!this.destroyed) {
            System.out.println("Resource finalizer running - cleanup already handled by guardian");
        }
    }
}

/**
 * A malicious subclass attempting to demonstrate why the guardian is necessary.
 * Even if this class tries to resurrect the object, the guardian ensures
 * proper cleanup of resources.
 */
class MaliciousResourceWithGuardian extends ResourceWithGuardian {
    private static MaliciousResourceWithGuardian resurrectedInstance;

    @Override
    protected void finalize() throws Throwable {
        // Attempt to resurrect the object
        System.out.println("Malicious subclass attempting resurrection");
        resurrectedInstance = this;
    }

    public static MaliciousResourceWithGuardian getResurrectedInstance() {
        return resurrectedInstance;
    }
} 