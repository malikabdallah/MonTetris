package model.thread;

import controller.Controller;

public class Dropping implements Runnable{

    private Controller controller;

    public Dropping(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void run() {

        while (true) {
            try {
                //controller.gameManager.activeChange();

                controller.gameManager.dropDown();

                Thread.sleep(1000);

            } catch ( InterruptedException e ) {}
        }
    }
}
