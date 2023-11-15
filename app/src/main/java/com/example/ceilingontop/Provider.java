package com.example.ceilingontop;

import java.util.List;

public class Provider {
    private String name;
    private String phone;
    private String location;
    private List<Plan> plans;

    public Provider(String name, String phone, String location, List<Plan> plans) {
        this.name = name;
        this.phone = phone;
        this.location = location;
        this.plans = plans;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getLocation() {
        return location;
    }

    public List<Plan> getPlans() {
        return plans;
    }

    // Nested class representing a service plan
    public static class Plan {
        private String speed;
        private String price;

        public Plan(String speed, String price) {
            this.speed = speed;
            this.price = price;
        }

        public String getSpeed() {
            return speed;
        }

        public String getPrice() {
            return price;
        }
    }

    @Override
    public String toString() {
        return name;
    }
}
