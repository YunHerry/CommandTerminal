package indi.yunherry.factory.bean;

/**
 * @author YunHerry
 */
public abstract class Execute extends Engine {
    private Command command;
    public Execute(Command command) {
        this.command = command;
    }
}
