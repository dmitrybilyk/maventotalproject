package com.learn.patterns.creational.prototype;

//Prototype design pattern is used in scenarios where application needs
// to create a number of instances of a class, which has almost same state
// or differs very little.

//In this design pattern, an instance of actual object (i.e. prototype) is
// created on starting, and thereafter whenever a new instance is required,
// this prototype is cloned to have another instance. The main advantage of
// this pattern is to have minimal instance creation process which is much
// costly than cloning process.

//I am creating an entertainment application that will require instances
// of Movie, Album and Show classes very frequently. I do not want to create
// their instances everytime as it is costly. So, I will create their prototype
// instances, and everytime when i will need a new instance, I will just
// clone the prototype.
public class PrototypeFactory
{
    public static class ModelType
    {
        public static final String MOVIE = ModelType.MOVIE;
        public static final String ALBUM = ModelType.ALBUM;
        public static final String SHOW = ModelType.SHOW;
    }
    private static java.util.Map<String , PrototypeCapable> prototypes = new java.util.HashMap<String , PrototypeCapable>();

    static
    {
        prototypes.put(ModelType.MOVIE, new Movie());
        prototypes.put(ModelType.ALBUM, new Album());
        prototypes.put(ModelType.SHOW, new Show());
    }

    public static PrototypeCapable getInstance(final String s) throws CloneNotSupportedException {
        return ((PrototypeCapable) prototypes.get(s)).clone();
    }
}