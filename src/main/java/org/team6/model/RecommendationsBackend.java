package org.team6.model;

import java.util.ArrayList;
import java.util.List;

public class RecommendationsBackend {
    // if personal recommendations are on, user data will be collected and used to generate recommendations
    private static boolean personalRecommendationsOn = true;
    private static final List<Observer> observers = new ArrayList<Observer>();

    private RecommendationsBackend() {
    }

    public static void addObserver(Observer observer) {
        observers.add(observer);
    }

    public static void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    private static void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }

    public static boolean isPersonalRecommendationsOn() {
        return personalRecommendationsOn;
    }

    public static void setPersonalRecommendationsOn(boolean privateRecommendationsOn) {
        RecommendationsBackend.personalRecommendationsOn = privateRecommendationsOn;
        notifyObservers();
    }
}
