package com.creation_and_destroying_objects;

/**
 * Builder pattern example - demonstrates a creational pattern
 * for constructing complex objects step by step.
 */
public class Builder {
    private final String name;
    private final int age;
    private final String address;
    private final String phone;

    private Builder(BuilderImpl builder) {
        this.name = builder.name;
        this.age = builder.age;
        this.address = builder.address;
        this.phone = builder.phone;
    }

    public static class BuilderImpl {
        private String name;
        private int age;
        private String address;
        private String phone;

        public BuilderImpl name(String name) {
            this.name = name;
            return this;
        }

        public BuilderImpl age(int age) {
            this.age = age;
            return this;
        }

        public BuilderImpl address(String address) {
            this.address = address;
            return this;
        }

        public BuilderImpl phone(String phone) {
            this.phone = phone;
            return this;
        }

        public Builder build() {
            return new Builder(this);
        }
    }

    public static BuilderImpl builder() {
        return new BuilderImpl();
    }

    // Getters
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }
} 