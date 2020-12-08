package wt.muppety.controller;

public interface IController<T> {
    void setAppController(AppController appController);

    void setData(T data);
}
