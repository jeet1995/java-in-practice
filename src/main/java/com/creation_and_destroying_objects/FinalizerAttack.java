package com.creation_and_destroying_objects;

/**
 * Demonstrates a finalizer attack and how to prevent it.
 * A finalizer attack occurs when a malicious subclass overrides finalize()
 * to resurrect an object that was meant to be destroyed, potentially
 * causing security issues or resource leaks.
 */
public class FinalizerAttack {
    public static void main(String[] args) {
        // Demonstrate the attack
        System.out.println("=== Demonstrating Finalizer Attack ===");
        try {
            // Create a malicious instance
            MaliciousResource resource = new MaliciousResource();
            // Attempt to destroy it
            resource.destroy();
            // Force garbage collection
            System.gc();
            // Wait for finalizer to run
            Thread.sleep(1000);
            // The resource is resurrected!
            System.out.println("Resource was resurrected: " + resource.isDestroyed());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Demonstrate prevention
        System.out.println("\n=== Demonstrating Prevention ===");
        try {
            // Create a safe instance
            SafeResource safeResource = SafeResource.newInstance();
            // Attempt to destroy it
            safeResource.destroy();
            // Force garbage collection
            System.gc();
            // Wait for finalizer to run
            Thread.sleep(1000);
            // The resource remains destroyed
            System.out.println("Resource remains destroyed: " + safeResource.isDestroyed());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

/**
 * A resource class vulnerable to finalizer attack.
 */
class Resource {
    private boolean destroyed = false;

    public void destroy() {
        this.destroyed = true;
        System.out.println("Resource destroyed");
    }

    public boolean isDestroyed() {
        return this.destroyed;
    }

    @Override
    protected void finalize() throws Throwable {
        if (!this.destroyed) {
            System.out.println("Resource finalized");
        }
    }
}

/**
 * A malicious subclass that demonstrates the finalizer attack.
 */
class MaliciousResource extends Resource {
    private static MaliciousResource resurrectedInstance;

    @Override
    protected void finalize() throws Throwable {
        if (!this.isDestroyed()) {
            System.out.println("MaliciousResource finalized");
        } else {
            // Attack: Resurrect the object
            System.out.println("MaliciousResource attempting resurrection");
            resurrectedInstance = this;
        }
    }

    public static MaliciousResource getResurrectedInstance() {
        return resurrectedInstance;
    }
}

/**
 * A safe resource class that prevents finalizer attacks.
 */
class SafeResource {
    private boolean destroyed = false;
    private final Object finalizerGuardian = new Object() {
        @Override
        protected void finalize() throws Throwable {
            // This anonymous inner class's finalizer will run before
            // the outer class's finalizer, allowing us to prevent resurrection
            if (!SafeResource.this.destroyed) {
                SafeResource.this.destroy();
            }
        }
    };

    // Private constructor to prevent inheritance
    private SafeResource() {
        System.out.println("SafeResource created");
    }

    // Factory method to create instances
    public static SafeResource newInstance() {
        return new SafeResource();
    }

    public void destroy() {
        this.destroyed = true;
        System.out.println("SafeResource destroyed");
    }

    public boolean isDestroyed() {
        return this.destroyed;
    }

    @Override
    protected void finalize() throws Throwable {
        if (!this.destroyed) {
            System.out.println("SafeResource finalized");
        }
    }
} 