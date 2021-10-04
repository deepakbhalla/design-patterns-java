package main.java.design.pattern;

public class SingletonImpl {

    /**
     * 5. Without volatile modifier, it’s possible for another thread in Java to see
     * half initialized state of instance variable, but with volatile variable
     * guaranteeing happens-before relationship, all the write will happen on
     * volatile instance before any read of instance variable
     */
    private static volatile SingletonImpl instance;

    /** 1. private constructor */
    private SingletonImpl() {
        /** 2. Prevent instance creation via reflection api */
        if (instance != null) {
            throw new RuntimeException("Use getInstance() method to get the instance of this class.");
        }
    }

    /** 3. Lazy loaded getInstance method */
    public SingletonImpl getInstance() {
        /** 4. Double check locking pattern */
        if (instance == null) { // first time check
            synchronized (SingletonImpl.class) {
                if (instance == null) { // second time check
                    instance = new SingletonImpl();
                }
            }
        }
        return instance;
    }

    /**
     * 6. To prevent creation of another instance while deserializing, provide the
     * implementation of readResolve() method. readResolve() replaces the object
     * read from the stream. This ensures that nobody can create another instance by
     * serializing and deserializing the singleton.
     */
    protected SingletonImpl readResolve() {
        return getInstance();
    }
}
