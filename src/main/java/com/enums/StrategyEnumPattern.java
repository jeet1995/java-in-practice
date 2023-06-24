package com.enums;

public final class StrategyEnumPattern {
    // enables a way to enforce a strategy for any new "Day" added
    // enables mapping a payment strategy to any new "DayType" added
    public enum Day {

        SATURDAY(DayType.WEEKEND), MONDAY(DayType.WEEKDAY);

        private final DayType dayType;

        Day(DayType dayType) {
            this.dayType = dayType;
        }

        public double computePay(Day day) {
            return day.dayType.calculatePay();
        }

        private enum DayType {
            WEEKDAY {
                @Override
                double calculatePay() {
                    return 5;
                }
            },
            WEEKEND {
                @Override
                double calculatePay() {
                    return 10;
                }
            };

            abstract double calculatePay();
        }
    }
}



