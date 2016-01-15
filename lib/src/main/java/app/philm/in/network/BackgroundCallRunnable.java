

package app.philm.in.network;

public abstract class BackgroundCallRunnable<R> {

    public void preExecute() {}

    public abstract R runAsync();

    public void postExecute(R result) {}

 }
